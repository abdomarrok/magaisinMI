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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static mymagasin.controller.LoginController.user;
import mymagasin.entitie.Article;

/**
 * FXML Controller class
 *
 * @author on
 */
public class Bon_demandeController implements Initializable {

   @FXML
    private TableView<Article> tableview;
    @FXML
    private TableColumn<Article, Integer> id_article;
    @FXML
    private TableColumn<Article, String> nom_article;
    @FXML
    private TableColumn<Article, String> qauntitie;
    @FXML
    private TableColumn<Article, String> unitie;
    @FXML
    private TableColumn<Article, String> nom_category;
    @FXML
    private Label N_A_Label;
    @FXML
    private TextField N_A_txt;
    @FXML
    private TextField Q_txt;
    @FXML
    private Label Q_Label;
    @FXML
    private Label U_Label;
    @FXML
    private TextField U_txt;
    @FXML
    private TextField N_C_txt;
    @FXML
    private Label N_C_Label;
    @FXML
    private Label userlabel;
    @FXML
    private Label datelabel;
    private int s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userlabel.setText(user);
        datelabel.setText("Date : "+java.time.LocalDate.now().toString());
        id_article.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        nom_article.setCellValueFactory(new PropertyValueFactory<>("nom_article"));
        qauntitie.setCellValueFactory(new PropertyValueFactory<>("qauntitie"));
        unitie.setCellValueFactory(new PropertyValueFactory<>("unitie"));
        nom_category.setCellValueFactory(new PropertyValueFactory<>("nom_category"));
        tableview.setItems(getArticles());
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }   
    private  ObservableList<Article> getArticles() {
         ObservableList<Article> articls=FXCollections.observableArrayList();
        try {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT * FROM article";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            int t_id_article= resultSet.getInt("id_article");
            String t_nom_article=resultSet.getString("nom_article");
            String t_qauntitie=resultSet.getString("qauntitie");
            String t_unitie=resultSet.getString("unitie"); 
            String t_nom_category=resultSet.getString("nom_category");
            articls.add(new Article(t_id_article,t_nom_article , t_qauntitie, t_unitie, t_nom_category));
                    }
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(GestionDesArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articls;
    }

     @FXML
    private int getSelectedArticle(MouseEvent event) throws SQLException {
        s=tableview.getSelectionModel().getSelectedIndex();
     int id_to_edit =id_article.getCellData(s);
      N_A_txt.setText(nom_article.getCellData(s));
     Q_txt.setText("1");
     U_txt.setText(unitie.getCellData(s));
     N_C_txt.setText(nom_category.getCellData(s));
     return id_to_edit;
    }

    @FXML
    private void confirmDemande(MouseEvent event) throws SQLException {
          String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                statement.execute("INSERT INTO bon_domande(date_de_creation,username,id_article,nom_article,qauntitie,unitie)VALUES("
                + "'" + java.time.LocalDate.now().toString() + "',"
                + "'" + user + "',"
                + "'" + id_article.getCellData(s) + "',"
                + "'" + N_A_txt.getText()+ "',"
                + "'" + Q_txt.getText()+ "',"
                + "'" + U_txt.getText()+ "')");   
            }      
        
    }
    
}
