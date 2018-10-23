/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dto.Merch;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author patty
 */
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllMerch method, of class VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllMerch() throws Exception {
        assertEquals(1, service.getAllMerch().size());

    }

    /**
     * Test of getRealMerch method, of class VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMerch() throws Exception {
//        Merch merch = service.getRealMerch("A");
//        assertNotNull(merch);
//        merch = service.getRealMerch("GGG");
//        assertNull(merch);
    }

    /**
     * Test of makeChange method, of class VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMakeChange() throws Exception {
    }

    /**
     * Test of updateInventory method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateInventory() throws Exception {
    }

    /**
     * Test of getAllMerchForDisplay method, of class
     * VendingMachineServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllMerchForDisplay() throws Exception {
    }

    
}
