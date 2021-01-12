package com.htg.goods.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String pathInfo = request.getPathInfo();
        String queryString = request.getQueryString();
        String remoteUser = request.getRemoteUser();
        String contextPath = request.getContextPath();
        log.info("     method :: {}", method);
        log.info(" requestURI :: {}", requestURI);
        log.info("queryString :: {}", queryString);
        log.info("   pathInfo :: {}", pathInfo);
        log.info(" remoteUser :: {}", remoteUser);
        log.info("contextPath :: {}", contextPath);

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String item = name + " => ";
            Enumeration<String> headers = request.getHeaders(name);
            while (headers.hasMoreElements()) {
                item = item + headers.nextElement() + " ;; ";
            }
            log.info(item);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
