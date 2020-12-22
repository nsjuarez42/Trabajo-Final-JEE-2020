
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private UserBeanLocal userBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*Todo:
        revisar que hacer con security forms
        User validation in database
        dispatch to main */
        //Se consiguen los parametros del formulario
        String user, pass;
        user = request.getParameter("username");
        pass = request.getParameter("password");
        

        List<User> list = userBean.getUsers();

        /*TODO 
    see if theres better way to search and validate user
    maybe do binary search
         */
        for (User u : list) {
            if (u.getUser().equals(user) && u.getPass().equals(pass)) {
                userBean.updateRanking();
                request.setAttribute("user", u);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp");
                dispatcher.forward(request, response);

            } else {
               RequestDispatcher dispatcher = request.getRequestDispatcher("LoginError.jsp");
               dispatcher.forward(request, response);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
