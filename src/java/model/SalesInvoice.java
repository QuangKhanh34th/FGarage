/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author USER
 */
public class SalesInvoice {

    public int invoiceID;
    public Date invoiceDate;
    public BigDecimal salesID;
    public BigDecimal carID;
    public BigDecimal custID;

    public SalesInvoice() {
    }

    public SalesInvoice(int invoiceID, Date invoiceDate, BigDecimal salesID, BigDecimal carID, BigDecimal custID) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.salesID = salesID;
        this.carID = carID;
        this.custID = custID;
    }

    public SalesInvoice(BigDecimal salesID, BigDecimal carID, BigDecimal custID) {
        this.invoiceDate = new Date(System.currentTimeMillis());
        this.salesID = salesID;
        this.carID = carID;
        this.custID = custID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getSalesID() {
        return salesID;
    }

    public void setSalesID(BigDecimal salesID) {
        this.salesID = salesID;
    }

    public BigDecimal getCarID() {
        return carID;
    }

    public void setCarID(BigDecimal carID) {
        this.carID = carID;
    }

    public BigDecimal getCustID() {
        return custID;
    }

    public void setCustID(BigDecimal custID) {
        this.custID = custID;
    }

}
