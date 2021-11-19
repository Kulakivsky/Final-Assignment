package com.example.demo.datasource;

import com.example.demo.auth.ApplicationUserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationUserMapper implements RowMapper<ApplicationUserDto> {

    @Override
    public ApplicationUserDto mapRow(ResultSet resultSet, int i) throws SQLException {
        ApplicationUserDto applicationUser = new ApplicationUserDto();

        applicationUser.setId(resultSet.getInt("id"));
        applicationUser.setUsername(resultSet.getString("username"));
        applicationUser.setPassword(resultSet.getString("password"));
        applicationUser.setBalance_id(resultSet.getInt("balance_id"));

        return applicationUser;
    }
}
