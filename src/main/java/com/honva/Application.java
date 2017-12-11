package com.honva;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.honva.common.MyProperty;

@SpringBootApplication
@EnableConfigurationProperties(MyProperty.class)
@MapperScan("com.honva.mapper")
public class Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
