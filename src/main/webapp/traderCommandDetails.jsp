<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 23/02/2022
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<a href="index.jsp">Home</a>

<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <h1>COMMAND RECAP</h1>

    <tr>
        <th>NAME</th>
        <th>CCATEGORY</th>
        <th>PRICE</th>
        <th>EXPIRATION DATE</th>
        <th>COMMANDE DATE</th>
        <th>COMMANDE NUMBER</th>
    </tr>
    <c:forEach items="${commandDetails}" var="c">
        <tr>
            <td><center>${c.produce.name}</center></td>
            <td><center>${c.produce.category}</center></td>
            <td><center>${c.produce.price}</center></td>
            <td><center>${c.produce.expirationDate}</center></td>
            <td><center>${c.dateCommandeValidate}</center></td>
            <td><center>${c.noCommande}</center></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
