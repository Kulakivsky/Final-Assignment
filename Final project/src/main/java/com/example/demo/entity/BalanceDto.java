package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

//@Entity
//@Table(name ="balance")
public class BalanceDto {


    private int balanceId;

    @Min(value = 0, message = "Balance cann`t be lower than 0")
    @Max(value = 2_147_483_647, message = "Balance is too high, please contact administration")
    private int balance = 0;


    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
