package com.example.demo;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagerServer
public class TmApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmApplication.class, args);
	}

}
