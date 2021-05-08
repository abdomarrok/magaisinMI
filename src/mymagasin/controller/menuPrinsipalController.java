
package mymagasin.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class menuPrinsipalController extends AnchorPane implements Initializable {

   
         private Scene scene;
          private Parent root;
    @FXML
    private Label service_name;
    @FXML
    private Label gestion_des_fournisseur;
    @FXML
    private Label bon_demande;
    @FXML
    private Label bon_reception;
    @FXML
    private Label bon_sortie;
    @FXML
    private Label bon_comande;
    @FXML
    private Label etat_de_stock;
    @FXML
    private Label gestion_des_article;
    @FXML
    private AnchorPane menu_APane;
    @FXML
    private Label gestion_des_utilisateur;
    
    
    public void initServiceName(String s){
           service_name.setText(s);
           if(service_name.getText().equals("MG")){
               bon_comande.setVisible(false);
               gestion_des_utilisateur.setVisible(false);
           }
           if(service_name.getText().equals("SRV")){
               gestion_des_fournisseur.setVisible(false);
               bon_reception.setVisible(false);
               bon_comande.setVisible(false);
               bon_sortie.setVisible(false);
               gestion_des_article.setVisible(false); 
               gestion_des_utilisateur.setVisible(false);
           }
          
        
    }      
  
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
    }
    
    @FXML
    public void showLogin(MouseEvent event){
        try {
            root =FXMLLoader.load(getClass().getResource("/mymagasin/fxml_files/login.fxml"));
            scene = new Scene(root);
            Stage  stage;
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    @FXML
     public void showGestionArtice(MouseEvent event){
        try {
            root =FXMLLoader.load(getClass().getResource("/mymagasin/fxml_files/gestion_des_article.fxml"));
            scene = new Scene(root);  
            Stage  stage;
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
             stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
     public void showGestionFournisseur(MouseEvent event){
        try {
            root =FXMLLoader.load(getClass().getResource("/mymagasin/fxml_files/gestion_des_fournisseur.fxml"));
            scene = new Scene(root);  
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