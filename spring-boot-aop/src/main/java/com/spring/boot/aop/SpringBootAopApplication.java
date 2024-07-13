package com.spring.boot.aop;

import com.spring.boot.aop.beans.Employee;
import com.spring.boot.aop.services.LoginService;
import com.spring.boot.aop.services.ProductService;
import com.spring.boot.aop.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootAopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootAopApplication.class, args);
//		Employee employee = context.getBean(Employee.class);
//		employee.work();
        LoginService loginService = context.getBean(LoginService.class);
        loginService.logout();
        ProductService productService = context.getBean(ProductService.class);
        productService.printAllProducts();

        UserService userService = context.getBean(UserService.class);
        userService.createUser("ayush ");

        System.out.println("_______________");
//        loginService.mainLogic("system","admin123");
        loginService.getAllUser("admi23523n","admin123");

    }

}
