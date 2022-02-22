#!/bin/bash
#这里可替换为你自己的执行程序，其他代码无需更改

APP_NAME=test

app="$1"

if [ $app = "adminservice" ]; then
 APP_NAME=AdminService-1.0-SNAPSHOT.jar
elif [ $app = "adminfeign" ]; then
 APP_NAME=AdminFeign-1.0-SNAPSHOT.jar
fi
if [ $app = "corpfeign" ]; then
APP_NAME=CorpFeign-1.0-SNAPSHOT.jar
fi
if [ $app = "corpservice" ]; then
APP_NAME=CorpService-1.0-SNAPSHOT.jar
fi
if [ $app = "gisfeign" ]; then
APP_NAME=GisFeign-1.0-SNAPSHOT.war
fi
if [ $app = "gyzhfeign" ]; then
APP_NAME=GyzhFeign-1.0-SNAPSHOT.war
fi
if [ $app = "gyzhservice" ]; then
APP_NAME=GyzhService-1.0-SNAPSHOT.jar
fi
if [ $app = "listfeign" ]; then
APP_NAME=ListFeign-1.0-SNAPSHOT.jar
fi
if [ $app = "plantfeign" ]; then
APP_NAME=PlantFeign-1.0-SNAPSHOT.jar
fi
if [ $app = "plotfeign" ]; then
APP_NAME=PlotFeign-1.0-SNAPSHOT.war
fi
if [ $app = "plotservice" ]; then
APP_NAME=PlotService-1.0-SNAPSHOT.jar
fi
if [ $app = "tenantservice" ]; then
APP_NAME=TenantService-1.0-SNAPSHOT.jar
fi
if [ $app = "zuul" ]; then
APP_NAME=Zuul-1.0-SNAPSHOT.jar
fi

if [ $app = "configserver" ]; then
APP_NAME=ConfigServer-1.0-SNAPSHOT.jar
fi

if [ $app = "gyzhappfeign" ]; then
APP_NAME=GyzhAppFeign-1.0-SNAPSHOT.jar
fi

if [ $app = "publicfeign" ]; then
APP_NAME=PublicFeign-1.0-SNAPSHOT.jar
fi

#使用说明，用来提示输入参数
usage() {
 echo "Usage: sh 脚本名.sh [start|stop|restart|status]"
 exit 1
}

#检查程序是否在运行
is_exist(){
 pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
 #如果不存在返回1，存在返回0
 if [ -z "${pid}" ]; then
 return 1
 else
 return 0
 fi
}

#启动方法
start(){
 is_exist
 if [ $? -eq "0" ]; then
 echo "${APP_NAME} is already running. pid=${pid} ."
 else
 nohup /data/openmap/openj9/bin/java -jar /data/spring/$APP_NAME --Xmx200m --spring.profiles.active=guest > /data/spring/log/$APP_NAME.log 2>&1 &
 echo "${APP_NAME} start success"
 fi
}

#停止方法
stop(){
 is_exist
 if [ $? -eq "0" ]; then
 kill -9 $pid
 else
 echo "${APP_NAME} is not running"
 fi
}

#输出运行状态
status(){
 is_exist
 if [ $? -eq "0" ]; then
 echo "${APP_NAME} is running. Pid is ${pid}"
 else
 echo "${APP_NAME} is NOT running."
 fi
}

#重启
restart(){
 stop
 start
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$2" in
 "start")
 start
 ;;
 "stop")
 stop
 ;;
 "status")
 status
 ;;
 "restart")
 restart
 ;;
 *)
 usage
 ;;
esac