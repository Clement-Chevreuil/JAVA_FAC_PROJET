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
    <jsp:include page="../bootstrap.jsp" />
</head>
<body>
<jsp:include page="../navbar.jsp" />

<table class="table table-secondary table-striped">
    <h1>COMMAND RECAP</h1>

    <tr>
        <th><center>COMMANDE DATE</center></th>
        <th><center>COMMANDE NUMBER</center></th>
        <th><center>ACTIONS</center></th>
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
