package com.upgrad.customeronboardingservicemaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomerOnboardingServiceMasterApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerOnboardingServiceMasterApplication.class, args);
	}
}
