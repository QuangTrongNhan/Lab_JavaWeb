<%-- 
    Document   : login
    Created on : Jan 26, 2021, 9:06:04 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>

<body>
    <form action="login" class="box" method="POST" >
        <h1>Login</h1>
        <input id = "username" type="text" value="${param.txtEmail}" name="txtEmail" placeholder="Email" required>
        <input id="password" type="password" name="txtPassword" placeholder="password" required>
        <input id = "btnSubmit" type="submit" name="" value="Login">
        <a href="createAccount.jsp"><b>Create account</b></a>
        <c:if test="${requestScope.error != null}">
            <font color="red">            
                <p>${requestScope.error}</p>
            </font>       
        </c:if>
    </form>
    
</body>
</html>
