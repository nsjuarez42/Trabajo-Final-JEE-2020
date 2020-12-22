<%-- 
    Document   : Ranking
    Created on : 10/12/2020, 03:10:18 PM
    Author     : Nicolas
--%>

<%@page import="com.example.servlets.GetBeansServlet"%>
<%@page import="com.example.model.UserBeanLocal"%>
<%@page import="com.example.model.UserBeanLocal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ranking.jsp</title>
        <link rel="stylesheet" href="ranking.css">
    </head>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap');
        .background{
            background-image:url("https://images.unsplash.com/photo-1494548162494-384bba4ab999?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80") ;
            background-repeat: no-repeat;
            background-size: cover;
        }
        h1{
            font-family: 'Roboto', sans-serif;
            text-align: center;
        }
        .table{
            width:60%;
            border-collapse: collapse;
        }
        .table_row{

        }
        .table_header{
            border:2px solid black;
            font-family: 'Roboto', sans-serif;
            letter-spacing: 2px;
            background:dimgray;
        }
        .table_data{
            border:1px solid black;
            font-family: 'Roboto', sans-serif;
            text-align: center;
            background:lightgray;

        }

    </style> 

    <%@include file="header.jsp"%>
    <body>

        <div class="background">
            <!--Lista de rankings por jugador,ranking y puntaje 
            -->
            <h1>Rankings</h1>
            <table class="table">
                <tr class="table_row">
                    <th class="table_header">Usuario</th>
                    <th class="table_header">Ranking</th>
                </tr>  
                <c:forEach var="user" items="${requestScope.rankings}">
                    <tr class="table_row">
                        <td class="table_data">${user.user}</td> 
                        <td class="table_data">${user.ranking}</td>
                        <td class="table_data">${user.puntaje}</td>
                    </tr>   
                </c:forEach>  
            </table>
        </div>


        <%@include file="footer.jsp"%>
