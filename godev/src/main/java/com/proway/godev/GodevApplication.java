package com.proway.godev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.proway.godev.entities"})
public class GodevApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodevApplication.class, args);
	}

}
