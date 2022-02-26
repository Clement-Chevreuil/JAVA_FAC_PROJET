<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 24/02/2022
  Time: 12:24
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
<table>
    <h1>COMMAND RECAP</h1>

    <tr>
        <th>COMMANDE DATE</th>
        <th>COMMANDE NUMBER</th>
        <th>ACTIONS</th>
    </tr>
    <c:forEach items="${commandDetails}" var="c">
        <tr>
            <td><center>${c.dateCommandeValidate}</center></td>
            <td><center>${c.noCommande}</center></td>
            <td>
                <center>
                    <a href="consumerCommand?noCommande=${c.noCommande}">SHOW</a>
                </center>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
