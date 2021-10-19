/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.w.vendingmachine.DAO;

import com.w.vendingmachine.DTO.VendingMachine;
import com.w.vendingmachine.DTO.COIN;
import com.w.vendingmachine.DTO.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author William
 */
public class VendingMachineDAOImpl implements VendingMachineDAO {

    VendingMachineAuditDAO vma = new VendingMachineAuditDAO();
    VendingMachine vm = new VendingMachine("vm.txt");
    public static final String DELIMITER = "::";
    private Map<String, Item> items = new HashMap<>();

    @Override
    public void AddMoney(BigDecimal coin) throws VendingMachinePersistenceException {

        if (COIN.isMember(coin)) {
            vm.setDepositAmount(vm.getDepositAmount().add(coin));
        } else {
            throw new VendingMachinePersistenceException(
                    "Not a valid coin");

        }
    }

    @Override
    public BigDecimal GetBalance() throws FileNotFoundException {
        vma.WriteAuditLog(("GetBalance:  " + vm.getDepositAmount().toString()));
        return vm.getDepositAmount();
    }

    @Override
    public Item CheckForItem(String itemName) {
        return items.get(itemName);
    }

    @Override
    public void DispenseItem(String itemName) throws FileNotFoundException {
        vma.WriteAuditLog("dispensed item:  " + itemName);

        Item item = items.get(itemName);

        System.out.println(item.getStock());
        item.setStock(item.getStock() - 1);
        System.out.println(item.getStock());
        items.put(itemName, item);
//            System.out.println(items.get(itemName).getStock());
//            items.get(itemName).setStock(items.get(itemName).getStock()-1);
//            System.out.println(items.get(itemName).getStock());
    }

    @Override
    public void RemoveMoney(BigDecimal amount) throws FileNotFoundException{
        
        vm.setDepositAmount(vm.getDepositAmount().subtract(amount));
        vma.WriteAuditLog("Money subtracted, new balance:    "+vm.getDepositAmount());
    }

    private Item UnmarshallItem(String itemAsText) throws VendingMachinePersistenceException {
        // itemAsText is expecting a line read in from our file.
        String[] itemTokens = itemAsText.split(DELIMITER);

        Item itemFromFile = new Item();

        itemFromFile.setName(itemTokens[0]);
        itemFromFile.setCost(new BigDecimal(itemTokens[1]));
        itemFromFile.setStock(parseInt(itemTokens[2]));
//        if (itemFromFile.getStock() == parseInt("NaN")) {
//            throw new VendingMachinePersistenceException( ////change this if you want to create a new file instead
//                    "-_- Could not Unmarshall item.");
//        }

        // We have now created a item! Return it!
        return itemFromFile;
    }

    @Override
    public void LoadVendingMachine() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(vm.getVendingMachineFile())));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException( ////change this if you want to create a new file instead
                    "-_- Could not load vm data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent item unmarshalled
        Item currentItem;
        // Go through LIBRARY_FILE line by line, decoding each line into a 
        // Item object by calling the UnmarshallItem method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Item
            currentItem = UnmarshallItem(currentLine);

            // We are going to use the item id as the map key for our item object.
            // Put currentItem into the map using item id as the key
            items.put(currentItem.getName(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private String MarshallItem(Item aItem) {
        // We need to turn a Item object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 
        // Start with the item id, since that's supposed to be first.
        String itemAsText = aItem.getName() + DELIMITER;

        // add the rest of the properties in the correct order:
        // cost
        itemAsText += aItem.getCost().toString() + DELIMITER;
        //stock
        itemAsText += aItem.getStock();

        // We have now turned a item to text! Return it!
        return itemAsText;
    }

    /**
     * Writes all Items in the vending machine out to a VENDING_MACHINE_FILE.
     * See loadVendingMachine for file format.
     *
     * @throws VendingMachinePersistenceException if an error occurs writing to
     * the file
     */
    @Override
    public void WriteVendingMachine() throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(vm.getVendingMachineFile()));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }

        // Write out the Item objects to the vm file.
        // NOTE TO THE APPRENTICES: We could just grab the item map,
        // get the Collection of Items and iterate over them but we've
        // already created a method that gets a List of Items so
        // we'll reuse it.
        String itemAsText;
        List<Item> itemList = new ArrayList(this.getAllItems().values());
        for (Item currentItem : itemList) {
            // turn a Item into a String
            itemAsText = MarshallItem(currentItem);
            // write the Item object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    public void WriteVendingMachine(Map<String, Item> Items) throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(vm.getVendingMachineFile()));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }

        // Write out the Item objects to the vm file.
        // NOTE TO THE APPRENTICES: We could just grab the item map,
        // get the Collection of Items and iterate over them but we've
        // already created a method that gets a List of Items so
        // we'll reuse it.
        String itemAsText;
        List<Item> itemList = new ArrayList(Items.values());
        for (Item currentItem : itemList) {
            // turn a Item into a String
            itemAsText = MarshallItem(currentItem);
            // write the Item object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public void LoadNewVM(String fileName) {
        vm.setVendingMachineFile(fileName);
    }

    @Override
    public Map<String, Item> getAllItems() throws VendingMachinePersistenceException {
        //10LoadVendingMachine();
        //  return new ArrayList(dvds.values());
        return items;
    }
}
