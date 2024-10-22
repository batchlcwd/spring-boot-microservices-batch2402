package com.gateway.service.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class AppConfig {


    @Bean
    public KeyResolver keyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10, 20, 1);
    }


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                //configured circuit breaker
                .route(r ->
                        r.path("/category/**")
                                .filters(f -> f.rewritePath("/category/?(?<segment>.*)", "/${segment}")
                                        .addResponseHeader("-X-CUSTOM-HEADER", "added by abhishek")
                                        .circuitBreaker(breaker -> breaker.setName("category-breaker")
                                                .setFallbackUri("forward:/categoryFallbackUri")
                                        )
                                )
                                .uri("lb://category-service")
                )
                .route(r -> r.path("/course/**")
                        .filters(f -> f.rewritePath("/course/?(?<segment>.*)", "/${segment}")
                                .requestRateLimiter(rateConfig ->
                                        rateConfig.setKeyResolver(keyResolver())
                                                .setRateLimiter(redisRateLimiter()))

                        )
                        .uri("lb://course-service"))

                //configured retry
                .route(r -> r.path("/video/**")
                        .filters(f ->
                                        f.rewritePath("/video/?(?<segment>.*)", "/${segment}")
                                                .retry(
                                                        retryConfig ->
                                                                retryConfig.setRetries(3)
                                                                        .setMethods(HttpMethod.GET)
                                                                        .setBackoff(
                                                                                Duration.ofMillis(100),
                                                                                Duration.ofMillis(1000),
                                                                                2,
                                                                                true
                                                                        )
                                                )


//                                .circuitBreaker(breaker -> breaker.setName("video-breaker")
//
//                                )

                        )
                        .uri("lb://video-service"))


//                .route(
//                information [url,predicate,filters])
//                .route()
                .build();

    }

}
