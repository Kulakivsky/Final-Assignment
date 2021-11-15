package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

//TODO: create db connection
@Component
@Repository("daoService")
public class ApplicationUserDaoService implements ApplicationUserDao {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private List<ApplicationUser> applicationUsersList = new ArrayList<>();

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder,
                                     JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsersList()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    public void save(ApplicationUser applicationUser) {



        applicationUsersList.add(applicationUser);
    }

    public List<ApplicationUser> getApplicationUsersList() {
        applicationUsersList = Lists.newArrayList(
                new ApplicationUser(
                        "annasmith",
                        passwordEncoder.encode("password")),
                new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("password"))
        );
        return applicationUsersList;
    }

    public ApplicationUser show(int id) {
        return applicationUsersList.stream().filter(applicationUser -> applicationUser.getId() == id).findAny().orElse(null);
    }
}
