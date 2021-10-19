/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w.vendingmachine.DAO;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author William
 */
public class VendingMachineAuditDAO {
VendingMachineAuditDAO(){
    this.fileName = "AuditLog.txt";
}
    String fileName = "AuditLog.txt";
    LocalDateTime ldt = LocalDateTime.now();

    /**
     * Writes all Items in the vending machine out to a VENDING_MACHINE_FILE.
     * See loadVendingMachine for file format.
     *
     * @throws VendingMachinePersistenceException if an error occurs writing to
     * the file
     */
    public void WriteAuditLog(String action) throws FileNotFoundException {
        ldt = LocalDateTime.now();
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            throw new FileNotFoundException();
        }

        // Write out the Item objects to the vm file.
        // NOTE TO THE APPRENTICES: We could just grab the item map,
        // get the Collection of Items and iterate over them but we've
        // already created a method that gets a List of Items so
        // we'll reuse it.
        String itemAsText = ldt + "  " + action;

        out.println(itemAsText);
        // force PrintWriter to write line to the file
        out.flush();

        // Clean up
        out.close();
    }

}
