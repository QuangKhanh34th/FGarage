
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.CarDTO;
import model.Part;
import model.PartDetailDTO;
import model.ServiceDetailDTO;
import model.ServiceModel;
import model.ServiceTicket;
import model.ServiceTicketDTO;
import model.ServiceTicketDetailDTO;
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
            if (connection != null) {
                connection.close();
            }
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

    public List<ServiceTicketDTO> getAllServiceTickets() {
        List<ServiceTicketDTO> list = new ArrayList<>();
        String sql = "SELECT serviceTicketID, dateReceived, dateReturned, custID, carID FROM ServiceTicket";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int serviceTicketID = rs.getInt("serviceTicketID");
                Date dateReceived = rs.getDate("dateReceived");
                Date dateReturned = rs.getDate("dateReturned");
                String custID = rs.getString("custID");
                String carID = rs.getString("carID");

                list.add(new ServiceTicketDTO(serviceTicketID, dateReceived, dateReturned, custID, carID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ServiceTicketDTO> searchServiceTickets(String searchType, String searchValue) {
        List<ServiceTicketDTO> list = new ArrayList<>();
        String sql = "SELECT serviceTicketID, dateReceived, dateReturned, custID, carID FROM ServiceTicket WHERE ";

        switch (searchType) {
            case "custID":
                sql += "custID LIKE ?";
                break;
            case "carID":
                sql += "carID LIKE ?";
                break;
            case "dateReceived":
                sql += "dateReceived = ?";
                break;
            default:
                return list;
        }

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            if (searchType.equals("dateReceived")) {
                ps.setDate(1, Date.valueOf(searchValue));
            } else {
                ps.setString(1, "%" + searchValue + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int serviceTicketID = rs.getInt("serviceTicketID");
                    Date dateReceived = rs.getDate("dateReceived");
                    Date dateReturned = rs.getDate("dateReturned");
                    String custID = rs.getString("custID");
                    String carID = rs.getString("carID");

                    list.add(new ServiceTicketDTO(serviceTicketID, dateReceived, dateReturned, custID, carID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ServiceTicketDTO> searchByCustID(String custID) {
        return searchServiceTickets("custID", custID);
    }

    public List<ServiceTicketDTO> searchByCarID(String carID) {
        return searchServiceTickets("carID", carID);
    }

    public List<ServiceTicketDTO> searchByDateReceived(String dateReceived) {
        return searchServiceTickets("dateReceived", dateReceived);
    }

    public List<ServiceTicketDTO> getServiceTicketsByCustomerID(String custID) {
        List<ServiceTicketDTO> tickets = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT serviceTicketID, dateReceived, dateReturned, carID "
                        + "FROM ServiceTicket WHERE custID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, custID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int serviceTicketID = rs.getInt("serviceTicketID");
                    Date dateReceived = rs.getDate("dateReceived");
                    Date dateReturned = rs.getDate("dateReturned");
                    String carID = rs.getString("carID");

                    tickets.add(new ServiceTicketDTO(serviceTicketID, dateReceived, dateReturned, custID, carID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tickets;
    }

    private static void insertServiceMechanic(Connection cn, String serviceTicketID, String serviceID, long mechanicID, int hours, String comment, double rate) throws SQLException {
        String sql = "INSERT INTO ServiceMechanic (serviceTicketID, serviceID, mechanicID, hours, comment, rate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pStatement = cn.prepareStatement(sql)) {
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
        try (PreparedStatement pStatement = cn.prepareStatement(sql)) {
            pStatement.setString(1, serviceTicketID);
            pStatement.setInt(2, partID);
            pStatement.setInt(3, numberUsed);
            pStatement.setDouble(4, retailPrice * numberUsed);
            pStatement.executeUpdate();
        }
    }

    public ServiceTicketDetailDTO getServiceTicketInfo(int serviceTicketID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ServiceTicketDetailDTO detail = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT st.dateReceived, st.dateReturned, "
                        + "c.serialNumber, c.model, c.colour, c.year "
                        + "FROM ServiceTicket st "
                        + "JOIN Cars c ON st.carID = c.carID "
                        + "WHERE st.serviceTicketID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, serviceTicketID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    CarDTO car = new CarDTO(rs.getString("serialNumber"),
                            rs.getString("model"),
                            rs.getString("colour"),
                            rs.getInt("year"));
                    detail = new ServiceTicketDetailDTO(serviceTicketID,
                            rs.getDate("dateReceived"),
                            rs.getDate("dateReturned"),
                            car,
                            new ArrayList<>(),
                            new ArrayList<>());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return detail;
    }

    public List<ServiceDetailDTO> getServiceDetails(int serviceTicketID) {
        Connection conn = null;
        List<ServiceDetailDTO> services = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT s.serviceName, m.mechanicName, sm.hours, sm.comment, sm.rate, s.hourlyRate "
                        + "FROM ServiceMehanic sm "
                        + "JOIN Service s ON sm.serviceID = s.serviceID "
                        + "JOIN Mechanic m ON sm.mechanicID = m.mechanicID "
                        + "WHERE sm.serviceTicketID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, serviceTicketID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    services.add(new ServiceDetailDTO(
                            rs.getString("serviceName"),
                            rs.getString("mechanicName"),
                            rs.getInt("hours"),
                            rs.getDouble("rate"),
                            rs.getDouble("hourlyRate"),
                            rs.getString("comment")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return services;
    }

    public List<PartDetailDTO> getPartDetails(int serviceTicketID) {
        Connection conn = null;
        List<PartDetailDTO> parts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT p.partName, pu.numberUsed, pu.price "
                        + "FROM PartsUsed pu "
                        + "JOIN Parts p ON pu.partID = p.partID "
                        + "WHERE pu.serviceTicketID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, serviceTicketID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    parts.add(new PartDetailDTO(
                            rs.getString("partName"),
                            rs.getInt("numberUsed"),
                            rs.getDouble("price")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return parts;
    }
}
