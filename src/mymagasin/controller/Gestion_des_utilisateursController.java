/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static mymagasin.controller.LoginController.service_name;
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
    @FXML
    private Button ADD;
    @FXML
    private Button UPDATE;
    @FXML
    private Button DELETE;
    @FXML
    private GridPane gridpane;
    @FXML
    private Label U_N_Label;
    @FXML
    private TextField U_N_txt;
    @FXML
    private TextField F_N_txt;
    @FXML
    private Label F_N_Label;
    @FXML
    private Label L_N_Label;
    @FXML
    private TextField L_N_txt;
    @FXML
    private TextField Pass_txt;
    @FXML
    private Label Pass_Label;
    @FXML
    private Label S_N_Label;
    @FXML
    private TextField S_N_txt;
     private Scene scene;
          private Parent root;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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

    @FXML
    private void addUser(MouseEvent event) throws SQLException {
         String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                statement.execute("insert into user (username,first_name,last_name,pass,service_name) "
                                                            + "value (" + "'" + U_N_txt.getText()+ "',"
                                                                        + "'" + F_N_txt.getText()+ "',"
                                                                        + "'" + L_N_txt.getText()+ "',"
                                                                        + "'" + Pass_txt.getText()+ "',"
                                                                        + "'" + S_N_txt.getText()+ "')" );   
                                                                                                 
            }
            updateUser(event);
    }

    @FXML
    private void editUser(MouseEvent event) throws SQLException {
          String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement(); 
           statement.execute("update user set username= '"+ U_N_txt.getText()+"',"
                        + "first_name = '"+F_N_txt.getText()+"',"
                        + "last_name = '"+ L_N_txt.getText()+"',"
                        +"pass='"+ Pass_txt.getText()+"',"
                        + "service_name='"+ S_N_txt.getText()+      
                         "' where username = " +"'"+getSelectedUser(event)+"'");
                            updateUser(event);
            }
    }

    @FXML
    private void deleteuser(MouseEvent event) throws SQLException {
         int s=tableview.getSelectionModel().getSelectedIndex();
     String user_to_remove =usernameColumn.getCellData(s);
     String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                statement.execute("delete from user where username = '" + user_to_remove+"'");
            }      
            updateUser(event);
    }

    @FXML
    private void showMenuPrinsipal3(MouseEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mymagasin/fxml_files/menuPrinsipal.fxml"));
        root = loader.load();  
            menuPrinsipalController controller = loader.getController();
            scene = new Scene(root);
            controller.initServiceName(service_name);
            Stage  stage;
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    private void updateUser(MouseEvent event) throws SQLException {
      ObservableList<Service> users=FXCollections.observableArrayList();
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
      usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        first_nameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        passColumn.setCellValueFactory(new PropertyValueFactory<>("pass"));
        service_nameColumn.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        tableview.setItems(getUsers());
       tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        U_N_txt.setText("");F_N_txt.setText("");L_N_txt.setText("");Pass_txt.setText("");S_N_txt.setText("");
   
                
   
    }

    @FXML
    private String getSelectedUser(MouseEvent event) {
          int s=tableview.getSelectionModel().getSelectedIndex();
     String id_to_edit =usernameColumn.getCellData(s);
     U_N_txt.setText(usernameColumn.getCellData(s));
     F_N_txt.setText(first_nameColumn.getCellData(s));
     L_N_txt.setText(last_nameColumn.getCellData(s));
     Pass_txt.setText(passColumn.getCellData(s));
     S_N_txt.setText(service_nameColumn.getCellData(s));
     return id_to_edit;
    }

}
