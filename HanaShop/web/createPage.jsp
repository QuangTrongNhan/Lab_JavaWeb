<%-- 
    Document   : createPage
    Created on : Jan 19, 2021, 2:57:34 PM
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>CREATE PRODUCT</h1>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <a href="login.jsp">Login</a>                      
            </c:when>
            <c:otherwise>
                <form action="ServletDispatcher">
            ProductID: <input type="text" required="" name="txtProductID" value="${param.txtProductID}" /> <br>              
            
            ProductName: <input type="text" required="" name="txtProductName" value="${param.txtProductName}" /><br>
            Image: <input type="file" name="txtFile" required="" value="${param.txtFile}" /><br>
            Description: <input type="text" required="" name="txtDescription" value="${param.txtDescription}" /><br>
            Price: <input type="number" min="1" required="" name="Price" value="${param.Price}" /><br>
            createDate:<p name="txtDate" id="date"></p><br> 
            categoryID: <select name="categoryID">
                <option >
                    f
                </option>
                <option>
                    d
                </option>
            </select><br>
            Quantity: <input type="number" min="1" required="" name="txtQuantity" value="${param.txtQuantity}" /><br>
            status: <select name="active">
                <option>1</option>
            </select>
            <input type="submit" value="Create" name="btnAction" />
            <a href="AdminPageServlet?pageIndex=1">Back to admin page</a>
        </form>

            </c:otherwise>
        </c:choose>
        

        <script>
            var date = new Date();
            setInterval(1000, getDate());
            function getDate() {
                document.getElementById('date').innerHTML = date.getDate() + ":" + date.getMonth() + 1 + ":" + date.getFullYear();
            }
        </script>
    </body>
</html>
