package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProductMain {

	public static void main(String[] args) {
		SpringApplication.run(ProductMain.class, args);
		System.out.println("Server started at port 8081");

	}

}
