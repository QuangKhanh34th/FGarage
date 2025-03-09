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
public class Mechanic implements Serializable{
    private int mechanicID;
    private String mechanicName;

    public Mechanic() {
    }

    public Mechanic(int mechanicID, String mechanicName) {
        this.mechanicID = mechanicID;
        this.mechanicName = mechanicName;
    }

    public int getMechanicID() {
        return mechanicID;
    }

    public void setMechanicID(int mechanicID) {
        this.mechanicID = mechanicID;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }
    
    
}
