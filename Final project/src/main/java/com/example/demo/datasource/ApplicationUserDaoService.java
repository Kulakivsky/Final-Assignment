package com.example.demo.datasource;

import com.example.demo.entity.ApplicationUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Component
@Repository("daoService")
public class ApplicationUserDaoService implements ApplicationUserDao {

    private BalanceDao balanceDao;
    private CartDAO cardDAO;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private List<ApplicationUserDto> applicationUsersList = new ArrayList<>();

    @Autowired
    public ApplicationUserDaoService(JdbcTemplate jdbcTemplate, DataSource dataSource,
                                     BalanceDao balanceDao, CartDAO cartDAO, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
        this.balanceDao = balanceDao;
        this.cardDAO = cartDAO;
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("applicationuser").usingGeneratedKeyColumns("id");
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

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", applicationUser.getUsername());
        parameters.put("password", passwordEncoder.encode(applicationUser.getPassword()));
        parameters.put("balance_id", balanceDao.createBalance());
        parameters.put("internet_service_id", applicationUser.getInternetServiceId());
        parameters.put("phone_service_id", applicationUser.getPhoneServiceId());
        parameters.put("tv_service_id", applicationUser.getTvServiceId());

        cardDAO.createCart((int) simpleJdbcInsert.executeAndReturnKey(parameters));
    }

    public void updateUser(int id, ApplicationUserDto applicationUser) {
        jdbcTemplate.update("UPDATE applicationUser SET internet_service_id=?, phone_service_id=?, tv_service_id=? WHERE id=?",
                applicationUser.getInternetServiceId(),
                applicationUser.getPhoneServiceId(),
                applicationUser.getTvServiceId(),
                id);
    }

    public void updateApplicationUser(int id, ApplicationUserDto applicationUser) {
        jdbcTemplate.update("UPDATE applicationUser SET username=?, password=? WHERE id=?",
                applicationUser.getUsername(),
                passwordEncoder.encode(applicationUser.getPassword()),
                id);
    }

    public void deleteApplicationUser(int id){
        balanceDao.deleteBalance(showApplicationUser(id).getBalanceId());
        cardDAO.deleteCard(id);
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

    public ApplicationUserDto findUserByPasswordAndUsername(String username, String password) {
        ApplicationUserDto applicationUserDto = new ApplicationUserDto();
        Map<String, Object> applicationUserMap = jdbcTemplate.queryForMap("SELECT * FROM applicationuser WHERE password=?", password);
        applicationUserDto.setId((Integer) applicationUserMap.get("id"));
        applicationUserDto.setUsername(username);
        applicationUserDto.setPassword(password);
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
