/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.entitie;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author on
 */
public class Article {
   private SimpleStringProperty nom_article,qauntitie,unitie,nom_category;
   private int id_article;

    public Article(int id_article, String nom_article, String qauntitie, String unitie, String nom_category) {
        this.id_article = id_article;
        this.nom_article = new SimpleStringProperty(nom_article);
        this.qauntitie = new SimpleStringProperty(qauntitie);
        this.unitie = new SimpleStringProperty(unitie);
        this.nom_category = new SimpleStringProperty(nom_category);
       
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article=id_article;
    }

    public String getNom_article() {
        return nom_article.get();
    }

    public void setNom_article(String nom_article) {
        this.nom_article.set(nom_article);
    }

    public String getQauntitie() {
        return qauntitie.get();
    }

    public void setQauntitie(String qauntitie) {
        this.qauntitie.set(qauntitie);
    }

    public String getUnitie() {
        return unitie.get();
    }

    public void setUnitie(String unitie) {
        this.unitie.set(unitie);
    }

    public String getNom_category() {
        return nom_category.get();
    }

    public void setNom_category(String nom_category) {
        this.nom_category.set(nom_category);
    }
   
    
}
