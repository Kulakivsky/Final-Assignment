package com.example.demo.entity.services;

import static com.example.demo.entity.services.ServiceType.INTERNET;
import static com.example.demo.entity.services.ServiceType.TV;

public class TvServiceDTO implements Service {

    private int id;
    private ServiceType type = TV;

    private String nameOfTariff;
    private int priceOfTariff;

    private int numberOfChannels;

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

    public int getNumberOfChannels() {
        return numberOfChannels;
    }

    public void setNumberOfChannels(int numberOfChannels) {
        this.numberOfChannels = numberOfChannels;
    }
}
