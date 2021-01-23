<%-- 
    Document   : Login.jsp
    Created on : Jan 7, 2021, 9:40:34 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form method="POST" action="LoginServlet">
            username: <input type="text" name="txtUsername" value="" placeholder="Enter your username"/> <br/>
            Password: <input type="password" name="txtPassword" value="" placeholder="Enter your password"/> <br/>
            <input type="submit" value="Login" name="btnAction"/> 
            <input type="reset" value="Reset" />
        </form>
        <c:if test="${requestScope.error != null}">
            <font color="red">            
                <p>${requestScope.error}</p>
            </font>       
        </c:if>
            <a href="ServletDispatcher?btnAction=start">Home Page</a>
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/HanaShop/LoginGoogleServlet&response_type=code
    &client_id=702010910467-e44d24m127jtmpmtes54675ip21d693h.apps.googleusercontent.com&approval_prompt=force" >Login Google</a>            
    </body>
</html>
