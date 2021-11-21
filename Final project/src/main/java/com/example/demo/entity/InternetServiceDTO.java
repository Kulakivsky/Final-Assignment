package com.example.demo.entity;

public class InternetServiceDTO {

    private int internetService_id;

    private String nameOfTariff;
    private int priceOfTariff;

    private int speedOfInternet;
    private int numberOfData;


    public int getInternetService_id() {
        return internetService_id;
    }

    public void setInternetService_id(int internetService_id) {
        this.internetService_id = internetService_id;
    }

    public String getNameOfTariff() {
        return nameOfTariff;
    }

    public void setNameOfTariff(String nameOfTariff) {
        this.nameOfTariff = nameOfTariff;
    }

    public int getPriceOfTariff() {
        return priceOfTariff;
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
