package com.example.demo.Balance;

import com.example.demo.auth.ApplicationUserDto;
import com.example.demo.datasource.ApplicationUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BalanceDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private List<BalanceDto> balanceList = new ArrayList<>();

    @Autowired
    public BalanceDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("balance").usingGeneratedKeyColumns("balance_id");
    }

    public int createBalance() {
        BalanceDto balanceDto = new BalanceDto();
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("balance", balanceDto.getBalance());
        simpleJdbcInsert.executeAndReturnKey(parameters);

        return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
//        jdbcTemplate.update("INSERT INTO balance (balance) VALUES(?)",
//                balanceDto.getBalance());
//
//       return jdbcTemplate.queryForObject("SELECT last_insert_id() FROM balance", Integer.class);
    }

    public void updateBalance(int id, BalanceDto balanceDto) {
        jdbcTemplate.update("UPDATE balance SET balance=? WHERE balance_id=?",
                balanceDto.getBalance(),
                id);
    }

    public void deleteBalance(int id){
        jdbcTemplate.update("DELETE FROM balance WHERE balance_id=?", id);
    }


    public List<BalanceDto> getBalanceList() {
        balanceList.clear();

        String SQL = "SELECT * FROM balance";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);

        while (sqlRowSet.next()) {
            BalanceDto balanceDto = new BalanceDto();
            balanceDto.setBalanceId(sqlRowSet.getInt("balance_id"));
            balanceDto.setBalance(sqlRowSet.getInt("balance"));
            balanceList.add(balanceDto);
        }
        return balanceList;
    }

    public BalanceDto showBalance(int id) {

        BalanceDto balanceDto = jdbcTemplate.queryForObject("SELECT * FROM balance WHERE balance_id=?",
                new Object[]{id},  new BalanceMapper());
        return balanceDto;
    }


}
