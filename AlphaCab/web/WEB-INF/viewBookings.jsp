<%-- 
    Document   : viewBookings
    Created on : 25-Nov-2018, 20:44:02
    Author     : k4-bourne
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Bookings</title>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>Details of your bookings:</h1>
        <h2>Your pending demands:</h2>
        <div class = "displayBookings">
            <%=(String)(request.getAttribute("query"))%>
        </div>
        <h2>Your journeys:</h2>
        <div class = "displayJourneys">
            <%=(String)(request.getAttribute("journeyQuery"))%>
        </div>
        <br>
        <jsp:include page="foot.jsp"/>
    </body>
</html>

