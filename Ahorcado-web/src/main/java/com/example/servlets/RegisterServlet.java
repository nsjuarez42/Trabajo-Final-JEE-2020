/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.servlets;

import com.example.entities.User;
import com.example.model.UserBeanLocal;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicolas
 */
@WebServlet(name="RegisterServlet" ,urlPatterns={"/register"})
public class RegisterServlet extends HttpServlet {
    
 
    
    @EJB private UserBeanLocal userBean;
    
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
    /*todo
    Consigue los datos del formulario
    para crear un nuevo usuario
    */
     List<User> lst =userBean.getUsers();
    String user = request.getParameter("fuser");
    String email = request.getParameter("femail");
    String password = request.getParameter("fpass");
    String confirmPass = request.getParameter("fconfirmpass");
     
   
   //se comprueba si el mail es valido
   String regex = "^(.+)@(.+)$";
   Pattern pattern=Pattern.compile(regex);
   Matcher matcher = pattern.matcher(email);
   if(!matcher.matches()){
    request.setAttribute("notMail", true);
    dispatchToErrorPage(request,response);
   }else{
       
  //   check that user does already exist
  for(User u:lst) {
  
      //Si el usuario ya existe
  if(u.getUser().equals(user)){
          //search method with binary search???
    request.setAttribute("UserExists", true);
    dispatchToErrorPage(request,response);
  }
  } 
  

  
  
  
    if(!password.equals(confirmPass)){
             request.setAttribute("PasswordsnotEqual", true);
             dispatchToErrorPage(request,response);
    }else{
    
    
    
    /*creacion de usuario y persistencia*/
    User nuser = new User();
    nuser.setUser(user);
    nuser.setPass(password);
    nuser.setMail(email);
    userBean.createUser(nuser);
  
    /*agrega al request el atributo de usuario*/
    request.setAttribute("user",nuser);
    
    /*Redireccionar al menu principal*/
    RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp");  
    dispatcher.forward(request, response);
    }
    }}
    private void dispatchToErrorPage(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
      RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterError.jsp");
      dispatcher.forward(request, response); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
     processRequest(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
     processRequest(request,response);
    }
    
}
