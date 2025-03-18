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
import model.Part;
import model.ServiceModel;
import model.ServiceTicket;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class ServiceTicketDAO {
    public static ArrayList<ServiceTicket> getAllServiceModels() {
        ArrayList<ServiceTicket> ticketList = new ArrayList<>();
        try {
            Connection connection = DBUtils.getConnection();
            
            if (connection != null) {
                String sql = "SELECT serviceTicketID, dateReceived, dateReturned, custID, carID FROM ServiceTicket";
                //use static statement because no parameter are required
                Statement st = connection.createStatement();
                ResultSet result = st.executeQuery(sql);
                
                //import result into cusList
                while (result.next()) {
                    String id = result.getString("serviceTicketID");
                    String dateReceived = result.getString("dateReceived");
                    String dateReturned = result.getString("dateReturned");
                    String custID = result.getString("custID");
                    String carID = result.getString("carID");

                    
                    ServiceTicket serviceTicket = new ServiceTicket(id, dateReceived, dateReturned, custID, carID);
                    ticketList.add(serviceTicket);
                }
            }
            
            //close connection
            if (connection != null) connection.close();
        } catch (Exception e) {
            System.out.println("Unexpected error occured in ServiceModelDAO.getAllServiceModels()");
        }
        
        return ticketList;
    }
    
    public static int addServiceTicket(ServiceTicket targetTicket, ArrayList<ServiceModel> addedServices, ArrayList<Part> addedParts, long mechanicID, String mechanicComment) {
        int result = 0;
        Connection cn = null;
        PreparedStatement pStatement = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "INSERT INTO ServiceTicket (dateReceived, dateReturned, custID, carID) VALUES (?, ?, ?, ?)";
                pStatement = cn.prepareStatement(sql);

                pStatement.setString(1, targetTicket.getDateReceived());
                pStatement.setString(2, targetTicket.getDateReturned());
                pStatement.setString(3, targetTicket.getCustID());
                pStatement.setString(4, targetTicket.getCarID());

                result = pStatement.executeUpdate();

                if (result > 0) {
                    sql = "SELECT top 1 serviceTicketID FROM ServiceTicket ORDER BY serviceTicketID DESC";
                    pStatement = cn.prepareStatement(sql);
                    ResultSet table = pStatement.executeQuery();

                    if (table != null && table.next()) {
                        String serviceTicketId = table.getString("serviceTicketID");

                        // Insert into ServiceMechanic
                        if (addedServices != null) {
                            for (ServiceModel service : addedServices) {
                                insertServiceMechanic(cn, serviceTicketId, service.getServiceID(), mechanicID, service.getHours(), mechanicComment, service.getHourlyRate());
                            }
                        }

                        // Insert into PartsUsed
                        if (addedParts != null) {
                            for (Part part : addedParts) {
                                insertPartsUsed(cn, serviceTicketId, part.getPartID(), part.getNumberUsed(), part.getRetailPrice());
                            }
                        }
                    }
                }

                pStatement.close();
                cn.commit();
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ServiceTicketDAO.java] error adding ServiceTicket");
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

   
    private static void insertServiceMechanic(Connection cn, String serviceTicketID, String serviceID, long mechanicID, int hours, String comment, double rate) throws SQLException {
        String sql = "INSERT INTO ServiceMechanic (serviceTicketID, serviceID, mechanicID, hours, comment, rate) VALUES (?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement pStatement = cn.prepareStatement(sql)) {
            pStatement.setString(1, serviceTicketID);
            pStatement.setString(2, serviceID);
            pStatement.setLong(3, mechanicID);
            pStatement.setInt(4, hours);
            pStatement.setString(5, comment);
            pStatement.setDouble(6, rate);
            pStatement.executeUpdate();
        }
    }

    
    private static void insertPartsUsed(Connection cn, String serviceTicketID, int partID, int numberUsed, double retailPrice) throws SQLException {
        String sql = "INSERT INTO PartsUsed (serviceTicketID, partID, numberUsed, price) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement pStatement = cn.prepareStatement(sql)) {
            pStatement.setString(1, serviceTicketID);
            pStatement.setInt(2, partID);
            pStatement.setInt(3, numberUsed);
            pStatement.setDouble(4, retailPrice*numberUsed);
            pStatement.executeUpdate();
        }
    }
}
