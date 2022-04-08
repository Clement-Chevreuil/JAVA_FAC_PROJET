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
    <title>Title</title>
    <jsp:include page="../bootstrap.jsp" />
</head>

<jsp:include page="../navbar.jsp" />
<div>
<div class="container">
    <div class="row align-items-center justify-content-md-center " style="height:90vh">
        <div class="col-auto"/>
<%--@elvariable id="user" type="com.example.application_entreprise_projet.CLASS.User"--%>
<f:form modelAttribute="user" method="post" action="connexion">

    <div  class="mb-3">
        <h4 for="email">Email:</h4>
        <f:input style="width: 400px" id="email" class="form-control" placeholder="Email" path="email"/>
    </div>

    <div  class="mb-3">
        <h4 for="password">Password:</h4>
        <f:input style="width: 400px" type="password" id="password" class="form-control" placeholder="Password" path="password"/>
    </div>

    <input class="btn btn-secondary" type="submit" value="LOG"/>
</f:form>

    </div>
</div>
</div>
</body>
</html>
