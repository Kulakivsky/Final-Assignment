package com.example.demo.datasource;

import com.example.demo.entity.PhoneServiceDTO;
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
public class PhoneServiceDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private List<PhoneServiceDTO> phoneServiceList = new ArrayList<>();

    @Autowired
    public PhoneServiceDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("phoneService").usingGeneratedKeyColumns("phoneService_id");
    }

    public int createPhoneService() {
        PhoneServiceDTO phoneServiceDTO = new PhoneServiceDTO();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nameOfTariff", phoneServiceDTO.getNameOfTariff());
        parameters.put("priceOfTariff", phoneServiceDTO.getPriceOfTariff());
        parameters.put("numberOfData", phoneServiceDTO.getNumberOfData());
        parameters.put("numberOfMinutes", phoneServiceDTO.getNumberOfMinutes());
        simpleJdbcInsert.executeAndReturnKeyHolder(parameters);

        return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
    }

    public void updatePhoneService(int id, PhoneServiceDTO phoneServiceDTO) {
        jdbcTemplate.update("UPDATE phoneService SET nameOfTariff=?, priceOfTariff=?," +
                        " numberOfData=?, numberOfMinutes=?  WHERE phoneService_id=?",
                phoneServiceDTO.getNameOfTariff(),
                phoneServiceDTO.getPriceOfTariff(),
                phoneServiceDTO.getNumberOfData(),
                phoneServiceDTO.getNumberOfMinutes(),
                id);
    }

    public void deletePhoneService(int id){
        jdbcTemplate.update("DELETE FROM phoneService WHERE phoneService_id=?", id);
    }

    public List<PhoneServiceDTO> getPhoneServiceList() {
        phoneServiceList.clear();

        String SQL = "SELECT * FROM phoneService";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);

        while (sqlRowSet.next()) {
            PhoneServiceDTO phoneServiceDTO = new PhoneServiceDTO();
            phoneServiceDTO.setPhoneService_id(sqlRowSet.getInt("phoneService_id"));
            phoneServiceDTO.setNameOfTariff(sqlRowSet.getString("nameOfTariff"));
            phoneServiceDTO.setPriceOfTariff(sqlRowSet.getInt("priceOfTariff"));
            phoneServiceDTO.setNumberOfData(sqlRowSet.getInt("numberOfData"));
            phoneServiceDTO.setNumberOfMinutes(sqlRowSet.getInt("numberOfMinutes"));
            phoneServiceList.add(phoneServiceDTO);
        }
        return phoneServiceList;
    }


    public PhoneServiceDTO showPhoneService(int id) {
        PhoneServiceDTO phoneServiceDTO = new PhoneServiceDTO();

        Map<String, Object> phoneServiceMap = jdbcTemplate.queryForMap("SELECT * FROM phoneService WHERE phoneService_id=?", id);
        phoneServiceDTO.setPhoneService_id(id);
        phoneServiceDTO.setNameOfTariff((String) phoneServiceMap.get("nameOfTariff"));
        phoneServiceDTO.setPriceOfTariff((Integer) phoneServiceMap.get("priceOfTariff"));
        phoneServiceDTO.setNumberOfData((Integer) phoneServiceMap.get("numberOfData"));
        phoneServiceDTO.setNumberOfMinutes((Integer) phoneServiceMap.get("numberOfMinutes"));

        return phoneServiceDTO;
    }
}
