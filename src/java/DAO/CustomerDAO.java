/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Customer;
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
}
