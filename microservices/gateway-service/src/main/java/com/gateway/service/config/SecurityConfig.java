package com.gateway.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security) {

        security.cors(e -> e.disable()).csrf(e -> e.disable())
                .authorizeExchange(exchange ->
                                exchange.pathMatchers(HttpMethod.GET).permitAll()
//                                .pathMatchers("/ayush").hasRole("ADMIN")
                                        .anyExchange().authenticated()
                ).
                oauth2ResourceServer(
                        customizer -> customizer.jwt(Customizer.withDefaults())
                );

        return security.build();
    }

}
