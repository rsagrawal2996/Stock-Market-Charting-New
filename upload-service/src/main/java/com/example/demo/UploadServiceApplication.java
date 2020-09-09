package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.service.UploadService;
import com.example.demo.service.UploadServiceImpl;

@SpringBootApplication
public class UploadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadServiceApplication.class, args);
	}
	
//	@Bean
//	public UploadService getBean() {
//		return new UploadServiceImpl();
//	}

}
