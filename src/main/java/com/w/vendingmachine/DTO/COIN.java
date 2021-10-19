/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.w.vendingmachine.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author William
 */
public enum COIN {
    TENDOLLARS(new BigDecimal("10.0")),
    QUARTER(new BigDecimal("0.25")),
    DIME(new BigDecimal("0.10")),
    NICKEL(new BigDecimal("0.05")),
    PENNY(new BigDecimal("0.01"));

    private final BigDecimal denomValue;

    COIN(BigDecimal denomValue) {
        this.denomValue = denomValue;
    }

    static public boolean isMember(BigDecimal value) {
        COIN[] allCoins = COIN.values();
        for (COIN aCoin : allCoins) {
            if (aCoin.denomValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int change(BigDecimal balance) {

        return balance.divide(this.denomValue, 0, RoundingMode.DOWN).toBigInteger().intValue();

    }

    public BigDecimal denomValue() {
        return denomValue;
    }

//        BigDecimal toDenomination(BigDecimal numPennies) {
//            return numPennies.divide(denomValue);
//        }
}
