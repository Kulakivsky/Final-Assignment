package com.example.demo.entity;

public class TvServiceDTO {

    private int tvService_id;

    private String nameOfTariff;
    private int priceOfTariff;

    private int numberOfChannels;


    public int getTvService_id() {
        return tvService_id;
    }

    public void setTvService_id(int tvService_id) {
        this.tvService_id = tvService_id;
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

    public int getNumberOfChannels() {
        return numberOfChannels;
    }

    public void setNumberOfChannels(int numberOfChannels) {
        this.numberOfChannels = numberOfChannels;
    }
}
