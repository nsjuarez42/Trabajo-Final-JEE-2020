/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import com.example.entities.Word;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nicolas
 */
@Local
public interface WordBeanLocalLocal {
    
    public void addWord(char[] letters);
    
    public void deleteWord(String word);
    
    public List<Word> getWords();
    
    public Word getWord(String word);
    
    public List<Character> getRandomWord();
    
    
}
