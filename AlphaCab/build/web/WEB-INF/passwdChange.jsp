<%-- 
    Document   : passwdChange
    Created on : 11-Mar-2016, 01:02:16
    Author     : me-aydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Change Password</title>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>Password change</h1>
           <form method="POST" action="Update.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>New Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td><input type="password" name="newpasswd"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Change"/></td>
                </tr>
            </table>
        </form>
        <%=((String)(request.getAttribute("msg"))!=null)?(String)(request.getAttribute("msg")):""%>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
