package com.polishop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan (basePackages="com.polishop, com.polishop.entities, com.polishop.repositories, com.polishop.services")
@EntityScan(basePackages = {"com.polishop.entities"}) 
@EnableJpaRepositories ("com.polishop.repositories")
public class PolishopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolishopApplication.class, args);
	}

}
