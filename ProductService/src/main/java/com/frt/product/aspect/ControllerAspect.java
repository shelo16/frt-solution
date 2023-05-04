package com.frt.product.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ControllerAspect {

    @Pointcut("within(com.frt.product.controller..*)")
    public void allControllerMethods() {
    }

    @Before("allControllerMethods()")
    public void logIncomingRequestData(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;
        if (request != null) {
            log.info("================Received Http Request================");
            log.info("SERVICE : Product");
            log.info("HTTP METHOD = {}", request.getMethod());
            log.info("URI = {}", request.getRequestURI());
            log.info("QUERY = {}", request.getQueryString());
            log.info("CLASS_METHOD = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.info("ARGS = {}", Arrays.toString(joinPoint.getArgs()));
            log.info("REQUESTER IP = {}", request.getRemoteAddr());
        }
    }

}
