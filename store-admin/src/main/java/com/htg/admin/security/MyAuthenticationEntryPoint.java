package com.htg.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htg.common.result.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* 调用需要Token 的接口,结果没带token 导致异常 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static Logger logger = LoggerFactory.getLogger(MyAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.info("method: " + request.getMethod());
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        String content = mapper.writeValueAsString(CommonResult.error("访问异常,需要认证"));
        response.getOutputStream().write(content.getBytes("UTF-8"));
    }
}
