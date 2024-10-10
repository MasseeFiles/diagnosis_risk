package com.medilabo.diagnosis_risk.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Interceptor de toutes les requetes http entrantes du
 * microservice. Utilisé par OpenFeign pour ajouter le header Authorization
 * dans les requetes sortantes si reçu.
 */
@Component
public class ForwardedAuthHeaderInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            String authorizationHeader =
                    requestAttributes.getRequest().getHeader("Authorization");
            if (authorizationHeader != null) {
                System.out.println("Authorization header found in incoming request :" + authorizationHeader);

                requestTemplate.header("Authorization", authorizationHeader);
            } else {
                System.out.println("No authorization header found in incoming request");
            }
        }
    }

}