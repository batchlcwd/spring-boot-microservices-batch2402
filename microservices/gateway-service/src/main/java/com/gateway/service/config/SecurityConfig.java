package com.gateway.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security) {

        security.cors(e -> e.disable()).csrf(e -> e.disable())
                .authorizeExchange(exchange ->
                                exchange.pathMatchers(HttpMethod.GET).permitAll()
                                        .pathMatchers("/category/**").hasRole("CATEGORY")
                                        .pathMatchers("/video/**").hasRole("VIDEO")
                                        .pathMatchers("/course/**").hasRole("COURSE")
//                                          .pathMatchers("/ayush").hasRole("ADMIN")
                                        .anyExchange().authenticated()
                ).
                oauth2ResourceServer(
                        customizer -> customizer.jwt(
                                jwtSpec -> jwtSpec.jwtAuthenticationConverter(authorityExtractor())
                        )
                );


        return security.build();
    }


    private Converter<Jwt, Mono<AbstractAuthenticationToken>> authorityExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);


    }


}
