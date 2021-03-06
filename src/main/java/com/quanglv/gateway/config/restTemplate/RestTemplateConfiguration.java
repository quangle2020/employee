package com.quanglv.gateway.config.restTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {

    static final int TIMEOUT = 1200000;

//    @Bean
//    @Primary
//    RestTemplate restTemplateWithConnectReadTimeout() {
//        return new RestTemplateBuilder()
//                .setConnectTimeout(Duration.ofMillis(TIMEOUT))
//                .setReadTimeout(Duration.ofMillis(TIMEOUT))
//                .build();
//    }
//
//    @Bean
//    @Qualifier
//    RestTemplate restTemplateWithConnectTimeout() {
//        return new RestTemplateBuilder()
//                .setConnectTimeout(Duration.ofMillis(TIMEOUT))
//                .build();
//    }

    @Bean
    @Qualifier
    RestTemplate restTemplateTimeoutWithRequestFactory() {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(TIMEOUT);
            requestFactory.setReadTimeout(TIMEOUT);

            return new RestTemplate(requestFactory);
    }
}
