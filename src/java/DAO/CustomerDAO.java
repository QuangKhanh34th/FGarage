/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
