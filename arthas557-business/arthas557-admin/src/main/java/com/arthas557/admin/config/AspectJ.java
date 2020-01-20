package com.arthas557.admin.config;

import com.arthas557.admin.entity.Log;
import com.arthas557.admin.entity.User;
import com.arthas557.admin.service.LogService;
import com.arthas557.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class AspectJ {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Pointcut(value = "execution(* com.arthas557.admin.service.UserService.*(..))")
    public void point() {

    }

    @AfterThrowing(pointcut = "point()", throwing = "ex")
    public void ex(JoinPoint joinPoint, Exception ex) {
        log.info("发生了异常", ex);
        Log log = new Log();
        log.setContent("exception");
        logService.insert(log);
        System.out.println("日常日志保存完毕");
    }
}
