/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Merch;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patty
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    private Merch merch;
    private List<Merch> merchList = new ArrayList<>();
    
    public VendingMachineDaoStubImpl() {
        merch = new Merch("A");
        merch.setMerchName("Cookie");
        merch.setMerchPrice(new BigDecimal("0.50"));
        merch.setMerchInventory(100);
        
        merchList.add(merch);
        
    }
    
    @Override
    public List<Merch> getAllMerch() throws VendingMachinePersistenceException {
        return merchList;
    }
    
    @Override
    public Merch getMerch(String merchId) throws VendingMachinePersistenceException {
        if (merchId.equals(merch.getMerchId())) {
            return merch;
        } else {
            return null;
        }
    }
    
    @Override
    public String makeChange(int change) throws VendingMachinePersistenceException {
        return "Hello";
    }
    
    @Override
    public void updateInventory(Merch purchase) throws VendingMachinePersistenceException {
        merch.setMerchInventory(merch.getMerchInventory() - 1);
    }
    
    @Override
    public List<Merch> getAllMerchForDisplay() throws VendingMachinePersistenceException {
        return merchList;
    }
    
}
