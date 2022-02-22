package com.htg.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

@Configuration
public class EnvConfig {

    @Bean
    public AntPathMatcher getPathMatcher() {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return antPathMatcher;
    }

}
