/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ReportDAO {
  
    private Connection cn;
    PreparedStatement pt = null;
    ResultSet rs = null;

    // Thống kê số lượng xe bán theo từng năm
    public Map<Integer, Integer> getCarsSoldByYear() throws SQLException, ClassNotFoundException {
        Map<Integer, Integer> carsSoldByYear = new HashMap<>();

        try {
            cn = utils.DBUtils.getConnection();
            String sql = "SELECT YEAR(invoiceDate) AS year, COUNT(*) AS total FROM SalesInvoice GROUP BY YEAR(invoiceDate) ORDER BY year";
            pt = cn.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                carsSoldByYear.put(rs.getInt("year"), rs.getInt("total"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pt != null) {
                pt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return carsSoldByYear;
    }

    // Thống kê mẫu xe bán chạy nhất
    public ArrayList<String[]> getBestSellingCarModels() throws SQLException {
        ArrayList<String[]> modelBestSelling = new ArrayList<>();
        int maxSales = 0;
        try {
            cn = utils.DBUtils.getConnection();
            String sql = "SELECT model, total FROM (\n"
                    + "    SELECT model, COUNT(*) AS total, DENSE_RANK() OVER (ORDER BY COUNT(*) DESC) AS rnk\n"
                    + "    FROM Cars \n"
                    + "    JOIN SalesInvoice ON Cars.carID = SalesInvoice.carID \n"
                    + "    GROUP BY model\n"
                    + ") ranked WHERE rnk = 1;";
            pt = cn.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                String model = rs.getString("model");
                String total = String.valueOf(rs.getInt("total")); // Chuyển int -> String
                modelBestSelling.add(new String[]{model, total});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pt != null) {
                pt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

        return modelBestSelling;
    }

// Thống kê phụ tùng được sử dụng nhiều nhất
    public ArrayList<String[]> getBestUsedParts() throws SQLException {
        ArrayList<String[]> bestUsedParts = new ArrayList<>();
        try {
            cn = utils.DBUtils.getConnection();
            //String sql = "SELECT partName, COUNT(*) AS usage FROM PartsUsed Join Parts On Parts.partID = PartsUsed.partID GROUP BY partName ORDER BY usage DESC";
            String sql = "SELECT partName, usage FROM (\n"
                    + "                       SELECT partName, COUNT(*) AS usage, DENSE_RANK() OVER (ORDER BY COUNT(*) DESC) AS rnk\n"
                    + "                        FROM PartsUsed \n"
                    + "                      JOIN Parts ON Parts.partID = PartsUsed.partID \n"
                    + "                        GROUP BY partName\n"
                    + "                    ) ranked WHERE rnk = 1;";
            pt = cn.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                String partName = rs.getString("partName");
                String usage = String.valueOf(rs.getInt("usage"));
                bestUsedParts.add(new String[]{partName, usage});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pt != null) {
                pt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

        return bestUsedParts;
    }

    // Tìm 3 thợ máy sửa chữa nhiều nhất
    public Map<String, Integer> getTopMechanics() throws SQLException {
        Map<String, Integer> topMechanics = new LinkedHashMap<>();

        try {
            cn = utils.DBUtils.getConnection();
            String sql = "SELECT TOP 3 m.mechanicName, COUNT(*) AS total_repairs "
           + "FROM Mechanic m "
           + "JOIN ServiceMechanic s ON m.mechanicID = s.mechanicID "
           + "GROUP BY m.mechanicName "
           + "ORDER BY total_repairs DESC";

            pt = cn.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                topMechanics.put(rs.getString("mechanicName"), rs.getInt("total_repairs"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pt != null) {
                pt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return topMechanics;
    }
}