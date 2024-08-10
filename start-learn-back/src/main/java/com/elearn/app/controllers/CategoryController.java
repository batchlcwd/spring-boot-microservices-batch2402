package com.elearn.app.controllers;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.dtos.CustomPageResponse;
import com.elearn.app.entities.Category;
import com.elearn.app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {


    private CategoryService categoryService;

    //category: create


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CategoryDto categoryDto
//           BindingResult result
    ) {

//        if(result.hasErrors())
//        {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    "Invalid  Data"
//            );
//        }

        CategoryDto createdDto = categoryService.insert(categoryDto);
        //201[Created]
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDto);

    }


    // category get:all


    @GetMapping
    public CustomPageResponse<CategoryDto> getAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    ) {
        return categoryService.getAll(pageNumber, pageSize, sortBy);

    }


    // single category

    @GetMapping("/{categoryId}")
    public CategoryDto getSingle(
            @PathVariable String categoryId
    ) {
        return categoryService.get(categoryId);
    }


    //  delete category

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage> delete(
            @PathVariable String categoryId
    ) {
        categoryService.delete(categoryId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Category deleted !!");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }


    // update category

    @PutMapping("/{categoryId}")
    public CategoryDto update(
            @PathVariable String categoryId,
            @RequestBody CategoryDto categoryDto
    ) {
        return categoryService.update(categoryDto, categoryId);
    }


}