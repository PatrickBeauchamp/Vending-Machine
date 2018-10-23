/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Merch;
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
import java.util.stream.Collectors;

/**
 *
 * @author patty
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Merch> allMerch = new HashMap<>();

    @Override
    public List<Merch> getAllMerch() throws
            VendingMachinePersistenceException {
        loadInventory();
        return new ArrayList<Merch>(allMerch.values());
    }

    @Override
    public List<Merch> getAllMerchForDisplay() throws
            VendingMachinePersistenceException {
        loadInventory();
        return allMerch.values().stream()
                .filter(m -> m.getMerchInventory() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Merch getMerch(String merchId) throws
            VendingMachinePersistenceException {
        loadInventory();
        return allMerch.get(merchId);
    }

    @Override
    public String makeChange(int change) {
        ArrayList<Integer> returnChange = new ArrayList<>();
        returnChange.add(change / 25);
        change -= returnChange.get(0) * 25;
        returnChange.add(change / 10);
        change -= returnChange.get(1) * 10;
        returnChange.add(change / 5);
        change -= returnChange.get(2) * 5;
        returnChange.add(change / 1);
        String coins = "";
        if (returnChange.get(0) != 0) {
            coins += returnChange.get(0) + " quarter(s) ";
        }
        if (returnChange.get(1) != 0) {
            coins += returnChange.get(1) + " dime(s) ";
        }
        if (returnChange.get(2) != 0) {
            coins += returnChange.get(2) + " nickel(s) ";
        }
        if (returnChange.get(3) != 0) {
            coins += returnChange.get(3) + " pennies ";
        }
        return coins += "is your change.";
    }

    @Override
    public void updateInventory(Merch purchase) throws VendingMachinePersistenceException {
        int newInventory = purchase.getMerchInventory() - 1;
        purchase.setMerchInventory(newInventory);
        loadInventory();
        allMerch.replace(purchase.getMerchId(), purchase);
        writeInventory();

    }

    private void loadInventory() throws
            VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load data into memory...");
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Merch currentMerch = new Merch(currentTokens[0]);
            currentMerch.setMerchName(currentTokens[1]);

            // to convert file string to BigDecimal
            currentMerch.setMerchPrice(new BigDecimal(currentTokens[2]));

            // to convert file string to int
            currentMerch.setMerchInventory(parseInt(currentTokens[3]));
            allMerch.put(currentMerch.getMerchId(), currentMerch);
        }
        scanner.close();
    }

    private void writeInventory() throws
            VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not save Inventory data...");
        }

        List<Merch> merchList = this.getAllMerch();
        for (Merch currentMerch : merchList) {
            out.println(currentMerch.getMerchId() + DELIMITER
                    + currentMerch.getMerchName() + DELIMITER
                    + currentMerch.getMerchPrice() + DELIMITER
                    + currentMerch.getMerchInventory());
            out.flush();
        }
        out.close();
    }

}
