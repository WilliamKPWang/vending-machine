/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.w.vendingmachine.DAO;

import com.w.vendingmachine.DTO.Item;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author William
 */
public interface VendingMachineDAO {

    void AddMoney(BigDecimal coin) throws VendingMachinePersistenceException;

    BigDecimal GetBalance()throws FileNotFoundException;


    Item CheckForItem(String itemName) throws VendingMachinePersistenceException;

    Map<String, Item> getAllItems() throws VendingMachinePersistenceException;

    void LoadNewVM(String fileName);

    void LoadVendingMachine() throws VendingMachinePersistenceException;

    void WriteVendingMachine() throws VendingMachinePersistenceException;
    void WriteVendingMachine(Map<String, Item> Items) throws VendingMachinePersistenceException;

    void RemoveMoney(BigDecimal amount)throws FileNotFoundException;
    void DispenseItem(String itemName)throws FileNotFoundException;
}
