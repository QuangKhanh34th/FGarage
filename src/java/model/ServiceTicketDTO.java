/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Horwlt
 */
public class ServiceTicketDTO implements Serializable {

    private int serviceTicketID;
    private Date dateReceived;
    private Date dateReturned;
    private int custID;
    private String carID;

    public ServiceTicketDTO() {
    }

    public ServiceTicketDTO(int serviceTicketID, Date dateReceived, Date dateReturned, int custID, String carID) {
        this.serviceTicketID = serviceTicketID;
        this.dateReceived = dateReceived;
        this.dateReturned = dateReturned;
        this.custID = custID;
        this.carID = carID;
    }

    public int getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(int serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    @Override
    public String toString() {
        return "ServiceTicketDTO{"
                + "serviceTicketID=" + serviceTicketID
                + ", dateReceived=" + dateReceived
                + ", dateReturned=" + dateReturned
                + ", carID=" + carID
                + '}';
    }

}
