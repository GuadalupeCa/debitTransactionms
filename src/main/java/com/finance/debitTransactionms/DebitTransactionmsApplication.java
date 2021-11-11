package com.finance.debitTransactionms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DebitTransactionmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebitTransactionmsApplication.class, args);
	}

}
