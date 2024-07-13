package com.spring.boot.aop.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public void printAllProducts() {

        System.out.println("all products");
        System.out.println("product 1");
        System.out.println("product 2");

    }
}
