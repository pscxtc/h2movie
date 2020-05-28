#!/usr/bin/env bash
codePath="/root/gitSpace/h2movie/";#自己的项目路径
action=$1;#通过调用传入  [./updateCode.sh  test1]  test1 就是 $1
project=$2;
if [ ! -n "$1" ] ;then
  action="deploy";
fi

if [ ! -n "$2" ] ;then
  project="h2";
fi
case $action in
    deploy) echo -e "部署";docker stack deploy -c docker-compose.yml $project;docker stack services $project;;
    rm) echo -e "删除";docker stack rm $project;docker stack services $project;;
    logs) echo -e "日志";docker service logs -f $project;;
    update) echo -e "强制更新";docker service update --force --detach=false $project;;
    ps) echo -e "状态";docker stack services $project;;
    *) echo -e "参数异常!可选[deploy/rm/logs/update]";;
esac
echo "操作完毕";
