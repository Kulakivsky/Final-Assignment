package com.example.demo.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

public class PostgresDataSource implements CommandLineRunner {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresDataSource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO students (name, email) VALUES ("
                + " 'Nam Ha Minh', 'nam@codejava.net')";

        int rows = jdbcTemplate.update(sql);
        if(rows>0){
            System.out.println("This shit insert something");
        }
    }
}
