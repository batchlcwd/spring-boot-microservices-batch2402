package com.substring.spring.core.spring_boot_core;

import com.substring.spring.core.spring_boot_core.comp.Father;
import com.substring.spring.core.spring_boot_core.comp.Student;
import com.substring.spring.core.spring_boot_core.controllers.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import substring.Car;

@SpringBootApplication
@ComponentScan(basePackages = {"substring", "com.substring.spring.core.spring_boot_core"})
public class SpringBootCoreApplication
{



    public static void main(String[] args) throws InterruptedException {
        //bootstrapping your application
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootCoreApplication.class, args);
//        Student student = applicationContext.getBean(Student.class);
//        System.out.println(student);
//        student.study();
//        Car car = applicationContext.getBean(Car.class);
//        System.out.println(car);

        Father father = applicationContext.getBean("father", Father.class);
        father.useStudent();
//        HomeController controller = applicationContext.getBean(HomeController.class);
//        controller.loginUser();
//        controller.logoutUser();
        Car bean1 = applicationContext.getBean(Car.class);
        Car bean2 = applicationContext.getBean(Car.class);
        Car bean3 = applicationContext.getBean(Car.class);
        System.out.println(bean1);
        System.out.println(bean2);
        System.out.println(bean3);

        System.out.println(father.getTest());
    }

}
