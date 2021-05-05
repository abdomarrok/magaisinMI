/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymagasin.controller;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author on
 */
public class Person {
     private SimpleStringProperty firstName, lastName;
    private LocalDate birthday;
    
    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
    }

    public SimpleStringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

    public SimpleStringProperty getLastName() {
        return lastName;
    }

    public void setLastName(SimpleStringProperty lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
    
}
