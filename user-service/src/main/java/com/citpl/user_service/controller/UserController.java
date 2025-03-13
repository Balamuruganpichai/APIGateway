package com.citpl.user_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @GetMapping
    public String viewUser (HttpServletRequest request) {
        String httpMethod = request.getMethod();
        String relativePath = request.getRequestURI();
        log.info("method ===>: {}", httpMethod);
        log.info("path ===>: {}", relativePath);
        return "View USER!";
    }

    @PostMapping
    public String addUser (HttpServletRequest request)
    {
        String httpMethod = request.getMethod();
        String relativePath = request.getRequestURI();
        log.info("method ===>: {}", httpMethod);
        log.info("path ===>: {}", relativePath);
        return "Create USER!";
    }
}

