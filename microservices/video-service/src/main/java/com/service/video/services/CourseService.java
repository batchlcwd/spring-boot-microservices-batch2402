package com.service.video.services;

import com.service.video.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service",fallback = CourseServiceFallback.class)
public interface CourseService {


    @GetMapping("/api/v1/courses/{courseId}")
    CourseDto getCourseById(@PathVariable String courseId);

}
