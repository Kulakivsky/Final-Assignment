package com.example.demo.datasource.services;

import com.example.demo.entity.services.PhoneServiceDTO;
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
                .withTableName("phone_service").usingGeneratedKeyColumns("phone_service_id");
    }

    public int createPhoneService(PhoneServiceDTO phoneServiceDTO) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name_of_tariff", phoneServiceDTO.getNameOfTariff());
        parameters.put("price_of_tariff", phoneServiceDTO.getPriceOfTariff());
        parameters.put("number_of_data", phoneServiceDTO.getNumberOfData());
        parameters.put("number_of_minutes", phoneServiceDTO.getNumberOfMinutes());

        return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
    }

    public void updatePhoneService(int id, PhoneServiceDTO phoneServiceDTO) {
        jdbcTemplate.update("UPDATE phone_service SET name_of_tariff=?, price_of_tariff=?," +
                        " number_of_data=?, number_of_minutes=?  WHERE phone_service_id=?",
                phoneServiceDTO.getNameOfTariff(),
                phoneServiceDTO.getPriceOfTariff(),
                phoneServiceDTO.getNumberOfData(),
                phoneServiceDTO.getNumberOfMinutes(),
                id);
    }

    public void deletePhoneService(int id){
        jdbcTemplate.update("DELETE FROM phone_service WHERE phone_service_id=?", id);
    }

    public List<PhoneServiceDTO> getPhoneServiceList() {
        phoneServiceList.clear();

        String SQL = "SELECT * FROM phone_service";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);

        while (sqlRowSet.next()) {
            PhoneServiceDTO phoneServiceDTO = new PhoneServiceDTO();
            phoneServiceDTO.setId(sqlRowSet.getInt("phone_service_id"));
            phoneServiceDTO.setNameOfTariff(sqlRowSet.getString("name_of_tariff"));
            phoneServiceDTO.setPriceOfTariff(sqlRowSet.getInt("price_of_tariff"));
            phoneServiceDTO.setNumberOfData(sqlRowSet.getInt("number_of_data"));
            phoneServiceDTO.setNumberOfMinutes(sqlRowSet.getInt("number_of_minutes"));
            phoneServiceList.add(phoneServiceDTO);
        }
        return phoneServiceList;
    }


    public PhoneServiceDTO showPhoneService(int id) {
        PhoneServiceDTO phoneServiceDTO = new PhoneServiceDTO();

        Map<String, Object> phoneServiceMap = jdbcTemplate.queryForMap("SELECT * FROM phone_service WHERE phone_service_id=?", id);
        phoneServiceDTO.setId(id);
        phoneServiceDTO.setNameOfTariff((String) phoneServiceMap.get("name_of_tariff"));
        phoneServiceDTO.setPriceOfTariff((Integer) phoneServiceMap.get("price_of_tariff"));
        phoneServiceDTO.setNumberOfData((Integer) phoneServiceMap.get("number_of_data"));
        phoneServiceDTO.setNumberOfMinutes((Integer) phoneServiceMap.get("number_of_minutes"));

        return phoneServiceDTO;
    }
}
