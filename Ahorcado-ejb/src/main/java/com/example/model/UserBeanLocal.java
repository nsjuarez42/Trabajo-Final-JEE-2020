/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import com.example.entities.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nicolas
 */
@Local
public interface UserBeanLocal extends Serializable {
   public void createUser(User user);
   public void deleteUser(User user);
   public List<User> getUsers();
   public void updateUser(User user,User newUser);
   public List<User> updateRanking();
   public String getUserLogged();
   public String getGroupLogged();


}
