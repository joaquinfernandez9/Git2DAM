package com.example.springcosas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//(scanBasePackages = {"com.example.springcosas.data", "com.example.springcosas.domain", "com.example.springcosas.spring", "com.example.springcosas.util" })
@SpringBootApplication
public class SpringRestMavenJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestMavenJavaApplication.class, args);
	}

}
