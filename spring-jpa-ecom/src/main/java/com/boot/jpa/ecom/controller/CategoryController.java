package com.boot.jpa.ecom.controller;

import com.boot.jpa.ecom.entities.Category;
import com.boot.jpa.ecom.services.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {




    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //get all category


//    /categories
//    GET
    @RequestMapping
    public List<Category> allCategories(){
        return categoryService.getAll();
    }

}


///
/*
Assignment


1. get  all categories
2. get single category by id
3. get products
4. get product by id
5 get product  by title
6. search product by title




 */