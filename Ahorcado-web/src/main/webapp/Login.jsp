<%-- 
    Document   : Login
    Created on : 27/11/2020, 10:59:57 AM
    Author     : Nicolas
--%>

<%@page import="com.example.servlets.GetBeansServlet"%>
<%@page import="com.example.model.UserBeanLocal"%>
<%@page import="com.example.model.UserBeanLocal"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="login.css">

    </head>
    <%@include file="header.jsp"%>
    <style>

        @import url('https://fonts.googleapis.com/css2?family=Open+Sans&display=swap');
        *{
            text-align: center;
            font-family:'Open Sans',sans-serif;
        }
        div{
            background:linear-gradient(#FFB347,#FFCC33);
        }
        .title{
            text-decoration: underline;
        }
        .label{
            padding-left:38px;
        }
        .textin{
            margin-top: 15px;
            border-radius: 10px;
            border-color: black;
        }

        .button{
            font-weight: bold;
            background:#E5F0FF;
        }
        .button:hover{
            padding-left: 9px;
            padding-right: 9px;
            background-color: darkgreen;
        }

    </style> 
    <body>




        <h1 class="title">Login</h1>
        <!--Formulario de Login con directiva de seguridad-->
        <form action="j_security_check" method="POST">
            <label for="j_username" class="label">User</label>
            <input type="text" required="true" requiredMessage="User field cant be empty" name="j_username" class="textin">
            <br>  

            <label for="j_password">Password</label>
            <input type="password" required="true" requiredMessage="Password field cant be empty" name="j_password" class="textin">
            <br>

            <input type="submit" value="Login" class="button">

        </form>  

        <%@include file="footer.jsp" %>
