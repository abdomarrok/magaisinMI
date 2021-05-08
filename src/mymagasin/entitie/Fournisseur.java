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
public class Fournisseur {
   private SimpleStringProperty nom_fournisseur;
   private int id_fournisseur;

    public Fournisseur(int id_fournisseur, String nom_fournisseur) {
        this.id_fournisseur = id_fournisseur;
        this.nom_fournisseur = new SimpleStringProperty(nom_fournisseur);
       
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur=id_fournisseur;
    }

    public String getNom_fournisseur() {
        return nom_fournisseur.get();
    }

    public void setNom_fournisseur(String nom_fournisseur) {
        this.nom_fournisseur.set(nom_fournisseur);
    }

   
    
}
