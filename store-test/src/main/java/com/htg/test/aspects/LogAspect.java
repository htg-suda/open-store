package com.htg.test.aspects;


import com.htg.test.aop.LogWriter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Order(1000)
@Slf4j
@Aspect
@Component
public class LogAspect {
    /* 这里使用注解表示有这种注解的 方法就是切入口 */
    @Pointcut("@annotation(com.htg.test.aop.LogWriter)")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        log.debug("-- start --");
        Object[] args = joinPoint.getArgs();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();


        Object proceed = joinPoint.proceed();
        log.debug("-- end --");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method signatureMethod = signature.getMethod();
        LogWriter writer = signatureMethod.getAnnotation(LogWriter.class);
        String name = writer.name();
        String value = writer.value();
        log.debug("value:{},name:{}",value,name);
        return proceed;
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        log.debug("-- before --");
    }


    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        log.debug("-- after --");
    }


}
