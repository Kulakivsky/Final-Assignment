package com.example.demo.datasource;

import com.example.demo.entity.ApplicationUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
@Repository("daoService")
public class ApplicationUserDaoService implements ApplicationUserDao {

    private BalanceDao balanceDao;
    private final JdbcTemplate jdbcTemplate;
    private List<ApplicationUserDto> applicationUsersList = new ArrayList<>();

    @Autowired
    public ApplicationUserDaoService(JdbcTemplate jdbcTemplate, BalanceDao balanceDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.balanceDao = balanceDao;
    }

    @Override
    public Optional<ApplicationUserDto> selectApplicationUserByUsername(String username) {
        return getApplicationUsersList()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    public void saveApplicationUser(ApplicationUserDto applicationUser) {
        jdbcTemplate.update("INSERT INTO ApplicationUser (username, password, balance_id) VALUES(?,?,?)",
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                balanceDao.createBalance());
    }

    public void updateApplicationUser(int id, ApplicationUserDto applicationUser) {
        jdbcTemplate.update("UPDATE ApplicationUser SET username=?, password=? WHERE id=?",
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                id);
    }

    public void deleteApplicationUser(int id){
        balanceDao.deleteBalance(showApplicationUser(id).getBalance_id());
        jdbcTemplate.update("DELETE FROM ApplicationUser WHERE id=?", id);
    }


    public List<ApplicationUserDto> getApplicationUsersList() {
        applicationUsersList.clear();

        String SQL = "SELECT * FROM ApplicationUser";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);

        while (sqlRowSet.next()) {
            ApplicationUserDto applicationUser = new ApplicationUserDto();
            applicationUser.setId(sqlRowSet.getInt("id"));
            applicationUser.setUsername(sqlRowSet.getString("username"));
            applicationUser.setPassword(sqlRowSet.getString("password"));
            applicationUser.setBalance_id(sqlRowSet.getInt("balance_id"));
            applicationUsersList.add(applicationUser);
        }
        return applicationUsersList;
    }

    public ApplicationUserDto showApplicationUser(int id) {
        ApplicationUserDto applicationUserDto = new ApplicationUserDto();

        Map<String, Object> applicationUserMap = jdbcTemplate.queryForMap("SELECT * FROM applicationuser WHERE id=?", id);

        applicationUserDto.setId(id);
        applicationUserDto.setUsername((String) applicationUserMap.get("username"));
        applicationUserDto.setPassword((String) applicationUserMap.get("password"));
        applicationUserDto.setBalance_id((Integer) applicationUserMap.get("balance_id"));
        return applicationUserDto;
    }
}
