package com.frt.authservice.controller;

import com.frt.authservice.service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class TestController {

    private final JwtUtil jwtUtil;

    @GetMapping
    public String getHello() {
//        throw new ExpiredJwtException(null, null, FrtError.USER_SESSION_EXPIRED.getDescription());
        return "hello";
    }
}
