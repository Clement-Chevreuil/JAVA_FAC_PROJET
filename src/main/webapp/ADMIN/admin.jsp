<%@ page import="java.util.Date" %>
<%@ page import="java.time.Year" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Catalogue de produits</title>
    <jsp:include page="../bootstrap.jsp" />
</head>
<body>
<jsp:include page="../navbar.jsp" />
<%--@elvariable id="user" type="com.example.application_entreprise_projet.CLASS.User"--%>

<div class="container">
    <div class="row align-items-center justify-content-md-center " style="height:50vh">
        <div class="col">
<table class="table table-secondary table-striped">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">EMAIL</th>
        <th scope="col">DELETE</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${consumers}" var="u">

        <tr>
            <td>${u.id}</td>
            <td>${u.email}</td>
            <td><a href="deleteUser?id=${u.id}">Delete</a></td>
        </tr>

    </c:forEach>
    </tbody>
</table>
        </div>
        <div class="col">

<table class="table table-secondary table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>EMAIL</th>
        <th>DELETE</th>
        <th>ACCEPT</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${traders}" var="t">
        <tr>
            <td>${t.id}</td>
            <td>${t.email}</td>
            <td><a href="deleteUser?id=${t.id}">Delete</a></td>
            <td>
                <c:if test="${t.traderValidation == false}">
                    <a type="button" href="ValidationTrader?id=${t.id}" >ACCEPT</a>
                </c:if>
                <c:if test="${t.traderValidation == true}">
                    <p> Validated</p>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
        </div>

<form action="adminStatistic">




    <%
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
    %>

    <center><h3>Begin Year</h3></center>

    <select class="form-select" name="option">
        <% for(int i = 2018; i < calendar.get(Calendar.YEAR) + 1; i++) { %>
            <option value="<%= i %>"><%= i %></option>
        <% } %>
    </select>

    <br>

    <center><button class="btn btn-primary"type=submit">CLICK</button></center>

</form>
    </div>
</div>
</body>
</html>