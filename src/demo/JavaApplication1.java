/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author on
 */
public class JavaApplication1 {

    
    public static void main(String[] args) throws SQLException {
      
         Connection dbConnection = null;

    try {
      String url = "jdbc:mysql://localhost:3306/mystock";
      Properties info = new Properties();
      info.put("user", "root");
      info.put("password", "");

      dbConnection = (Connection) DriverManager.getConnection(url, info);

      if (dbConnection != null) {
        System.out.println("Successfully connected to MySQL database test");
         Statement statement =(Statement) dbConnection.createStatement();
         String query ="SELECT * FROM user";
         String firstName;
        ResultSet resultSet =statement.executeQuery(query);
        while(resultSet.next()){
        firstName = resultSet.getString("pass");
       System.out.println(firstName+"");
     }
        dbConnection.close();
    }
    }catch (SQLException ex) {
      System.out.println("An error occurred while connecting MySQL databse");
    }
    
  }
  
      /*  String query ="SELECT * FROM `emp`";
ResultSet resultSet =statement.executeQuery(query);*/


        
    }
    

