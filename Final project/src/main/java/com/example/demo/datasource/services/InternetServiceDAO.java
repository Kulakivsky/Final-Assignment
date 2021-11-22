package com.example.demo.datasource.services;

import com.example.demo.entity.services.InternetServiceDTO;
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
public class InternetServiceDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private List<InternetServiceDTO> internetServiceList = new ArrayList<>();

    @Autowired
    public InternetServiceDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("internet_service").usingGeneratedKeyColumns("internet_service_id");
    }

    public int createInternetService(InternetServiceDTO internetServiceDTO) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name_of_tariff", internetServiceDTO.getNameOfTariff());
        parameters.put("price_of_tariff", internetServiceDTO.getPriceOfTariff());
        parameters.put("speed_of_internet", internetServiceDTO.getSpeedOfInternet());
        parameters.put("number_of_data", internetServiceDTO.getNumberOfData());

        return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
    }

    public void updateInternetService(int id, InternetServiceDTO internetServiceDTO) {
        jdbcTemplate.update("UPDATE internet_service SET name_of_tariff=?, price_of_tariff=?," +
                        " speed_of_internet=?, number_of_data=?  WHERE internet_service_id=?",
                internetServiceDTO.getNameOfTariff(),
                internetServiceDTO.getPriceOfTariff(),
                internetServiceDTO.getSpeedOfInternet(),
                internetServiceDTO.getNumberOfData(),
                id);
    }

    public void deleteInternetService(int id){
        jdbcTemplate.update("DELETE FROM internet_service WHERE internet_service_id=?", id);
    }

    public List<InternetServiceDTO> getInternetServiceList() {
        internetServiceList.clear();

        String SQL = "SELECT * FROM internet_service";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);

        while (sqlRowSet.next()) {
            InternetServiceDTO internetServiceDTO = new InternetServiceDTO();
            internetServiceDTO.setId(sqlRowSet.getInt("internet_service_id"));
            internetServiceDTO.setNameOfTariff(sqlRowSet.getString("name_of_tariff"));
            internetServiceDTO.setPriceOfTariff(sqlRowSet.getInt("price_of_tariff"));
            internetServiceDTO.setSpeedOfInternet(sqlRowSet.getInt("speed_of_internet"));
            internetServiceDTO.setNumberOfData(sqlRowSet.getInt("number_of_data"));

            internetServiceList.add(internetServiceDTO);
        }
        return internetServiceList;
    }

    public InternetServiceDTO showInternetService(int id) {
        InternetServiceDTO internetServiceDTO = new InternetServiceDTO();

        Map<String, Object> internetServiceMap = jdbcTemplate.queryForMap("SELECT * FROM internet_service WHERE internet_service_id=?", id);
        internetServiceDTO.setNameOfTariff((String) internetServiceMap.get("name_of_tariff"));
        internetServiceDTO.setPriceOfTariff((Integer) internetServiceMap.get("price_of_tariff"));
        internetServiceDTO.setSpeedOfInternet((Integer) internetServiceMap.get("speed_of_internet"));
        internetServiceDTO.setNumberOfData((Integer) internetServiceMap.get("number_of_data"));

        return internetServiceDTO;
    }
}
