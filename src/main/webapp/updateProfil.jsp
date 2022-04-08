<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 21/02/2022
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
    <jsp:include page="bootstrap.jsp" />

</head>
<div>
<jsp:include page="navbar.jsp" />

<div class="container">
    <div class="row align-items-center justify-content-md-center " style="height:90vh">
        <div class="col-auto"/>
<%--@elvariable id="user" type="com.example.application_entreprise_projet.CLASS.User"--%>
            <f:form modelAttribute="user" method="post" action="updateUser">
                <table>
                    <tr>
                        <td>Email:</td>
                        <td><f:input class="form-control" path="email"/></td>
                    </tr>
                    <tr>
                        <td>First Name:</td>
                        <td><f:input class="form-control" path="firstName"/></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><f:input class="form-control" path="lastName"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><f:input class="form-control" path="password"/></td>

                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td><f:input class="form-control" path="country"/></td>

                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><f:input class="form-control" path="city"/></td>

                    </tr>
                    <tr>
                        <td>PostCode:</td>
                        <td><f:input class="form-control" path="postCode"/></td>

                    </tr>
                    <tr>
                        <td>Street:</td>
                        <td><f:input class="form-control" path="street"/></td>

                    </tr>
                    <tr>

                        <td><input class="btn btn-secondary" type="submit" value="UPDATE"/></td>

                    </tr>
                </table>
            </f:form>
        </div>
    </div>
</div>

</body>
</html>
