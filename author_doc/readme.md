[文章参考](https://www.cnblogs.com/songwenjie/p/9371422.html)

- ####1,使用docker-compose 文件创建一个主从 数据库

```yaml
version: '3'
services:
  # 注意 mysql 的配置文件在容器的 /etc/mysql 目录下的 my.cnf 其中指定了 一个 包含了 !includedir /etc/mysql/conf.d 下的配置文件,
  # 所以只要将 /etc/mysql/conf.d 文件夹映射到宿主机就可以了
  #---------mysql--------------
  mysql_main:
    image: mysql:8.0.20
    restart: always   # 容器应该在停止的情况下总是重启，比如，服务器启动时，这个容器就跟着启动，不用手动启动
    container_name: mysql_main
    ports:
      - 1106:3306       # 端口映射
    volumes:
      - ./volumes/mysql/main/db:/var/lib/mysql # 数据文件挂载
      - ./volumes/mysql/main/conf.d:/etc/mysql/conf.d #配置文件挂载
      - ./volumes/mysql/main/log:/var/log/mysql #日志文件挂载
    environment:       # 设置环境变量
      MYSQL_ROOT_PASSWORD: 123456

    #---------mysql--------------
  mysql_slave:
    image: mysql:8.0.20
    restart: always   # 容器应该在停止的情况下总是重启，比如，服务器启动时，这个容器就跟着启动，不用手动启动
    container_name: mysql_slave_01
    ports:
      - 2206:3306       # 端口映射
    volumes:
      - ./volumes/mysql/slave_01/db:/var/lib/mysql # 数据文件挂载
      - ./volumes/mysql/slave_01/conf.d:/etc/mysql/conf.d #配置文件挂载
      - ./volumes/mysql/slave_01/log:/var/log/mysql #日志文件挂载
    environment: # 设置环境变量
      MYSQL_ROOT_PASSWORD: 123456
```
- ####2,主数据库配置
```
[mysqld]
## 同一局域网内注意要唯一
server-id=100  
## 开启二进制日志功能，可以随便取（关键）
log-bin=mysql-bin
```


- ####3,向主数据库设置一个 slave 账户,用于从数据库
```sql
CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'slave'@'%';
```

- ####4,从数据库配置
```
[mysqld]
## 设置server_id,注意要唯一
server-id=101  
## 开启二进制日志功能，以备Slave作为其它Slave的Master时使用
log-bin=mysql-slave-bin   
## relay_log配置中继日志
relay_log=edu-mysql-relay-bin  
```
- ####5,链接Master(主)和Slave(从)
```sql
change master to master_host='172.17.0.2', master_user='slave', master_password='123456', master_port=3306, master_log_file='mysql-bin.000001', master_log_pos= 2830, master_connect_retry=30;
```
