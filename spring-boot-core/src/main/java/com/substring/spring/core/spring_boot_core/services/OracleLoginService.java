package com.substring.spring.core.spring_boot_core.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class OracleLoginService implements LoginService {
    @Override
    public void login() throws InterruptedException {
        System.out.println("login using oracle db");
    }

    @Override
    public void logout() {
        System.out.println("logout using  oracle db");
    }
}
