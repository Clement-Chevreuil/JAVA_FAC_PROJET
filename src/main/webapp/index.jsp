<%@ page import="com.example.application_entreprise_projet.CLASS.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <jsp:include page="bootstrap.jsp" />
</head>
<body>

<div class="container">
    <div class="row align-items-center justify-content-md-center " style="height:90vh">
        <div class="col-auto"/>
        <h1><%= "WELCOME TO ANTI-WASTE WEBSITE" %>
</h1>
<br/>



        <c:if test="${sessionScope['user'] == null}">
            <center>
                <a type="button" class="btn btn-secondary" href="login">Login</a>
                <a type="button" class="btn btn-secondary" href="register">Register</a>
            </center>
        </c:if>
        <c:if test="${sessionScope['user'] != null}">
            <center>
                <a type="button" class="btn btn-secondary" href="redirection">Accueil</a>
            </center>
        </c:if><br>
</div></div>
</div>

</body>
</html>