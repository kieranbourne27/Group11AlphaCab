<%-- 
    Document   : Foot
    Created on : 16-Nov-2015, 13:44:47
    Author     : me-aydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%
     if (session.getAttribute("username") != null) {
 %>
 <form method="POST" action="LogoutService.do" >
    <a href="login.jsp">Logout</a>
 </form>
 <a href="index.jsp"> Back to Main </a>
 <%}%>

