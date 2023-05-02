package com.frt.AuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		System.out.println("health-check commit");
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
