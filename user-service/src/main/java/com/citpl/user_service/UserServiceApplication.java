package com.citpl.user_service;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
//		Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//		String encodedKey = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
//		System.out.println("Generated Key: " + encodedKey);
	}

}
