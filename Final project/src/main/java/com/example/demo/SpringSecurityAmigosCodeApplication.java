package com.example.demo;

import com.example.demo.datasource.PostgresDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringSecurityAmigosCodeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAmigosCodeApplication.class, args);
	}
}

