package com.elearn.app.config.security;


import com.elearn.app.dtos.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
public class SecurityConfig {


    private AuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;


    public SecurityConfig(AuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter authenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

//
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
////        service ke ander create user
//
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("ram")
//                        .password("ram")
//                        .roles("ADMIN")
//                        .build()
//        );
//
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("shyam")
//                        .password("ram")
//                        .roles("USER")
//                        .build()
//        );
//
//
//        return userDetailsManager;
//
//
//    }
//
//


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // customization


        //routes/urls

//      httpSecurity.authorizeHttpRequests(e->{
//          e.requestMatchers("/api/v1/categories").permitAll()
//                  .anyRequest()
//                  .authenticated();
//      });

        //as per requirement
//        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.cors(cor -> {
            CorsConfiguration config = new CorsConfiguration();
//            config.addAllowedOrigin("http://localhost:5173");
            config.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:4200"));
            config.addAllowedMethod("*");
            config.addAllowedHeader("*");
            config.setAllowCredentials(true);
            config.setMaxAge(300L);
            UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
            configurationSource.registerCorsConfiguration("/**", config);

            cor.configurationSource(configurationSource);
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers("/doc.html","/v3/api-docs/**","/swagger-ui/**","/swagger-resources/**").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll().
                        requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("GUEST", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")

                        .anyRequest()
                        .authenticated());


        httpSecurity.sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling(e ->
                e.authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            CustomMessage customMessage = new CustomMessage();
                            customMessage.setMessage("You dont have permission to perform this operations !! " + accessDeniedException.getMessage());
                            customMessage.setSuccess(false);
                            String stringMessage = new ObjectMapper().writeValueAsString(customMessage);
                            response.getWriter().println(stringMessage);

                        })
        );

//            auth.requestMatchers(HttpMethod.GET, "/api/v1/categories").permitAll()
//                    .requestMatchers("/client-login", "/client-login-process").permitAll()
//                    .requestMatchers("/api/v1/users").permitAll()
////                    .requestMatchers(HttpMethod.GET, "/api/v1/courses").permitAll()
//                    .anyRequest()
//                    .authenticated();


//);

//
//        httpSecurity.formLogin(
//                form ->
//
//                {
//                    form.loginPage("/client-login");
//                    form.usernameParameter("username");
//                    form.passwordParameter("userpassword");
//                    form.loginProcessingUrl("/client-login-process");
//                    form.successForwardUrl("/success");
//
////                    form.successHandler();
////                    form.failureHandler();
//
//                }
//
//        );
//
//        httpSecurity.logout(logout ->
//
//        {
//            logout.logoutUrl("/logout");
//        });


//        httpSecurity.httpBasic(hbasic-> hbasic.authenticationEntryPoint(authenticationEntryPoint));


//        //
//
//        httpSecurity.cors()

//        httpSecurity.exceptionHandling(ex -> {
//            ex.authenticationEntryPoint(authenticationEntryPoint);
//        });

//


        return httpSecurity.build();
    }


}
