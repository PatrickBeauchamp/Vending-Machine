/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Merch;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author patty
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;

    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
    }

    @Override
    public List<Merch> getAllMerch() throws
            VendingMachinePersistenceException {
        return dao.getAllMerch();
    }

    @Override
    public Merch getMerch(String merchId, BigDecimal money) throws
            VendingMachinePersistenceException,
            VendingMachineNoMerchInventoryException,
            VendingMachineNotEnoughMoneyException,
            VendingMachineNoSuchMerchException {

        checkInventory(merchId, money);

        return dao.getMerch(merchId);
    }

    @Override
    public Merch getRealMerch(String merchID) throws VendingMachinePersistenceException {
        return dao.getMerch(merchID);
    }

    @Override
    public void updateInventory(Merch purchase) throws VendingMachinePersistenceException {
        dao.updateInventory(purchase);
    }

    @Override
    public String makeChange(int change) throws VendingMachinePersistenceException {
        String coins = dao.makeChange(change);
        return coins;
    }

    @Override
    public List<Merch> getAllMerchForDisplay() throws
            VendingMachinePersistenceException {
        return dao.getAllMerchForDisplay();
    }

    private void checkInventory(String merchId, BigDecimal money) throws
            VendingMachineNoMerchInventoryException, VendingMachinePersistenceException,
            VendingMachineNoSuchMerchException, VendingMachineNotEnoughMoneyException {

        if (dao.getMerch(merchId) == null) {
            throw new VendingMachineNoSuchMerchException(
                    "\nError: No such product.");
        } else if (dao.getMerch(merchId).getMerchInventory() < 1) {
            throw new VendingMachineNoMerchInventoryException(
                    "\nError: " + dao.getMerch(merchId).getMerchName().trim()
                    + " are Out of Stock.");
        }

        if ((money.compareTo(dao.getMerch(merchId).getMerchPrice())) < 0) {
            throw new VendingMachineNotEnoughMoneyException(
                    "\nError: " + dao.getMerch(merchId).getMerchName().trim()
                    + " cost $" + dao.getMerch(merchId).getMerchPrice()
                    + " and you only have $" + money);
        }
    }
}
