package com.example.demo.datasource;

import com.example.demo.auth.ApplicationUserDto;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUserDto> selectApplicationUserByUsername(String username);

}
