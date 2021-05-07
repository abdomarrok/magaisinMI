
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
    
    
    public void initServiceName(String s){
           service_name.setText(s);
        
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
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}