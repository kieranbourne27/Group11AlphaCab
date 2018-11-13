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
        <title>Taxi Booking Page</title>
    </head>
    <body>
        <h1>Taxi Booking Page</h1>
        </br>
        
        <form method="POST" action="requestBooking.do">
            <table>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="firstName"/></td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td><input type="password" name="surname"/></td>
                </tr>
                <tr>
                    <td>Start Address (from):</td>
                    <td><input type="password" name="startAddress"/></td>
                </tr>
                <tr>
                    <td>Destination Address (To):</td>
                    <td><input type="password" name="destAddress"/></td>
                </tr>
                <tr>
                    <td>Pickup Date:</td>
                    <td><input type="date" name="date"/></td>
                </tr>
                <tr>
                    <td>Pickup Time:</td>
                    <td><input type="time" name="pickupTime"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Booking"/></td>
                </tr>
            </table>
        </form>
        
        </br>
        <form method = "POST" action = "redirect.do">
            <input type="submit" value="Login Page"/>
        </form>
    </body>
</html>
