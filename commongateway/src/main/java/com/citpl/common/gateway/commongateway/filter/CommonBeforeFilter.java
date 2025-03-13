package com.citpl.common.gateway.commongateway.filter;

import java.util.UUID;
import java.util.function.Function;

import org.springframework.web.servlet.function.ServerRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonBeforeFilter {

//	public static Function<ServerRequest, ServerRequest> instrument(String header) {
//		log.info("instrument");
//		return request -> ServerRequest.from(request).header(header, generateId()).build();
//	}
//
//	private static String generateId() {
//		return UUID.randomUUID().toString();
//	}

}
