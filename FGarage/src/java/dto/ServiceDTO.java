package dto;

public class ServiceDTO {

    private int serviceID;
    private String serviceName;
    private double hourlyRate;

    public ServiceDTO() {
    }

    public ServiceDTO(int serviceID, String serviceName, double hourlyRate) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.hourlyRate = hourlyRate;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
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

    @Override
    public String toString() {
        return "ServiceDTO { "
                + "serviceID=" + serviceID
                + ", serviceName='" + serviceName + '\''
                + ", hourlyRate=" + hourlyRate
                + " }";
    }
}
