package dao;

import dto.ServiceTicketDTO;
import dbutils.DBUtils;
import dto.CarDTO;
import dto.PartDetailDTO;
import dto.ServiceDetailDTO;
import dto.ServiceTicketDetailDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceTicketDAO {

    // Lấy tất cả service tickets
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

    // Tìm kiếm Service Ticket theo một trường cụ thể
    public List<ServiceTicketDTO> searchServiceTickets(String searchType, String searchValue) {
        List<ServiceTicketDTO> list = new ArrayList<>();
        String sql = "SELECT serviceTicketID, dateReceived, dateReturned, custID, carID FROM ServiceTicket WHERE ";

        // Kiểm tra searchType hợp lệ để tránh SQL Injection
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
                return list; // Nếu searchType không hợp lệ, trả về danh sách rỗng
        }

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            if (searchType.equals("dateReceived")) {
                ps.setDate(1, Date.valueOf(searchValue)); // Chuyển đổi String thành Date
            } else {
                ps.setString(1, "%" + searchValue + "%"); // Tìm kiếm gần đúng
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

    // Tìm kiếm theo custID
    public List<ServiceTicketDTO> searchByCustID(String custID) {
        return searchServiceTickets("custID", custID);
    }

    // Tìm kiếm theo carID
    public List<ServiceTicketDTO> searchByCarID(String carID) {
        return searchServiceTickets("carID", carID);
    }

    // Tìm kiếm theo dateReceived
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

    // Lấy thông tin cơ bản của Service Ticket + Car
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

    // Lấy danh sách dịch vụ đã thực hiện
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

    // Lấy danh sách phụ tùng đã sử dụng
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
