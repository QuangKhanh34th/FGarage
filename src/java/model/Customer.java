/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Customer implements Serializable {

    private int custID;
    private String custName;
    private String phone;
    private String sex;
    private String cusAddress;

    public Customer() {
    }

    public Customer(int custID, String custName, String phone, String sex, String cusAddress) {
        this.custID = custID;
        this.custName = custName;
        this.phone = phone;
        this.sex = sex;
        this.cusAddress = cusAddress;
    }

    public Customer(String custName, String phone, String sex, String cusAddress) {
        this.custName = custName;
        this.phone = phone;
        this.sex = sex;
        this.cusAddress = cusAddress;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    // *** THIS IS THE CRUCIAL PART THAT IS LIKELY MISSING OR INCORRECT ***
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Same object instance
        if (o == null || getClass() != o.getClass()) return false; // Null or different class
        Customer customer = (Customer) o; // Cast to Customer
        // Compare all relevant fields for equality
        return custID == customer.custID &&
               Objects.equals(custName, customer.custName) && // Use Objects.equals for null-safe string comparison
               Objects.equals(phone, customer.phone) &&
               Objects.equals(sex, customer.sex) &&
               Objects.equals(cusAddress, customer.cusAddress);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(custID, custName, phone, sex, cusAddress);
    }

    @Override
    public String toString() {
        return "Customer{"
                + "custID=" + custID
                + ", custName='" + custName + '\''
                + ", custPhone='" + phone + '\''
                + ", custGender='" + sex + '\''
                + ", custAddress='" + cusAddress + '\''
                + '}';
    }
}
