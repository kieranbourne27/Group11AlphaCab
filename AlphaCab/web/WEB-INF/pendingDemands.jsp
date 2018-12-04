<%-- 
    Document   : assignDriver
    Created on : 19-Nov-2018, 14:22:06
    Author     : c2-sellick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Pending Demands</title>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>Pending Demands:</h1>
        <h3>List of Demands:</h3>
        
        <div class = "displayBookings">
            <%=(String)(request.getAttribute("demandQuery"))%>
        </div> </br>
        
        <h3>Drivers:</h3>        
        <div class = "displayDrivers">
            <%=(String)(request.getAttribute("driverQuery"))%>
        </div> </br>
        
        <h3>Please provide your following details:</h3>        
        <div class = "assignDriver" style = "border: 3px solid black; border-collapse: collapse; width: 300px;">
            <form action="AssignDriver.do">
                <table>
                    <tr>
                        <td>Demand ID:</td>
                        <td><input type="text" name="demandID" required/></td>
                    </tr>
                    <tr>
                        <td>Mileage:</td>
                        <td><input type="text" name="mileage" required/></td>
                    </tr>
                    <tr>
                        <td>Driver Registration:</td>
                        <td><input type="text" name="driverReg" required/></td>
                    </tr>
                    <tr> 
                        <td><input type="submit"/></td>
                    </tr>
                </table>
            </form>
        </div>
        
        <jsp:include page="foot.jsp"/>    
    </body>
</html>
