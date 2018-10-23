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
public class VendingMachineNoSuchMerchException extends Exception {

    public VendingMachineNoSuchMerchException(String message) {
        super(message);
    }

    public VendingMachineNoSuchMerchException(String message, Throwable cause) {
        super(message, cause);
    }
}
