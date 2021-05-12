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
public class User {
    
        SimpleStringProperty username;
        SimpleStringProperty first_name;
        SimpleStringProperty last_name;
        SimpleStringProperty pass;

    public User(String username, String first_name, String last_name, String pass) {
        this.username = new SimpleStringProperty(username);
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.pass = new SimpleStringProperty(pass);
    }
    
   
}
