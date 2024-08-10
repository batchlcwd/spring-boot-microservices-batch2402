package com.elearn.app.services;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPageResponse;

import java.util.List;

public interface CategoryService {

    //create

    CategoryDto insert(CategoryDto categoryDto);

    CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy);

    CategoryDto get(String categoryId);

    void delete(String categoryId);


    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //search

    public void addCourseToCategory(String catId, String course);


    List<CourseDto> getCoursesOfCat(String categoryId);
}
