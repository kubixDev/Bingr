package com.kubixdev.bingr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kubixdev.bingr.repository")
public class BingrApplication {
	public static void main(String[] args) {
		SpringApplication.run(BingrApplication.class, args);
	}
}