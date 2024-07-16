package com.substring.jdbc.ecom;

import com.substring.jdbc.ecom.dao.ProductDao;
import com.substring.jdbc.ecom.dao.impl.ProductDaoImpl;
import com.substring.jdbc.ecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringJdbcEcomApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringJdbcEcomApplication.class, args);

        // product create karenge:
        Product product1 = new Product();
        product1.setId(102);
        product1.setTitle("Iphone 14");
        product1.setDescription("Best IOS Phone");
        product1.setPrice(124000);
        ProductDao productDao = context.getBean(ProductDao.class);
        Product product = productDao.create(product1);
        System.out.println(product);
       

        //Assigment : menu driven progam:

//        *********** WELCOME TO ECOM APP********
        //PRESS 1 for adding product
        //PRESS 2 for updating product



    }


}
