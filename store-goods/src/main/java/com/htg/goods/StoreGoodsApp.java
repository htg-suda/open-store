package com.htg.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan(basePackages = {"com.htg.goods.mapper"})
@SpringBootApplication
public class StoreGoodsApp {
    public static void main(String[] args) {
        SpringApplication.run(StoreGoodsApp.class);
    }
}
