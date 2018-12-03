<%-- 
    Document   : Foot
    Created on : 16-Nov-2015, 13:44:47
    Author     : me-aydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="main.css">
</head>
<div class="header">
    <form method="POST" action="UserService.do">
 <%
     if (session.getAttribute("userType").toString().trim().equals("admin")) {
 %>
        <button type="submit" name="tbl" value="List" class="btn-link"> List Users <b>|</b></button>
        <button type="submit" name="tbl" value="NewUser" class="btn-link"> Create User <b>|</b></button>
        <button type="submit" name="tbl" value="Delete" class="btn-link"> Delete User <b>|</b></button>
        <button type="submit" name="tbl" value="Bookings" class="btn-link"> View Bookings <b>|</b></button>
        <button type="submit" name="tbl" value="SetPrice" class="btn-link"> Set Price <b>|</b></button>
        <button type="submit" name="tbl" value="PendingDemands" class="btn-link"> Demands <b>|</b></button>
        <button type="submit" name="tbl" value="Reporting" class="btn-link"> Reporting <b>|</b></button>
        <button type="submit" name="tbl" value="Update" class="btn-link"> Change Password</button>
    </form>
<%}else if (session.getAttribute("userType").toString().trim().equals("customer")) {%>
    <form method="POST" action="UserService.do">
        <button type="submit" name="tbl" value="Invoices" class="btn-link">Invoices</button>
        <button type="submit" name="tbl" value="CreateBooking" class="btn-link">Create Booking</button>
        <button type="submit" name="tbl" value="ViewBookings" class="btn-link">View your Bookings</button>
        <button type="submit" name="tbl" value="Update" class="btn-link">Change Password</button>
    </form>
<%}else if (session.getAttribute("userType").toString().trim().equals("driver")) {%>
    <form method="POST" action="UserService.do">
        <button type="submit" name="tbl" value="DriverJourneys" class="btn-link">Driver Journeys</button>
        <button type="submit" name="tbl" value="Update" class="btn-link">Change Password</button>
    </form>
<%}%>

 </div></br></br>

