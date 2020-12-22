/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import com.example.entities.Word;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Nicolas
 */
@DeclareRoles({"admin", "user"})
@Stateless
public class WordBeanLocal implements WordBeanLocalLocal {

    @Resource
    SessionContext ctx;

    @PersistenceContext(unitName = "Ahorcado-PU")
    private EntityManager em;

    public WordBeanLocal() {
        EntityManagerFactory emf = em.getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    @RolesAllowed(value = {"admin"})
    public void addWord(char[] letters) {

        System.out.println("user " + ctx.getCallerPrincipal().getName() + " adds a new word");
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));
        if (letters.length > 0) {
            Word word = new Word(letters);
            try {

                em.persist(word);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            return;
        }
    }

    @RolesAllowed(value = {"admin"})
    public void deleteWord(String word) {
        System.out.println("user " + ctx.getCallerPrincipal().getName() + " deletes word " + word);
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));

        Word delword = new Word(word.toCharArray());

        if (em.contains(delword)) {
            try {
                em.remove(delword);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    @PermitAll
    public List<Word> getWords() {
        System.out.println("user " + ctx.getCallerPrincipal().getName() + " gets all words");
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));
  
        Query query = em.createNativeQuery("SELECT * FROM WORD *");
        List<Word> words = query.getResultList();

        return words;
      
    }

    @PermitAll
    public Word getWord(String word) {
        System.out.println("user " + ctx.getCallerPrincipal().getName() + " gets word:" + word);
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));

        List<Word> words = getWords();

        for (Word w : words) {
            if (Arrays.equals(w.getLetters(), word.toCharArray())) {
                return em.find(Word.class,w.getId());
            }
        }

        return null;
    }

    @RolesAllowed(value = {"admin", "user"})
    public List<Character> getRandomWord() {
        int high = 0;
        List<Word> words = getWords();
        for (Word word : words) {
            if (word.getId() > high) {
                high = word.getId();
            }
        }

        Random ran = new Random();
        int wordIndex = ran.nextInt(high);

        List<Character> chars = new ArrayList();
        char[] word = words.get(wordIndex).getLetters();

        for (int i = 0; i < word.length; i++) {
            chars.add(Character.valueOf(word[i]));
        }
        return chars;

    }

}
