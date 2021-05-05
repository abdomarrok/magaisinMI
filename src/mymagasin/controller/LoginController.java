
package mymagasin.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Login Controller.
 */
public class LoginController extends AnchorPane implements Initializable {

    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;
    
    private Scene scene;
    private Parent root;
    private Node no;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
        userId.setPromptText("username");
        password.setPromptText("password");
        
    }        
    @FXML
    public void processLogin(ActionEvent event) throws IOException {  
        
      boolean login_test=false;
         try {
         String user = userId.getText(); String pass = password.getText();
             String url="jdbc:mysql://localhost:3306/mystock";
                Properties info = new Properties();
             info.put("user", "root");
             info.put("password", "");
        Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT * FROM user where username="+"'"+user+"' AND pass='"+pass+"'";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
            while(resultSet.next())
                    {     
            String pass1= resultSet.getString("pass");
            String user1=resultSet.getString("username");
            System.out.println(user1+"  "+pass1);
                if(user.equals(user1)&&pass.equals(pass1)){
                    System.out.println("login secsuss"); 
                        login_test=true;
                     showProfile(event);
                 }                                               
                    }
                                   }
            if(!login_test){
                System.out.println("login failed");
            }
    }catch (SQLException ex) {
      System.out.println("An error occurred while connecting MySQL databse"+ex.getMessage());
      
    }
     
    }

    
    public void showProfile(ActionEvent event){
        try {
            root =FXMLLoader.load(getClass().getResource("/mymagasin/fxml_files/menuPrinsipal.fxml"));
            scene = new Scene(root); 
            Stage  stage;
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
  
 }
