FROM openjdk:8-jdk-buster
# 将本地文件夹挂载到当前容器
# VOLUME /tmp

# 拷贝文件到容器，也可以直接写成ADD microservice-discovery-eureka-0.0.1-SNAPSHOT.jar /app.jar
ADD ./target/store-config-server-1.0-SNAPSHOT.jar /app.jar
# 开放5001端口
EXPOSE 5001

ENTRYPOINT ["java","-Xmx200m","-jar","-Dspring.profiles.active=pro","/app.jar"]