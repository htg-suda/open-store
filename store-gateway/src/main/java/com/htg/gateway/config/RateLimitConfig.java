package com.htg.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimitConfig {
    @Bean(name = "apiKeyResolver")
    public KeyResolver apiKeyResolver() {

        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
