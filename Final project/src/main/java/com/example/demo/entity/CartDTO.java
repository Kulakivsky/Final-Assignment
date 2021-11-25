package com.example.demo.entity;

import com.example.demo.datasource.services.TvServiceDAO;
import com.example.demo.entity.services.InternetServiceDTO;
import com.example.demo.entity.services.PhoneServiceDTO;
import com.example.demo.entity.services.TvServiceDTO;

public class CartDTO {

    private int user_id;
    private int internet_service_id;
    private int phone_service_id;
    private int tv_service_id;

    public CartDTO() {}

    public CartDTO(int user_id, int internet_service_id, int phone_service_id, int tv_service_id) {
        this.user_id = user_id;
        this.internet_service_id = internet_service_id;
        this.phone_service_id = phone_service_id;
        this.tv_service_id = tv_service_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getInternet_service_id() {
        return internet_service_id;
    }

    public void setInternet_service_id(int internet_service_id) {
        this.internet_service_id = internet_service_id;
    }

    public int getPhone_service_id() {
        return phone_service_id;
    }

    public void setPhone_service_id(int phone_service_id) {
        this.phone_service_id = phone_service_id;
    }

    public int getTv_service_id() {
        return tv_service_id;
    }

    public void setTv_service_id(int tv_service_id) {
        this.tv_service_id = tv_service_id;
    }

}
