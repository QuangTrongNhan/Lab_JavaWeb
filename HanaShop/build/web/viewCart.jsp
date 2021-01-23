<%-- 
    Document   : viewCart
    Created on : Jan 14, 2021, 10:40:16 AM
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <c:if test="${sessionScope.customer != null}">
            welcome, ${sessionScope.customer.fullname}
            <input type="hidden" name="txtUserID" value="${sessionScope.customer.fullname}" />
        </c:if>
        <p>----------------------------</p>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>quantity</th>         
                        <th>image</th>
                        <th>price</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${cart.items}">
                    <form action="ServletDispatcher">
                        <tr>
                            <td>
                                <input type="text" name="txtName" value="${entry.value.productName}" readonly="" />
                            </td>
                            <td>
                                <input type="number" name="txtQuantity" min="1" required="" value="${entry.value.quantity}" />
                                <input type="hidden" name="txtProductID" value="${entry.value.productID}" />                                   
                            </td>
                            <td>                                      
                                <img style="width:70px;height: 70px" src="${entry.value.image}">
                            </td>
                            <td>${entry.value.price*entry.value.quantity}</td>
                            <td>
                                <input type="submit" value="Edit" name="btnAction" />
                            </td>
                            <td>
                                <input type="submit" value="DeleteItem" name="btnAction" onclick=" return confirm('Do you want to delete!!!')" />
                            </td>
                        </tr>  
                    </form>
                </c:forEach>

            </tbody>
        </table>
        <p>-----------------------------------</p>
        <form action="ServletDispatcher">
            Address: <input type="text" name="txtAddress" required="" value="" />
            <c:if test="${sessionScope.customer != null}">
                <input type="hidden" name="txtUserID" value="${sessionScope.customer.fullname}" />
            </c:if>
            <c:set var="total" value="${0}"/> <br/>
            <font style="color: red">
            <p>
                ${requestScope.err} 
            </p>                           
            </font>
            
            <c:forEach var="entry" items="${cart.items}">                         
                <c:set var="total" value="${total+entry.value.price*entry.value.quantity}" /> 
                <input type="hidden" name="txtProductID" value="${entry.value.productID}" /> 
            </c:forEach>

            <h1 style="color: red">Total:${total}</h1>
            <input type="hidden" name="txtTotal" value="${total}" />
            <a href="HomePageServlet?pageIndex=1">click to buy something</a><br>

            <br><input style="border: 1px solid red" type="submit" value="PAY" name="btnAction" />
            <input type="submit" value="PAY BY VNPAY" name="btnAction" />
        </form>






    </c:if>
    <c:if test="${empty cart.items}">
        <h2>
            No cart is exist!
        </h2>
        <a href="HomePageServlet?pageIndex=1">click to buy something</a>
    </c:if>
</body>
</html>
