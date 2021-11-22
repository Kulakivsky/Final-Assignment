package com.example.demo.entity.services;

public interface Service {

    ServiceType getType();

    int getId();

    String getNameOfTariff();

    int getPriceOfTariff();

}
