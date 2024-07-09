package com.live.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	// tight coupling
	// field injection
//	@Autowired
	private Wheel wheel;

	private Engine engine;

//	@Autowired
//	//Constructor injection
//	public Car(Wheel wheel) {
//		super();
//		this.wheel = wheel;
//	}

	public Wheel getWheel() {
		return wheel;
	}

	public Car(Wheel wheel, Engine engine) {
		super();
		this.wheel = wheel;
		this.engine = engine;
	}

//
//	@Autowired
//	public void setWheel(Wheel wheel) {
//		System.out.println("Setting wheel");
//		this.wheel = wheel;
//	}

	public void drive() {
		System.out.println("car is  driving");
		wheel.use();
		engine.use();
	}
}
