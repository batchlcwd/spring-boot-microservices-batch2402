package com.gateway.service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r ->
                        r.path("/category/**")
                                .filters(
                                        f ->
                                                f.rewritePath("/category/?(?<segment>.*)", "/${segment}")
                                                        .addResponseHeader("-X-CUSTOM-HEADER", "added by abhishek")

                                )
                                .uri("lb://category-service")
                )
//                .route(
//                information [url,predicate,filters])
//                .route()
                .build();

    }

}
