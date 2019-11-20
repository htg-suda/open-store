package com.htg.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htg.admin.utils.JWTUtil;
import com.htg.common.result.CommonResult;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* 每个 请求都要经过这个 过滤器 */
@Slf4j
@Component
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      //  SecurityContextHolder.clearContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.info("exist is {}", authentication.getPrincipal());
        }else log.info("not exist auth");

        String authorization = request.getHeader("Authorization");
        log.info("Authorization is {}", authorization);
        if (StringUtils.isNoneBlank(authorization)) { // 获取了token
            try {
                /* token 中获取 用户名 */
                String userName = JWTUtil.getUserName(authorization);
                List<GrantedAuthority> authorities = new ArrayList<>();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch (ExpiredJwtException e) { // token 过期
                e.printStackTrace();
                /* 存在一个 开头为 token_ 的 token 但是 这个token 解析不到用户名 ,或则当前线程中存在一个 已经验证过的用户 */
                ObjectMapper mapper = new ObjectMapper();
                response.setContentType("application/json;charset=UTF-8");
                String content = mapper.writeValueAsString(CommonResult.error("token 过期"));
                response.getOutputStream().write(content.getBytes("UTF-8"));
                return;
            } catch (MalformedJwtException e) {  // token 异常
                e.printStackTrace();
                ObjectMapper mapper = new ObjectMapper();
                response.setContentType("application/json;charset=UTF-8");
                String content = mapper.writeValueAsString(CommonResult.error("token 异常"));
                response.getOutputStream().write(content.getBytes("UTF-8"));
                return;
            } catch (SignatureException e) {    // token 签名异常
                e.printStackTrace();
                ObjectMapper mapper = new ObjectMapper();
                response.setContentType("application/json;charset=UTF-8");
                String content = mapper.writeValueAsString(CommonResult.error("token 签名异常"));
                response.getOutputStream().write(content.getBytes("UTF-8"));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
