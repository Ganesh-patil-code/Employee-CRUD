package com.soprasteria.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.soprasteria.repo")
@EntityScan("com.soprasteria.model")
@SpringBootApplication(scanBasePackages = "com.soprasteria")
public class SpringBootCrudApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
		}

}
