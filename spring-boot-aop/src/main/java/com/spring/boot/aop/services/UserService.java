package com.spring.boot.aop.services;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    public void createUser(String name) {
        System.out.println("creating user with name " + name);
        System.out.println("user saved");
    }
}
