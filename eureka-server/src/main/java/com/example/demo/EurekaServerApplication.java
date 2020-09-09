package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.CrossOrigin;

//import de.codecentric.boot.admin.server.config.EnableAdminServer;


//@EnableAdminServer
@SpringBootApplication
@EnableEurekaServer
@CrossOrigin(origins = "http://localhost:8761")
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
