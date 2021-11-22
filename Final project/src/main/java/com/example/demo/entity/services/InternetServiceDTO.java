package com.example.demo.entity.services;

import static com.example.demo.entity.services.ServiceType.INTERNET;

public class InternetServiceDTO implements Service {

    private int id;
    private ServiceType type = INTERNET;

    private String nameOfTariff;
    private int priceOfTariff;

    private int speedOfInternet;
    private int numberOfData;

    @Override
    public ServiceType getType() {
        return type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNameOfTariff() {
        return nameOfTariff;
    }

    @Override
    public int getPriceOfTariff() {
        return priceOfTariff;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameOfTariff(String nameOfTariff) {
        this.nameOfTariff = nameOfTariff;
    }

    public void setPriceOfTariff(int priceOfTariff) {
        this.priceOfTariff = priceOfTariff;
    }

    public int getSpeedOfInternet() {
        return speedOfInternet;
    }

    public void setSpeedOfInternet(int speedOfInternet) {
        this.speedOfInternet = speedOfInternet;
    }

    public int getNumberOfData() {
        return numberOfData;
    }

    public void setNumberOfData(int numberOfData) {
        this.numberOfData = numberOfData;
    }
}
