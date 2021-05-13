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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mymagasin.entitie.BonCommande;
import static mymagasin.controller.LoginController.service_name;

/**
 * FXML Controller class
 *
 * @author on
 */
public class BonCommandeController implements Initializable {

    @FXML
    private Label bon_demande;
    @FXML
    private ComboBox<String> articleCB;
    @FXML
    private TextField Q_txt;
    @FXML
    private ComboBox<String> U_txt;
    @FXML
    private DatePicker DatePiker;
    @FXML
    private TextArea destinataire_txt;
    @FXML
    private TableView<BonCommande> tableview;
    @FXML
    private TableColumn<BonCommande,String> articleColumn;
    @FXML
    private TableColumn<BonCommande,String> qauntitieColmun;
    @FXML
    private TableColumn<BonCommande,String> unitieColmun;
    @FXML
    private Button print_Btn;
    @FXML
    private Button save_Btn;
    @FXML
    private Button validate_Btn;
    @FXML
    private Label nom_article_Label;
    @FXML
    private Label qauntitie_Label;
    @FXML
    private Label unitie_Label;
     ObservableList<String> Articls_option=getNomArticles();
     ObservableList<String> Unitie_option=getUnitie();
     ObservableList<BonCommande> boncommande=FXCollections.observableArrayList();
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
        articleCB.setItems(Articls_option);
        U_txt.setItems(Unitie_option);
        articleColumn.setCellValueFactory(new PropertyValueFactory<>("nom_article"));
        qauntitieColmun.setCellValueFactory(new PropertyValueFactory<>("qauntitie"));
        unitieColmun.setCellValueFactory(new PropertyValueFactory<>("unitie"));
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

    private ObservableList<String> getUnitie() {
        ObservableList<String> unitie=FXCollections.observableArrayList();
        try {
            String url="jdbc:mysql://localhost:3306/mystock";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");
            Connection dbConnection = (Connection) DriverManager.getConnection(url, info);
            if (dbConnection != null) {
                Statement statement =(Statement) dbConnection.createStatement();
                String query;
                query = "SELECT unitie FROM article";
                ResultSet resultSet =statement.executeQuery(query);
                System.out.println("Successfully connected to MySQL database test");
                while(resultSet.next())
                    {     
            String t_unitie_article=resultSet.getString("unitie");
           
            unitie.add(t_unitie_article);
                    }
            }      
        } catch (SQLException ex) {
            Logger.getLogger(GestionDesArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unitie;
        
    }
    
    @FXML
    private void add_to_table(MouseEvent event) {
        Add_Articles();
        tableview.setItems(boncommande);
        articleCB.setValue("");
        Q_txt.setText("");
        U_txt.setValue("");
    }
  
    @FXML
     private ObservableList<BonCommande> Add_Articles() {
         
    
            String t_nom_article=articleCB.getValue();
            String t_qauntitie=Q_txt.getText();
            String t_unitie=U_txt.getValue();
            
            boncommande.add(new BonCommande(t_nom_article , t_qauntitie, t_unitie));
          
        return boncommande;
    }
     @FXML
     private void removeSelectedArticle(MouseEvent event){
        int s=tableview.getSelectionModel().getSelectedIndex();
         articleCB.setValue(articleColumn.getCellData(s));
        Q_txt.setText(qauntitieColmun.getCellData(s));
        U_txt.setValue(unitieColmun.getCellData(s));
        tableview.getItems().remove(s);
        articleCB.setValue("");
        Q_txt.setText("");
        U_txt.setValue("");
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
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
