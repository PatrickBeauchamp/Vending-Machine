/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Merch;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patty
 */
public interface VendingMachineDao {
    
    List<Merch> getAllMerch() throws
            VendingMachinePersistenceException;
    
    Merch getMerch(String merchId) throws
            VendingMachinePersistenceException;
    
    String makeChange(int change) throws
            VendingMachinePersistenceException;
    
    public void updateInventory(Merch purchase) throws
            VendingMachinePersistenceException;
    
    List<Merch> getAllMerchForDisplay() throws
            VendingMachinePersistenceException;
}
