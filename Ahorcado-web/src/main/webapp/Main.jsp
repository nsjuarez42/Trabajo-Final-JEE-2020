<%-- 
    Document   : Main
    Created on : 27/11/2020, 11:26:00 AM
    Author     : Nicolas
--%>

<%@page import="com.example.servlets.GetBeansServlet"%>
<%@page import="com.example.model.UserBeanLocal"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="main.css">
    </head>
    <%@include file="header.jsp"%>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Open+Sans&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Open+Sans&family=Oswald:wght@300&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Andika+New+Basic&display=swap');
        html,body{
            height:100%;
        }
        canvas
        {text-align:center;
        }
        *{
            font-family: 'Oswald', sans-serif;
        }
        .textin{
            background-image: url("http://3.bp.blogspot.com/-4oAWWCcNNz4/Tjr3nKNyVUI/AAAAAAAAPLU/Pouua-pNsEY/s1600/sq.gif");    
            width: 195px;
            height: 18px;
            background-size: 20px;
            font-size: 13px;
            padding-left: 5px;
            letter-spacing: 12px;
            border-radius: 12px;
            letter-spacing: 12px;
            border-width: 3px;
        }
        #all{
            background-image: url("https://images.unsplash.com/photo-1509316785289-025f5b846b35?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1055&q=80");
            background-position:center;
            background-repeat: no-repeat;
            background-size: cover;
            height:100%;
        }
        .mistakes{
            text-decoration: underline;
            grid-column:3;
            grid-row:1;
        }
        .game{
            margin-left:30%;
            display:grid;
            grid-template-columns: repeat(3,33%);
            grid-template-rows: repeat(3,33%);
        }

        .submit{
            grid-column:2;
            grid-row:3;
            align-self: center;
        }
        .input{
            grid-column:2;
            grid-row:2;
            justify-self:left;
            align-self:center;
        }
        .hangman{
            grid-column:1;
            grid-row:1/3;
            justify-self: center;
            align-self: center;
        }
        .playbutton{
            grid-column:1;
            grid-row:3;
            justify-self: right;
            align-self:baseline;
        }
        .sidenav{
            display: grid;
            grid-template-columns: 100%;
            grid-template-rows: repeat(6,40px);   
            position: fixed;
            width:15%;
            background: linear-gradient(#FFB347,#FFCC33);
            border:solid;
            border-color: black;
            height: 35%;
            justify-items: stretch;
        }

        p{
            color:black;
            font-size: 115%;
        }
        .navbutton{
            background:#FFE55E;
            border-radius: 8px;
            border-style:double;
            padding:3px 3px;
            width: 60%;
            font-size: 90%;
        }
        .button{
            background:#FFE55E;
            border-radius: 5px;
            border-style:double;
            padding:10px 10px;
            width: 40%;
            font-size: 90%;  
        }
        .button:hover{
            padding:12px 12px;
        }

        .navbutton:hover{
            box-shadow: 4px 5px black;
        }

        #play-button{
            background:#41B619;  
            padding:12px 8px 12px 8px;
            border-style: solid;
            border-color: black;
            border-radius:5px;
            font-size: 15px;
            font-family: 'Andika New Basic', sans-serif;
            width:40%; 
        }
        #play-button:hover{
            box-shadow:6px 3px 5px black;
        }
        .link{
            text-decoration: none;
            color:black;
        }
        .link:hover{
            text-decoration: underline;
        }

    </style> 
   <%
      UserBeanLocal bean =((GetBeansServlet)getServletContext().getAttribute("getBeans")).getBean();
      String user = bean.getUserLogged();
      String group = bean.getGroupLogged();
      pageContext.setAttribute("group", group);
      pageContext.setAttribute("user", user);
   %>

    <body> <div id="all">
      
            <!--Barra de navegacion lateral con los datos del usuario-->
            <nav class="sidenav">
                <!--name and user manage-->
                <p id="username">${requestScope.user.name}</p>
                <a href="usermanage.jsp" class="link">Edit user info</a> <br>
                <!--Ranking-->
                <p id="ranking">${requestScope.user.ranking}</p><br>
                <!--all Ranking button-->
                <form action="/Ranking" method="GET">
                    <input type="submit" value="Todos los Rankings" class="navbutton">
                </form>
                <!--logout button-->
                <form action="/logout" method="POST">
                    <button type="submit" class="navbutton">Logout</button><br>
                </form>


                <!--Si el usuario es admin puede administrar palabras-->
                <c:if test="${group eq admin}">
                    <form action="/maintoadmin" method="GET">
                        <input type="submit" value="Administrar palabras"  class="navbutton"><br>
                    </form>
                </c:if>

            </nav>

            <!--juego del ahorcado-->
            <div class="game">
                <div class="hangman">
                    <canvas id="hangman" width="200" height="300"></canvas>
                </div>

                <div class="playbutton">
                    <form action="/mainWord">

                        <%
                            List<Character> guess = (List<Character>) request.getAttribute("guess");
                        %>
                        <form action="/main" method="GET">
                            <input type="submit" value="play game(solo)" id="play-button" class="button"><br>
                        </form>
                </div>
                        
                <div class="input">
                    <input type="text" name="guess" id="letter" class="textin" value=<%=guess.toString()%>>
               </div>
                    
                    <div class="submit">        
                        <input type="submit" value="Aceptar" class="button">
                    </div>        

                    </form>

                    <div class="mistakes">
                        <p class="mistakes">Errores</p>
                        <p id="mistakes" class="mistakes">7</p>
                    </div>

                </div>
            </div>



            <%@include file="footer.jsp" %>       
