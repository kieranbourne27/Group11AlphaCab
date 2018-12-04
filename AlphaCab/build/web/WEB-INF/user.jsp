<%-- 
    Document   : driver
    Created on : 01-Nov-2015, 15:18:08
    Author     : me-aydin
--%>

<%@page import="model.Jdbc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Login Main Page</title>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
        return;
    }
%>
        <jsp:include page="header.jsp"/>
        <%! int i=0;
            String str="Register"; 
            String url = "NewUser.do";
        %>
        <%
            String[] userNames = (String[])request.getAttribute("userNames");
            if((String)request.getAttribute("msg")=="del") {
                str = "Delete";
                url = "Delete.do";
        %>
        <h1>Delete User details:</h1>
        <form method="POST" action="<%=url%>">     
            <table>
                <tr>
                    <th></th>
                    <th>Select Username:</th>
                </tr>
                <tr>
                    <td>
                        <select name="username">
                            <%
                                for(int i = 0; i < userNames.length; i++){
                                    String name = userNames[i];
                            %>
                            <option value = " <%=(String)(name)%> "><%=(String)(name)%></option>
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="<%=str%>"/></td>
                </tr>
            </table>
        </form> 
        <%
            }
            else {
                str="Register";
                url = "NewUser.do";
        %>
        <h1>User's details:</h1>
        <form method="POST" action="<%=url%>">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" required/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" required/></td>
                </tr>
                <tr>
                    <td>User Type (customer, admin, driver):</td>
                    <td>
                        <select name="userType" required>
                            <option value = "admin">Admin</option>
                            <option value = "driver">Driver</option>
                            <option value = "customer">Customer</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Registration Number:</td>
                    <td><input type="text" name="regNumber"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="<%=str%>"/></td>
                </tr>
            </table>
        </form> 
        <%
            } 
        %>
        
        <%
            if (i++>0 && request.getAttribute("message")!=null) {
                out.println(request.getAttribute("message"));
                i--;
            }
        %>
        </br>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
