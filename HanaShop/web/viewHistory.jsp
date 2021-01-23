<%-- 
    Document   : viewHistory
    Created on : Jan 21, 2021, 7:08:28 PM
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <h1>History</h1>
        <c:if test="${requestScope.listHistory != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>UserID</th>
                        <th>totalPrice</th>
                        <th>orderDate</th>
                        <th>OrderID</th>
                    </tr>
                </thead>
                <c:forEach var="listHis" items="${requestScope.listHistory}">
                    <tbody>
                        <tr>
                            <td>${listHis.userID}</td>
                            <td>${listHis.totalPrice}</td>
                            <td>${listHis.orderDate}</td>
                            <td><a href="ViewOrderDetail?orderID=${listHis.orderID}">View Detail</a></td>
                    
                        </tr>
                    </tbody>
                </c:forEach>
            </table>  
            <a href="HomePageServlet?pageIndex=1">Home Page</a>
        </c:if>
        
    </body>
</html>
