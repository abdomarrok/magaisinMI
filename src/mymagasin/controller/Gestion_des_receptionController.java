/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mymagasin.entitie.Article;
import mymagasin.entitie.BonLivrison;

/**
 * FXML Controller class
 *
 * @author on
 */
public class Gestion_des_receptionController implements Initializable {

    @FXML
    private TextField U_BL_txt;
    @FXML
    private TextField Q_BL_txt;
    @FXML
    private DatePicker D_BL_piker;
    @FXML
    private ComboBox<String> N_A_CB;
    @FXML
    private ComboBox<Integer> id_BC_CB;
    @FXML
    private ComboBox<String> N_F_CB;
    @FXML
    private TextField N_A_BR_txt;
    @FXML
    private TextField U_BR_txt;
    @FXML
    private TextField Q_BR_txt;
    @FXML
    private TextField N_F_txt;
    @FXML
    private DatePicker D_R_piker;
    @FXML
    private ComboBox<Integer> id_BL_CB;
    ObservableList<String> Articls_option=getNomArticles();
     ObservableList<String> N_F_option=getNF();
     ObservableList<Integer> id_BC_option=getBC();
    @FXML
    private TableView<BonLivrison> tableview;
    @FXML
    private TableColumn<BonLivrison, Integer> id_bl;
    @FXML
    private TableColumn<BonLivrison, String> date_livrison;
    @FXML
    private TableColumn<BonLivrison, String> nom_article;
    @FXML
    private TableColumn<BonLivrison, String> qauntitie;
    @FXML
    private TableColumn<BonLivrison, String> unitie;
    @FXML
    private TableColumn<BonLivrison, Integer> id_bc;
    @FXML
    private TableColumn<BonLivrison, String> nom_fournisseur;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_bl.setCellValueFactory(new PropertyValueFactory<>("id_bl"));
        id_bc.setCellValueFactory(new PropertyValueFactory<>("id_bc"));
        nom_article.setCellValueFactory(new PropertyValueFactory<>("nom_article"));
        qauntitie.setCellValueFactory(new PropertyValueFactory<>("qauntitie"));
        unitie.setCellValueFactory(new PropertyValueFactory<>("unitie"));
        nom_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nom_fourniseur"));
        date_livrison.setCellValueFactory(new PropertyValueFactory<>("date_livrison"));
         tableview.setItems(getBonLivrison());
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        N_A_CB.setItems(Articls_option);
        N_F_CB.setItems(N_F_option);
       id_BC_CB.setItems(id_BC_option);
      
               }
    @FXML
    private void addBL(MouseEvent event) throws SQLException {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                statement.execute("INSERT INTO bon_livrison(date_de_livrison,nom_article,qauntitie,unitie,id_bc,nom_fournisseur) VALUES ("
                + "'" + D_BL_piker.getValue() + "',"
                + "'" + N_A_CB.getValue() + "',"
                + "'" + Q_BL_txt.getText() + "',"
                + "'" + U_BL_txt.getText() + "',"        
                + "'" + id_BC_CB.getValue() + "',"        
                + "'" + N_F_CB.getValue() + "')");   
            }      
            updateBL(event);
            tableview.getSelectionModel().selectLast();
            
    }
    private ObservableList<String> getNomArticles() {
      ObservableList<String> articls=FXCollections.observableArrayList();
        try {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT nom_article FROM article";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            String t_nom_article=resultSet.getString("nom_article");
           
            articls.add(t_nom_article);
                    }
            }      
        } catch (SQLException ex) {
            Logger.getLogger(GestionDesArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articls;
    }

    private ObservableList<String> getNF() {
         ObservableList<String> nomfournisseur=FXCollections.observableArrayList();
        try {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT nom_fournisseur FROM fournisseur";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            String t_nomfournisseur=resultSet.getString("nom_fournisseur");
           
            nomfournisseur.add(t_nomfournisseur);
                    }
            }      
        } catch (SQLException ex) {
            Logger.getLogger(GestionDesArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomfournisseur;
      
    }

    private ObservableList<Integer> getBC() {
         ObservableList<Integer> id_BC=FXCollections.observableArrayList();
        try {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT * FROM `bon_comande`";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            int t_id_BC=resultSet.getInt("id_bc");
           
            id_BC.add(t_id_BC);
                    }
            }      
        } catch (SQLException ex) {
            Logger.getLogger(GestionDesArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_BC;
    }

    private ObservableList<BonLivrison> getBonLivrison() {
         ObservableList<BonLivrison> bl=FXCollections.observableArrayList();
        try {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT * FROM bon_livrison";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            int t_id_bl= resultSet.getInt("id_bl");
            int t_id_bc= resultSet.getInt("id_bc");
            Date t_date_livrison=resultSet.getDate("date_de_livrison");
            String t_nom_article=resultSet.getString("nom_article");
            String t_qauntitie=resultSet.getString("qauntitie");
            String t_unitie=resultSet.getString("unitie"); 
            String t_nom_fournisseur=resultSet.getString("nom_fournisseur");
            bl.add(new BonLivrison(t_id_bl,t_id_bc,t_date_livrison,t_nom_article,t_qauntitie,t_unitie,t_nom_fournisseur));
                    }
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(GestionDesArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bl; //To change body of generated methods, choose Tools | Templates.
    }
    
    private void updateBL(MouseEvent event) throws SQLException {
          ObservableList<BonLivrison> bl=FXCollections.observableArrayList();
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT * FROM bon_livrison";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            int t_id_bl= resultSet.getInt("id_bl");
            int t_id_bc= resultSet.getInt("id_bc");
            Date t_date_livrison=resultSet.getDate("date_de_livrison");
            String t_nom_article=resultSet.getString("nom_article");
            String t_qauntitie=resultSet.getString("qauntitie");
            String t_unitie=resultSet.getString("unitie"); 
            String t_nom_fournisseur=resultSet.getString("nom_fournisseur");
            bl.add(new BonLivrison(t_id_bl,t_id_bc,t_date_livrison,t_nom_article,t_qauntitie,t_unitie,t_nom_fournisseur));
                    }
            }
         id_bl.setCellValueFactory(new PropertyValueFactory<>("id_bl"));
        id_bc.setCellValueFactory(new PropertyValueFactory<>("id_bc"));
        nom_article.setCellValueFactory(new PropertyValueFactory<>("nom_article"));
        qauntitie.setCellValueFactory(new PropertyValueFactory<>("qauntitie"));
        unitie.setCellValueFactory(new PropertyValueFactory<>("unitie"));
        nom_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nom_fourniseur"));
        date_livrison.setCellValueFactory(new PropertyValueFactory<>("date_livrison"));
         tableview.setItems(getBonLivrison());
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      U_BL_txt.setText("");
      Q_BL_txt.setText("");  
      D_BL_piker.setValue(LocalDate.now());
      N_A_CB.setValue("");
      N_F_CB.setValue("");
      id_BC_CB.setValue(0);
    }
}
