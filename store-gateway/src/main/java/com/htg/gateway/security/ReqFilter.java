package com.htg.gateway.security;

import com.htg.gateway.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ReqFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("-- do  filter --");
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            /* token 中获取 用户名 */
            String userName = JWTUtil.getUserName(token);
            log.info("AuthGlobalFilter.filter() user:{}", userName);
            ServerHttpRequest request = exchange.getRequest().mutate().header("user", userName).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ExpiredJwtException e) { // token 过期
            e.printStackTrace();
        } catch (MalformedJwtException e) {  // token 异常
            e.printStackTrace();
        } catch (SignatureException e) {    // token 签名异常
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
