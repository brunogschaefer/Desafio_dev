package com.proway.godev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.proway.godev")
public class GodevApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodevApplication.class, args);
	}

}
