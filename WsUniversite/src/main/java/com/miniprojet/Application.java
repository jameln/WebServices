package com.miniprojet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//@EnableEurekaServer


@SpringBootApplication
@EnableEurekaClient
@RestController


public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
