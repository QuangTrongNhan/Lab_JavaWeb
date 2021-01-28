<%-- 
    Document   : createAccount
    Created on : Jan 26, 2021, 9:17:49 PM
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
        <link rel="stylesheet" href="css/login.css">
    <body>
        <form class="box" action="create" method="POST" >
            <c:set var="error" value="${requestScope.CREATEERROR}" />
            <h1>Create Account</h1>
            <input id = "username" type="email" value="${param.txtEmail}" name="txtEmail" placeholder="Email" required>
            <input id = "username" type="text" value="${param.txtUsername}" name="txtUsername" placeholder="Username" required>
            <input id="password" type="password" value="${param.txtPassword}" name="txtPassword" placeholder="password" required>
            <input id="password" type="password" name="txtRePassword" placeholder="re-password" required>
            <c:if test="${not empty error.confirmNotMatched}">
                <font color="red">${error.confirmNotMatched}
                </font><br/>            
            </c:if>
            <input id = "btnSubmit" type="submit" name="btnAction" value="create">
            <a href="login.jsp">You have already account</a>
        </form>
    </body>
</html>
