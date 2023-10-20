package com.logisticcompany.logisticcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class LogisticcompanyApplication {
	public static void main(String[] args) {
		SpringApplication.run(LogisticcompanyApplication.class, args);
	}
}
