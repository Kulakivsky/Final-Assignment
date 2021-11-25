package com.example.demo.datasource;

import com.example.demo.entity.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CartDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    public void createCart(int id) {
        jdbcTemplate.update(
                "INSERT INTO cart (user_id, internet_service_id, phone_service_id, tv_service_id) VALUES(?,?,?,?)",
                id, 0, 0, 0);
    }


    public void updateInternetInCart(int id, int internetId) {
        jdbcTemplate.update("UPDATE cart SET internet_service_id=? WHERE user_id=?",
                internetId,
                id);
    }

    public void updatePhoneInCart(int id, int phoneId) {
        jdbcTemplate.update("UPDATE cart SET phone_service_id=? WHERE user_id=?",
                phoneId,
                id);
    }

    public void updateTvInCart(int id, int tvId) {
        jdbcTemplate.update("UPDATE cart SET tv_service_id=? WHERE user_id=?",
                tvId,
                id);
    }

    public void deleteCard(int id){
        jdbcTemplate.update("DELETE FROM cart WHERE user_id=?", id);
    }

    public CartDTO showCard(int id) {
        CartDTO cartDTO = new CartDTO();

        Map<String, Object> cardMap = jdbcTemplate.queryForMap("SELECT * FROM cart WHERE user_id=?", id);
        cartDTO.setUser_id(id);
        cartDTO.setInternet_service_id((Integer) cardMap.get("internet_service_id"));
        cartDTO.setPhone_service_id((Integer) cardMap.get("phone_service_id"));
        cartDTO.setTv_service_id((Integer) cardMap.get("tv_service_id"));
        return cartDTO;
    }
}
