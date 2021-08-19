package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringSecurityAmigosCodeApplication implements CommandLineRunner {

	public SpringSecurityAmigosCodeApplication() {}

	public SpringSecurityAmigosCodeApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAmigosCodeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "INSERT INTO students (name, email) VALUES ("
				+ " 'Nam Ha Minh', 'nam@codejava.net')";

			int rows = jdbcTemplate.update(sql);

	}
}

