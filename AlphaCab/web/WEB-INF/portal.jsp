<%-- 
    Document   : index
    Created on : 09-Mar-2016, 16:52:19
    Author     : me-aydin
--%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Portal</title>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        
        <jsp:include page="header.jsp"/>
        <p><b><%
        if (session.getAttribute("username") != null) {
            out.println("Currently logged in as: " + session.getAttribute("username"));
        }
        %></b></p>
        <h1>Welcome to the portal page</h1>
        <form method="POST" action="UserService.do">
            <% 
                if(session.getAttribute("userType").equals("admin")){
            %>
            <input type="radio" name="tbl" value="List">List Users<br />
            <input type="radio" name="tbl" value="NewUser">New User<br />
            <input type="radio" name="tbl" value="Delete">Delete a User<br />
            <input type="radio" name="tbl" value="Bookings">View Bookings<br />
            <%}%>
            <input type="radio" name="tbl" value="Update">Password Change<br />
            <br />
            <input type=submit value="Action"> <br />
        </form>
            
        <p><%
        if (request.getAttribute("message") != null) {
            out.println(request.getAttribute("message"));
        }
        %></p>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
