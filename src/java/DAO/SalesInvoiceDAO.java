/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.SalesInvoice;

/**
 *
 * @author USER
 */
public class SalesInvoiceDAO {

    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;

    public ArrayList<SalesInvoice> getAllInvoice() throws SQLException {
        ArrayList<SalesInvoice> salesInvoiceList = new ArrayList<>();
        try {
            cn = utils.DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM SalesInvoice";
                pt = cn.prepareStatement(sql);
                rs = pt.executeQuery();
                while (rs != null && rs.next()) {

                    salesInvoiceList.add(new SalesInvoice(rs.getInt("invoiceID"), rs.getDate("invoiceDate"), rs.getBigDecimal("salesID"), rs.getBigDecimal("carID"), rs.getBigDecimal("custID")));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SQLException("Lỗi khi lấy danh sách hóa đơn: " + ex.getMessage());

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pt != null) {
                    pt.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return salesInvoiceList;
    }

    public void addSalesInvoice(BigDecimal salesID, BigDecimal carID, BigDecimal custID) throws SQLException {
        try {
            cn = utils.DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO SalesInvoice (invoiceDate, salesID, carID, custID) values (?,?,?,?) ";
                pt = cn.prepareStatement(sql);
                pt.setDate(1, new Date(System.currentTimeMillis()));
                pt.setBigDecimal(2, salesID);
                pt.setBigDecimal(3, carID);
                pt.setBigDecimal(4, custID);
                pt.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SQLException("Lỗi khi thêm hóa đơn: " + ex.getMessage());

        } finally {
            if (pt != null) {
                pt.close();
            }

            if (cn != null) {
                cn.close();
            }
        }
    }

}
