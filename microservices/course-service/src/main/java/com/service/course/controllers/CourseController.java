package com.service.course.controllers;

import com.service.course.dto.CourseDto;
import com.service.course.dto.CustomMessage;
import com.service.course.dto.ResourceContentType;
import com.service.course.services.CourseService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin("http://localhost:4200")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseDto));
    }

    @PutMapping("/{id}")


    public ResponseEntity<CourseDto> updateCourse(

            @PathVariable String id, @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDto));
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CourseDto>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> searchCourses(
            @RequestParam String keyword) {
        System.out.println("searching element");
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }

    // going to create one banner[image upload api]


    @PostMapping("/{courseId}/banners")
    public ResponseEntity<?> uploadBanner(
            @PathVariable String courseId,
            @RequestParam("banner") MultipartFile banner
    ) throws IOException {

        // validate the file

        String contentType = banner.getContentType();

        if (contentType == null) {
            contentType = "image/png";
        } else if (contentType.equalsIgnoreCase("image/png") || contentType.equalsIgnoreCase("image/jpeg")) {
        } else {
            CustomMessage customMessage = new CustomMessage();
            customMessage.setSuccess(false);
            customMessage.setMessage("Invalid file");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customMessage);

        }

        //
        System.out.println(banner.getOriginalFilename());
        System.out.println(banner.getName());
        System.out.println(banner.getSize());
        System.out.println(banner.getContentType());
        CourseDto courseDto = courseService.saveBanner(banner, courseId);
//        System.out.println(banner.get);
        return ResponseEntity.ok(courseDto);
    }

    // serve banner
    @GetMapping("/{courseId}/banners")
    public ResponseEntity<Resource> serverBanner(
            @PathVariable String courseId,
            @RequestHeader("Content-Type") String contentType,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            ServletContext context
    ) {


        //

        System.out.println(request.getContextPath());
        System.out.println(request.getPathInfo());

//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements())
//        {
//            String header = headerNames.nextElement();
//            System.out.println( header+" : "+request.getHeader(header));
//        }


        ResourceContentType resourceContentType = courseService.getCourseBannerById(courseId);

        return ResponseEntity
                .ok().contentType(MediaType.parseMediaType(resourceContentType.getContentType()))
                .body(resourceContentType.getResource());

    }
}
