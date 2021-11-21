package com.example.demo.datasource;

import com.example.demo.entity.TvServiceDTO;
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
                    .withTableName("tvService").usingGeneratedKeyColumns("tvService_id");
        }

        public int createTvService() {
            TvServiceDTO tvServiceDTO = new TvServiceDTO();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nameOfTariff", tvServiceDTO.getNameOfTariff());
            parameters.put("priceOfTariff", tvServiceDTO.getPriceOfTariff());
            parameters.put("numberOfChannels", tvServiceDTO.getNumberOfChannels());

            simpleJdbcInsert.executeAndReturnKeyHolder(parameters);

            return (int) simpleJdbcInsert.executeAndReturnKey(parameters);
        }

        public void updateTvService(int id, TvServiceDTO tvServiceDTO) {
            jdbcTemplate.update("UPDATE tvService SET nameOfTariff=?, priceOfTariff=?," +
                            " numberOfChannels=? WHERE tvService_id=?",
                    tvServiceDTO.getNameOfTariff(),
                    tvServiceDTO.getPriceOfTariff(),
                    tvServiceDTO.getNumberOfChannels(),
                    id);
        }

        public void deleteTvService(int id){
            jdbcTemplate.update("DELETE FROM tvService WHERE tvService_id=?", id);
        }

        public List<TvServiceDTO> getTvServiceList() {
            tvServiceList.clear();

            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM tvService");

            while (sqlRowSet.next()) {
                TvServiceDTO tvServiceDTO = new TvServiceDTO();
                tvServiceDTO.setTvService_id(sqlRowSet.getInt("tvService_id"));
                tvServiceDTO.setNameOfTariff(sqlRowSet.getString("nameOfTariff"));
                tvServiceDTO.setPriceOfTariff(sqlRowSet.getInt("priceOfTariff"));
                tvServiceDTO.setNumberOfChannels(sqlRowSet.getInt("numberOfChannels"));
                tvServiceList.add(tvServiceDTO);
            }
            return tvServiceList;
        }


        public TvServiceDTO showTvService(int id) {
            TvServiceDTO tvServiceDTO = new TvServiceDTO();

            Map<String, Object> tvServiceMap = jdbcTemplate.queryForMap("SELECT * FROM tvService WHERE tvService_id=?", id);
            tvServiceDTO.setTvService_id(id);
            tvServiceDTO.setNameOfTariff((String) tvServiceMap.get("nameOfTariff"));
            tvServiceDTO.setPriceOfTariff((Integer) tvServiceMap.get("priceOfTariff"));
            tvServiceDTO.setNumberOfChannels((Integer) tvServiceMap.get("numberOfChannels"));

            return tvServiceDTO;
        }
}
