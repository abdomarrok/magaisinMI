/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.DBConnection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 *
 * @author on
 */
public  class DBConnection {
    private static String url="jdbc:mysql://localhost:3306/mystock";
   private static Properties info = new Properties();
            

    public DBConnection() {
            info.put("user", "root");
             info.put("password", "");
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public final  Connection getConnenction() throws SQLException{      
       Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
        return dbConnection;
}
        }
            
  
