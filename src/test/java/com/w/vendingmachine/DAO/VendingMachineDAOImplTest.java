/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.w.vendingmachine.DAO;

import com.w.vendingmachine.DTO.Item;
import com.w.vendingmachine.DTO.VendingMachine;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author William
 */
public class VendingMachineDAOImplTest {

    public VendingMachineDAOImplTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        VendingMachine vm = new VendingMachine("vm.txt");
        vm.setDepositAmount(BigDecimal.ZERO);
        VendingMachineDAO dao = new VendingMachineDAOImpl();
        Map<String, Item> Items = new HashMap<>();
        Items.put("Water", new Item("Water", "0.99", 13));
        Items.put("Snickers", new Item("Snickers", "1.50", 10));
        Items.put("Kitkat", new Item("Kitkat", "1.25", 0));
        dao.WriteVendingMachine(Items);
        dao.LoadNewVM("vm.txt");
        dao.LoadVendingMachine();

    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testSomeMethod() {
    }

    /**
     * Test of AddMoney method, of class VendingMachineDAOImpl.
     */
    @Test
    public void testAddMoney() throws Exception {
        //Arrange
        System.out.println("AddMoney");
        BigDecimal coin = new BigDecimal("0.25");
        VendingMachineDAOImpl instance = new VendingMachineDAOImpl();

        //Act
        instance.AddMoney(coin);

        //Assert
        assertEquals(instance.vm.getDepositAmount(), coin, "valid coin add should be added to the balance");

    }

    /**
     * Test of GetBalance method, of class VendingMachineDAOImpl.
     */
    @Test
    public void testGetBalance()throws Exception {
        System.out.println("GetBalance");
        VendingMachineDAOImpl instance = new VendingMachineDAOImpl();
        BigDecimal expResult = new BigDecimal("0.00");
        BigDecimal result = instance.GetBalance();
        assertEquals(expResult, result);

    }

    /**
     * Test of CheckForItem method, of class VendingMachineDAOImpl.
     */
    @Test
    public void testCheckForItem() throws Exception {
        System.out.println("CheckForItem");
        String itemName = "Water";
        VendingMachineDAOImpl instance = new VendingMachineDAOImpl();
        instance.LoadNewVM("vm.txt");
        instance.LoadVendingMachine();
        Item expResult = new Item("Water", "0.99", 13);
        Item result = instance.CheckForItem(itemName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of DispenseItem method, of class VendingMachineDAOImpl.
     */
    @Test
    public void testDispenseItem() throws Exception {
        System.out.println("DispenseItem");
        String itemName = "Water";
        VendingMachineDAOImpl instance = new VendingMachineDAOImpl();
        instance.LoadNewVM("vm.txt");
        instance.LoadVendingMachine();
        instance.DispenseItem(itemName);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(12, instance.CheckForItem(itemName).getStock(), "Should only have 12 stock left");

    }

    /**
     * Test of RemoveMoney method, of class VendingMachineDAOImpl.
     */
    @Test
    public void testRemoveMoney() throws Exception {
        System.out.println("RemoveMoney");
        BigDecimal balance = new BigDecimal("10.00");
        BigDecimal amount = new BigDecimal("5.00");
        VendingMachineDAOImpl instance = new VendingMachineDAOImpl();
        instance.LoadNewVM("vm.txt");
        instance.LoadVendingMachine();
        instance.vm.setDepositAmount(balance);
        instance.RemoveMoney(amount);
        BigDecimal expResult = new BigDecimal("5.00");
        // TODO review the generated test code and remove the default call to fail.

        assertEquals(expResult, instance.vm.getDepositAmount());
    }
//    @Test
//    public void testAddInvalidCoin() throws Exception{
//        System.out.println("RemoveMoney");
//        BigDecimal amount = new BigDecimal("10.00");
//        VendingMachineDAOImpl instance = new VendingMachineDAOImpl();
//        instance.AddMoney(amount);
//        // TODO review the generated test code and remove the default call to fail.
//        
//        assertEquals(Exception, instance.vm.getDepositAmount());
//    }

    /**
     * Test of getAllItems method, of class VendingMachineDAOImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {
        System.out.println("getAllItems");
        VendingMachineDAOImpl instance = new VendingMachineDAOImpl();
        Map<String, Item> expResult = new HashMap<>();
        Map<String, Item> result = instance.getAllItems();
        assertEquals(expResult, result);
    }

}
