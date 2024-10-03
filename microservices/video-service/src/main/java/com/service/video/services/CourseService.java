package com.service.video.services;

import com.service.video.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service",url = "http://course-service:9092/api/v1")
public interface CourseService {


    @GetMapping("/courses/{courseId}")
    CourseDto getCourseById(@PathVariable String courseId);

}
