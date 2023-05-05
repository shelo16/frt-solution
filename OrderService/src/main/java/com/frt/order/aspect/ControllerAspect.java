package com.frt.order.aspect;

import com.frt.order.exception.model.customexception.GeneralException;
import com.frt.order.exception.util.FrtError;
import com.frt.order.model.order.PostOrderRequest;
import com.frt.order.persistence.util.Role;
import com.frt.order.service.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ControllerAspect {

    private final JwtUtil jwtUtil;

    @Pointcut("within(com.frt.order.controller.OrderController)")
    public void orderControllerMethods() {
    }

    @Pointcut("within(com.frt.order.controller..*)")
    public void allControllerMethods() {
    }

    @Pointcut("orderControllerMethods()")
    public void beforeOrderControllerMethods() {
    }

    @Pointcut("orderControllerMethods()")
    public void afterOrderControllerMethods() {
    }

    @Before("allControllerMethods()")
    @Order(1)
    public void logIncomingRequestData(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;
        if (request != null) {
            log.info("================Received Http Request================");
            log.info("SERVICE : OrderService");
            log.info("HTTP METHOD = {}", request.getMethod());
            log.info("URI = {}", request.getRequestURI());
            log.info("QUERY = {}", request.getQueryString());
            log.info("CLASS_METHOD = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.info("ARGS = {}", Arrays.toString(joinPoint.getArgs()));
            log.info("REQUESTER IP = {}", request.getRemoteAddr());
        }
    }

    @Before("beforeOrderControllerMethods()")
    @Order(2)
    public void validateUserBeforeInvokingOrderOperations(JoinPoint joinPoint) throws AuthenticationException {

        log.info("validating user for data access");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        final String authHeader = request.getHeader("Authorization");
        String jwt;
        Long idToken;
        Long idUser;
        PostOrderRequest postOrderRequest;
        String role;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.error(FrtError.USER_NOT_AUTHENTICATED.getDescription());
            throw new AuthenticationException(FrtError.USER_NOT_AUTHENTICATED.getDescription());
        }

        jwt = authHeader.substring(7);
        if (!StringUtils.hasLength(jwt)) {
            return;
        }

        role = jwtUtil.extractClaim(jwt, claims -> claims.get("role", String.class));
        if (StringUtils.hasLength(role) && role.equalsIgnoreCase(Role.ADMIN.name())) {
            return;
        }

        idToken = jwtUtil.extractClaim(jwt, claims -> claims.get("id", Long.class));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            String paramName = parameterNames[i];
            if ("clientId".equalsIgnoreCase(paramName)) {
                idUser = (Long) parameterValues[i];
                checkValidId(idToken, idUser);
                break;
            }

            if ("postOrderRequest".equalsIgnoreCase(paramName)) {
                postOrderRequest = (PostOrderRequest) parameterValues[i];
                if (postOrderRequest != null) {
                    idUser = postOrderRequest.getUserId();
                    checkValidId(idToken, idUser);
                }
            }
        }
    }

    private void checkValidId(Long idToken, Long idUser) {
        if (!Objects.equals(idToken, idUser)) {
            log.error("TokenId : " + idToken + " | userId : " + idUser);
            throw new GeneralException(FrtError.USER_ACCESS_DENIED);
        }
    }

}
