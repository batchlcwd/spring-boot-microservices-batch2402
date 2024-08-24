package com.elearn.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("GUEST", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
//                        .requestMatchers("/all").permitAll()
                        .anyRequest()
                        .authenticated());

//            auth.requestMatchers(HttpMethod.GET, "/api/v1/categories").permitAll()
//                    .requestMatchers("/client-login", "/client-login-process").permitAll()
//                    .requestMatchers("/api/v1/users").permitAll()
////                    .requestMatchers(HttpMethod.GET, "/api/v1/courses").permitAll()
//                    .anyRequest()
//                    .authenticated();


//);


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

        httpSecurity.logout(logout ->

        {
            logout.logoutUrl("/logout");
        });


        httpSecurity.httpBasic(Customizer.withDefaults());


//        //
//
//        httpSecurity.cors()
//


        return httpSecurity.build();
    }


}
