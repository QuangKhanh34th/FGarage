/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
                      long mechanicID = table.getLong("mechanicID");
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
    
    //SELECT mechanicID, mechanicName FROM Mechanic
    public static ArrayList<Mechanic> getAllMechanics() {
        ArrayList<Mechanic> mechanicList = new ArrayList<>();
        try {
            Connection connection = DBUtils.getConnection();
            
            if (connection != null) {
                String sql = "SELECT mechanicID, mechanicName FROM Mechanic";
                //use static statement because no parameter are required
                Statement st = connection.createStatement();
                ResultSet result = st.executeQuery(sql);
                
                //import result into cusList
                while (result.next()) {
                    long mechanicID = result.getLong("mechanicID");
                    String mechanicName = result.getString("mechanicName");
                    
                    Mechanic mechanic = new Mechanic(mechanicID, mechanicName);
                    mechanicList.add(mechanic);
                }
                
                System.out.println("[MechanicDAO.java] mechanicList first ID: " + mechanicList.get(0).getMechanicID());
            }
            
            //close connection
            if (connection != null) connection.close();
        } catch (Exception e) {
            System.out.println("Unexpected error occured in MechanicDAO.getAllMechanics()");
        }
        
        return mechanicList;
    }

}
