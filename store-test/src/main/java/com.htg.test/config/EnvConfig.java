package com.htg.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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
}
