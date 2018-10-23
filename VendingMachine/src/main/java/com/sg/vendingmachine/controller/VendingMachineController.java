/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Merch;
import com.sg.vendingmachine.service.VendingMachineDataValidationException;
import com.sg.vendingmachine.service.VendingMachineNoMerchInventoryException;
import com.sg.vendingmachine.service.VendingMachineNoSuchMerchException;
import com.sg.vendingmachine.service.VendingMachineNotEnoughMoneyException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author patty
 */
public class VendingMachineController {

    VendingMachineView view;

    private VendingMachineServiceLayer service;

    private UserIO io = new UserIOConsoleImpl();

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws VendingMachinePersistenceException, VendingMachineNoMerchInventoryException, VendingMachineDataValidationException, VendingMachineNoSuchMerchException, VendingMachineNotEnoughMoneyException {
        String continueResponse;
        boolean keepGoing = true;
        boolean isFirstTime = true;
        String selection;
        Merch purchase = null;
        BigDecimal money = new BigDecimal("0");
        BigDecimal zero = new BigDecimal("0");
        BigDecimal hundred = new BigDecimal("100");

        listMerch();
        continueResponse = view.getResponse();
        while (keepGoing) {
            if (continueResponse.equals("0")) {
                keepGoing = false;
                view.displayExitMessage();
            }
            if (isFirstTime) {
                money = view.getMoney();
                isFirstTime = false;
            }
            selection = view.getMerchSelection().toUpperCase();
            try {
                purchase = service.getMerch(selection, money);
                service.updateInventory(purchase);
                if (money.compareTo(purchase.getMerchPrice()) == 0) {
                    view.displayThankYouNoChange(purchase.getMerchName().trim());
                    keepGoing = false;
                } else if (money.compareTo(purchase.getMerchPrice()) > 0) {
                    int change = (money.subtract(purchase.getMerchPrice()).multiply(hundred)).intValue();
                    String coins = service.makeChange(change);
                    view.displayPurchaseAndChange(coins, purchase.getMerchName().trim());
                    keepGoing = false;
                }

            } catch (VendingMachineNoSuchMerchException ex) {
                view.displayErrorMessage(ex.getMessage());
            } catch (VendingMachineNotEnoughMoneyException ex) {
                int change = (money.multiply(hundred)).intValue();
                String coins = service.makeChange(change);
                view.displayErrorMessage(ex.getMessage());
                view.displayChange(coins);
                keepGoing = false;
                view.displayExitMessage();
            } catch (VendingMachineNoMerchInventoryException ex) {
                int change = (money.multiply(hundred)).intValue();
                String coins = service.makeChange(change);
                view.displayErrorMessage(ex.getMessage());
                view.displayChange(coins);
                keepGoing = false;
                view.displayExitMessage();
            }

        }
        keepGoing = false;

    }

    public void listMerch() throws VendingMachinePersistenceException {
        List<Merch> merchList = service.getAllMerchForDisplay();
        view.displayAllMerch(merchList);
    }
}
