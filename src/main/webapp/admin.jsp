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
        <th>EDIT</th>
        <th>DELETE</th>
    </tr>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.email}</td>
            <td>${u.trader}</td>
            <td><a href="deleteUser?id=${u.id}">Delete</a></td>
            <td><a href="editUser?id=${u.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

<a href="index.jsp">Accueil</a>
</body>
</html>