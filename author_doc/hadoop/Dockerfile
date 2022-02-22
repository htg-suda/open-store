FROM ubuntu:20.04

# 作者描述信息
MAINTAINER htg@suda.com

# 更新阿里的源
COPY ./sources.list /etc/apt/sources.list

# 更新源、安装openssh 并修改配置文件和生成key 并且同步时间 ,创建证书
RUN apt-get update
# 安装vim 和 net-tools
RUN apt-get install -y vim net-tools
# 安装 openssh
RUN apt-get install -y openssh-client openssh-server
# 生成ssh 秘钥对，会在 /root/.ssh/ 下生成 id_rsa 和 id_rsa.pub  文件
RUN ssh-keygen -f $HOME/.ssh/id_rsa -t rsa -N ''

RUN rm -rf /var/lib/apt/lists/*
#设置用于ssh连接的root密码,设置root 密码为 admin
RUN  echo "root:admin" | chpasswd

RUN  mkdir -p /var/run/sshd
#允许root连接
RUN sed -i "s/#PermitRootLogin.*/PermitRootLogin yes/g" /etc/ssh/sshd_config

# 添加JDK add 命令会自动解压下面的文件 ,所以不用解压，copy 命令不会
ADD openjdk-11_28_linux-x64_bin.tar.gz /usr/local
RUN mv /usr/local/jdk-11 /usr/local/java


# add 命令会自动解压下面的文件 ，copy 命令不会
ADD hadoop-3.3.1.tar.gz /usr/local
RUN mv /usr/local/hadoop-3.3.1 /usr/local/hadoop

# 配置JDK环境
ENV JAVA_HOME /usr/local/java
ENV PATH $JAVA_HOME/bin:$PATH

# 配置 hadoop 环境
ENV HADOOP_HOME /usr/local/hadoop
ENV PATH $HADOOP_HOME/bin:$PATH

# 配置 Hadoop 运行时候的环境
ENV HDFS_DATANODE_USER root
ENV HDFS_NAMENODE_USER root
ENV HDFS_SECONDARYNAMENODE_USER root
ENV YARN_RESOURCEMANAGER_USER root
ENV YARN_NODEMANAGER_USER root

# 开放22端口
EXPOSE 22

# 执行ssh启动命令
CMD ["/usr/sbin/sshd", "-D"]

# 构建镜像
# docker build -t "hadoop-openjdk11-ssh:1.0.1" .

# 创建网络
# docker network create -d bridge --subnet=172.20.1.0/16 --gateway=172.20.1.1 htg_net

# 运行容器
# docker run -id --privileged --network=htg_net --ip 172.20.1.10  --hostname=hadoop10 --name=hadoop10  -p 50070:50070 -p 8088:8088 hadoop-openjdk8-ssh:1.0.1
# docker run -id --privileged --network=htg_net --ip 172.20.1.20  --hostname=hadoop20 --name=hadoop20  hadoop-openjdk8-ssh:1.0.1
# docker run -id --privileged --network=htg_net --ip 172.20.1.30  --hostname=hadoop30 --name=hadoop30  hadoop-openjdk8-ssh:1.0.1
# docker run -id --privileged --network=htg_net --ip 172.20.1.40  --hostname=hadoop40 --name=hadoop40  hadoop-openjdk8-ssh:1.0.1

# 修改容器的配置
# docker container update --restart always

# 数据初始化
# hdfs namenode -format

# ./sbin/start-dfs.sh

# ssh-copy-id -i ~/.ssh/id_rsa.pub root@hadoop10
# ssh-copy-id -i ~/.ssh/id_rsa.pub root@hadoop20
# ssh-copy-id -i ~/.ssh/id_rsa.pub root@hadoop30