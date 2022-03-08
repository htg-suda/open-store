package com.htg.test.controller;

import com.apifan.common.random.source.AreaSource;
import com.apifan.common.random.source.OtherSource;
import com.apifan.common.random.source.PersonInfoSource;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.htg.test.aop.DistributedLock;
import com.htg.test.aop.LogWriter;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping(value = "try-demo")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private int count = 0;

    @Autowired
    private ZooKeeper zkClient;

    @Autowired
    private CuratorFramework curatorFramework;

    @Value("${zookeeper.address}")
    private String zkAddress;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "分布式锁测试")
    @DistributedLock
    @LogWriter(value = "test", name = "测试")
    @PostMapping(value = "/t10")
    public String getTest(String param, HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        log.info("count is {}", count);
        //   int i = 1 / 0;
        return "===> " + param + " " + count++;
    }

    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "restTemplate测试")
    @PostMapping(value = "/t20")
    public String getTest02(String param) {
        log.info("param is {}", param);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://188.188.188.188/doc", param, String.class);
        return "===> " + param + " " + count++;
    }


    /**
     * 创建持久化节点
     * -- 客户端断开连接后，节点数据持久化在磁盘上，不会被删除。
     *
     * @param path 路径
     * @param data 数据
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "Curator操作ZooKeeper测试")
    @PostMapping(value = "/t30")
    public String getTest03(String path, String data) {
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            if (stat == null)
                curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(path, data.getBytes(StandardCharsets.UTF_8));
            else
                curatorFramework.setData().forPath(path, data.getBytes(StandardCharsets.UTF_8));
            //   String respPath = zkClient.create(path, data.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            log.info("path:{},data:{}", path, data);
            return path + " : " + data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "sentinel 流量测试接口")
    @PostMapping(value = "/t40")
    public String getTest04(String path, String data) {
        long start = System.currentTimeMillis();
        String sql001 = "select count(*) from tb_user where username=?";

        String nickname = PersonInfoSource.getInstance().randomChineseNickName(5);
        String prv = AreaSource.getInstance().randomProvince();
        String companyName = OtherSource.getInstance().randomCompanyName("上海");
        String phoneType = OtherSource.getInstance().randomMobileModel();

        String username = PersonInfoSource.getInstance().randomEnglishName().replace(" ", "_");
        Long count = jdbcTemplate.queryForObject(sql001, Long.class, username);
        while (count > 0) {
            log.info("exists username : {}", username);
            username = PersonInfoSource.getInstance().randomEnglishName().replace(" ", "_");
            count = jdbcTemplate.queryForObject(sql001, Long.class, username);
        }
        String sql = "insert into tb_user (uid,sid,username,nickname,prv,commpany,phone_type,create_time,update_time) value (?,?,?,?,?,?,?,now(),now())";
        try {
            int update = jdbcTemplate.update(sql, IdWorker.get32UUID(), IdWorker.getId(), username, nickname, prv, companyName, phoneType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "user" + "::" + username;
    }


    public void lock() throws Exception {
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();
        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
        mutex.acquire();


        //获得了锁, 进行业务流程
        log.info("-- 开始业务 --");
        Thread.sleep(1000);
        log.info("-- 结束业务 释放锁 --");
        //完成业务流程, 释放锁
        mutex.release();
        //关闭客户端
        client.close();

        //可以看到关键的核心操作就只有mutex.acquire()和mutex.release()，简直太方便了！
    }




}
