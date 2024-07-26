package com.boot.jpa.ecom;

import com.boot.jpa.ecom.entities.Category;
import com.boot.jpa.ecom.entities.Product;
import com.boot.jpa.ecom.repositories.CategoryRepository;
import com.boot.jpa.ecom.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringJpaEcomApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("starting the test");
    }

    //test cases

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testProductRepository() {
        System.out.println("testing product repository");
        List<Product> products = productRepository.searchProductsByTitle("max");
        products.forEach(System.out::println);


    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void createCategoryTest() {

        Category category = new Category();
        category.setId(2);
        category.setTitle("trending");
        categoryRepository.save(category);


    }


    @Test
    void updateProductTest() {

        productRepository.findById(1).ifPresentOrElse(product -> {
            //product hai wala kam karna hai
            Category category = categoryRepository.findById(1).get();
            product.setCategory(category);
            productRepository.save(product);
        }, () -> {
            //product nhi mila to kya karna
            System.out.println("product not  found");
        });
    }


    @Test
    void joinQueryTest(){


        //category title: we have

        // fetch all products: we have  to fetch this product



        //fetch:join

        List<Product> trending = productRepository.getProductByCategoryTitle("trending");
        trending.forEach(System.out::println);


    }

}
