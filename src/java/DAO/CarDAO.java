/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Car;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class CarDAO {
    public static ArrayList<Car> getAllCars() {
        ArrayList<Car> carList = new ArrayList<>();
        try {
            Connection connection = DBUtils.getConnection();
            
            if (connection != null) {
                String sql = "SELECT carID, serialNumber, model, colour, year FROM Cars";
                //use static statement because no parameter are required
                Statement st = connection.createStatement();
                ResultSet result = st.executeQuery(sql);
                
                //import result into cusList
                while (result.next()) {
                    int id = result.getInt("carID");
                    String serialNumber = result.getString("serialNumber");
                    String model = result.getString("model");
                    String colour = result.getString("colour");
                    String year = result.getString("year");
                    
                    Car customer = new Car(id, serialNumber, model, colour, year);
                    carList.add(customer);
                }
            }
            
            //close connection
            if (connection != null) connection.close();
        } catch (Exception e) {
            System.out.println("Unexpected error occured in CarDAO.getAllCars()");
        }
        
        return carList;
    }
}
