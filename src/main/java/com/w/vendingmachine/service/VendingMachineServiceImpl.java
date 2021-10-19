package com.w.vendingmachine.service;

import com.w.vendingmachine.DAO.VendingMachineDAO;
import com.w.vendingmachine.DAO.VendingMachinePersistenceException;
import com.w.vendingmachine.DTO.COIN;
import com.w.vendingmachine.DTO.Item;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author William
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    private final VendingMachineDAO dao;

    public VendingMachineServiceImpl(VendingMachineDAO dao) {
        this.dao = dao;
    }

    @Override
    public Map<String, Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    public void getAddMoney(BigDecimal coin) throws VendingMachinePersistenceException {
        dao.AddMoney(coin);
    }

    @Override
    public Item buyItem(String itemName) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        Map<String, Item> items = dao.getAllItems();
        CheckStock(itemName);
        CheckFunds(itemName);
        dao.RemoveMoney(items.get(itemName).getCost());
        dao.DispenseItem(itemName);
        return dao.CheckForItem(itemName);

    }

    private void CheckStock(String itemName) throws VendingMachinePersistenceException, NoItemInventoryException {
        Map<String, Item> items = dao.getAllItems();
        if (items.get(itemName) == null) {
            throw new VendingMachinePersistenceException("not a valid item");
        }
        if (items.get(itemName).getStock() <= 0) {
            throw new NoItemInventoryException("out of stock");
        }
    }

    private void CheckFunds(String itemName) throws VendingMachinePersistenceException, InsufficientFundsException {
        Map<String, Item> items = dao.getAllItems();
        if (items.get(itemName).getCost().compareTo(dao.GetBalance()) == 1) {
            throw new InsufficientFundsException("not enough funds");
        }
    }

    @Override
    public Map<COIN, Integer> getChange() {
        Map<COIN, Integer> change = new HashMap<>();

        BigDecimal balance = dao.GetBalance();

        for (COIN aCoin : COIN.values()) {
            change.put(aCoin, aCoin.change(balance));
            balance = balance.subtract(aCoin.denomValue().multiply(BigDecimal.valueOf(aCoin.change(balance))));
        }
        return change;

    }

    @Override
    public String getGetBalance() {
        return NumberFormat.getCurrencyInstance().format(dao.GetBalance());

    }

    public void getLoadVendingMachine() throws VendingMachinePersistenceException {
        dao.LoadVendingMachine();
    }

    public void getWriteVendingMachine() throws VendingMachinePersistenceException {
        dao.WriteVendingMachine();
    }

    public void getLoadNewVM(String fileName) {
        dao.LoadNewVM(fileName);
    }

}
