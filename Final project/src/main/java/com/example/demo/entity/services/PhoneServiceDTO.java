package com.example.demo.entity.services;

import static com.example.demo.entity.services.ServiceType.INTERNET;
import static com.example.demo.entity.services.ServiceType.PHONE;

public class PhoneServiceDTO implements Service{

    private int id;
    private ServiceType type = PHONE;

    private String nameOfTariff;
    private int priceOfTariff;

    private int numberOfMinutes;
    private int numberOfData;

    @Override
    public ServiceType getType() {
        return type;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNameOfTariff() {
        return nameOfTariff;
    }

    public void setNameOfTariff(String nameOfTariff) {
        this.nameOfTariff = nameOfTariff;
    }

    @Override
    public int getPriceOfTariff() {
        return priceOfTariff;
    }

    public void setPriceOfTariff(int priceOfTariff) {
        this.priceOfTariff = priceOfTariff;
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public void setNumberOfMinutes(int numberOfMinutes) {
        this.numberOfMinutes = numberOfMinutes;
    }

    public int getNumberOfData() {
        return numberOfData;
    }

    public void setNumberOfData(int numberOfData) {
        this.numberOfData = numberOfData;
    }
}
