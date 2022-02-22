package com.htg.gateway.filters;

import com.htg.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/*参考掘金： https://juejin.cn/post/6850037263707930631*/

/**
 * Created by macro on 2020/6/17.
 */
@Slf4j
@Component
public class GlobalAuthFilter implements WebFilter {

    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private static final String X_CLIENT_TOKEN = "x-client-token";

    @Value("${ignore-urls}")
    private List<String> ignoreUrls;


    @Autowired
    private AntPathMatcher antPathMatcher;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        HttpHeaders headers = request.getHeaders();
        /* token 中获取 用户名 */
        log.info("req: uri:{} , method:{} , auth:{}", url, method, authentication);

        if (ignoreUrls.stream().anyMatch(item -> antPathMatcher.match(item, url))) return chain.filter(exchange);

        if (StringUtils.isBlank(authentication)) return unauthorized(exchange);

        try {
            /* token 中获取 用户名 */
            String userName = JWTUtil.getUserName(authentication);
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            //TODO 转发的请求都加上服务间认证token
            builder.header(X_CLIENT_TOKEN, "add-client-token");
            //将jwt token中的用户信息传给服务
            builder.header(X_CLIENT_TOKEN_USER, userName);
            exchange = exchange.mutate().request(builder.build()).build();
            return chain.filter(exchange);
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) { // token 过期
            log.error(e.getMessage());
            /* 存在一个 开头为 token_ 的 token 但是 这个token 解析不到用户名 ,或则当前线程中存在一个 已经验证过的用户 */
            return unauthorized(exchange);
        }

    }


    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        DataBuffer buffer = response.bufferFactory().wrap("{\"code\":401,\"msg\":\"用户未认证\"}".getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }
}
