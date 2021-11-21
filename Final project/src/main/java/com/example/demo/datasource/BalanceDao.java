package com.example.demo.datasource;

import com.example.demo.entity.BalanceDto;
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
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("balance", balanceDto.getBalance());
        simpleJdbcInsert.executeAndReturnKey(parameters);

        return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
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
        BalanceDto balanceDto = new BalanceDto();
        Map<String, Object> balanceMap = jdbcTemplate.queryForMap("SELECT * FROM balance WHERE balance_id=?", id);
        balanceDto.setBalanceId(id);
        balanceDto.setBalance((Integer) balanceMap.get("balance"));

        return balanceDto;
    }
}
