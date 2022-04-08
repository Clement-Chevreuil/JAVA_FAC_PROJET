<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 22/02/2022
  Time: 14:11
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
    <h1>Shopping Bag</h1>
    <tr>
        <th>NAME</th>
        <th>CCATEGORY</th>
        <th>PRICE</th>
        <th>EXPIRATION DATE</th>
    </tr>
    <c:forEach items="${produces}" var="produce">
        <tr>
            <td>${produce.produce.name}</td>
            <td>${produce.produce.category}</td>
            <td>${produce.produce.price}</td>
            <td>${produce.produce.expirationDate}</td>
        </tr>
    </c:forEach>
</table>

<form action="ShoppingBagValidation">
    <input type="date" name="dateClick">
    <button class="btn btn-secondary" type="submit">Validate</button>
</form>

</body>
</html>
