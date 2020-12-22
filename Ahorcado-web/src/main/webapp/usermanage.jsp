<%-- 
    Document   : usermanage
    Created on : 12/12/2020, 11:14:03 PM
    Author     : Nicolas
--%>

<%@page import="com.example.servlets.GetBeansServlet"%>
<%@page import="com.example.model.UserBeanLocal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>userManage.jsp</title>
        <link rel="stylesheet" href="usermanage.css">
    </head>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Oswald:wght@200&display=swap');
        body,html{
            height:100%
        }
        .form{
            background-image:url("https://images.unsplash.com/photo-1515552638994-8ce3dc5fea97?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=675&q=80");
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center;
            height: 100%;
        }
        .button{
            border-radius: 8px;
            padding:5px 5px;

        }
        .textin{
            border-radius: 8px;

        }
        .text,h1,.textin{
            font-family: 'Oswald',sans-serif;
        }
        .text{
            font-weight: bold;
            margin-bottom: 0%;
            font-size: 140%;
        }


    </style> 
    <%@include file="header.jsp" %>
    <body>

        <h1>Usuario actual es <%=user%> del grupo <%=group%></h1><br>
        <div class=form>
            <h1 class="main">User administration</h1>

            <p class="text">Change User</p>
            <form action="/UserManage" method="POST">
                <input name="newUser" type="text" class="textin"><br>
                <input type="submit" value="Cambiar Usuario" class="button">
            </form>  

            <p class="text">Change Password</p><br>
            <form action="/UserManage" method="POST">
                <input name="newPassword" type="password" class="textin"><br>
                <input type="submit" value="Cambiar contrasena" class="button">
            </form>
        </div>

        <%@include file="footer.jsp"%>
