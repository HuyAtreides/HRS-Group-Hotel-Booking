package com.hrs.hotelbooking.infra.security;

import com.hrs.hotelbooking.infra.security.filters.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                requestMatcherRegistry -> {
                    requestMatcherRegistry
                            .requestMatchers("/hotel/search")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                }
        )
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);;

        return httpSecurity.build();
    }
}
