<%-- 
    Document   : homePage
    Created on : Jan 7, 2021, 9:54:26 AM
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
        <c:if test="${sessionScope.user == null}">
            <a href="login.jsp">Login</a>        
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <font color="red">
            welcome, ${sessionScope.user.fullname}
            <font/>
            <form action="ServletDispatcher">
                <input type="submit"  value="Logout" name="btnAction" />
                <a href="createPage.jsp">Create</a>
            </form>
        </c:if>
            <div style="text-align: center;">
                <ul style="list-style-type:none;display: flex;">
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <c:choose>
                            <c:when test="${pageIndex == i}">
                                <li style="width: 30px;height:30px;
                                    margin: auto;
                                    border:solid activeborder 4px;border-radius: 50%;background-color: yellow">
                                    <a href="AdminPageServlet?pageIndex=${i}">${i}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li style="width: 30px;height:30px;
                                    margin: auto;
                                    border:solid activeborder 4px;border-radius: 50%;background-color: yellow">
                                    <a  href="AdminPageServlet?pageIndex=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>

        <table border="1">
            <thead>
                <tr>
                    <th>ProductID</th>
                    <th>ProductName</th>
                    <th>image</th>
                    <th>description</th>
                    <th>price</th>
                    <th>createDate</th>
                    <th>categoryID</th>
                    <th>quantity</th>
                    <th>status</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="P" items="${product}">
                <form action="ServletDispatcher">
                    <tr>                           
                    <input type="hidden" name="txtUserID" value="${sessionScope.user.userID}" />

                    <td>
                        <input readonly="" type="text" name="txtProductID" value="${P.productID}" />
                    </td>   
                    <td>
                        <input type="text" name="txtProductName"  value="${P.productName}" required=""/>
                    </td>
                    <td>
                        <img style="width: 150px;height: 100px" src="${P.image}"  alt="">
                        <input type="file" name="txtFile" value="browser..." />
                    </td>   
                    <td>
                        <input  style="height: 100px" type="text" required="" name="txtDescription" value="${P.description}" />
                    </td>

                    <td>
                        <input type="number" name="txtPrice" value="${P.price}" min="1" required=""/>                              
                    </td> 
                    <td>${P.createDate}</td> 
                    <td>
                        <c:if test="${requestScope.listCategoryID != null}">
                            <select name="listCategoryID">
                                <c:forEach var="listCategoryID" items="${requestScope.listCategoryID}">
                                    <c:if test="${listCategoryID == P.categoryID}">
                                        <option value="${listCategoryID}" selected="">${listCategoryID}</option>
                                    </c:if>
                                    <c:if test="${listCategoryID != P.categoryID}">
                                        <option value="${listCategoryID}">${listCategoryID}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </c:if>
                    </td>
                    <td>
                        <input type="number" name="txtQuantity" value="${P.quantity}" min="0" required="" />
                    </td>                           
                    <td>
                        <c:if test="${requestScope.status != null}">
                            <select name="status">
                                <c:forEach var="status" items="${requestScope.status}">
                                    <c:if test="${status == P.status}">
                                        <option value="${status}" selected="" >${status}</option>
                                    </c:if>
                                    <c:if test="${status != P.status}">
                                        <option value="${status}" >${status}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </c:if>
                    </td> 
                    <td>
                        <input type="submit" value="Update" name="btnAction" />
                        <input type="hidden" name="pageIndex" value="${param.pageIndex}" />
                    </td>
                    <td>
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this item')" 
                               name="btnAction"  />
                        <input type="hidden" name="pageIndex" value="${param.pageIndex}" />
                    </td>
                    ${requestScope.duplicate}
                </form>
            </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
