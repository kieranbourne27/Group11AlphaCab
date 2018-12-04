<%-- 
    Document   : results
    Created on : 30-Oct-2015, 10:02:53
    Author     : me-aydin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>DB Results</title>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>List of all users:</h1>
        <div class = "displayUsers">
            <%=(String)(request.getAttribute("query"))%>
        </div>
        <br>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
