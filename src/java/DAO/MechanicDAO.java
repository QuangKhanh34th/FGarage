/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Mechanic;
import model.Mechanic;
import utils.DBUtils;

/**
 *
 * @author ASUS
 */
public class MechanicDAO {
    public Mechanic checkLogin(String name){
        Mechanic rs=null;
        Connection cn=null;
        try{
          cn=DBUtils.getConnection();
          if(cn!=null){
              String sql = "select mechanicID,mechanicName\n"
                      + "from dbo.Mechanic\n"
                      + "where mechanicName = ?";
              PreparedStatement st=cn.prepareStatement(sql);
              st.setString(1, name);
              ResultSet table=st.executeQuery();
                if(table!=null){
                  while(table.next()){
                      int mechanicID = table.getInt("mechanicID");
                      String mechanicName = table.getString("mechanicName");
                      
                      rs=new Mechanic(mechanicID, mechanicName);
                      
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
