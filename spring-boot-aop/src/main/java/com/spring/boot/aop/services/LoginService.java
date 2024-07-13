package com.spring.boot.aop.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public void mainLogic(String username, String password) {

        System.out.println("username : " + username);
        System.out.println("password : " + password);
        System.out.println("Access  the useful apis");

    }

    public void getAllUser(String username, String password) {
        System.out.println("printing users");
        System.out.println("user1");
        System.out.println("user2");
    }

    public void logout() {
        System.out.println("user is getting logout");
    }
}
