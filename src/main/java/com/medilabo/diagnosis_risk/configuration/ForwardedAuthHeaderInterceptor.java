package com.medilabo.diagnosis_risk.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
                //verification de la reception du token
                System.out.println("Retrieved authorization token from incoming request: " + authorizationHeader);

                // ajout dans toutes les requetes sortantes
                requestTemplate.header("Authorization", authorizationHeader);
            } else {
                System.out.println("No token found from incoming request");
            }
        }
        // verification de l'envoi du token
        System.out.println("Retrieved authorization token for outgoing request : " + requestTemplate.headers());
    }

}