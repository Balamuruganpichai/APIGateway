package com.citpl.common.gateway.commongateway.config;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ValidationInterceptor implements HandlerInterceptor {

    private final WebClient webClient;

    @Value("${spring.validation.uri}")
    private String validationServiceUrl; // Fetch from application.yml

    public ValidationInterceptor(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        String method = request.getMethod();
        log.info("Intercepting Request: [{}] {}", request.getMethod(), path);

        // Exclude validation service from validation
        if (path.equals("/validate") || path.startsWith("/validate/")) {
            log.info("Skipping Validation for Validation Microservice");
            return true;
        }

        // Extract headers
        String token = extractToken(request);
        // String token = request.getHeader("X-Validation-Token");
        String orgId = request.getHeader("X-Org-Id");

        if (orgId == null) {
            log.warn("Missing X-Org-Id!");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Missing X-Org-Id");
            return false;
        }

        try {
            // Construct the validation URL dynamically
            String uri = UriComponentsBuilder.fromHttpUrl(validationServiceUrl)
                    .path("/validate")
                    .queryParam("relativePath", path)
                    .queryParam("method", method.toUpperCase())
                    .toUriString();

            log.info("Sending Validation Request to: {}", uri);

            // Call validation service with dynamic method
//            Map<String, Object> result = webClient.method(HttpMethod.GET) // Change to dynamic if needed
//                    .uri(uri)
//                    .headers(headers -> {
//                        // headers.set("X-Validation-Token", token);
//                        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
//                        headers.set("X-Org-Id", orgId);
//                    })
//                    .retrieve()
//                    .bodyToMono(Boolean.class)
//                    .block(); // Blocking call
            // **Use dynamic HTTP method**
            HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());
            return Boolean.TRUE.equals(webClient.method(httpMethod)
                    .uri(uri)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());
//            Map<String, Object> result = webClient.method(httpMethod)
//                    .uri(uri)
//                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                    .retrieve()
//                    .bodyToMono(Map.class)
//                    .block();
           // System.out.println("mmmm: "+result.get("valid"));

           // if (result != null && (boolean) result.get("valid")) {
             //   System.out.println("nnnn: ");
              //  List<String> roles = (List<String>) result.get("roles");

                // Example: Allow only users with "ADMIN" role
                // if (roles.contains("ADMIN")) {
                //if (result != null) {
                 //   System.out.println("pppp: ");
                  //  return true;
//                } else {
//                    response.sendError(HttpStatus.FORBIDDEN.value(), "Access Denied");
//                    return false;
//                }
          //  }
         //   response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Token");
        //    return false;
        } catch (ExpiredJwtException e) {
            log.error("JWT Token Expired: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Token Expired");
            return false;

        } catch (JwtException e) {
            log.error("Invalid JWT Token: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Invalid Token");
            return false;

        } catch (Exception e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Error validating token");
            return false;
        }
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("Missing Authorization Header");
    }
}

