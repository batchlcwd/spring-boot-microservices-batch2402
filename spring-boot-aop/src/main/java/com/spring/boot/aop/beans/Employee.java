package com.spring.boot.aop.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


public class Employee
// implements InitializingBean, DisposableBean
 {
    public Employee() {
        System.out.println("employee created");
    }

    public void work() {
        System.out.println("Employee is working");
    }


    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing  employee");
        System.out.println("db connection open");
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("Cleaning up employee");
        System.out.println("closing the database connection");
    }
}
