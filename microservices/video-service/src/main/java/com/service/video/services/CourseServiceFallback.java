package com.service.video.services;

import com.service.video.dto.CourseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CourseServiceFallback implements  CourseService{
    @Override
    public CourseDto getCourseById(String courseId) {

        CourseDto courseDto = new CourseDto();
        courseDto.setId("1234");
        courseDto.setTitle("This is dummy course");
        courseDto.setLongDesc("This is fallbacked called when course service is not available");
        return courseDto;
    }
}
