package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.happy.dao"})
public class HappyHouseFinalTeam9Application {
	public static void main(String[] args) {
		SpringApplication.run(HappyHouseFinalTeam9Application.class, args);
	}
	//test
}
