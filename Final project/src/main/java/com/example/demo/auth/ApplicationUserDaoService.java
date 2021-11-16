package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    private List<ApplicationUser> applicationUsersList = new ArrayList<>();

//    TODO: Delet this filed later;
    private boolean listHasBeenCreated;

    @Autowired
    public ApplicationUserDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    public void update(int id, ApplicationUser applicationUser) {

        ApplicationUser applicationUserToBeUpdated = show(id);
        applicationUserToBeUpdated.setId(applicationUser.getId());
        applicationUserToBeUpdated.setUsername(applicationUser.getUsername());
    }

    public void delete(int id){
        applicationUsersList.removeIf(applicationUser -> applicationUser.getId() == id);
    }


//    TODO: delete later
    public void creatFewPeople(){
        applicationUsersList = Lists.newArrayList(
                new ApplicationUser(
                        1,
                        "annasmith",
                        "password"),
                new ApplicationUser(
                        2,
                        "linda",
                        "password")
        );
    }

    public List<ApplicationUser> getApplicationUsersList() {
        if(listHasBeenCreated != true){
            creatFewPeople();
            listHasBeenCreated = true;
        }

        return applicationUsersList;
    }

    public ApplicationUser show(int id) {
        return applicationUsersList.stream().filter(applicationUser -> applicationUser.getId() == id).findAny().orElse(null);
    }
}
