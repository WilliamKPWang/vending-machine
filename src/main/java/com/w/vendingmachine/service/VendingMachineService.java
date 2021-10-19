/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.w.vendingmachine.service;

import com.w.vendingmachine.DAO.VendingMachinePersistenceException;
import com.w.vendingmachine.DTO.COIN;
import com.w.vendingmachine.DTO.Item;
import java.util.Map;

/**
 *
 * @author William
 */
public interface VendingMachineService {

    Map<String, Item> getAllItems() throws
            VendingMachinePersistenceException;

    String getGetBalance();

    Item buyItem(String itemName) throws
            VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException;
    Map<COIN, Integer>  getChange();
}
