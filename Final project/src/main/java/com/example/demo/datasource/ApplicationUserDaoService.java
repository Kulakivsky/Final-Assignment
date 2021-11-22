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

    /**
     *
     * @param applicationUser
     * balanceDao.createBalance() auto create id for user and return int of id
     */
    public void createApplicationUser(ApplicationUserDto applicationUser) {
        jdbcTemplate.update(
                "INSERT INTO ApplicationUser (username, password, balance_id, internet_service_id, phone_service_id, tv_service_id) " +
                        "VALUES(?,?,?,?,?,?)",
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                balanceDao.createBalance(),
                applicationUser.getInternetServiceId(),
                applicationUser.getPhoneServiceId(),
                applicationUser.getTvServiceId());
    }

    public void updateApplicationUser(int id, ApplicationUserDto applicationUser) {
        jdbcTemplate.update("UPDATE applicationUser SET username=?, password=? WHERE id=?",
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                id);
    }

    public void deleteApplicationUser(int id){
        balanceDao.deleteBalance(showApplicationUser(id).getBalanceId());
        jdbcTemplate.update("DELETE FROM applicationUser WHERE id=?", id);
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
            applicationUser.setBalanceId(sqlRowSet.getInt("balance_id"));
            applicationUser.setInternetServiceId(sqlRowSet.getInt("internet_service_id"));
            applicationUser.setPhoneServiceId(sqlRowSet.getInt("phone_service_id"));
            applicationUser.setTvServiceId(sqlRowSet.getInt("tv_service_id"));
//            ApplicationUserDto applicationUser = new ApplicationUserDto(
//                    sqlRowSet.getInt("id"),
//                    sqlRowSet.getString("username"),
//                    sqlRowSet.getString("password"),
//                    sqlRowSet.getInt("balance_id"),
//                    sqlRowSet.getInt("internet_service_id"),
//                    sqlRowSet.getInt("phone_service_id"),
//                    sqlRowSet.getInt("tv_service_id"));

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
        applicationUserDto.setBalanceId((Integer) applicationUserMap.get("balance_id"));
        applicationUserDto.setInternetServiceId((Integer) applicationUserMap.get("internet_service_id"));
        applicationUserDto.setPhoneServiceId((Integer) applicationUserMap.get("internet_service_id"));
        applicationUserDto.setTvServiceId((Integer) applicationUserMap.get("tv_service_id"));

//        ApplicationUserDto applicationUserDto = new ApplicationUserDto(
//                id, (String) applicationUserMap.get("username"),
//                (String) applicationUserMap.get("password"),
//                (Integer) applicationUserMap.get("balance_id"),
//                (Integer) applicationUserMap.get("internet_service_id"),
//                (Integer) applicationUserMap.get("phone_service_id"),
//                (Integer) applicationUserMap.get("tv_service_id"));
        return applicationUserDto;
    }
}
