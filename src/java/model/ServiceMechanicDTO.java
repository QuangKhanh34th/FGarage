package model;

public class ServiceMechanicDTO {

    private int serviceTicketID;
    private int serviceID;
    private String mechanicID;
    private int hours;
    private String comment;
    private double rate;

    public ServiceMechanicDTO() {
    }

    public ServiceMechanicDTO(int serviceTicketID, int serviceID, String mechanicID, int hours, String comment, double rate) {
        this.serviceTicketID = serviceTicketID;
        this.serviceID = serviceID;
        this.mechanicID = mechanicID;
        this.hours = hours;
        this.comment = comment;
        this.rate = rate;
    }

    public int getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(int serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getMechanicID() {
        return mechanicID;
    }

    public void setMechanicID(String mechanicID) {
        this.mechanicID = mechanicID;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ServiceMechanicDTO{"
                + "serviceTicketID=" + serviceTicketID
                + ", serviceID=" + serviceID
                + ", mechanicID='" + mechanicID + '\''
                + ", hours=" + hours
                + ", comment='" + comment + '\''
                + ", rate=" + rate
                + '}';
    }
}
