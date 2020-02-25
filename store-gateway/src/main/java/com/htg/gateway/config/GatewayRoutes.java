package com.htg.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 https://cloud.spring.io/spring-cloud-gateway/reference/html
* */
/* http://www.ityouknow.com/springcloud/2018/12/12/spring-cloud-gateway-start.html */
@Configuration
public class GatewayRoutes {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        /* Route（路由）：这是网关的基本构建块。它由一个 ID，一个目标 URI，一组断言和一组过滤器定义。如果断言为真，则路由匹配。*/
        return builder.routes()
                .route(r -> (
                        r.path("/java/**").or().path("/exam/**")      // 配置路由匹配路径
                        .filters(f -> f.stripPrefix(1))   // 去掉一个前缀
                        .uri("http://localhost:9999/helloWorld")) // 匹配到路由后的uri 地址
                ).build();
    }
}
