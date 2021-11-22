package com.example.demo.entity;

import com.example.demo.datasource.services.TvServiceDAO;
import com.example.demo.entity.services.InternetServiceDTO;
import com.example.demo.entity.services.PhoneServiceDTO;
import com.example.demo.entity.services.TvServiceDTO;

public class CardDTO {

    public ApplicationUserDto applicationUserDto;
    public InternetServiceDTO internetServiceDTO;
    public PhoneServiceDTO phoneServiceDTO;
    public TvServiceDTO tvServiceDTO;
    public int sum;

    public ApplicationUserDto getApplicationUserDto() {
        return applicationUserDto;
    }

    public void setApplicationUserDto(ApplicationUserDto applicationUserDto) {
        this.applicationUserDto = applicationUserDto;
    }

    public InternetServiceDTO getInternetServiceDTO() {
        return internetServiceDTO;
    }

    public void setInternetServiceDTO(InternetServiceDTO internetServiceDTO) {
        this.internetServiceDTO = internetServiceDTO;
    }

    public PhoneServiceDTO getPhoneServiceDTO() {
        return phoneServiceDTO;
    }

    public void setPhoneServiceDTO(PhoneServiceDTO phoneServiceDTO) {
        this.phoneServiceDTO = phoneServiceDTO;
    }

    public TvServiceDTO getTvServiceDTO() {
        return tvServiceDTO;
    }

    public void setTvServiceDTO(TvServiceDTO tvServiceDTO) {
        this.tvServiceDTO = tvServiceDTO;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
