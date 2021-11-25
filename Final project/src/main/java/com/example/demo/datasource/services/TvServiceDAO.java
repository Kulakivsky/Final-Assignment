package com.example.demo.datasource.services;

import com.example.demo.entity.services.TvServiceDTO;
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
public class TvServiceDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private List<TvServiceDTO> tvServiceList = new ArrayList<>();

    @Autowired
    public TvServiceDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tv_service").usingGeneratedKeyColumns("tv_service_id");
    }

    public int createTvService(TvServiceDTO tvServiceDTO) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name_of_tariff", tvServiceDTO.getNameOfTariff());
        parameters.put("price_of_tariff", tvServiceDTO.getPriceOfTariff());
        parameters.put("number_of_channels", tvServiceDTO.getNumberOfChannels());

        return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
    }

    public void updateTvService(int id, TvServiceDTO tvServiceDTO) {
        jdbcTemplate.update("UPDATE tv_service SET name_of_tariff=?, price_of_tariff=?," +
                        " number_of_channels=? WHERE tv_service_id=?",
                tvServiceDTO.getNameOfTariff(),
                tvServiceDTO.getPriceOfTariff(),
                tvServiceDTO.getNumberOfChannels(),
                id);
    }

    public void deleteTvService(int id) {
        jdbcTemplate.update("DELETE FROM tv_service WHERE tv_service_id=?", id);
    }


    /**
     * This method won't return service with id=0, as it is service one;
     */
    public List<TvServiceDTO> getTvServiceList() {
        tvServiceList.clear();

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM tv_service");

        while (sqlRowSet.next()) {
            TvServiceDTO tvServiceDTO = new TvServiceDTO();
            tvServiceDTO.setId(sqlRowSet.getInt("tv_service_id"));
            tvServiceDTO.setNameOfTariff(sqlRowSet.getString("name_of_tariff"));
            tvServiceDTO.setPriceOfTariff(sqlRowSet.getInt("price_of_tariff"));
            tvServiceDTO.setNumberOfChannels(sqlRowSet.getInt("number_of_channels"));
            tvServiceList.add(tvServiceDTO);
        }
        tvServiceList.remove(0);
        return tvServiceList;
    }


    public TvServiceDTO showTvService(int id) {
        TvServiceDTO tvServiceDTO = new TvServiceDTO();

        Map<String, Object> tvServiceMap = jdbcTemplate.queryForMap("SELECT * FROM tv_service WHERE tv_service_id=?", id);
        tvServiceDTO.setId(id);
        tvServiceDTO.setNameOfTariff((String) tvServiceMap.get("name_of_tariff"));
        tvServiceDTO.setPriceOfTariff((Integer) tvServiceMap.get("price_of_tariff"));
        tvServiceDTO.setNumberOfChannels((Integer) tvServiceMap.get("number_of_channels"));

        return tvServiceDTO;
    }
}
