package com.gateway.service.controller;

import com.gateway.service.payload.CategoryFailDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class FallbackController {

    @RequestMapping("/categoryFallbackUri")
    public Mono<CategoryFailDto> categoryFallBack(){
        CategoryFailDto categoryFailDto = new CategoryFailDto("Category service is unavailable !!", false);
        return Mono.just(categoryFailDto);
    }

    @RequestMapping("/courseFallback")
    public Mono<String> courseFallback(){
        return Mono.just("Course Service is down");
    }


}
