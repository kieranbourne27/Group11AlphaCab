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
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>Current Bookings:</h1>
        
        <div class = "displayBookings">
<% 
  String table = (String)(request.getAttribute("query"));
  if(table.equals("NULL")){
%>
          <p>Sorry you have no invoices.
<%}else {%>
            <%=(String)(request.getAttribute("query"))%>
<%}%>
        </div>
        <br>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
