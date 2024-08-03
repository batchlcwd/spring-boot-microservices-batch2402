package com.boot.jpa.ecom.controller;

import com.boot.jpa.ecom.entities.Category;
import com.boot.jpa.ecom.listeners.CategoryEventListener;
import com.boot.jpa.ecom.models.CategoryCreateRequest;
import com.boot.jpa.ecom.services.CategoryService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public List<Category> allCategories() {
        return categoryService.getAll();
    }


    //get single category

    @RequestMapping("/{categoryId}")
    public Category getOne(
            @PathVariable("categoryId") int id

            // @PathVariable  String categoryTitle
    ) {

        System.out.println(id);
//        System.out.println(categoryTitle);
        Category category = categoryService.getById(id);
        return category;

    }


    @GetMapping("/query")
    public List<Category> getCategoryList(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber
    ) {
        System.out.println("Page number : " + pageNumber);
        System.out.println("Page Size : " + pageSize);
        return null;

    }


    // get category and create category

    //    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PostMapping("/create")
    public Category createCategory(
            @RequestBody CategoryCreateRequest categoryCreateRequest
    ) {

        System.out.println(categoryCreateRequest);
        Category category = new Category();
        category.setTitle(categoryCreateRequest.getTitle());
        return categoryService.create(category);

    }

    @PostMapping("/image")
    public String imageUploader(
            @RequestParam("file") MultipartFile file
    ) {
        System.out.println(file.getOriginalFilename());
        //write...

        return "file uploaded";
    }


    //controller level method handler

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleException(RuntimeException ex) {
//        System.out.println(ex.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body("Error occurred " + ex.getMessage());
//
//
//    }


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