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
        <h1>List of all users:</h1>
        <div class = "displayUsers">
            <%=(String)(request.getAttribute("query"))%>
        </div>
        <br>
        <div class="footer">
            <jsp:include page="foot.jsp"/>
        </div>
    </body>
</html>
