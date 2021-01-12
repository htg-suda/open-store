package com.htg.gateway.security;

import com.htg.gateway.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/*参考掘金： https://juejin.cn/post/6850037263707930631*/

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 * Created by macro on 2020/6/17.
 */
@Slf4j
@Component
public class AuthGlobalFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
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
}
