package com.elearn.app.services;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.ResourceContentType;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.IconUIResource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepo courseRepository;


    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private  FileService fileService;
    public CourseServiceImpl(CourseRepo courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {

        courseDto.setId(UUID.randomUUID().toString());
        courseDto.setCreatedDate(new Date());

        Course course = modelMapper.map(courseDto, Course.class);

        Course savedCourse = courseRepository.save(course);


        //String cat=courseDto.getCategoryId();

//        categoryService.addCourseToCategory(cat,savedCourse.getId());


        return modelMapper.map(savedCourse, CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(String id, CourseDto courseDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        modelMapper.map(courseDto, course);
        //one by one
        Course updatedCourse = courseRepository.save(course);
        return modelMapper.map(updatedCourse, CourseDto.class);
    }

    @Override
    public CourseDto getCourseById(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    public Page<CourseDto> getAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        List<CourseDto> dtos = courses.getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());

        //if you want you can create your page response

        return new PageImpl<>(dtos, pageable, courses.getTotalElements());
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseDto> searchCourses(String keyword) {


//        if(title)
//        {
//
//        }else if(desc)
//        {
//
//        }
//        else if(price)
//        {
//
//        }
        List<Course> courses = courseRepository.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(keyword, keyword);
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found!!"));
        String filePath = fileService.save(file, AppConstants.COURSE_BANNER_UPLOAD_DIR, file.getOriginalFilename());
        course.setBanner(filePath);
        course.setBannerContentType(file.getContentType());
        return modelMapper.map( courseRepository.save(course),CourseDto.class);
    }

    @Override
    public ResourceContentType getCourseBannerById(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found!!"));
        String bannerPath = course.getBanner();
        Path path = Paths.get(bannerPath);
        Resource resource=new FileSystemResource(path);
        ResourceContentType resourceContentType = new ResourceContentType();
        resourceContentType.setResource(resource);
        resourceContentType.setContentType(course.getBannerContentType());
        return resourceContentType;
    }
}
