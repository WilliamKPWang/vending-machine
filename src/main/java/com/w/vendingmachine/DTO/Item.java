/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w.vendingmachine.DTO;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

/**
 *
 * @author William
 */
public class Item {

    public Item() {
    }

    public Item(String Name, BigDecimal Cost, int Stock) {
        this.Name = Name;
        this.Cost = Cost;
        this.stock = Stock;
    }

    public Item(String Name, String Cost, int Stock) {
        this.Name = Name;
        this.Cost = new BigDecimal(Cost);
        this.stock = Stock;
    }
    private String Name;
    private BigDecimal Cost;
    private int stock;

    public String getFormattedCost() {//formatted string of item cost
        return NumberFormat.getCurrencyInstance().format(Cost);
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public BigDecimal getCost() {
        return Cost;
    }

    public void setCost(BigDecimal Cost) {
        this.Cost = Cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.Name);
        hash = 53 * hash + Objects.hashCode(this.Cost);
        hash = 53 * hash + this.stock;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.stock != other.stock) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.Cost, other.Cost)) {
            return false;
        }
        return true;
    }

}
