<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 19/02/2022
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<%--@elvariable id="user" type="com.example.application_entreprise_projet.CLASS.User"--%>
<f:form modelAttribute="user" method="post" action="saveUser">
    <table>
        <tr>
            <td>Email:</td>
            <td><f:input path="email"/></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><f:input path="firstName"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><f:input path="lastName"/></td>
        </tr>
        <tr>
            <td>Passwors:</td>
            <td><f:input path="password"/></td>

        </tr>
        <tr>
            <td>Country:</td>
            <td><f:input path="country"/></td>

        </tr>
        <tr>
            <td>City:</td>
            <td><f:input path="city"/></td>

        </tr>
        <tr>
            <td>PostCode:</td>
            <td><f:input path="postCode"/></td>

        </tr>
        <tr>
            <td>Street:</td>
            <td><f:input path="street"/></td>

        </tr>
        <tr>
            <td>Trader:</td>
            <td>
                <f:select path="trader">
                    <f:option value="true">OUI</f:option>
                    <f:option value="false">NON</f:option>
                </f:select>
            </td>

        </tr>
        <tr>

            <td><input type="submit" value="Save"/></td>

        </tr>
    </table>
</f:form>

<a href="index.jsp">Accueil</a>
</body>
</html>
