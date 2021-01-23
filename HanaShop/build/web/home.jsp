<%-- 
    Document   : home.jsp
    Created on : Jan 13, 2021, 3:27:06 PM
    Author     : pc
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>     
        <h1 style="color: red;margin: auto; width:10vw;border: 3px solid brown;border-radius: 20%;background-color: greenyellow">Hana Shop</h1>
        <c:choose>
            <c:when test="${sessionScope.customer != null}">
                <font style="color: red">
                welcome, ${sessionScope.customer.fullname}             
                <form action="ServletDispatcher">
                    <input type="hidden" name="txtUserID" value="${sessionScope.customer.fullname}" />
                    <input type="submit"  value="Logout" name="btnAction" />
                    <input type="submit" value="View Historty" name="btnAction" />
                </form>
                </font>
            </c:when>
            <c:otherwise>
                <a href="login.jsp">Login</a>
            </c:otherwise>
        </c:choose>

        <div style="text-align: center;">
            <ul style="list-style-type:none;display: flex;">
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <c:choose>
                        <c:when test="${pageIndex == i}">
                            <li style="width: 30px;height:30px;
                                margin: auto;
                                border:solid activeborder 4px;border-radius: 50%;background-color: yellow">
                                <a href="HomePageServlet?pageIndex=${i}">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li style="width: 30px;height:30px;
                                margin: auto;
                                border:solid activeborder 4px;border-radius: 50%;background-color: yellow">
                                <a  href="HomePageServlet?pageIndex=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <table border="1">
            <thead>
                <tr>
                    <th>productName</th>
                    <th>image</th>
                    <th>description</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>Buy</th>

                </tr>
            </thead>

            <tbody>
                <c:forEach var="list" items="${listHomePage}" >
                <form action="ServletDispatcher">
                    <tr>
                    <input type="hidden" name="txtProductID"  value="${list.productID}" />
                    <td>                            
                        <input type="text" name="txtProductName" readonly="" value="${list.productName}" />                                
                    </td>
                    <td>
                        <img style="width: 150px;height: 100px" src="${list.image}">
                        <input type="hidden" name="image" value="${list.image}" />
                    </td>
                    <td>
                        <input type="text" name="txtDescription" readonly="" value="${list.description}" />
                    </td>
                    <td>
                        <input type="text" name="priceItem" readonly="" value="${list.price}" />
                    </td> 
                    <td>
                        <input type="text" name="quantity" readonly="" value="${list.quantity}" />
                    </td> 
                    <td>
                        <input type="submit" value="Add" name="btnAction" />

                        <input type="hidden" name="pageIndex" value="${param.pageIndex}" />
                    </td>
                    </tr>
                </form>
            </c:forEach>  
        </tbody>          
    </table>
    <form action="ServletDispatcher">
        <input type="submit" value="View Item" name="btnAction" />
    </form>


</body>
</html>
