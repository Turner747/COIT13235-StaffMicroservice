package com.optimed.StaffMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.optimed.StaffMicroservice.repository")
@EntityScan(basePackages = "com.optimed.StaffMicroservice.model")
//@ComponentScan(basePackages = "com.optimed.StaffMicroservice.service")
//@ConfigurationPropertiesScan(basePackages = "com.optimed.StaffMicroservice.config")
public class StaffMicroserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(StaffMicroserviceApplication.class, args);
	}

}
