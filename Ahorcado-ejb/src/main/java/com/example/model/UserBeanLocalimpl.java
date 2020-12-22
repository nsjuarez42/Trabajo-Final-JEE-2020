/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import com.example.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Nicolas
 */
@DeclareRoles(value = {"admin", "user"})
@Stateful
public class UserBeanLocalimpl implements UserBeanLocal {

    @Resource
    SessionContext ctx;

    @PersistenceContext(unitName = "Ahorcado-PU")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    public UserBeanLocalimpl() {
        EntityManagerFactory emf = em.getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    @PermitAll
    public void createUser(User user) {

        System.out.println("user " + ctx.getCallerPrincipal().getName() + " adds a new user");
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));
        System.out.println("New user: " + user.toString());

        try {
            ut.begin();
            if (user != null) {
                em.persist(user);
                ut.commit();
            }
        } catch (Exception ex) {
            try {
                ut.rollback();
            } catch (SystemException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        }
    }

    @RolesAllowed(value = {"admin"})
    public void deleteUser(User user) {
        System.out.println("user " + ctx.getCallerPrincipal().getName() + " deletes a user");
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));
        System.out.println("User to delete: " + user.toString());

        try {
            InitialContext ic = new InitialContext();
            ut = (UserTransaction) ic.lookup("name");
        } catch (NamingException ne) {
            ne.printStackTrace();
        }

        if (em.contains(user)) {
            em.remove(user);
        }
    }

    @RolesAllowed(value = {"admin", "user"})
    public List<User> getUsers() {
        System.out.println("user " + ctx.getCallerPrincipal().getName() + " gets all users");
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));
        Query query;
        try {
            query = em.createNativeQuery("SELECT * FROM User *");
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @RolesAllowed(value = {"admin", "user"})
    public void updateUser(User user, User newUser) {
        System.out.println("user " + ctx.getCallerPrincipal().getName() + " updates a user");
        System.out.println("User to update: " + user.toString());
        System.out.println("New user: " + newUser.toString());
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));
        try {
            ut.begin();
            deleteUser(user);
            if (newUser != null) {
                em.persist(newUser);
                ut.commit();
            }else{
             ut.rollback();
            }
        } catch (Exception ex) {
            try {
                ut.rollback();
            } catch (SystemException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        }
    }

    //Actualiza el ranking teniendo en cuenta los puntajes de cada usuario
    @RolesAllowed(value = {"admin", "user"})
    public List<User> updateRanking() {
        System.out.println("user " + ctx.getCallerPrincipal().getName() + " updates user ranking");
        System.out.println("adminRole: " + ctx.isCallerInRole("admin"));
        System.out.println("UserRole: " + ctx.isCallerInRole("user"));
        List<User> lst = getUsers();
        int size = lst.size();

        List<Integer> puntajes = new ArrayList();

        for (int i = 0; i < size; i++) {
            puntajes.add(lst.get(i).getPuntaje());
        }

        List<Integer> orderpuntajes = quickSort(puntajes);

        List<User> ranking = new ArrayList();

        for (int i = 0; i < size; i++) {

            if (lst.get(i).equals(orderpuntajes)) {
                lst.get(i).setRanking(i);
                ranking.add(lst.get(i));
            }
        }
        System.out.println("Ranking: " + ranking.toString());
        return ranking;
    }

    //Algoritmo que utiliza recursion para ordenar de Mayor a menor
    @PermitAll
    public List<Integer> quickSort(List<Integer> arr) {

        int size = arr.size();

        if (arr.size() < 2) {
            System.out.println("base case");
            return arr;

        } else {

            int pivot = arr.get(0);
            System.out.println("pivot is: " + pivot);
            List<Integer> less = new ArrayList();
            for (int i = 0; i < size; i++) {
                if (arr.get(i) < pivot) {
                    less.add(arr.get(i));
                }

            }

            List<Integer> greater = new ArrayList();
            for (int i = 0; i < size; i++) {
                if (arr.get(i) > pivot) {
                    greater.add(arr.get(i));
                }
            }

            System.out.println("Less: " + less.toString());
            System.out.println("Greater: " + greater.toString());

            List<Integer> sort = new ArrayList();

            if (greater.size() < 2) {
                sort.add(greater.get(0));
            }
            for (int i = 0; i < greater.size(); i++) {
                sort.add(greater.get(i));
            }

            sort.add(pivot);

            if (less.size() < 2) {
                sort.add(less.get(0));
            }
            for (int i = 0; i < less.size(); i++) {
                sort.add(less.get(i));
            }

            System.out.println("Sort: " + sort.toString());
            return quickSort(sort);

        }

    }

    @Override
    public String getUserLogged() {
      return ctx.getCallerPrincipal().getName();
    }

    @Override
    public String getGroupLogged() {
          if(isUser()){
          return "user";
          }else if(isAdmin()){
          return "admin";
          }
          return null;
 
    }
    
    public boolean isAdmin(){
    return ctx.isCallerInRole("admin");
    }
    
    public boolean isUser(){
    return ctx.isCallerInRole("user");
    }

 

}
