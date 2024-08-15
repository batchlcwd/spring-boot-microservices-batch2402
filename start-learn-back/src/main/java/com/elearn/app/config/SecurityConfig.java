package com.elearn.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // customization


        //routes/urls

//      httpSecurity.authorizeHttpRequests(e->{
//          e.requestMatchers("/api/v1/categories").permitAll()
//                  .anyRequest()
//                  .authenticated();
//      });

        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/api/v1/categories").permitAll()
                        .requestMatchers("/client-login", "/client-login-process").permitAll()
//                    .requestMatchers(HttpMethod.GET, "/api/v1/courses").permitAll()
                    .anyRequest()
                    .authenticated();

        });


        httpSecurity.formLogin(
                form ->
                {
                    form.loginPage("/client-login");
                    form.usernameParameter("username");
                    form.passwordParameter("userpassword");
                    form.loginProcessingUrl("/client-login-process");
                    form.successForwardUrl("/success");
//                    form.successHandler();
//                    form.failureHandler();

                }

        );

//        httpSecurity.httpBasic(Customizer.withDefaults());
//        //
//
//        httpSecurity.cors()
//


        return httpSecurity.build();
    }


}
