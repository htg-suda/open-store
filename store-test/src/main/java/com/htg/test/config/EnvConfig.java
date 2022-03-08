package com.htg.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Configuration
public class EnvConfig {
    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate  getRestTemplate(){
     /*   HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(1000*60);
        httpRequestFactory.setConnectTimeout(1000*60);
        httpRequestFactory.setReadTimeout(1000*60);*/
        return new RestTemplate(/*httpRequestFactory*/);
    }



    @Value("${zookeeper.address}")
    private String zkAddress;

    @Value("${zookeeper.timeout}")
    private int sessionTimeout;


    @Bean(name = "zkClient")
    public ZooKeeper zkClient() {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(zkAddress, sessionTimeout, event -> {
                log.info("-- get event --");
                // 如果收到了服务端的响应事件，说明连接成功
                if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                    log.info("ZooKeeper连接成功...");
                }
            });

        } catch (Exception e) {
            log.error("初始化Zookeeper连接状态异常: {}", e.getMessage());
        }
        return zooKeeper;
    }

    @Bean
    public CuratorFramework getZKClient() {
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();
        return client;
    }

    /* 使用 CuratorCache 对数据进行监听 */
    @Bean
    public CuratorCache getNodeCache(CuratorFramework client) {
        CuratorCache curatorCache = CuratorCache.build(client, "/");
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forCreates(node -> log.info("Node created -> {}", node))  //创建节点
                .forChanges((oldNode, node) -> log.info("Node change -> oldNode:{},node:{}", oldNode, node))  //节点变化
                .forDeletes(oldNode -> log.info("Node deleted -> oldNode:{}", oldNode)) // 删除节点
                .forInitialized(() -> log.info("Node Initialized"))
                .build();

        curatorCache.listenable().addListener(listener);
     /*   curatorCache.listenable().addListener(new CuratorCacheListener() {
            @Override
            public void event(Type type, ChildData oldData, ChildData data) {

            }
        });*/
        curatorCache.start();
        return curatorCache;
    }

}
