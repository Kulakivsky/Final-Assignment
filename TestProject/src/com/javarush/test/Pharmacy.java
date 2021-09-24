package com.javarush.test;

import java.util.List;

public class Pharmacy {

    public String name;
    public List medicine;

    public Pharmacy(String name, List medicine) {
        this.name = name;
        this.medicine = medicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getMedicine() {
        return medicine;
    }

    public void setMedicine(List medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return medicine.toString();
    }
}
