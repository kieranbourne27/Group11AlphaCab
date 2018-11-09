<%-- 
    Document   : index
    Created on : 09-Mar-2016, 16:52:19
    Author     : me-aydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is expected to serve as a proper Web Page</h1>
        <form method="POST" action="UserService.do">
        <p />
            View a table <br />
            <input type="radio" name="tbl" value="List">List Users<br />
            <input type="radio" name="tbl" value="NewUser">New User<br />
            <input type="radio" name="tbl" value="Update">Password Change<br />
            <input type="radio" name="tbl" value="Delete">Delete a User<br />
            <input type=submit value="Action"> <br />
        </form> 
    </body>
</html>
