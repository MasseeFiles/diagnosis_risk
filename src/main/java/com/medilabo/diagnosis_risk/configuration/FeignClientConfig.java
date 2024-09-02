package com.medilabo.diagnosis_risk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public ForwardedAuthHeaderInterceptor forwardedAuthHeaderInterceptor() {
        return new ForwardedAuthHeaderInterceptor();
    }
}