package com.example.demo.datasource;

import com.example.demo.entity.InternetServiceDTO;
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
                .withTableName("internetService").usingGeneratedKeyColumns("internetService_id");
    }

    public int createInternetService() {
        InternetServiceDTO internetServiceDTO = new InternetServiceDTO();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nameOfTariff", internetServiceDTO.getNameOfTariff());
        parameters.put("priceOfTariff", internetServiceDTO.getPriceOfTariff());
        parameters.put("speedOfInternet", internetServiceDTO.getSpeedOfInternet());
        parameters.put("numberOfData", internetServiceDTO.getNumberOfData());
        simpleJdbcInsert.executeAndReturnKeyHolder(parameters);

        return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
    }

    public void updateInternetService(int id, InternetServiceDTO internetServiceDTO) {
        jdbcTemplate.update("UPDATE internetService SET nameOfTariff=?, priceOfTariff=?," +
                        " speedOfInternet=?, numberOfData=?  WHERE internetService_id=?",
                internetServiceDTO.getNameOfTariff(),
                internetServiceDTO.getPriceOfTariff(),
                internetServiceDTO.getSpeedOfInternet(),
                internetServiceDTO.getNumberOfData(),
                id);
    }

    public void deleteInternetService(int id){
        jdbcTemplate.update("DELETE FROM internetService WHERE internetService_id=?", id);
    }

    public List<InternetServiceDTO> getInternetServiceList() {
        internetServiceList.clear();

        String SQL = "SELECT * FROM internetService";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(SQL);

        while (sqlRowSet.next()) {
            InternetServiceDTO internetServiceDTO = new InternetServiceDTO();
            internetServiceDTO.setInternetService_id(sqlRowSet.getInt("internetService_id"));
            internetServiceDTO.setNameOfTariff(sqlRowSet.getString("nameOfTariff"));
            internetServiceDTO.setPriceOfTariff(sqlRowSet.getInt("priceOfTariff"));
            internetServiceDTO.setSpeedOfInternet(sqlRowSet.getInt("speedOfInternet"));
            internetServiceDTO.setNumberOfData(sqlRowSet.getInt("numberOfData"));

            internetServiceList.add(internetServiceDTO);
        }
        return internetServiceList;
    }

    public InternetServiceDTO showInternetService(int id) {
        InternetServiceDTO internetServiceDTO = new InternetServiceDTO();

        Map<String, Object> internetServiceMap = jdbcTemplate.queryForMap("SELECT * FROM internetService WHERE internetService_id=?", id);
        internetServiceDTO.setNameOfTariff((String) internetServiceMap.get("nameOfTariff"));
        internetServiceDTO.setPriceOfTariff((Integer) internetServiceMap.get("priceOfTariff"));
        internetServiceDTO.setSpeedOfInternet((Integer) internetServiceMap.get("speedOfInternet"));
        internetServiceDTO.setNumberOfData((Integer) internetServiceMap.get("numberOfData"));

        return internetServiceDTO;
    }
}
