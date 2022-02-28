<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 24/02/2022
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="Deconnexion">Deconnexion</a>
<table>
    <h1>Shopping Bag</h1>
    <tr>
        <th>NAME</th>
        <th>CCATEGORY</th>
        <th>PRICE</th>
        <th>EXPIRATION DATE</th>
        <th>COMMANDE DATE</th>
        <th>COMMANDE NUMBER</th>
        <th>EMAIL - TRADER</th>
        <th>COUNTRY</th>
        <th>CITY</th>
        <th>POST CODE</th>
        <th>STREET </th>

    </tr>
    <c:forEach items="${commandDetails}" var="buy">
        <tr>
            <td><center>${buy.produce.name}</center></td>
            <td><center>${buy.produce.category}</center></td>
            <td><center>${buy.produce.price}</center></td>
            <td><center>${buy.produce.expirationDate}</center></td>

            <td><center>${buy.user.firstName}</center></td>
            <td><center>${buy.user.lastName}</center></td>
            <td><center>${buy.user.email}</center></td>
            <td><center>${buy.user.country}</center></td>
            <td><center>${buy.user.city}</center></td>
            <td><center>${buy.user.postCode}</center></td>
            <td><center>${buy.user.street}</center></td>

        </tr>
    </c:forEach>
</table>

<a href="ConsumerIndex">Accueil</a>
</body>
</html>
