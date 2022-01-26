package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name ="balance")
public class BalanceDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int balanceId;

    @Min(value = 0, message = "Balance cann`t be lower than 0")
    @Max(value = 2_147_483_647, message = "Balance is too high, please contact administration")
    @Column(name = "balance", nullable = false)
    private int balance = 0;


    public BalanceDto() {
    }

    public BalanceDto(int balanceId, int balance) {
        this.balanceId = balanceId;
        this.balance = balance;
    }

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

    @Override
    public String toString() {
        return "BalanceDto{" +
                "balanceId=" + balanceId +
                ", balance=" + balance +
                '}';
    }
}
