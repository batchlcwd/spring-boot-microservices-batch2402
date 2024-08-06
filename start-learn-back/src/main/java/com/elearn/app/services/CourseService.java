package com.elearn.app.services;

import com.elearn.app.dtos.CourseDto;
import com.elearn.app.entities.Course;

import java.util.List;

public interface CourseService {

    CourseDto create(CourseDto courseDto);

    List<CourseDto> getAll();

    CourseDto update(CourseDto dto, String courseId);

    void delete(String courseId);

    List<CourseDto> searchByTitle(String titleKeyword);


}
