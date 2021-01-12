package com.htg.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.htg")
@EnableDiscoveryClient
@SpringBootApplication
public class StoreAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreAuthApplication.class);
    }

}
