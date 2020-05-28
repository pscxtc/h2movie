#!/bin/bash
export JAVA_HOME=/usr/java/jdk1.8.0_191-amd64
echo ${JAVA_HOME}
codePath="/root/jenkinsSpace";#jenkins部署路径
project=$1;#项目名称,与路径名相同 例如:third-server-service
startParam=$2;#
if [ ! -n "$1" ] ;then
  project="third-server-service";
fi

if [ ! -n "$2" ] ;then
  startParam="";
fi
echo "部署 $1"
pid=`ps -ef | grep $project.jar | grep -v grep | awk '{print $2}'`
echo "旧应用进程id：$pid"
if [ -n "$pid" ]
then
kill -9 $pid
echo "kill $pid 完毕"
fi
echo "授权当前用户"
chmod 777 $codePath/$project/$project.jar
echo "开始启动...."
cd $codePath/$project/
nohup java -jar $project.jar $startParam > $project.log 2>&1 &
echo "启动成功"