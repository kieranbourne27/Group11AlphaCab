<%-- 
    Document   : login
    Created on : 05-Nov-2018, 14:30:15
    Author     : k4-bourne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <h3>Please enter your login information</h3>
        <form method="POST" action="Login.do">
        <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Login"/></td>
                </tr>
        </table>
        </form>
        <p> <a href ="signupUser.jsp">Sign up</a></p> 
        
        <p><%
            if (request.getAttribute("message") != null) {
                out.println(request.getAttribute("message"));
            }
            %></p>
    </body>
</html>
