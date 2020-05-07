#!/usr/bin/env bash
#根据当前路径,循环启动其下所有 jar文件,并且在打开日志,在日志打印至 Started 下一行时,关闭日志
cur_Dir=$(pwd)
target=$1;
jars=`find ${cur_Dir}/${target} -name '*.jar'`;
for state in ${jars}
do
        echo "部署 ${state##*/}"
        pid=`ps -ef | grep ${state##*/}  | grep -v grep  | awk '{print $2}'`
        echo “旧应用进程id：$pid”
        if [ -n "$pid" ]
        then
                for kid in ${pid}
                do
                        kill -9 $kid
                        echo "kill $kid 完毕"
                done
        fi
        nohup java -Xms256m -Xmx512m -jar ${state} --spring.cloud.nacos.config.server-addr=192.168.17.63:8848  --spring.cloud.nacos.config.namespace=57f1e614-0d90-47a7-9181-726d1a51cdc5 --spring.profiles.active=dev  > ${state%.*}.log 2>&1 &
        echo "打开日志: ${state%.*}.log"
        tail -200f ${state%.*}.log|sed -e '/Root WebApplicationContext*/{n;Q}'
done
