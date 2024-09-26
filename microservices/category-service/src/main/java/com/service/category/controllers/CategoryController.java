package com.service.category.controllers;


import com.service.category.config.AppConstants;
import com.service.category.dto.CategoryDto;
import com.service.category.dto.CustomMessage;
import com.service.category.dto.CustomPageResponse;
import com.service.category.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")public class CategoryController {

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
    )
    {
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


    @PostMapping("/{categoryId}/courses/{courseId}")
    public ResponseEntity<CustomMessage> addCourseToCategory(
            @PathVariable String categoryId,
            @PathVariable String courseId
    ) {
        categoryService.addCourseToCategory(categoryId, courseId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Category Updated !!");
        customMessage.setSuccess(true);
        return ResponseEntity.ok(customMessage);
    }



}
