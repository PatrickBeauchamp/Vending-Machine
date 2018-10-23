/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Merch;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author patty
 */
public interface VendingMachineServiceLayer {

    List<Merch> getAllMerch() throws
            VendingMachinePersistenceException;

    Merch getMerch(String merchId, BigDecimal money) throws
            VendingMachinePersistenceException,
            VendingMachineNoMerchInventoryException,
            VendingMachineNotEnoughMoneyException,
            VendingMachineNoSuchMerchException;

    String makeChange(int change) throws
            VendingMachinePersistenceException;

    public void updateInventory(Merch purchase) throws
            VendingMachinePersistenceException;

    List<Merch> getAllMerchForDisplay() throws
            VendingMachinePersistenceException;

    /**
     *
     * @param selection
     * @return
     */
    public Merch getRealMerch(String selection) throws
            VendingMachinePersistenceException;

}
