package com.medilabo.diagnosis_risk.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger("ForwardedAuthHeaderInterceptor");

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null) {

                logger.info("Authorization header found in incoming request :" + authorizationHeader);

                requestTemplate.header("Authorization", authorizationHeader);

            } else {
                logger.info("No authorization header found in incoming request");
            }
        }
    }

}

