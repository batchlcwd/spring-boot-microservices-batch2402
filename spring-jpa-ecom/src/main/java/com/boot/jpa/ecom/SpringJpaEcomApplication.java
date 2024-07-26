package com.boot.jpa.ecom;

import com.boot.jpa.ecom.entities.Product;
import com.boot.jpa.ecom.repositories.ProductRepository;
import com.boot.jpa.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.boot.jpa.ecom", "in.repositories"})
public class SpringJpaEcomApplication implements CommandLineRunner {


    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaEcomApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("application starting");

        //

//        Product product = new Product();
//
//        product.setTitle("Iphone 14 pro max");
//        product.setDescription("this is good phone");
//        product.setPrice(136000.50);
//        product.setLive(false);
//        Product product1 = productService.create(product);
//        System.out.println(product1);
//        System.out.println("product created:");

//        productService.all().forEach(System.out::println);
//        System.out.println(productService.byId(1));


    }
}
