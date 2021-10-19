/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w.vendingmachine.DTO;

/**
 *
 * @author William
 */
import java.math.BigDecimal;
import java.util.Objects;

public class VendingMachine {

    private String VENDING_MACHINE_FILE;
    private BigDecimal depositAmount = new BigDecimal("0.00");

    public VendingMachine(String vmFile) {
        this.VENDING_MACHINE_FILE = vmFile;
    }

    public String getVendingMachineFile() {
        return VENDING_MACHINE_FILE;
    }

    public void setVendingMachineFile(String vmFile) {
        this.VENDING_MACHINE_FILE = vmFile;
    }

    public void setDepositAmount(BigDecimal amount) {
        depositAmount = amount;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.VENDING_MACHINE_FILE);
        hash = 13 * hash + Objects.hashCode(this.depositAmount);
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
        final VendingMachine other = (VendingMachine) obj;
        if (!Objects.equals(this.VENDING_MACHINE_FILE, other.VENDING_MACHINE_FILE)) {
            return false;
        }
        if (!Objects.equals(this.depositAmount, other.depositAmount)) {
            return false;
        }
        return true;
    }



}
