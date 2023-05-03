package com.frt.authservice.aspect;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.util.FrtError;
import com.frt.authservice.service.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import java.util.Objects;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ControllerAspect {

    private final JwtUtil jwtUtil;

    @Pointcut("execution(* com.frt.authservice.controller.user.*.*(..))")
    public void userControllerMethods() {
    }

    @Before("userControllerMethods()")
    public void validateUserBeforeInvokingUserOperations(JoinPoint joinPoint) throws AuthenticationException {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        final String authHeader = request.getHeader("Authorization");
        String jwt;
        Long idToken;
        Long idUser = null;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AuthenticationException("User not authenticated");
        }

        jwt = authHeader.substring(7);
        idToken = jwtUtil.extractClaim(jwt, claims -> claims.get("id", Long.class));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            String paramName = parameterNames[i];
            if ("id".equalsIgnoreCase(paramName)) {
                idUser = (Long) parameterValues[i];
                break;
            }
        }

        if (!Objects.equals(idToken, idUser)) {
            throw new GeneralException(FrtError.USER_ACCESS_DENIED);
        }
    }


}
