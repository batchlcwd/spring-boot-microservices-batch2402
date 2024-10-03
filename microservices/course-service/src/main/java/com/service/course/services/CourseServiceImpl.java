package com.service.course.services;

import com.service.course.config.AppConstants;
import com.service.course.dto.CategoryDto;
import com.service.course.dto.CourseDto;
import com.service.course.dto.ResourceContentType;
import com.service.course.dto.VideoDto;
import com.service.course.entities.Course;
import com.service.course.exception.ResourceNotFoundException;
import com.service.course.repositories.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepo courseRepository;


    private ModelMapper modelMapper;

    private RestTemplate restTemplate;

    private WebClient.Builder webClient;


    @Autowired
    private FileService fileService;

    public CourseServiceImpl(WebClient.Builder webClient, CourseRepo courseRepository, ModelMapper modelMapper, RestTemplate restTemplate, FileService fileService) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.fileService = fileService;
        this.webClient = webClient;
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
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        modelMapper.map(courseDto, course);
        //one by one
        Course updatedCourse = courseRepository.save(course);
        return modelMapper.map(updatedCourse, CourseDto.class);
    }


    //single course
    @Override
    public CourseDto getCourseById(String id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        // get category detail of the course

        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        //load category of the video [Category Service]
        courseDto.setCategoryDto(getCategoryOfCourse(course.getCategoryId()));
        //load[Video Services] video services to load videos of this course
        courseDto.setVideos(getVideosOfCourse(course.getId()));
        return courseDto;
    }


    // all course get
    @Override
    public Page<CourseDto> getAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);

        //one by one// api call to category service to get category detail
        List<CourseDto> dtos = courses.getContent().stream().
                map(course -> {
                    CourseDto dto = modelMapper.map(course, CourseDto.class);
                    dto.setCategoryDto(getCategoryOfCourse(dto.getCategoryId()));
                    //load videos of all current course [Video Service]
                    dto.setVideos(getVideosOfCourse(dto.getId()));
                    return dto;
                })
                .collect(Collectors.toList());


        //if you want you can create your page response
//        List<CourseDto> newDtos = dtos.stream().map(courseDto -> {
//            // write implementation here
//
////            ResponseEntity<List<CategoryDto>> exchange = restTemplate.exchange(
////                    AppConstants.CATEGORY_SERVICE_BASE_URL + "/categories/" + courseDto.getCategoryId(),
////
////                    HttpMethod.GET,
////                    null,
////
////                    new ParameterizedTypeReference<List<CategoryDto>>() {
////                    });
////
////            List<CategoryDto> body = exchange.getBody();
//
//            courseDto.setCategoryDto(getCategoryOfCourse(courseDto.getCategoryId()));
//            return courseDto;
//        }).collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, courses.getTotalElements());
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    /// search
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
        return courses.stream().map(course -> {
            CourseDto dto = modelMapper.map(course, CourseDto.class);
            dto.setCategoryDto(getCategoryOfCourse(dto.getCategoryId()));
            //load videos of searched course[Video Service]
            dto.setVideos(getVideosOfCourse(dto.getId()));
            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found!!"));
        String filePath = fileService.save(file, AppConstants.COURSE_BANNER_UPLOAD_DIR, file.getOriginalFilename());
        course.setBanner(filePath);
        course.setBannerContentType(file.getContentType());
        return modelMapper.map(courseRepository.save(course), CourseDto.class);
    }

    @Override
    public ResourceContentType getCourseBannerById(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found!!"));
        String bannerPath = course.getBanner();
        Path path = Paths.get(bannerPath);
        Resource resource = new FileSystemResource(path);
        ResourceContentType resourceContentType = new ResourceContentType();
        resourceContentType.setResource(resource);
        resourceContentType.setContentType(course.getBannerContentType());
        return resourceContentType;
    }

    // api call for loading category using category id
//    [Rest Template]
    public CategoryDto getCategoryOfCourse(String categoryId) {
        try {
            ResponseEntity<CategoryDto> exchange = restTemplate.exchange(AppConstants.CATEGORY_SERVICE_BASE_URL + "/categories/" + categoryId, HttpMethod.GET, null, CategoryDto.class);

            HttpEntity<CategoryDto> categoryDtoHttpEntity = new HttpEntity<>(
                    new CategoryDto()
            );


            return exchange.getBody();
        } catch (HttpClientErrorException ex) {
//            ex.printStackTrace();
            return null;
        }
    }

        // call video-service to get videos of course
        public List<VideoDto> getVideosOfCourse(String courseId)
        {
            return webClient.build().
                    get()
                    .uri(AppConstants.VIDEO_SERVICE_BASE_URL + "/videos/course/{id}", courseId)
                    .retrieve()
                    .bodyToFlux(VideoDto.class)
                    .collectList()
                    .block();
        }
    }

