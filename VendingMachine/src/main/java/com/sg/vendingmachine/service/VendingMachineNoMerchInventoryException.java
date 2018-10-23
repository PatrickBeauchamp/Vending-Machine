/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author patty
 */
public class VendingMachineNoMerchInventoryException extends Exception {

    public VendingMachineNoMerchInventoryException(String message) {
        super(message);
    }

    public VendingMachineNoMerchInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
