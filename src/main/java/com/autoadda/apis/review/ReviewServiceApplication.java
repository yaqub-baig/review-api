package com.autoadda.apis.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableFeignClients(basePackages = "com.autoadda.apis.review.client")
public class ReviewServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run( ReviewServiceApplication.class, args);
	}
}