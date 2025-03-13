package com.citpl.common.gateway.commongateway.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

//@Configuration
public class CacheConfig {

//    @Bean
//    public Cache<String, Boolean> validationTokenCache() {
//        return CacheBuilder.newBuilder()
//                .expireAfterWrite(10, TimeUnit.MINUTES)  // Cached entries expire after 10 minutes
//                .maximumSize(1000)                       // Max 1000 entries
//                .build();
//    }
}
