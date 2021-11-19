package com.example.demo.Balance;
import com.example.demo.auth.ApplicationUserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceMapper implements RowMapper<BalanceDto>{

    @Override
    public BalanceDto mapRow(ResultSet resultSet, int i) throws SQLException {
        BalanceDto balanceDto = new BalanceDto();

        balanceDto.setBalanceId(resultSet.getInt("balance_id"));
        balanceDto.setBalance(resultSet.getInt("balance"));

        return balanceDto;
    }
}
