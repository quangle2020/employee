package com.quanglv.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                 .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and().oauth2ResourceServer().jwt();
    }
}
