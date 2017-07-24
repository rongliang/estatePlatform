package com.kerry.estate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class EstateProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstateProviderApplication.class, args);
	}
}