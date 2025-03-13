package com.citpl.common.gateway.commongateway.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MDCFilter  {
   // public class MDCFilter implements Filter {
	
//    private static final String REQUEST_ID_HEADER = "X-Request-Id";
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String requestId = httpRequest.getHeader(REQUEST_ID_HEADER);
//        log.debug("request Id: {}", requestId);
//
//        if (requestId == null || requestId.isEmpty()) {
//            requestId = UUID.randomUUID().toString(); // Generate if missing
//        }
//
//        MDC.put(REQUEST_ID_HEADER, requestId); // Store in MDC
//        log.info("log filter initiated");
//        try {
//            chain.doFilter(request, response);
//        } finally {
//            MDC.remove(REQUEST_ID_HEADER); // Clean up after request processing
//        }
//
//	}

}
