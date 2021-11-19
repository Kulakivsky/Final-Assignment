package com.example.demo.datasource;

import com.example.demo.auth.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BalanceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void editBalance(int id, ApplicationUser applicationUser) {

            jdbcTemplate.update("UPDATE ApplicationUser SET balance=? WHERE id=?",
                    applicationUser.getBalance(),
                    id);
        }
}
