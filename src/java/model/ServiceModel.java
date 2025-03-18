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
public class ServiceModel implements Serializable{
    private String serviceID;
    private String serviceName;
    private double hourlyRate;
    private int hours;
    
    public ServiceModel() {
        
    }

    public ServiceModel(String serviceID, String serviceName, double hourlyRate) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.hourlyRate = hourlyRate;
    }

    public ServiceModel(String serviceID, String serviceName, double hourlyRate, int hours) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    
    
}
