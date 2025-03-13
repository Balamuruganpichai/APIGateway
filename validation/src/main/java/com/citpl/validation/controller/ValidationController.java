package com.citpl.validation.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.citpl.validation.dto.RoleMasterDto;
import com.citpl.validation.service.RoleMasterService;
import com.citpl.validation.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.function.ServerRequest;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

//@Slf4j
@RestController
@RequestMapping("/validate")
public class ValidationController {
    private final JwtUtil jwtUtil;

    @Autowired
    private RoleMasterService roleMasterService;

    public ValidationController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    private static final Logger log = LoggerFactory.getLogger(ValidationController.class);
    // Mocked Valid Tokens (Replace with Database or External Verification Logic)
    private static final List<String> VALID_TOKENS = Arrays.asList("token123", "secure456", "gateway789");

    /**
     * Validate a given token from the request.
     * Example: http://localhost:8081/validate?token=your-token-here
     */
//    @GetMapping
//    public ResponseEntity<Boolean> validateToken(HttpServletRequest request) {
//       // log.info("Received validation request for token: {}", token);
//        String requestUrl = request.getMethod();  // Get full URL
//        String requestUri = request.getRequestURI();  // Get only the path
//
//        log.info("request.getMethod(): {}", requestUrl);
//        log.info("Request URI: {}", requestUri);
//
//        //boolean isValid = VALID_TOKENS.contains(token);
//        boolean isValid=true;
//        log.info("Validation Result: {}", isValid);
//
//        return ResponseEntity.ok(isValid);
//    }
    @GetMapping
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader, HttpServletRequest request) {
        try {
            //System.out.println("mmmm");
            String token = extractToken(authHeader);
            System.out.println("Generate token: "+jwtUtil.generateTestJwt());
            List<String> roles = jwtUtil.extractValueFromToken(token);
            System.out.println("bbbb: "+roles);
            String method = request.getParameter("method");
            String path = request.getParameter("relativePath");
            System.out.println("httpMethod: "+method);
            System.out.println("relativePath: "+path);
            boolean isAuthorized = roleMasterService.getRoleMaster(path, method, roles);
            return ResponseEntity.ok(isAuthorized);
            // List<String> roles = List.of();
            // roles.set(0,"ADMIN");
            //  List<String> role = (List<String>) roles.get("roles");
//            if (roles.get(0).contains("ADMIN")) {
//
//                return ResponseEntity.ok(Map.of("valid", true, "roles", roles));
//            }
//            return ResponseEntity.ok(Map.of("valid", true, "roles", roles));
        } catch (ExpiredJwtException e) {
            log.error("JWT Token Expired: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        } catch (JwtException e) {
            log.error("Invalid JWT Token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        } catch (Exception e) {
            log.error("Error in postCheckToken: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    @PostMapping
    public ResponseEntity<Boolean> postCheckToken(@RequestHeader("Authorization") String authHeader, HttpServletRequest request) {
        try {
            //System.out.println("mmmm");
            String token = extractToken(authHeader);
            System.out.println("Generate token: "+jwtUtil.generateTestJwt());
            List<String> roles = jwtUtil.extractValueFromToken(token);
            System.out.println("bbbb: "+roles);
            String method = request.getParameter("method");
            String path = request.getParameter("relativePath");
            System.out.println("httpMethod: "+method);
            System.out.println("relativePath: "+path);
            boolean isAuthorized = roleMasterService.getRoleMaster(path, method, roles);
            return ResponseEntity.ok(isAuthorized);
            // List<String> roles = List.of();
            // roles.set(0,"ADMIN");
            //  List<String> role = (List<String>) roles.get("roles");
//            if (roles.get(0).contains("ADMIN")) {
//
//                return ResponseEntity.ok(Map.of("valid", true, "roles", roles));
//            }
//            return ResponseEntity.ok(Map.of("valid", true, "roles", roles));
        } catch (ExpiredJwtException e) {
            log.error("JWT Token Expired: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        } catch (JwtException e) {
            log.error("Invalid JWT Token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        } catch (Exception e) {
            log.error("Error in postCheckToken: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

//    @PostMapping
//    public ResponseEntity<?> postCheckToken(@RequestHeader("Authorization") String authHeader) {
//        try {
//            //System.out.println("mmmm");
//            String token = extractToken(authHeader);
//            System.out.println("Generate token: "+jwtUtil.generateTestJwt());
//            List<String> roles = jwtUtil.extractValueFromToken(token);
//            System.out.println("bbbb: "+roles);
//            // List<String> roles = List.of();
//            // roles.set(0,"ADMIN");
//            //  List<String> role = (List<String>) roles.get("roles");
//            if (roles.get(0).contains("ADMIN")) {
//
//                return ResponseEntity.ok(Map.of("valid", true, "roles", roles));
//            }
//            return ResponseEntity.ok(Map.of("valid", true, "roles", roles));
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body(Map.of("valid", false, "error", e.getMessage()));
//        }
//    }

    /**
     * Advanced validation with multiple headers (POST Request).
     * Example JSON:
     * {
     *   "X-Validation-Token": "token123",
     *   "X-User-ID": "user1"
     * }
     */
//    @PostMapping
//    public ResponseEntity<Boolean> validateHeaders(@RequestBody Map<String, String> headers) {
//        log.info("Received validation request with headers: {}", headers);
//
//        String token = headers.get("X-Validation-Token");
//        if (token == null || !VALID_TOKENS.contains(token)) {
//            log.warn("Invalid or missing token!");
//            return ResponseEntity.ok(false);
//        }
//
//        log.info("Validation Successful!");
//        return ResponseEntity.ok(true);
//    }
    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("Missing Authorization Header");
    }
}
