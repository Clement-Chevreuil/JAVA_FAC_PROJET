<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 19/02/2022
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--@elvariable id="user" type="com.example.application_entreprise_projet.CLASS.User"--%>
<f:form modelAttribute="user" method="post" action="login">
    <table>
        <tr>
            <td>Email:</td>
            <td><f:input path="email"/></td>

        </tr>
        <tr>
            <td>Passwors:</td>
            <td><f:input path="password"/></td>

        </tr>
        <tr>
            <td><input type="submit" value="LOG"/></td>

        </tr>
    </table>
</f:form>

<a href="index.jsp">Accueil</a>
</body>
</html>
