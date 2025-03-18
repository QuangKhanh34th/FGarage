/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.ServiceModel;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class ServiceModelDAO {
    public static ArrayList<ServiceModel> getAllServiceModels() {
        ArrayList<ServiceModel> serviceList = new ArrayList<>();
        try {
            Connection connection = DBUtils.getConnection();
            
            if (connection != null) {
                String sql = "SELECT serviceID, serviceName, hourlyRate FROM Service";
                //use static statement because no parameter are required
                Statement st = connection.createStatement();
                ResultSet result = st.executeQuery(sql);
                
                //import result into cusList
                while (result.next()) {
                    String id = result.getString("serviceID");
                    String serviceName = result.getString("serviceName");
                    double hourlyRate = result.getDouble("hourlyRate");

                    
                    ServiceModel serviceModel = new ServiceModel(id, serviceName, hourlyRate);
                    serviceList.add(serviceModel);
                }
            }
            
            //close connection
            if (connection != null) connection.close();
        } catch (Exception e) {
            System.out.println("Unexpected error occured in ServiceModelDAO.getAllServiceModels()");
        }
        
        return serviceList;
    }
}
