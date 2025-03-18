/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Horwlt
 */
public class ServiceTicketDetailDTO {

    private int serviceTicketID;
    private Date dateReceived;
    private Date dateReturned;
    private CarDTO car;
    private List<ServiceDetailDTO> services;
    private List<PartDetailDTO> parts;

    public ServiceTicketDetailDTO() {
    }

    public ServiceTicketDetailDTO(int serviceTicketID, Date dateReceived, Date dateReturned, CarDTO car, List<ServiceDetailDTO> services, List<PartDetailDTO> parts) {
        this.serviceTicketID = serviceTicketID;
        this.dateReceived = dateReceived;
        this.dateReturned = dateReturned;
        this.car = car;
        this.services = services;
        this.parts = parts;
    }

    /**
     * @return the serviceTicketID
     */
    public int getServiceTicketID() {
        return serviceTicketID;
    }

    /**
     * @param serviceTicketID the serviceTicketID to set
     */
    public void setServiceTicketID(int serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    /**
     * @return the dateReceived
     */
    public Date getDateReceived() {
        return dateReceived;
    }

    /**
     * @param dateReceived the dateReceived to set
     */
    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * @return the dateReturned
     */
    public Date getDateReturned() {
        return dateReturned;
    }

    /**
     * @param dateReturned the dateReturned to set
     */
    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    /**
     * @return the car
     */
    public CarDTO getCar() {
        return car;
    }

    /**
     * @param car the car to set
     */
    public void setCar(CarDTO car) {
        this.car = car;
    }

    /**
     * @return the services
     */
    public List<ServiceDetailDTO> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<ServiceDetailDTO> services) {
        this.services = services;
    }

    /**
     * @return the parts
     */
    public List<PartDetailDTO> getParts() {
        return parts;
    }

    /**
     * @param parts the parts to set
     */
    public void setParts(List<PartDetailDTO> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "ServiceTicketDetailDTO{"
                + "serviceTicketID=" + serviceTicketID
                + ", dateReceived=" + dateReceived
                + ", dateReturned=" + dateReturned
                + ", car=" + car
                + ", services=" + services
                + ", parts=" + parts
                + '}';
    }

}
