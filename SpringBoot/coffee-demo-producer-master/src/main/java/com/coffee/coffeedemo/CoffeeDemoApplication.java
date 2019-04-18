package com.coffee.coffeedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.coffee")
public class CoffeeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeDemoApplication.class, args);
	}

}
