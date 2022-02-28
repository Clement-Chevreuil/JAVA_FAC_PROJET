<%@ page import="com.example.application_entreprise_projet.CLASS.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "WELCOME TO ANTI-WASTE WEBSITE" %>
</h1>
<br/>
    <a href="login">Login</a>
    <a href="register">Register</a>


    <%
        HttpSession session1 = request.getSession(true);
        String sessionIsLoad;
        if(session1.getAttribute("user") != null)
        {
             sessionIsLoad = "YES";
        }
        else
        {
            sessionIsLoad = "NO";
        }

    %>
    <br>
    <a>Session en cours : <%= sessionIsLoad %></a>
</body>
</html>