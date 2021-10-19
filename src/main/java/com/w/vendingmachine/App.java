/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w.vendingmachine;

import com.w.vendingmachine.DAO.VendingMachineDAO;
import com.w.vendingmachine.DAO.VendingMachineDAOImpl;
import com.w.vendingmachine.DAO.VendingMachinePersistenceException;
import com.w.vendingmachine.UI.UserIO;
import com.w.vendingmachine.UI.UserIOImpl;
import com.w.vendingmachine.UI.VendingMachineView;
import com.w.vendingmachine.controllers.VendingMachineController;
import com.w.vendingmachine.service.VendingMachineServiceImpl;

/**
 *
 * @author William
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOImpl();
        VendingMachineView view = new VendingMachineView(myIo);
        VendingMachineDAO dao = new VendingMachineDAOImpl();
        VendingMachineServiceImpl service = new VendingMachineServiceImpl(dao);
        VendingMachineController controller = new VendingMachineController(view, service);

        try {
            controller.run();
        }catch(VendingMachinePersistenceException crde){
            
        }
    }
}
