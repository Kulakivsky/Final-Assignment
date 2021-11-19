package com.example.demo.datasource;

import com.example.demo.auth.ApplicationUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationUserMapper implements RowMapper<ApplicationUser> {

    @Override
    public ApplicationUser mapRow(ResultSet resultSet, int i) throws SQLException {
        ApplicationUser applicationUser = new ApplicationUser();

        applicationUser.setId(resultSet.getInt("id"));
        applicationUser.setUsername(resultSet.getString("username"));
        applicationUser.setPassword(resultSet.getString("password"));
        applicationUser.setBalance(resultSet.getInt("balance"));

        return applicationUser;
    }
}
