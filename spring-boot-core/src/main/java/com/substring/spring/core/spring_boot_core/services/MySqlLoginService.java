package com.substring.spring.core.spring_boot_core.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MySqlLoginService implements LoginService {
    @Override
    public void login() throws InterruptedException {
        System.out.println("Logging user mysql database");
        System.out.println("wait....");
        Thread.sleep(5000);
        System.out.println("working...");
        Thread.sleep(5000);
    }

    @Override
    public void logout() {
        System.out.println("logging out using mysql database");
    }
}
