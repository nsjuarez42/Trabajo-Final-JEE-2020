/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.servlets;

import com.example.model.WordBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name="AdminWordsServlet",urlPatterns={"/adminWords"})
public class AdminWordsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
     @EJB private WordBeanLocal bean;
     
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      /*Servlet para administrar palabras*/  
      String npalabra = request.getParameter("palabra");
      String eliminar = request.getParameter("palabraeliminar");
      
      /*Si la palabra a agregar no esta vacia y no existe en la base de datos
      se agrega la palabra
      
      */
      if(!npalabra.isEmpty()){
      bean.addWord(npalabra.toCharArray());
      RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminWords.jsp");
      dispatcher.forward(request, response);
      }
      
      /*Si la palabra a eliminar no esta vacia 
      y existe en la base de datos se elimina*/
      
      if(!eliminar.isEmpty()){
      bean.deleteWord(eliminar);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminWords.jsp");
      dispatcher.forward(request, response);
      }

      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
