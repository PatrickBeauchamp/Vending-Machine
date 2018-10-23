/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author patty
 */
public class Merch {

    private String merchId;

    private String merchName;

    private BigDecimal merchPrice;

    private int merchInventory;

    // is this only for creating new merchandise?
    public Merch(String merchId) {
        this.merchId = merchId;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public BigDecimal getMerchPrice() {
        return merchPrice;
    }

    public void setMerchPrice(BigDecimal merchPrice) {
        this.merchPrice = merchPrice;
    }

    public int getMerchInventory() {
        return merchInventory;
    }

    public void setMerchInventory(int merchInventory) {
        this.merchInventory = merchInventory;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.merchId);
        hash = 97 * hash + Objects.hashCode(this.merchName);
        hash = 97 * hash + Objects.hashCode(this.merchPrice);
        hash = 97 * hash + this.merchInventory;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Merch other = (Merch) obj;
        if (this.merchInventory != other.merchInventory) {
            return false;
        }
        if (!Objects.equals(this.merchId, other.merchId)) {
            return false;
        }
        if (!Objects.equals(this.merchName, other.merchName)) {
            return false;
        }
        if (!Objects.equals(this.merchPrice, other.merchPrice)) {
            return false;
        }
        return true;
    }

}
