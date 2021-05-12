/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mymagasin.entitie.Fournisseur;
import mymagasin.entitie.Service;
import mymagasin.entitie.User;

/**
 * FXML Controller class
 *
 * @author on
 */
public class Gestion_des_utilisateursController implements Initializable {

    @FXML
    private TableView<Service> tableview;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> first_nameColumn;
    @FXML
    private TableColumn<User, String> last_nameColumn;
    @FXML
    private TableColumn<User, String> passColumn;
    @FXML
    private TableColumn<Service, String> service_nameColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        first_nameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        passColumn.setCellValueFactory(new PropertyValueFactory<>("pass"));
        service_nameColumn.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        tableview.setItems(getUsers());
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }    

    private ObservableList<Service> getUsers() {
         ObservableList<Service> users=FXCollections.observableArrayList();
        try {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT * FROM user";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            String t_username=resultSet.getString("username");
            String t_first_name=resultSet.getString("first_name");
            String t_last_name=resultSet.getString("last_name");
            String t_pass=resultSet.getString("pass");
            String t_servicename=resultSet.getString("service_name");
            users.add(new Service(t_username,t_first_name,t_last_name,t_pass,t_servicename));
                    }
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(GestionDesArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
//To change body of generated methods, choose Tools | Templates.
    }
    
}
