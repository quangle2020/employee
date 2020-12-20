package com.quanglv.employee.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "config", ignoreUnknownFields = false)
@PropertySource(value = "classpath:domain-config.properties", ignoreResourceNotFound = true)
@Data
public class DomainConfig {
    @Value("${authorization-domain}")
    private String authorizationDomain;
}
