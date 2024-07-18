package com.substring.jdbc.ecom;

import com.substring.jdbc.ecom.dao.CategoryDao;
import com.substring.jdbc.ecom.dao.ProductDao;
import com.substring.jdbc.ecom.model.Category;
import com.substring.jdbc.ecom.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcEcomApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringJdbcEcomApplication.class, args);


        ProductDao productDao = context.getBean(ProductDao.class);
        CategoryDao categoryDao = context.getBean(CategoryDao.class);
//
//        Category category = new Category();
//        category.setId(1001);
//        category.setTitle("mobiles");
//        category.setDescription("mobiles phones");
//        categoryDao.create(category);

        // product create karenge:
//        Product product1 = new Product();
//        product1.setId(102);
//        product1.setTitle("Iphone 14");
//        product1.setDescription("Best IOS Phone");
//        product1.setPrice(124000);
//        product1.setCatId(1001);
//        productDao.create(product1);

//        Product product2 = new Product();
//        product2.setId(104);
//        product2.setTitle("Samsung 14");
//        product2.setDescription("Best IOS Phone");
//        product2.setPrice(12400);
//        product2.setCatId(1002);
//        productDao.create(product2);


//        Product product = productDao.create(product1);
//        System.out.println(product);
//        List<Product> products = productDao.getAll();
//        products.forEach(item -> System.out.println(item));

        productDao.getAllWithCategory().forEach(System.out::println);

//        Product product = productDao.get(101);
//        System.out.println(product);
        // Assigment : menu driven progam:

        // *********** WELCOME TO ECOM APP********
        // PRESS 1 for adding product
        // PRESS 2 for updating product

        // assignment
    }

}
