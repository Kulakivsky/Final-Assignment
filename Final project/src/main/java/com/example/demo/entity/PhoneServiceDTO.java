package com.example.demo.entity;

public class PhoneServiceDTO {

    private int phoneService_id;

    private String nameOfTariff;
    private int priceOfTariff;

    private int numberOfMinutes;
    private int numberOfData;


    public int getPhoneService_id() {
        return phoneService_id;
    }

    public void setPhoneService_id(int phoneService_id) {
        this.phoneService_id = phoneService_id;
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
