#!/usr/bin/env bash
#配置全局代理
echo "#set proxy">>/etc/profile
echo "http_proxy=http://proxy1.bj.petrochina:8080/">>/etc/profile
echo "export http_proxy">>/etc/profile
source /etc/profile
echo "刷新/etc/profile完成"
#配置yum代理
echo "#set proxy">>/etc/yum.conf
echo "proxy=http://proxy1.bj.petrochina:8080/">>/etc/yum.conf

#安装 openjdk 1.8
yum install java-1.8.0-openjdk -y

#测试 jdk安装结果
java -version

echo "#set java">>/etc/profile
echo "JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.252.b09-2.el7_8.x86_64">>/etc/profile
echo "JRE_HOME=$JAVA_HOME/jre">>/etc/profile
echo "CLASS_PATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib">>/etc/profile
echo "PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin">>/etc/profile
echo "export JAVA_HOME JRE_HOME CLASS_PATH PATH">>/etc/profile

source /etc/profile

#打印 java 环境变量
echo "/etc/profile java 环境变量(无打印内容,说明安装失败):"
echo $JAVA_HOME

