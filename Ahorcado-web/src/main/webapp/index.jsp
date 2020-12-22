<%-- 
    Document   : index
    Created on : 27/11/2020, 10:51:47 AM
    Author     : Nicolas
--%>

<%@page import="com.example.servlets.GetBeansServlet"%>
<%@page import="com.example.model.UserBeanLocal"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="index.css">
    </head>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap');
        .submitbutton{
            font-family:'Oswald',sans-serif;
            font-size: 90%;
            font-weight: bold;
            padding:3px 20px;
            margin-top: 15px;
            text-decoration: none;
            background:#F6D365;
            border-width: 3px;
        }
        .buttons{
            display: flex;
            flex-direction: column;
            align-items: center;
            background:linear-gradient(#F6D365,#FDA085);
        }

        .submitbutton:hover{
            background:#008736;
        }


    </style>   
    <%@include file="header.jsp" %>
    <body>

        <!--Pagina de inicio-->
        <div class="buttons">
            <form action="Login.jsp">
                <input type="submit" value="Login" class="submitbutton">   
            </form> 
            <form action="Register.jsp">
                <input type="submit" value="Registrarse" class="submitbutton">
            </form>
        </div>





        <%@include file="footer.jsp" %>
