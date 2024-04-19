package com.oken.cars.okencars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class OkenCarsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(OkenCarsApplication.class, args);
		
		System.out.println("Connection pass success !");
	}
	
	@Bean
    public PasswordEncoder encoder() {
		
        return new BCryptPasswordEncoder();

    }
	

	
}
