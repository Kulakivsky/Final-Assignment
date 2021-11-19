package com.example.demo.datasource;

import com.example.demo.auth.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Repository("daoService")
public class ApplicationUserDaoService implements ApplicationUserDao {

    private final JdbcTemplate jdbcTemplate;
    private List<ApplicationUser> applicationUsersList = new ArrayList<>();

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

    public void saveApplicationUser(ApplicationUser applicationUser) {
        jdbcTemplate.update("INSERT INTO ApplicationUser (username, password) VALUES(?,?)",
                applicationUser.getUsername(),
                applicationUser.getPassword());
    }

    public void updateApplicationUser(int id, ApplicationUser applicationUser) {
        jdbcTemplate.update("UPDATE ApplicationUser SET username=?, password=? WHERE id=?",
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                id);
    }

    public void deleteApplicationUser(int id){
        jdbcTemplate.update("DELETE FROM ApplicationUser WHERE id=?", id);
    }


    public List<ApplicationUser> getApplicationUsersList() {
        applicationUsersList.clear();

        String SQL = "SELECT * FROM ApplicationUser";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);

        while (sqlRowSet.next()) {
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setId(sqlRowSet.getInt("id"));
            applicationUser.setUsername(sqlRowSet.getString("username"));
            applicationUser.setPassword(sqlRowSet.getString("password"));

            applicationUsersList.add(applicationUser);
        }
        return applicationUsersList;
    }

    public ApplicationUser showApplicationUser(int id) {

        ApplicationUser applicationUser = jdbcTemplate.queryForObject("SELECT * FROM applicationUser WHERE id=?",
                                new Object[]{id},  new ApplicationUserMapper());
        return applicationUser;
    }
}
