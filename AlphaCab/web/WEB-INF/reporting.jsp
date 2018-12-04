<%-- 
    Document   : reporting
    Created on : 02-Dec-2018, 18:22:25
    Author     : k4-bourne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Reporting Page</title>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>Daily Reporting:</h1>
        <form method="POST" action="Reporting.do">
            <table>
                <tr>
                    <td>Date to view report for:</td>
                    <td><input type="date" name="date" required/></td>
                </tr>       
                <tr> 
                    <td> <input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
        <%if (request.getAttribute("dailyReport") != null) {%>
            <p>
                <%=(String)(request.getAttribute("dailyReport"))%>
            </p>
            </br>
            <div class = "displayJourneys">
                <%=(String)(request.getAttribute("reportingQuery"))%>
            </div> 
            </br>
        <%}%>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
