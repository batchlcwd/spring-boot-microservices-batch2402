package com.boot.jpa.ecom.controller;

import com.boot.jpa.ecom.repositories.ProductRepository;
import com.boot.jpa.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    //field  injection

//    @Autowired
    private final ProductService productService;


    //constructor inject: recommended

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    public void test(){
     productService.getProductByPrice(120);
    }


}
