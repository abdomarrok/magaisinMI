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
public class BonCommande {
    private SimpleStringProperty nom_article,qauntitie,unitie;
     public BonCommande(String nom_article, String qauntitie, String unitie) {
        this.nom_article = new SimpleStringProperty(nom_article);
        this.qauntitie = new SimpleStringProperty(qauntitie);
        this.unitie = new SimpleStringProperty(unitie);
      
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
}
