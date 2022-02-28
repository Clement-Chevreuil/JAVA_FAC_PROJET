<%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 20/02/2022
  Time: 18:31
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
    <H1>Trader</H1>

    <a type="button" href="editUser">UPDATE PROFIL</a>


    <h2>ADD PRODUCE</h2>

    <%--@elvariable id="produce" type="com.example.application_entreprise_projet.CLASS.Produce"--%>
    <f:form modelAttribute="produce" method="post" action="saveProduce">
        <table>
            <tr>
                <td>Name :</td>
                <td><f:input path="name"/></td>

            </tr>
            <tr>
                <td>Category :</td>
                <td><f:input path="category"/></td>

            </tr>
            <tr>
                <td>Price :</td>
                <td><f:input path="price"/></td>

            </tr>
            <tr>
                <td>Date Expiration :</td>
                <td><f:input type="date" path="expirationDate"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Save"/></td>
            </tr>
        </table>
    </f:form>

    <h2>LIST PRODUCE</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>CATEGORY</th>
            <th>PRICE</th>
            <th>EXPIRATION DATE</th>
            <th>SOLD</th>
            <th>ACTIONS</th>
        </tr>
        <c:forEach items="${produces}" var="t">
            <tr>
                <td>${t.id}</td>
                <td>${t.name}</td>
                <td>${t.category}</td>
                <td>${t.price}</td>
                <td>${t.expirationDate}</td>
                <td>${t.sold}</td>
                <td>
                    <c:if test="${t.sold == 0}">
                        <a href="deleteProduce?id=${t.id}">Delete</a>
                        <a href="editProduce?id=${t.id}">Edit</a>
                    </c:if>
                    <c:if test="${t.sold == 1}">
                        <p>NO CHANGE POSSIBLE</p>
                    </c:if>

                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>LIST Commande Valide</h2>

    <table>
        <tr>
            <th>COMMANDE NUMBER</th>
            <th>DATE COMMANDE</th>
            <th>FIRST NAME</th>
            <th>LAST NAME</th>
            <th>EMAIL</th>
            <th>COUNTRY</th>
            <th>CITY</th>
            <th>POST CODE</th>
            <th>STREET</th>
            <th>ACTIONS</th>
        </tr>
        <c:forEach items="${commande}" var="t">
            <tr>
                <td>${t.noCommande}</td>
                <td>${t.dateCommandeValidate}</td>
                <td>${t.user.firstName}</td>
                <td>${t.user.lastName}</td>
                <td>${t.user.email}</td>
                <td>${t.user.country}</td>
                <td>${t.user.city}</td>
                <td>${t.user.postCode}</td>
                <td>${t.user.street}</td>
                <td>
                    <a href="traderCommandDetail?noCommande=${t.noCommande}">SEE</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
