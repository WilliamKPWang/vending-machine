/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w.vendingmachine.controllers;

import com.w.vendingmachine.DAO.VendingMachinePersistenceException;
import com.w.vendingmachine.UI.VendingMachineView;
import com.w.vendingmachine.service.InsufficientFundsException;
import com.w.vendingmachine.service.NoItemInventoryException;
import com.w.vendingmachine.service.VendingMachineServiceImpl;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

/**
 *
 * @author William
 */
public class VendingMachineController {

    private final VendingMachineView view;
    private final VendingMachineServiceImpl service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceImpl service) {
        this.view = view;
        this.service = service;
    }// constructor

    public void run() throws VendingMachinePersistenceException {
        service.getLoadNewVM("vm.txt");
        service.getLoadVendingMachine();
        boolean keepGoing = true;
        BigDecimal depositAmount;
        int menuSelection;

        view.PrintItems(service.getAllItems());
        depositAmount = view.DepositMoney();
        AddMoney(depositAmount);

        while (keepGoing) {
            menuSelection = view.PrintMenuAndGetSelection();
            switch (menuSelection) {
                case 1 -> //add money
                    AddMoney(view.DepositMoney());
                case 2 -> {
                    //buy something
                    PrintCandyMenu();
                    GetItemChoice();
                }
                case 0 ->
                    keepGoing = false;
                default ->
                    UnknownCommand();
            }
        }
        ExitMessage();

        service.getWriteVendingMachine();

    }

    private void UnknownCommand() {
        view.DisplayUnknownCommandBanner();
    }

    private void ExitMessage() {
        view.DisplayExitBanner();
    }

    private void AddMoney(BigDecimal deposit) {
        try {
            service.getAddMoney(deposit);
        } catch (VendingMachinePersistenceException e) {
            view.DisplayErrorMessage(e.getMessage());
        }
        try {
            view.PrintCurrentBalance(service.getGetBalance());
        } catch (FileNotFoundException e) {
            view.DisplayErrorMessage(e.getMessage());
        }
    }

    private void PrintCandyMenu() throws VendingMachinePersistenceException {
        view.PrintItems(service.getAllItems());
    }

    private void GetItemChoice() {
        try {
            view.DispenseItem(service.buyItem(view.GetItemChoice()));
            view.PrintCurrentBalance(service.getGetBalance());
            view.PrintChange(service.getChange());

        } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException | FileNotFoundException e) {
            view.DisplayErrorMessage(e.getMessage());
        }
    }
}
