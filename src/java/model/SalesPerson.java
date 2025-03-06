/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class SalesPerson implements Serializable {
    private int salesID;
    private String salesName;
    private String birthday;
    private String sex;
    private String salesAddress;

    public SalesPerson() {
    }

    public SalesPerson(int salesID, String salesName, String birthday, String sex, String salesAddress) {
        this.salesID = salesID;
        this.salesName = salesName;
        this.birthday = birthday;
        this.sex = sex;
        this.salesAddress = salesAddress;
    }

    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalesAddress() {
        return salesAddress;
    }

    public void setSalesAddress(String salesAddress) {
        this.salesAddress = salesAddress;
    }
    
    
    
}
