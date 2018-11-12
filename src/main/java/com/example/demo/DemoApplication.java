package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	String name="{\"name\":\"zhansan\",\"age\":\"100\",\"address\":\"xian city\",\"sex\":\"man\"}";
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
