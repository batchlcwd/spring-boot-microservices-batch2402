package com.substring.spring.core.spring_boot_core.controllers;

import com.substring.spring.core.spring_boot_core.services.LoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HomeController {
    private LoginService loginService;

    public HomeController( LoginService loginService) {
        this.loginService = loginService;
    }

    public void loginUser() throws InterruptedException {
        loginService.login();
        System.out.println("loggedIn  success");
    }

    public void logoutUser(){
        loginService.logout();
    }
}
