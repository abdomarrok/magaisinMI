/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.entitie;

/**
 *
 * @author on
 */
public class Article {
    private String  nom_article;
    private int qauntitie;
    private String unitie;
    public String nom_category;

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public int getQauntitie() {
        return qauntitie;
    }

    public void setQauntitie(int qauntitie) {
        this.qauntitie = qauntitie;
    }

    public String getUnitie() {
        return unitie;
    }

    public void setUnitie(String unitie) {
        this.unitie = unitie;
    }

    public String getNom_category() {
        return nom_category;
    }

    public void setNom_category(String nom_category) {
        this.nom_category = nom_category;
    }
    
}
