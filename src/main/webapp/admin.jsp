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



</head>
<body>
<%--@elvariable id="user" type="com.example.application_entreprise_projet.CLASS.User"--%>
<table>
    <tr>
        <th>ID</th>
        <th>EMAIL</th>
        <th>TRADER</th>
        <th>DELETE</th>
    </tr>
    <c:forEach items="${consumers}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.email}</td>
            <td>${u.trader}</td>
            <td><a href="deleteUser?id=${u.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<br>
<br>
<br>

<table>
    <tr>
        <th>ID</th>
        <th>EMAIL</th>
        <th>TRADER</th>
        <th>DELETE</th>
        <th>ACCEPT</th>
    </tr>
    <c:forEach items="${traders}" var="t">
        <tr>
            <td>${t.id}</td>
            <td>${t.email}</td>
            <td>${t.trader}</td>
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
</table>

<form action="adminStatistic">




    <%
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
    %>

    <p>Begin Year</p>

    <input name="option" type="number" min="2018" max="<%= calendar.get(Calendar.YEAR) %>">

    <button type=submit">CLICK</button>

</form>
<a href="index.jsp">Accueil</a>

</body>
</html>