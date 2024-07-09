package com.live;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liv.config.AppConfig;
import com.live.components.Car;
import com.live.components.Engine;
import com.live.components.Wheel;

public class App {
	public static void main(String[] args) {
//        System.out.println( "Hello World!" );

//        project id-> package
//        aritifact id->  project name

//		Wheel wheel = new Wheel();
		// spring--> wheel

//		Car car = new Car(wheel);
//		car.drive();

		// how we can get objects from spring container
		// access spring container
		// BeanFactory--> from spring 3 , it is deprecated
		// ApplicationContext--> represent spring container
//		ApplicationContext context= new ClassPathXmlApplicationContext("com/live/config.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		Wheel wheel = context.getBean(Wheel.class);
//		Engine engine = context.getBean(Engine.class);
//		wheel.use();
//		System.out.println(wheel);
//		System.out.println(engine);
		Car car = context.getBean(Car.class);
		car.drive();
	}
}

