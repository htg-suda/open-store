 # 基于哪个镜像
FROM openjdk:8-jdk-alpine
# 将本地文件夹挂载到当前容器
VOLUME /tmp
COPY ./target/store-test-1.0-SNAPSHOT.jar app.jar
EXPOSE 9999
ENTRYPOINT ["java","-Xmx200m","-jar","-Dspring.profiles.active=dev_remote","/app.jar"]