package com.example.demo;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDistributedTransaction
public class ProvinceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvinceApplication.class, args);
	}

}
