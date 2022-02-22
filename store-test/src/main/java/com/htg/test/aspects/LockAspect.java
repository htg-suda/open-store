package com.htg.test.aspects;


import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(100)
@Slf4j
@Aspect
@Component
public class LockAspect {
    @Autowired
    private CuratorFramework client;

    /* 这里使用注解表示有这种注解的 方法就是切入口 */
    @Pointcut("@annotation(com.htg.test.aop.DistributedLock)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Exception {

        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock"); //分布式可重入排它锁

        mutex.acquire();    // 请求锁，如果获取不到则会阻塞在这里
        log.debug("-- 开始业务 --"); //获得了锁, 进行业务流程
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            log.debug("-- 结束业务 释放锁 --");
            //完成业务流程, 无论如何都必须要释放锁，否者会导致其他程序阻塞
            mutex.release();
        }
        return proceed;
    }

}
