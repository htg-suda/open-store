package com.htg.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htg.common.result.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*调用接口,发生越权性行*/
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");

        String content = mapper.writeValueAsString(CommonResult.error( "访问权限异常"));
        response.getOutputStream().write(content.getBytes("UTF-8"));
    }
}
