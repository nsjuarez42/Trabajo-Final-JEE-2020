<%-- 
    Document   : RegisterError
    Created on : 22/12/2020, 11:52:59 AM
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error al registrarse</h1>
        <!--Se especifica error de registracion-->
        <c:if test="${requestScope.UserExists == true}">
            <p>El nombre de usuario ya existe</p>
        </c:if>
        <c:if test="${requestScope.PasswordsnotEqual ==true}">
            <p>Los campos contrasena y confirmar contrasena no coinciden</p>
        </c:if>    
        <c:if test="${requestScope.notMail ==true}">
            <p>El mail ingresado no es valido</p>
        </c:if>   
        <a href="Register.jsp">Volver a registrarse</a>
        <a href="index.jsp">Volver a la pagina de inicio</a>
    </body>
</html>
