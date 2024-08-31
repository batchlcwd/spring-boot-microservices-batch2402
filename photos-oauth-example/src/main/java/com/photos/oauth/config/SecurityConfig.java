package com.photos.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(request ->
                        request.requestMatchers("/photos").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .oauth2Login(oauth -> {

                    oauth.successHandler((request, response, authentication) -> {

                                System.out.println("Oauth login success");
                                System.out.println(authentication.toString());
                                if(authentication instanceof OAuth2AuthenticationToken)
                                {
                                    OAuth2User principal = (OAuth2User) authentication.getPrincipal();
                                    String username = principal.getAttribute("login");
                                    System.out.println(username);
                                }

                                response.sendRedirect("/");
                            })
                            .failureHandler((request, response, exception) -> {
                                System.out.println("login error");
                                response.sendRedirect("/");
                                exception.printStackTrace();
                            });

                })

        ;

        return httpSecurity.build();
    }
}
