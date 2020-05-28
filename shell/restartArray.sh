#!/bin/bash
export JAVA_HOME=/usr/bin/java
echo ${JAVA_HOME}
codePath="/root/jenkinsSpace";#jenkins部署路径
project=${1,,};#项目名称,强制小写,与路径名相同 例如:third-server-service
startParamStr=$2;#项目启动参数 例如: --spring.profiles.active=54,--spring.profiles.active=542
portStr=$3;#端口数组字符串 例如 8032,8033
eureka=$4;#注册中心地址 例如:http://192.168.17.161:7321
serviceIp=$5;#本服务ip	例如:192.168.17.161
eurekaName=${1^^};#erueka项目名称,强制大写,与路径名相同 例如:third-server-service
waitTime=$6;#等待时长
if [ ! -n "$1" ] ;
then
	echo "未传入 项目名称,异常结束"
	exit 1
fi

if [ ! -n "$2" ] ;
then
	echo "未传入 参数数据,异常结束"
	exit 1
fi

if [ ! -n "$3" ] ;
then
	echo "未传入 端口数据,异常结束"
	exit 1
fi

if [ ! -n "$4" ] ;
then
	echo "未传入 注册中心,异常结束"
	exit 1
fi

if [ ! -n "$5" ] ;
then
	echo "未传入 服务ip,异常结束"
	exit 1
fi

if [ ! -n "$6" ] ;
then
	waitTime=1m;
fi

echo "部署 $1 开始"

OLD_IFS="$IFS" #保存旧的分隔符
IFS=","
portArray=($portStr)
startParamArray=($startParamStr)
IFS="$OLD_IFS" # 将IFS恢复成原来的

for i in "${!portArray[@]}"; do

	echo "部署 ${portArray[i]}"
	curl -X PUT $eureka/eureka/apps/$eurekaName/$serviceIp:${portArray[i]}/status?value=OUT_OF_SERVICE
	echo "$eureka/eureka/apps/$eurekaName/$serviceIp:${portArray[i]}/status?value=OUT_OF_SERVICE"
	echo "$eureka 服务 $serviceIp:${portArray[i]} 下线完毕,$waitTime后重启"
	sleep $waitTime

	#根据端口号查询对应的pid
	pid=$(netstat -nlp | grep :${portArray[i]} | awk '{print $7}' | awk -F"/" '{ print $1 }');
	echo "旧应用进程id：$pid"
	#杀掉对应的进程，如果pid不存在，则不执行
	if [  -n  "$pid"  ] ;
	then
		kill  -9  $pid;
		echo "kill $pid 完毕"
	fi
	echo "开始启动 ${portArray[i]} .. ${startParamArray[i]} ...."
	cd $codePath/$project/
	nohup java -jar $project.jar ${startParamArray[i]} > $project-${portArray[i]}.log 2>&1 &
	echo "启动成功 ${portArray[i]},$waitTime后 上线"

	sleep $waitTime
	curl -X DELETE $eureka/eureka/apps/$eurekaName/$serviceIp:${portArray[i]}/status?value=UP
	echo "$eureka/eureka/apps/$eurekaName/$serviceIp:${portArray[i]}/status?value=UP"
	echo "$eureka 服务 $serviceIp:${portArray[i]} 上线完毕"

done