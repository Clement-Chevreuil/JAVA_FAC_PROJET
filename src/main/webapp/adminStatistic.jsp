<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Clement Chevreuil
  Date: 24/02/2022
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <% String test = (String)session.getAttribute("option1"); %>
    <input type="hidden" id="optionTest" value="<%=test%>">
    <title>Title</title>

    <%! List<String> listYEAR, listMONTH; %>
    <%! List<Integer> listStatYEAR, listStatMONTH; %>
    <%! Integer year; %>
    <%! HttpSession session;%>
    <% session = request.getSession(); %>
    <% listYEAR = (List<String>)session.getAttribute("YEAR"); %>
    <% listMONTH = (List<String>)session.getAttribute("MONTH"); %>
    <% listStatYEAR = (List<Integer>)session.getAttribute("listStatYEAR"); %>
    <% listStatMONTH = (List<Integer>)session.getAttribute("listStatMONTH"); %>
    <% year = (Integer)session.getAttribute("option"); %>


    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);
        google.charts.setOnLoadCallback(drawChart2);

        function drawChart() {

            var data = google.visualization.arrayToDataTable([
                ['Year', 'Sales'],
                <% for (int i = 0; i < listStatYEAR.size() ; i++) { %>
                ["<%= listYEAR.get(i) %>", <%= listStatYEAR.get(i) %> ],
                <% } %>
            ]);

            var options = {
                title: 'New User YEAR <%= year - 4 %> to <%= year %>',
                hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
                vAxis: {minValue: 0}
            };

            var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }

        function drawChart2() {

            var data = google.visualization.arrayToDataTable([
                ['Year', 'Sales'],
                <% for (int i = 0; i < listStatMONTH.size() ; i++) { %>
                ["<%= listMONTH.get(i) %>", <%= listStatMONTH.get(i) %> ],
                <% } %>
            ]);

            var options = {
                title: 'New User MONTH <%= year %>',
                hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
                vAxis: {minValue: 0}
            };

            var chart = new google.visualization.AreaChart(document.getElementById('chart_div2'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
    <div id="chart_div" style="width: 100%; height: 500px;"></div>
    <div id="chart_div2" style="width: 100%; height: 500px;"></div>
    <a href="index.jsp">Accueil</a>


</body>
</html>
