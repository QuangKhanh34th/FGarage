/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Horwlt
 */
public class PartDetailDTO {

    private String partName;
    private int numberUsed;
    private double price;

    public PartDetailDTO(String partName, int numberUsed, double price) {
        this.partName = partName;
        this.numberUsed = numberUsed;
        this.price = price;
    }

    public String getPartName() {
        return partName;
    }

    public int getNumberUsed() {
        return numberUsed;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PartDetailDTO{"
                + "partName='" + partName + '\''
                + ", numberUsed=" + numberUsed
                + ", price=" + price
                + '}';
    }

}
