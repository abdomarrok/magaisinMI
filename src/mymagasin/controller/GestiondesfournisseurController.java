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
import javafx.stage.Stage;
import static mymagasin.controller.LoginController.service_name;
import mymagasin.entitie.Article;
import mymagasin.entitie.Fournisseur;

/**
 * FXML Controller class
 *
 * @author on
 */
public class GestiondesfournisseurController implements Initializable {
      private Scene scene;
          private Parent root;

    @FXML
    private TableView<Fournisseur> tableview;
    @FXML
    private TableColumn<Fournisseur, Integer> firstC;
    @FXML
    private TableColumn<Fournisseur, String> secondC;
    @FXML
    private Button ADD;
    @FXML
    private Button UPDATE;
    @FXML
    private Button DELETE;
    @FXML
    private Label N_F_Label;
    @FXML
    private TextField N_F_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        firstC.setCellValueFactory(new PropertyValueFactory<>("id_fournisseur"));
        secondC.setCellValueFactory(new PropertyValueFactory<>("nom_fournisseur"));
        tableview.setItems(getFournisseur());
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }    

    private ObservableList<Fournisseur> getFournisseur() {
         ObservableList<Fournisseur> fournisseur=FXCollections.observableArrayList();
        try {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT * FROM fournisseur";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            int t_id_fournisseur= resultSet.getInt("id_fournisseur");
            String t_nom_fournisseur=resultSet.getString("nom_fournisseur");
            fournisseur.add(new Fournisseur(t_id_fournisseur,t_nom_fournisseur));
                    }
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(GestionDesArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fournisseur;
    }

    @FXML
    private void addFournisseur(MouseEvent event) throws SQLException {
          String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                statement.execute("insert into fournisseur (nom_fournisseur) value (" + "'" + N_F_txt.getText() + "')" );   
            }   
            updateFournisseur(event);
    }

    @FXML
    private void editFournisseur(MouseEvent event) throws SQLException {
          String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement(); 
           statement.execute("update fournisseur set nom_fournisseur= '"+ N_F_txt.getText()+"'"
                   +"where id_fournisseur = " +getSelectedFournisseur(event));
                            updateFournisseur(event);
            }
    }
    
     @FXML
    private int getSelectedFournisseur(MouseEvent event) throws SQLException {
        int s=tableview.getSelectionModel().getSelectedIndex();
     int id_to_edit =firstC.getCellData(s);
     N_F_txt.setText(secondC.getCellData(s));
     return id_to_edit;
    }

    @FXML
    private void deleteFournisseur(MouseEvent event) throws SQLException {
         int s=tableview.getSelectionModel().getSelectedIndex();
     int id_to_remove =firstC.getCellData(s);
     String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                statement.execute("delete from fournisseur where id_fournisseur = " + id_to_remove);
            }      
            updateFournisseur(event);
     
    }

    @FXML
    private void updateFournisseur(MouseEvent event) throws SQLException {
       ObservableList<Fournisseur> fournisseur=FXCollections.observableArrayList();
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT * FROM fournisseur";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            int t_id_fournisseur= resultSet.getInt("id_fournisseur");
            String t_nom_fournisseur=resultSet.getString("nom_fournisseur");
            fournisseur.add(new Fournisseur(t_id_fournisseur,t_nom_fournisseur));
                    }
           
            }   
       firstC.setCellValueFactory(new PropertyValueFactory<>("id_fournisseur"));
        secondC.setCellValueFactory(new PropertyValueFactory<>("nom_fournisseur"));
        tableview.setItems(getFournisseur());
        N_F_txt.setText("");
   
                
   
    }

    /**
     *
     * @param event
     */
    public void showMenuPrinsipal2(MouseEvent event){
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
}
