package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableWebSecurity
public class DemoApplication {
	public static ConfigurableApplicationContext context;
	public static void main(String[] args) {
		context = SpringApplication.run(DemoApplication.class, args);

	}

}
