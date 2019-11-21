#!/usr/bin/env bash
codePath="/root/gitSpace/h2movie/";#自己的项目路径
project=$1;#通过调用传入  [./updateCode.sh  test1]  test1 就是 $1
if [ ! -n "$1" ] ;then
  project="test";# 默认项目名  也可以搞个输入框自己输入
fi
cd $codePath$project
echo "git 更新代码开始。。。。。" ;
git pull  #正常更新
#git fetch --all ;
#git reset --hard origin/master ; #覆盖更新
echo "git 更新代码完成。。。。。";
mvn clean package;
docker image prune;
echo "y" |updateCode.sh
docker images;
