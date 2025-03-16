/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbutils.DBUtils;
import dto.CustomerDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Horwlt
 */
public class CustomerDAO {

    public CustomerDTO checkLogin(String name, String phone) {
        CustomerDTO customer = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT custID, custName, phone, sex, cusAddress "
                        + "FROM Customer WHERE custName=? AND phone=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, phone);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String custID = rs.getString("custID");
                    String custName = rs.getString("custName");
                    String custPhone = rs.getString("phone");
                    String sex = rs.getString("sex");
                    String cusAddress = rs.getString("cusAddress");

                    customer = new CustomerDTO(custID, custName, custPhone, sex, cusAddress);
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
        return customer;
    }

    public boolean updateCustomerProfile(CustomerDTO customer) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean updated = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE Customer SET custName=?, phone=?, sex=?, cusAddress=? WHERE custID=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, customer.getCustName());
                ps.setString(2, customer.getPhone());
                ps.setString(3, customer.getSex());
                ps.setString(4, customer.getCusAddress());
                ps.setString(5, customer.getCustID());

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    updated = true;
                }
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }
}
