package com.htg.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class RateLimitConfig {
    @Bean(name = "apiKeyResolver")
    public KeyResolver apiKeyResolver() {
        // 这里配置限流策略，实际可以根据路径或者IP;或者用户来限流
        return exchange -> {
            String path = exchange.getRequest().getPath().value();
            log.info("limit path is {}", path);
            return Mono.just(path);
        };
    }
}
