package com.example.Employee;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
		Logger logger = LoggerFactory.getLogger(EmployeeApplication.class);
		logger.debug("Hello World");
		
		
	}

}
