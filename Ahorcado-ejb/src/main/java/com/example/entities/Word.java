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

/**
 *
 * @author Nicolas
 */
@Entity
public class Word {
    public Word(){}
    
    public Word(char[] letters){
    this.letters = letters;
    }
    
    private char[] letters;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    public char[] getLetters(){
    return letters;
    }
    
    public void setLetters(char[] letters){
    this.letters = letters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
