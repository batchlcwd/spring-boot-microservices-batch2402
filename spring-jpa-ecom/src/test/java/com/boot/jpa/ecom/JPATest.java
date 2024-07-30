package com.boot.jpa.ecom;

import com.boot.jpa.ecom.entities.Category;
import com.boot.jpa.ecom.repositories.CategoryRepository;
import com.boot.jpa.ecom.services.CategoryService;
import com.boot.jpa.ecom.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPATest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    protected ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    public void criteriaTest(){


        List<Category> categories = categoryService.getAllCategories();
        categories.forEach(item-> System.out.println(item.getTitle()));

    }

    @Test
    public void transactTest(){
        productService.transactCategoryWithProduct();

    }

    @Test
    public void entityEvents(){
        Category category = new Category();
        category.setTitle("Event Category 2");
        categoryRepository.save(category);
        System.out.println("category saved");
    }
}
