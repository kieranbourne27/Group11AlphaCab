<%-- 
    Document   : requestBooking.jsp
    Created on : 12-Nov-2018, 14:21:32
    Author     : c2-sellick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Taxi Booking Page</title>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>Taxi Booking Page</h1>
        
        <p><%
        if (request.getAttribute("message") != null) {
            out.println("* " + request.getAttribute("message") + " *");
        }
        %></p></br>
        
        <form method="POST" action="requestBooking.do">
            <table>
                <tr>
                    <td>Start Address (from):</td>
                    <td><input type="text" name="startAddress" required/></td>
                </tr>
                <tr>
                    <td>Destination Address (To):</td>
                    <td><input type="text" name="destAddress" required/></td>
                </tr>
                <tr>
                    <td>Pickup Date:</td>
                    <td><input type="date" name="date" required/></td>
                </tr>
                <tr>
                    <td>Pickup Time:</td>
                    <td><input type="time" name="pickupTime" required/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Booking"/></td>
                </tr>
            </table>
        </form>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
