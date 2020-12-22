<%-- 
    Document   : Register
    Created on : 27/11/2020, 11:00:08 AM
    Author     : Nicolas
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="register.css">
    </head>
    <%@include file="header.jsp"%>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:ital,wght@1,300&display=swap');
        .button{
            margin-bottom: 15px;
            border-radius: 10px;
        }
        #Register{
            padding:10px 15px 10px 15px;
            font-family: 'Roboto',sans-serif;
            font-weight: bold;
            font-size: 15px;
        }

        #Register:hover{
            padding:12px 17px 12px 17px;
            border-radius: 8px;
            background:green;
        }
        .label{
            font-family: 'Roboto',sans-serif;
            font-weight: medium;
        }
        *{
            align-items: center;
            text-align: center;   
        }
        h1{
            font-family: 'Roboto',sans-serif;
            font-weight: medium;
            text-decoration: underline;
        }
        .form{
            background:linear-gradient(#FF9415,#FFC709);
            background-repeat: no-repeat;
            background-size: 100%;
            display: flex;
            flex-direction: column;
        }

    </style> 
 
    <body>

        <!--Formulario de Registracion-->
        <h1>Register</h1>

        <form method="POST" action="register">
            <label for="fuser" class="label">User</label>
            <input type="text" name="fuser" required="true" requiredMessage="user field cant be empty" class="button"><br>

            <label for="femail" class="label">Email</label>
            <input type="email" name="femail" required="true" requiredMessage="Email field cant be empty" class="button"><br>

            <label for="fpass" class="label">Password</label>
            <input type="password" name="fpass" required="true" requiredMessage="Password cant be empty" class="button"><br>

            <label for="fconfirmpass" class="label">Confirm password</label>
            <input type="password" name="fconfirmpass" required="true" requiredMessage="Confirm password" class="button"><br>
            <br>
            <input type="submit" value="Register" id="Register">
        </form>
    </div>     
  

    <%@include file="footer.jsp" %>
