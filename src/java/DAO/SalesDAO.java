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
    
    
}
