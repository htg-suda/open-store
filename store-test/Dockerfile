 # 基于哪个镜像
FROM arm64v8/openjdk:8-alpine
# 将本地文件夹挂载到当前容器
VOLUME /tmp

COPY ./target/store-test-1.0-SNAPSHOT.jar app.jar


EXPOSE 9999

# cd /nacos/bin; sh startup.sh -m standalone
ENTRYPOINT ["sh","/nacos/bin/startup.sh","-m","standalone"]