/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dto.MechanicDTO;
import dbutils.DBUtils;

/**
 *
 * @author user
 */
public class MechanicDAO {

    public MechanicDTO checkLogin(String name) {
        MechanicDTO mechanic = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT mechanicID, mechanicName FROM Mechanic WHERE mechanicName=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String mechanicID = rs.getString("mechanicID");
                    String mechanicName = rs.getString("mechanicName");
                    mechanic = new MechanicDTO(mechanicID, mechanicName);
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
        return mechanic;
    }
}
