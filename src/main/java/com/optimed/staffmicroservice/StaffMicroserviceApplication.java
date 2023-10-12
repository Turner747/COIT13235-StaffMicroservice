package com.optimed.staffmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.optimed.staffmicroservice.repository")
@EntityScan(basePackages = "com.optimed.staffmicroservice.model")
public class StaffMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaffMicroserviceApplication.class, args);
    }
}
