package com.citpl.ticket_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);
    @GetMapping
    public String viewTicket (HttpServletRequest request) {
        String httpMethod = request.getMethod();
        String relativePath = request.getRequestURI();
        log.info("method: {}, path: {}", httpMethod, relativePath);
        return "View TICKET!";
    }

    @PostMapping
    public String createTicket (HttpServletRequest request)
    {
        String httpMethod = request.getMethod();
        String relativePath = request.getRequestURI();
        log.info("path ===>: {}", relativePath);
        return "Create TICKET!";
    }
}
