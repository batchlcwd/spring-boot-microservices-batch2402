package com.service.category.services;

import com.service.category.dto.CategoryDto;
import com.service.category.dto.CustomPageResponse;

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

    List<CategoryDto> search(String keyword);


}
