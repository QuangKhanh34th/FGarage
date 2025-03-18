/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.ServiceModelDAO;
import java.util.ArrayList;
import model.ServiceModel;

/**
 *
 * @author ASUS
 */
public class ServiceModelService {
    public ArrayList<ServiceModel> getAllServices() {
        return ServiceModelDAO.getAllServiceModels();
    }
    
    public ServiceModel getServiceById(String serviceID, ArrayList<ServiceModel> serviceList) {
        ServiceModel result = null;
        if (serviceList==null) serviceList = getAllServices();
        
        for (ServiceModel serviceModel : serviceList) {
            if (serviceModel.getServiceID().equals(serviceID)) {
                result = serviceModel;
            }
        }
        return result;
    }
}
