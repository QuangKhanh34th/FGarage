package dao;

import dbutils.DBUtils;
import dto.ServiceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    // Lấy danh sách tất cả dịch vụ
    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> services = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT serviceID, serviceName, hourlyRate FROM Service";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    int serviceID = rs.getInt("serviceID");
                    String serviceName = rs.getString("serviceName");
                    double hourlyRate = rs.getDouble("hourlyRate");

                    services.add(new ServiceDTO(serviceID, serviceName, hourlyRate));
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

    // Lấy thông tin của một dịch vụ theo serviceID
    public ServiceDTO getServiceByID(int serviceID) {
        ServiceDTO service = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT serviceName, hourlyRate FROM Service WHERE serviceID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, serviceID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String serviceName = rs.getString("serviceName");
                    double hourlyRate = rs.getDouble("hourlyRate");

                    service = new ServiceDTO(serviceID, serviceName, hourlyRate);
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
        return service;
    }

    // Thêm một dịch vụ mới
    public boolean addService(ServiceDTO service) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Lấy serviceID lớn nhất + 1
                String getMaxIDSQL = "SELECT COALESCE(MAX(serviceID), 0) + 1 FROM Service";
                ps = conn.prepareStatement(getMaxIDSQL);
                rs = ps.executeQuery();

                int newServiceID = 1; // Mặc định là 1 nếu bảng rỗng
                if (rs.next()) {
                    newServiceID = rs.getInt(1);
                }

                // Đóng ResultSet và PreparedStatement trước khi dùng lại
                rs.close();
                ps.close();

                // Thực hiện INSERT với serviceID mới
                String insertSQL = "INSERT INTO Service (serviceID, serviceName, hourlyRate) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(insertSQL);
                ps.setInt(1, newServiceID);
                ps.setString(2, service.getServiceName());
                ps.setDouble(3, service.getHourlyRate());

                success = ps.executeUpdate() > 0;
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
        return success;
    }

    // Cập nhật thông tin một dịch vụ
    public boolean updateService(ServiceDTO service) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE Service SET serviceName = ?, hourlyRate = ? WHERE serviceID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, service.getServiceName());
                ps.setDouble(2, service.getHourlyRate());
                ps.setInt(3, service.getServiceID());

                success = ps.executeUpdate() > 0;
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
        return success;
    }

    // Xóa một dịch vụ theo serviceID
    public boolean deleteService(int serviceID) {
    Connection conn = null;
    PreparedStatement ps = null;
    boolean success = false;

    try {
        conn = DBUtils.getConnection();
        if (conn != null) {
            // Xóa tất cả các bản ghi liên quan trong bảng ServiceMechanic
            String deleteServiceMechanicSQL = "DELETE FROM ServiceMehanic WHERE serviceID = ?";
            ps = conn.prepareStatement(deleteServiceMechanicSQL);
            ps.setInt(1, serviceID);
            ps.executeUpdate();
            ps.close();

            // Xóa service trong bảng Service
            String deleteServiceSQL = "DELETE FROM Service WHERE serviceID = ?";
            ps = conn.prepareStatement(deleteServiceSQL);
            ps.setInt(1, serviceID);

            success = ps.executeUpdate() > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return success;
}

}
