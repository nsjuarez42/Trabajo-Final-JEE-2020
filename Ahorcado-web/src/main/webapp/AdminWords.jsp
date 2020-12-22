<%-- 
    Document   : AdminWords
    Created on : 12/12/2020, 09:10:15 PM
    Author     : Nicolas
--%>

<%@page import="com.example.servlets.GetBeansServlet"%>
<%@page import="com.example.model.UserBeanLocal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="adminwords.css">
    </head>
    <%@include file="header.jsp"%>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap');
        body,html{
            height:100%;
        }
        h1,td,.button,h3,.textin,a{
            font-family: 'Oswald',sans-serif;
            text-decoration: none;
            color:black;

        }
        td{
            border:1px solid black;
            background:white;
        }
        table{
            border: 2px solid black;

        }

        .textin{
            border-radius:8px;
            padding:5px 10px 5px 10px;

        }
        .all{
            background-image:url("https://images.unsplash.com/photo-1514471244491-6fb96fcdf04f?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1348&q=80");
            height: 100%;
            background-size:100% 100%;
            background-repeat: no-repeat;
        }
        .button{
            padding:5px 7px 5px 7px;
            border-radius: 10px;
            border-style: double;
            margin-top:1%;

        }
        .button:hover{
            background-color: grey;
        }
        a:hover{
            text-decoration: underline;

        }
    </style>
 
    <body>

        <div class="all">
           <h1>Administracion de Palabras</h1>
    
    <!--Formulario para agregar una palabra-->       
    <form action="/AdminWords" method="POST">
    <input type="text" onfocus="this.value=''" name="palabra" value="Palabra a agregar" class="textin">
    <input type="submit" class="button" value="Agregar">
    </form>
     <!--Formulario para eliminar una palabra-->
    <form action="/AdminWords" method="POST">
    <input type="text" onfocus="this.value=''" name="palabraeliminar" value="Palabra a eliminar" class="textin">
    <input type="submit" class="button" value="Eliminar">
    </form>
   
  <h3>Lista de palabras</h3>
        <table>
     <c:forEach var ="word" items="${requestScope.wordlist}" >  
        <tr>
           <td class="word">${word}</td>
           </tr>
       </c:forEach>
       
       </table><br>
           
           <a href="Main.jsp">Volver a menu principal</a>
  </div>


        <%@include file="footer.jsp"%>
