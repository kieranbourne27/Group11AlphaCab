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
 <%
     if (session.getAttribute("username") != null) {
 %>
    <form method="POST" action="UserService.do">
        <button type="submit" name="tbl" value="List" class="btn-link">List Users</button>
        <button type="submit" name="tbl" value="NewUser" class="btn-link">Create User</button>
        <button type="submit" name="tbl" value="Delete" class="btn-link">Delete User</button>
        <button type="submit" name="tbl" value="Bookings" class="btn-link">View Bookings</button>
<%}%>
        <button type="submit" name="tbl" value="Update" class="btn-link">Change Password</button>
    </form>
 </div>

