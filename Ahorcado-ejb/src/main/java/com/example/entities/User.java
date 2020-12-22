/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Nicolas
 */
@Entity
public class User {

    public User(){};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String mail;

    private String user;

    private String pass;

    private int puntaje;

    private int ranking;

    //use version or not???
    @Version
    private int version;

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String toString() {
        return "User:" + getUser()
                + "\nPassword: " + getPass()
                + "\nMail:" + getMail()
                + "\nScore " + getPuntaje()
                + "\nRanking: " + getRanking();

    }

}
