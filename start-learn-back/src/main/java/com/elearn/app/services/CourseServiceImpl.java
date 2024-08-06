package com.elearn.app.services;

import com.elearn.app.dtos.CourseDto;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.plaf.IconUIResource;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CourseServiceImpl  implements  CourseService{

    private CourseRepo courseRepo;
    
    private ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto create(CourseDto courseDto) {
        Course savedCourse = courseRepo.save(this.dtoToEntity(courseDto));
        return entityToDto(savedCourse);
    }

    @Override
    public List<CourseDto> getAll() {

        List<Course> courses = courseRepo.findAll();

        //all course into course dto [list]
        List<CourseDto> courseDtoList = courses
                .stream()
                .map(course ->
                        entityToDto(course))
                .collect(toList());

        return courseDtoList;
    }

    @Override
    public CourseDto update(CourseDto dto, String courseId) {
        return null;
    }

    @Override
    public void delete(String courseId) {
        Course course=courseRepo.
                findById(courseId).
                orElseThrow(() -> new ResourceNotFoundException("Course not found !!"));
               courseRepo.delete(course);

    }

    @Override
    public List<CourseDto> searchByTitle(String titleKeyword) {
        return null;
    }

    public CourseDto entityToDto(Course course){
//        CourseDto courseDto = new CourseDto();
//
//        courseDto.setId(courseDto.getId());
//        courseDto.setTitle(course.getTitle());

        //        .....
        //do for rest of the field
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        return courseDto;
    }

    public Course dtoToEntity(CourseDto dto){
//        Course course = new Course();
//        course.setId(dto.getId());
        ////
//        do rest of the field
        Course course = modelMapper.map(dto, Course.class);
        return course;
    }
}
