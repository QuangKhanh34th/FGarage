/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class DBUtils {
    public static Connection getConnection() throws Exception {
        Connection cn = null;
        String IP = "localhost";
        String instanceName = "QUANGKHANH";
        //TCP/IP port of the dbms
        String port = "1433";
        String userName = "sa";
        String pwd = "12345";
        String dbName = "Car_DealerShip";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port + 
                ";databasename=" + dbName + ";user=" +userName + ";password=" + pwd;
        
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn; 
    }
}
