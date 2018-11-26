<%-- 
    Document   : driverJourneys
    Created on : 26-Nov-2018, 08:48:07
    Author     : k4-bourne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Driver Journeys</title>
    </head>
    <body>
        <body><jsp:include page="header.jsp"/>
        <h1>Your Journeys</h1>
        <div class = "displayJourneys">
            <%=(String)(request.getAttribute("journeyQuery"))%>
        </div> </br>
    </body>
</html>
