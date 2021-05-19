/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.entitie;


import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author on
 */
public class BonLivrison {
    private int id_bl,id_bc;
    private Date date_livrison;
    private SimpleStringProperty nom_article,qauntitie,unitie,nom_fourniseur;

    public BonLivrison(int id_bl, int id_bc, Date date_livrison, String nom_article, String qauntitie, String unitie, String nom_fournisseur) {
        this.id_bl = id_bl;
        this.id_bc = id_bc;
        this.date_livrison = date_livrison;
         this.nom_article = new SimpleStringProperty(nom_article);
        this.qauntitie = new SimpleStringProperty(qauntitie);
        this.unitie = new SimpleStringProperty(unitie);
        this.nom_fourniseur = new SimpleStringProperty(nom_fournisseur);
    }



    public int getId_bl() {
        return id_bl;
    }

    public void setId_bl(int id_bl) {
        this.id_bl = id_bl;
    }

    public int getId_bc() {
        return id_bc;
    }

    public void setId_bc(int id_bc) {
        this.id_bc = id_bc;
    }

    public Date getDate_livrison() {
        return date_livrison;
    }

    public void setDate_livrison(Date date_livrison) {
        this.date_livrison = date_livrison;
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

    public String getNom_fourniseur() {
        return nom_fourniseur.get();
    }

    public void setNom_fourniseur(String nom_fourniseur) {
        this.nom_fourniseur.set(nom_fourniseur);
    }
   
   

    
}
