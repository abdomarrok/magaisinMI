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
public class Service extends User{
    private SimpleStringProperty service_name;

    public Service(String username, String first_name, String last_name, String pass,String service_name) {
        super(username,first_name,last_name,pass);
        this.service_name=new SimpleStringProperty(service_name);
    }
     public Service(String username, String first_name, String last_name, String pass) {
        super(username,first_name,last_name,pass);
        this.service_name=new SimpleStringProperty("SRV");
    }

    public String getService_name() {
        return service_name.get();
    }

    public void setService_name(String service_name) {
        this.service_name.set(service_name);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public String getLast_name() {
        return last_name.get();
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getPass() {
        return pass.get();
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }
        
}
