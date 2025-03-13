package com.citpl.common.gateway.commongateway.filter;

import java.util.function.Function;

import org.springframework.web.servlet.function.ServerRequest;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
public class RequestProcessor  {
	//public class RequestProcessor implements Function<ServerRequest, ServerRequest> {
//	private String name;
//
//	public RequestProcessor(String name) {
//		this.name = name;
//	}
//
//	@Override
//	public ServerRequest apply(ServerRequest request) {
//		log.info("path: {}", request.path());
//		return ServerRequest.from(request).build();
//	}

}
