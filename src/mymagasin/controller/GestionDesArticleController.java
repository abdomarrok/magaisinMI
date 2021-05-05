/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mymagasin.entitie.Article;

/**
 * FXML Controller class
 *
 * @author on
 */
public class GestionDesArticleController implements Initializable {

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

    @FXML
    private void buildData(MouseEvent event) {
    }

    @FXML
    private void buildData(ActionEvent event) {
    }

    private ObservableList<Article> getArticles() {
        
        ObservableList<Article> articls=FXCollections.observableArrayList();
        articls.add(new Article(1,"nomarticle" , "q", "u", "nomcategory"));
        
        return articls;
        
    }
    
}
