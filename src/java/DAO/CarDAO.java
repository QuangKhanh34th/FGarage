/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    
                    Car car = new Car(id, serialNumber, model, colour, year);
                    carList.add(car);
                }
            }
            
            //close connection
            if (connection != null) connection.close();
        } catch (Exception e) {
            System.out.println("Unexpected error occured in CarDAO.getAllCars()");
        }
        
        return carList;
    }
    
    public static boolean carExist(String serialNumber) {
        Connection cn = null;
        boolean exists = false;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT 1 FROM Cars WHERE REPLACE(serialNumber, ' ', '') = ?";
                PreparedStatement pStatement = cn.prepareStatement(sql);
                pStatement.setString(1, serialNumber);
                ResultSet rs = pStatement.executeQuery();
                exists = rs.next();
            } else {
                throw new Exception("Database connection is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return exists;
    }
    
    public static int addCar(Car target) {
        int result = 0;
        Connection cn = null;
        
        try {
            cn=DBUtils.getConnection();
            if (cn!=null) {
                String sql = "INSERT INTO Cars (serialNumber, model, colour, year) VALUES (?, ?, ?, ?)";
                PreparedStatement pStatement = cn.prepareStatement(sql);
                pStatement.setString(1, target.getSerialNumber());
                pStatement.setString(2, target.getModel());
                pStatement.setString(3, target.getColour());
                pStatement.setString(4, target.getYear());
                result = pStatement.executeUpdate();
                pStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[CarDAO.java] error adding Car");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        return result;
    }
    
    public static int deleteCar(int targetID) {
        int result = 0;
        Connection cn = null;
        
        try {
            cn=DBUtils.getConnection();
            if (cn!=null) {
                String sql = "DELETE FROM Cars WHERE carID = " + targetID;
                Statement statement = cn.createStatement();
                result = statement.executeUpdate(sql);
                statement.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[CarDAO.java] error removing Car");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static int updateCar(Car target) {
        int result = 0;
        Connection cn = null;
        
        try {
            cn=DBUtils.getConnection();
            if (cn!=null) {
                String sql = "UPDATE Cars SET serialNumber = ?, model = ?, colour = ?, year = ? WHERE carID = " + target.getCarID();
                PreparedStatement pStatement = cn.prepareStatement(sql);
                pStatement.setString(1, target.getSerialNumber());
                pStatement.setString(2, target.getModel());
                pStatement.setString(3, target.getColour());
                pStatement.setString(4, target.getYear());
                result = pStatement.executeUpdate();
                pStatement.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[CarDAO.java] error updating Car");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
