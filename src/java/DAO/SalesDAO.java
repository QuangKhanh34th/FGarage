/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Customer;
import model.SalesPerson;
import utils.DBUtils;

/**
 *
 * @author user
 */
public class SalesDAO {
    public SalesPerson checkLogin(String name){
        SalesPerson rs=null;
        Connection cn=null;
        try{
          cn=DBUtils.getConnection();
          if(cn!=null){
              String sql = "select salesID,salesName,birthday,sex,salesAddress\n"
                      + "from dbo.SalesPerson\n"
                      + "where salesName = ?";
              PreparedStatement st=cn.prepareStatement(sql);
              st.setString(1, name);
              ResultSet table=st.executeQuery();
                if(table!=null){
                  while(table.next()){
                      int salesID = table.getInt("salesID");
                      String salesName = table.getString("salesName");
                      String birthday = table.getString("birthday");
                      String sex = table.getString("sex");
                      String salesAddress = table.getString("salesAddress");
                      
                      rs=new SalesPerson(salesID, salesName, birthday, sex, salesAddress);
                      
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
            System.out.println("Unexpected error occured in SalesDAO.getAllSales()");
        }
        
        return cusList;
    }
}
