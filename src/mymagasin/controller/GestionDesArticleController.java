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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static mymagasin.controller.LoginController.service_name;
import mymagasin.entitie.Article;

/**
 * FXML Controller class
 *
 * @author on
 */
public class GestionDesArticleController implements Initializable {
     private Scene scene;
          private Parent root;

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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id_article.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        nom_article.setCellValueFactory(new PropertyValueFactory<>("nom_article"));
        qauntitie.setCellValueFactory(new PropertyValueFactory<>("qauntitie"));
        unitie.setCellValueFactory(new PropertyValueFactory<>("unitie"));
        nom_category.setCellValueFactory(new PropertyValueFactory<>("nom_category"));
        tableview.setItems(getArticles());
    }    

   
    private ObservableList<Article> getArticles() {
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
    public void showMenuPrinsipal(MouseEvent event){
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
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void addArticle(MouseEvent event) throws SQLException {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                statement.execute("insert into article (nom_article,qauntitie,unitie,nom_category) value ("
                + "'" + N_A_txt.getText() + "',"
                + "'" + Integer.valueOf(Q_txt.getText()) + "',"
                + "'" + U_txt.getText() + "',"
                + "'" + N_C_txt.getText() + "')");   
            }      
            updateArticle1(event);
    }
    

    private void updateArticle1(MouseEvent event) throws SQLException {
       ObservableList<Article> articls=FXCollections.observableArrayList();
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
            id_article.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        nom_article.setCellValueFactory(new PropertyValueFactory<>("nom_article"));
        qauntitie.setCellValueFactory(new PropertyValueFactory<>("qauntitie"));
        unitie.setCellValueFactory(new PropertyValueFactory<>("unitie"));
        nom_category.setCellValueFactory(new PropertyValueFactory<>("nom_category"));
        tableview.setItems(getArticles());
        N_A_txt.setText("");
     Q_txt.setText("");
     U_txt.setText("");
     N_C_txt.setText("");
                
   
    }

    @FXML
    private void deleteArticle(MouseEvent event) throws SQLException {
        int s=tableview.getSelectionModel().getSelectedIndex();
     int id_to_remove =id_article.getCellData(s);
     String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                statement.execute("delete from article where id_article = " + id_to_remove);
            }      
            updateArticle1(event);
     
    }
    @FXML
    private int getSelectedArticle(MouseEvent event) throws SQLException {
        int s=tableview.getSelectionModel().getSelectedIndex();
     int id_to_edit =id_article.getCellData(s);
     N_A_txt.setText(nom_article.getCellData(s));
     Q_txt.setText(qauntitie.getCellData(s));
     U_txt.setText(unitie.getCellData(s));
     N_C_txt.setText(nom_category.getCellData(s));
     return id_to_edit;
    }

    @FXML
    private void editArticle(MouseEvent event) throws SQLException {
        String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement(); 
           statement.execute("update article set nom_article= '"+ N_A_txt.getText()+"',"
                        + "qauntitie = '"+ Integer.valueOf(Q_txt.getText()) +"',"
                        + "unitie = '"+ U_txt.getText()+"',"
                        +"nom_category='"+ N_C_txt.getText()+
                         "' where id_article = " +getSelectedArticle(event));
                            updateArticle1(event);
            }
    }
   
    
    
}
