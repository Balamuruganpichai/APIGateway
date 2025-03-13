package com.citpl.common.gateway.commongateway.config;

import com.citpl.common.gateway.commongateway.config.ValidationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
  public class WebConfig implements WebMvcConfigurer {

    private final ValidationInterceptor validationInterceptor;

    public WebConfig(ValidationInterceptor validationInterceptor) {
        this.validationInterceptor = validationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validationInterceptor)
                .addPathPatterns("/**") // Apply to all routes
                .excludePathPatterns("/validate", "/validate/**"); // Ensure validation service is bypassed
    }
}
