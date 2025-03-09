/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import model.Part;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class PartDAO {

    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;

    public ArrayList<Part> getAllParts() throws SQLException {
        ArrayList<Part> partList = new ArrayList<>();
        try {
            cn = utils.DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Parts";
                pt = cn.prepareStatement(sql);
                rs = pt.executeQuery();
                while (rs.next()) {
                    partList.add(new Part(rs.getInt("partID"), rs.getString("partName"), rs.getDouble("purchasePrice"), rs.getDouble("retailPrice")));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Lỗi khi lấy danh sách phụ tùng: " + ex.getMessage());

        } finally {

            if (rs != null) {
                rs.close();
            }
            if (pt != null) {
                pt.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return partList;
    }

    public void addPart(Part part) throws SQLException, Exception {
        try {
            cn = utils.DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT Into Parts (partName, purchasePrice, retailPrice) VALUES (?,?,?)";
                pt = cn.prepareStatement(sql);
                pt.setString(1, part.getPartName());
                pt.setDouble(2, part.getPurchasePrice());
                pt.setDouble(3, part.getRetailPrice());
                pt.executeUpdate();
                System.out.println("Thêm thành công");
            }
        } catch (Exception ex) {
            System.out.println("Lỗi SQL: " + ex.getMessage());
            System.out.println("Lỗi khi thêm phụ tùng: " + ex.getMessage());

            throw ex;
        } finally {

            if (pt != null) {
                pt.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public void updatePart(Part part) throws SQLException {
        try {
            cn = utils.DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Parts SET partName=?, purchasePrice=?, retailPrice=? WHERE partID=?";
                pt = cn.prepareStatement(sql);
                pt.setString(1, part.getPartName());
                pt.setDouble(2, part.getPurchasePrice());
                pt.setDouble(3, part.getRetailPrice());
                pt.setInt(4, part.getPartID());
                pt.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("SQL Error: " + ex.getMessage());
        } finally {

            if (pt != null) {
                pt.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public void deletePart(int id) throws SQLException {
        try {
            cn = utils.DBUtils.getConnection();
            if (cn != null) {
                String sql = "DELETE FROM Parts WHERE partID=?";
                pt = cn.prepareStatement(sql);
                pt.setInt(1, id);
                pt.executeUpdate();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (pt != null) {
                pt.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public ArrayList<Part> getPartByName(String name) throws SQLException {
        ArrayList<Part> partList = new ArrayList<>();
        try {
            cn = utils.DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Parts WHERE partName LIKE LOWER(?)";
                pt = cn.prepareStatement(sql);
                pt.setString(1, "%" + name + "%");
                rs = pt.executeQuery();
                while (rs.next()) {
                    partList.add(new Part(rs.getInt("partID"), rs.getString("partName"), rs.getDouble("purchasePrice"), rs.getDouble("retailPrice")));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (pt != null) {
                pt.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return partList;
    }

    public ArrayList<Part> getPartByID(int id) throws SQLException {
        ArrayList<Part> partList = new ArrayList<>();
        try {
            cn = utils.DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Parts WHERE partID=?";
                pt = cn.prepareStatement(sql);
                pt.setInt(1, id);
                rs = pt.executeQuery();
                while (rs.next()) {
                    partList.add(new Part(rs.getInt("partID"), rs.getString("partName"), rs.getDouble("purchasePrice"), rs.getDouble("retailPrice")));
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pt != null) {
                pt.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return partList;
    }
}
