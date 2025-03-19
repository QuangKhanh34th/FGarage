package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ServiceMechanicDTO;
import utils.DBUtils;
import java.sql.Types;

public class ServiceMechanicDAO {

    
    public List<ServiceMechanicDTO> getServiceMechanicByMechanicID(long mechanicID) {
        List<ServiceMechanicDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM ServiceMehanic WHERE mechanicID = ?";
                ps = conn.prepareStatement(sql);
                ps.setLong(1, mechanicID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int serviceTicketID = rs.getInt("serviceTicketID");
                    int serviceID = rs.getInt("serviceID");
                    int hours = rs.getInt("hours");
                    String comment = rs.getString("comment");
                    if (comment == null) {
                        comment = "";
                    }
                    double rate = rs.getDouble("rate");

                    list.add(new ServiceMechanicDTO(serviceTicketID, serviceID, mechanicID, hours, comment, rate));
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
        return list;
    }

    
    public boolean updateServiceMechanic(int serviceTicketID, int serviceID, long mechanicID, int hours, String comment, double rate) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean updated = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE ServiceMehanic SET hours = ?, comment = ?, rate = ? WHERE serviceTicketID = ? AND serviceID = ? AND mechanicID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, hours);
                if (comment == null || comment.trim().isEmpty()) {
                    ps.setNull(2, Types.VARCHAR);
                } else {
                    ps.setString(2, comment);
                }
                ps.setDouble(3, rate);
                ps.setInt(4, serviceTicketID);
                ps.setInt(5, serviceID);
                ps.setLong(6, mechanicID);

                updated = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
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
        return updated;
    }
}
