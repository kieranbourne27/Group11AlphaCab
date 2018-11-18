<%-- 
    Document   : bookings
    Created on : 18-Nov-2018, 18:28:54
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
        <h1>Current Bookings:</h1>

        <%=(String)(request.getAttribute("query"))%>
        <br>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
