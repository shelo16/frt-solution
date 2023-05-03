package com.frt.authservice.aspect;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.util.FrtError;
import com.frt.authservice.persistence.util.Role;
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
import org.springframework.util.ObjectUtils;
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

    // TODO : Ignore this validation if user Role is ADmin
    @Before("userControllerMethods()")
    public void validateUserBeforeInvokingUserOperations(JoinPoint joinPoint) throws AuthenticationException {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        final String authHeader = request.getHeader("Authorization");
        String jwt;
        String emailToken;
        String emailUser;
        Long idToken;
        Long idUser;
        String role;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AuthenticationException("User not authenticated");
        }

        jwt = authHeader.substring(7);
        if (jwt.isEmpty()) {
            return;
        }

        role = jwtUtil.extractClaim(jwt, claims -> claims.get("role", String.class));
        if (role.equalsIgnoreCase(Role.ADMIN.name())) {
            return;
        }

        idToken = jwtUtil.extractClaim(jwt, claims -> claims.get("id", Long.class));
        emailToken = jwtUtil.extractEmail(jwt);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            String paramName = parameterNames[i];
            if ("id".equalsIgnoreCase(paramName)) {
                idUser = (Long) parameterValues[i];
                checkValidId(idToken, idUser);
                break;
            }

            if ("email".equalsIgnoreCase(paramName)) {
                emailUser = (String) parameterValues[i];
                checkValidEmail(emailToken, emailUser);
                break;
            }
        }
    }

    private void checkValidId(Long idToken, Long idUser) {
        if (!Objects.equals(idToken, idUser)) {
            throw new GeneralException(FrtError.USER_ACCESS_DENIED);
        }
    }
    private void checkValidEmail(String emailToken, String emailUser) {
        if (!Objects.equals(emailToken, emailUser)) {
            throw new GeneralException(FrtError.USER_ACCESS_DENIED);
        }
    }


}
