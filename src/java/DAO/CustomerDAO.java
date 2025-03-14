/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class CustomerDAO {
    public Customer checkLogin(String name, String phone){
        Customer rs=null;
        Connection cn=null;
        
        try{
          cn=DBUtils.getConnection();
          if(cn!=null){
              String sql = "select custID,custName,phone,sex,cusAddress\n"
                      + "from dbo.Customer\n"
                      + "where custName = ? and phone = ?";
              PreparedStatement st=cn.prepareStatement(sql);
              st.setString(1, name);
              st.setString(2, phone);
              ResultSet table=st.executeQuery();
                if(table!=null){
                  while(table.next()){
                      int custID = table.getInt("custID");
                      String custName = table.getString("custName");
                      String cusPhone = table.getString("phone");
                      String sex = table.getString("sex");
                      String cusAddress = table.getString("cusAddress");
                      
                      rs=new Customer(custID, custName, cusPhone, sex, cusAddress);
                      
                  }
              }
          }
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(cn!=null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
    
    
    
    public static ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> cusList = new ArrayList<>();
        try {
            Connection connection = DBUtils.getConnection();
            
            if (connection != null) {
                String sql = "select custID, custName, phone, sex, cusAddress from Customer";
                //use static statement because no parameter are required
                Statement st = connection.createStatement();
                ResultSet result = st.executeQuery(sql);
                
                //import result into cusList
                while (result.next()) {
                    int id = result.getInt("custID");
                    String custName = result.getString("custName");
                    String phone = result.getString("phone");
                    //init gender for proper displaying
                    String gender;
                    String sex = result.getString("sex");
                    if (sex.contains("F")) {
                        gender = "Female";
                    } else if (sex.contains("M")) {
                        gender = "Male";
                    //for error-detecting purposes
                    } else {
                        gender = "undefined";
                    }
                    String cusAddress = result.getString("cusAddress");
                    
                    Customer customer = new Customer(id, custName, phone, gender, cusAddress);
                    cusList.add(customer);
                }
            }
            
            //close connection
            if (connection != null) connection.close();
        } catch (Exception e) {
            System.out.println("Unexpected error occured in CustomerDAO.getAllCustomers()");
        }
        
        return cusList;
    }
    
    public static int addCustomer(Customer target) {
        int result = 0;
        Connection cn = null;
        
        try {
            cn=DBUtils.getConnection();
            if (cn!=null) {
                String sql = "INSERT INTO Customer (custName, phone, sex, cusAddress) VALUES (?, ?, ?, ?)";
                PreparedStatement pStatement = cn.prepareStatement(sql);
                pStatement.setString(1, target.getCustName());
                pStatement.setString(2, target.getPhone());
                pStatement.setString(3, target.getSex());
                pStatement.setString(4, target.getCusAddress());
                result = pStatement.executeUpdate();
                pStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[CustomerDAO.java] error adding Customer");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        return result;
    }
    
    public static int deleteCustomer(int targetID) {
        int result = 0;
        Connection cn = null;
        
        try {
            cn=DBUtils.getConnection();
            if (cn!=null) {
                String sql = "DELETE FROM Customer WHERE custID = " + targetID;
                Statement statement = cn.createStatement();
                result = statement.executeUpdate(sql);
                statement.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[CustomerDAO.java] error removing Customer");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static int updateCustomer(Customer target) {
        int result = 0;
        Connection cn = null;
        
        try {
            cn=DBUtils.getConnection();
            if (cn!=null) {
                String sql = "UPDATE Customer SET custName = ?, phone = ?, sex = ?, cusAddress = ? WHERE custID = " + target.getCustID();
                PreparedStatement pStatement = cn.prepareStatement(sql);
                pStatement.setString(1, target.getCustName());
                pStatement.setString(2, target.getPhone());
                pStatement.setString(3, target.getSex());
                pStatement.setString(4, target.getCusAddress());
                result = pStatement.executeUpdate();
                pStatement.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[CustomerDAO.java] error updating Customer");
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    //function to check for existing customer name (trimmed) or phone
    //return true if name or phone is duplicate
    public boolean customerExists(String custName, String phone) {
        Connection cn = null;
        boolean exists = false;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT 1 FROM Customer WHERE REPLACE(custName, ' ', '') = ? OR phone = ?";
                PreparedStatement pStatement = cn.prepareStatement(sql);
                pStatement.setString(1, custName);
                pStatement.setString(2, phone);
                ResultSet rs = pStatement.executeQuery();
                exists = rs.next();
            } else {
                throw new Exception("Database connection is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return exists;
    }
}
