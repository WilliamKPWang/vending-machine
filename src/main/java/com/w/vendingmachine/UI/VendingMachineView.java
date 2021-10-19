/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w.vendingmachine.UI;

import com.w.vendingmachine.DTO.COIN;
import com.w.vendingmachine.DTO.Item;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author William
 */
public class VendingMachineView {
    
    private UserIO io = new UserIOImpl();
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public void PrintItems(Map<String, Item> items) {
        io.print("Items:");
        
        for (Item currentCandy : items.values()) {
            io.print(currentCandy.getName() + "   " + currentCandy.getFormattedCost());
        }
        
    }

    public int PrintMenuAndGetSelection() {
        
        io.print("1. Add more money");
        io.print("2. Buy an item");
        io.print("0. Exit");
        return io.readInt("choose an option");
    }
    
    public BigDecimal DepositMoney() {
        io.print("Enter the amount of money to put in");
        
        return BigDecimal.valueOf(io.readDouble("Valid coins: nickels, dimes, quarters, dollars"));
    }
    
    public void PrintCurrentBalance(String balance) {
        io.print("Current Balance: " + balance);
    }

    public String GetItemChoice() {
        return io.readString("What would you like?");
    }

    public void PrintInsufficientFunds() {
        io.print("Not enough funds!");
    }
    public void DispenseItem (Item item){
        io.print(item.getName() +" was dispensed");
    }
    public void PrintChange(Map<COIN,Integer> coins) {
        io.print("your change is:");
         for (Map.Entry<COIN,Integer> aCoin : coins.entrySet()) {
         io.print(aCoin.getKey().name()+ ":  " + aCoin.getValue());
         }
    }
    
    public void DisplayExitBanner() {
        io.print("----Goodbye!----");
    }
    
    public void DisplayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void DisplayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
