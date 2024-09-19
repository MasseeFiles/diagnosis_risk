package com.medilabo.diagnosis_risk.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class FeignClientConfig {
    @Value("${GATEWAY_IP}")
    private String gatewayIp;

    @Value("${GATEWAY_PORT}")
    private int gatewayPort;

    @Bean
    public String toGatewayServiceUrl() {
        String uriGateway = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(gatewayIp)
                .port(gatewayPort)
                .build()
                .toUriString();

        return uriGateway;
    }

    @Bean
    public ForwardedAuthHeaderInterceptor forwardedAuthHeaderInterceptor() {
        return new ForwardedAuthHeaderInterceptor();
    }

}
