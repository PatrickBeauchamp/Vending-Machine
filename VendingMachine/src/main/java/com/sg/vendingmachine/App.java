/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineDataValidationException;
import com.sg.vendingmachine.service.VendingMachineNoMerchInventoryException;
import com.sg.vendingmachine.service.VendingMachineNoSuchMerchException;
import com.sg.vendingmachine.service.VendingMachineNotEnoughMoneyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author patty
 */
public class App {

    public static void main(String[] args) throws VendingMachinePersistenceException, VendingMachineNoMerchInventoryException, VendingMachineDataValidationException, VendingMachineNoSuchMerchException, VendingMachineNotEnoughMoneyException {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller =
                ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }

}
