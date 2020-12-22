/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.servlets;

import com.example.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "MainWordServlet",urlPatterns = {"/mainWord"})
public class MainWordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       /*Consigue la palabra completa del juego,el input del usuario,
        su cantidad de errores y ajusta el puntaje y el ranking del 
        usuario
        */
        List<Character> word = (List <Character>)request.getAttribute("word");
        User user =(User)request.getAttribute("user");
        String userGuess = request.getParameter("guess");
        String wordGuess = "";
        int mistakes = (int)request.getAttribute("gameMistakes");
        char[]guessarr = userGuess.toCharArray();
        int puntajeactual= user.getPuntaje();
        
        //si la cantidad de errores es igual a 7
        if(mistakes ==7 ){
            /*El usuario pierde el juego
            */
            resetGame(request,response);

        }
        
        /*si la adivinanza del usuario es igual a la palabra 
        el usuario gana y se reinicia el juego*/
        if(word.toString().equals("guess")){
            
        user.setPuntaje(puntajeactual + 1000);
        wordGuess += word.toString();
        resetGame(request,response);
     
        }else{
        
        for(int i=0;i<word.size();i++){
         
            for(int x =0;x<guessarr.length;x++){
                    
                       //si la letra es correcta
                   if(word.get(i).compareTo(guessarr[i]) == 0){
                        
                       user.setPuntaje(puntajeactual + 50);
                       wordGuess = wordGuess.concat(word.get(i).toString());
  
                   }else{
                   user.setPuntaje(puntajeactual -10);
                   mistakes+=1;
                   }
                
            }
            
            
        }}
        //Se actualiza el usuario en el request con el nuevo puntaje
        request.setAttribute("user", user);
        //Se actualiza el atributo guess para llenar las letras que el usuario ya adivino
        request.setAttribute("guess", wordGuess);
        RequestDispatcher dispatcher= request.getRequestDispatcher("Main.jsp");
        dispatcher.forward(request, response);
       
  
     
    }
   /* se reinician los atributos de error y la palabra
      el usuario debe tocar el boton nuevamente para jugar de nuevo*/
    private void resetGame(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
            request.setAttribute("word","");
            request.setAttribute("mistakes", 0);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp");
            dispatcher.forward(request, response);
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
