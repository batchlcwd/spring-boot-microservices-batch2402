package com.elearn.app.services;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.entities.Category;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CategoryRepo;
import com.elearn.app.repositories.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepo repo;

    private CourseRepo courseRepo;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo repo, CourseRepo courseRepo, ModelMapper modelMapper) {
        this.repo = repo;
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {

        // create category id

        String catId = UUID.randomUUID().toString();
        categoryDto.setId(catId);                //date
        categoryDto.setAddedDate(new Date());
        // convert dto to entity
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCat = repo.save(category);
        return modelMapper.map(savedCat, CategoryDto.class);
    }

    @Override
    public CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy) {

        Sort sort = Sort.by(sortBy).descending();
        // page request
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Category> categoryPage = repo.findAll(pageRequest);
        List<Category> all = categoryPage.getContent();
        List<CategoryDto> categoryDtoList = all.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).toList();
        CustomPageResponse<CategoryDto> customPageResponse = new CustomPageResponse<>();
        customPageResponse.setContent(categoryDtoList);
        customPageResponse.setLast(categoryPage.isLast());
        customPageResponse.setTotalElements(categoryPage.getTotalElements());
        customPageResponse.setTotalPages(categoryPage.getTotalPages());
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setPageSize(categoryPage.getSize());
        return customPageResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not found !!"));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not found !!"));
        repo.delete(category);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not found !!"));
        category.setTitle(categoryDto.getTitle());
        category.setDesc(categoryDto.getDesc());
        Category savedCategory = repo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    @Transactional
    public void addCourseToCategory(String catId, String courseid) {

        //cat ko get karie
        Category category = repo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category Not found"));


        // course ko get karie

        Course course = courseRepo.findById(courseid).orElseThrow(() -> new ResourceNotFoundException("Course not  found !!"));


        // course ke ander cat list mein cat
        //cat ke ander course hai usme couse

        category.addCourse(course);

        repo.save(category);

        System.out.println("category relationship updated");


    }

    @Override
    @Transactional
    public List<CourseDto> getCoursesOfCat(String categoryId) {
        Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
        List<Course> courses = category.getCourses();

        return courses.stream().map(course -> modelMapper.map(course, CourseDto.class)).toList();
    }
}
