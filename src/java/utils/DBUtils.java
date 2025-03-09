/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

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
        
        /*
        Connection cn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Car_Dealership;encrypt=false;trustServerCertificate=true";
        Properties props = new Properties();
        props.setProperty("user", "sa");
        props.setProperty("password", "12345");
        props.setProperty("useUnicode", "true");
        props.setProperty("characterEncoding", "UTF-8");
        cn = DriverManager.getConnection(url, props);
        return cn;
*/
    }
}
