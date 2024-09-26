package com.service.category.services;

import com.service.category.dto.CategoryDto;
import com.service.category.dto.CustomPageResponse;
import com.service.category.entities.Category;
import com.service.category.exception.ResourceNotFoundException;
import com.service.category.repositories.CategoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements  CategoryService{


    private CategoryRepo repo;


    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo repo,  ModelMapper modelMapper) {
        this.repo = repo;

        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {

        // create category id

        String catId = UUID.randomUUID().toString();
        categoryDto.setId(catId);                //date
        categoryDto.setAddedDate(new Date());
        System.out.println(categoryDto.getBannerImageUrl());
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
    public void addCourseToCategory(String catId, String course) {

    }


}
