<%-- 
    Document   : viewDetail
    Created on : Jan 21, 2021, 8:18:34 PM
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Detail</title>
    </head>
    <body>
    <c:if test="${requestScope.listDetail != null}">
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <c:forEach var="listDetail" items="${requestScope.listDetail}">
                <tbody>
                <tr>
                    <td>${listDetail.name}</td>
                    <td>${listDetail.price}</td>
                    <td>${listDetail.quantity}</td>
                </tr>
            </tbody>
            </c:forEach>
        </table>
        <a href="HomePageServlet?pageIndex=1">Back</a>
    </c:if>
    </body>
</html>
