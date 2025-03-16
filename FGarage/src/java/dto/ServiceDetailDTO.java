package dto;

public class ServiceDetailDTO {

    private String serviceName;
    private String mechanicName;
    private int hours;
    private double rate;
    private double hourlyRate;
    private String comment;

    public ServiceDetailDTO(String serviceName, String mechanicName, int hours, double rate, double hourlyRate, String comment) {
        this.serviceName = serviceName;
        this.mechanicName = mechanicName;
        this.hours = hours;
        this.rate = rate;
        this.hourlyRate = hourlyRate;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getHours() {
        return hours;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public double getRate() {
        return rate;
    }

    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String toString() {
        return "ServiceDetailDTO{"
                + "serviceName='" + serviceName + '\''
                + ", mechanicName='" + mechanicName + '\''
                + ", hours=" + hours
                + ", rate=" + rate
                + ", hourlyRate=" + hourlyRate
                + ", comment='" + comment + '\''
                + '}';
    }

}
