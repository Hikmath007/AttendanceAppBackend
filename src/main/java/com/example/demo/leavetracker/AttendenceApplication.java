package com.example.demo.leavetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.demo.leavetracker.mapper","com.example.demo.leavetracker.controller","com.example.demo.leavetracker.service"})
@EnableJpaRepositories(basePackages = {"com.example.demo.leavetracker.repository"})
@EntityScan(basePackages = {"com.example.demo.leavetracker.entity"})
public class AttendenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendenceApplication.class, args);
	}

}
