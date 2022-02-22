 # 基于哪个镜像
FROM openjdk:8-jdk-alpine
# 将本地文件夹挂载到当前容器
VOLUME /temp

ADD ./sentinel-dashboard-1.8.0.jar app.jar
EXPOSE 8080
EXPOSE 8719

ENTRYPOINT ["java","-Dserver.port=8080","-Dcsp.sentinel.dashboard.server=localhost:8080","-Dproject.name=sentinel-dashboard","-jar","/app.jar"]

# docker build -f Dockerfile -t sentinel-dashboard:1.8.0 .
# docker run -d -t  -p 0.0.0.0:9020:8080 -p 0.0.0.0:8719:8719 --restart=always --name sentinel-dashboard sentinel-dashboard:1.8.0
# docker container update --restart=always 容器名字
# 访问方式 http://宿主机ip:9020
# 密码和用户名都是sentinel