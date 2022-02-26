<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 20/02/2022
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <H1>Consumer</H1>
    <a type="button" href="editUser">UPDATE PROFIL</a>
    <br>
    <a type="button" href="ShoppingBagWaiting">Bag List Loading</a>
    <br>
    <a type="button" href="consumerCommandList">Bag List Buy</a>
    <br>
    <a href="index.jsp">Accueil</a>
    <br>
    <h2>LIST PRODUCE</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>EMAIL</th>
            <th>CATEGORY</th>
            <th>PRICE</th>
            <th>EXPIRATION DATE</th>
            <th>ACTIONS</th>
        </tr>
        <c:forEach items="${produces}" var="t">
            <tr>
                <td>${t.id}</td>
                <td>${t.name}</td>
                <td>${t.category}</td>
                <td>${t.price}</td>
                <td>${t.expirationDate}</td>
                <td>
                    <a href="addShoppingBag?id=${t.id}">Reservation</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
