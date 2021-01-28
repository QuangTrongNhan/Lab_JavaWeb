<%-- 
    Document   : startQuiz
    Created on : Jan 27, 2021, 9:18:35 PM
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Start Quiz</title>
        <link rel="stylesheet" href="css/startQuiz.css">
    </head>
    <body>
        <header>
            <c:choose>
                <c:when test="${sessionScope.student != null}">
                    <nav class="header-fix">
                        <div class="nav-title">
                            <div>
                                <img src="./img/logoFPT.png" alt="logo" style="width: 84px;height: 81px">
                            </div>
                            <div class="title">
                                <h1>FPT QUIZ</h1>
                            </div>
                            <div class="login-logout">
                                <div class="d-flex">
                                    <div>
                                        <img src="./img/account.png" style="width: 40px;height: 40px">
                                        <div>
                                            <span>${sessionScope.student.name}</span>
                                        </div> 
                                    </div>

                                </div>
                                <form action="logout">
                                    <div>
                                        <input type="submit" value="Logout" name="btnLogout" />
                                    </div>
                                </form>
                            </div>                
                        </div>
                    </nav>
                </c:when>
                <c:otherwise>
                    <c:redirect url="login.jsp"></c:redirect>
                </c:otherwise>
            </c:choose>
        </header>
        <div class="body-cont">
            <form class="box" action="action">
                <h1>WELCOME QUIZ</h1>
                <div>
                    <input type="text" name="" value="" placeholder="Code"/>
                </div>
                <div>
                    <input type="submit" value="Start" />
                </div>

            </form>
        </div>

        <footer>
            <div class="footer">
                <div class="footer-img" >
                <img src="./img/fb.png" style="width: 30px;height: 30px">
                <img src="./img/tw.png" style="width: 30px;height: 30px">
                <img src="./img/ins.jpg" style="width: 30px;height: 30px">
            </div>
                This is page was developed by   <strong>NhanQT</strong>.</div>
        </footer>
    </body>
</html>
