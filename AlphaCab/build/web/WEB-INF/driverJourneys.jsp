<%-- 
    Document   : driverJourneys
    Created on : 26-Nov-2018, 08:48:07
    Author     : k4-bourne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Driver Journeys</title>

        <style>
            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            #map {
                height: 40%;
                width: 40%;
            }
            /* Optional: Makes the sample page fill the window. */
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
        </style>
    </head>
    <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>Your Journeys</h1>
        <div class = "displayJourneys">
            <%=(String) (request.getAttribute("journeyQuery"))%>
        </div> </br>

        <div class = "createMap" style = "border: 3px solid black; border-collapse: collapse; width: 300px;">
            <h2>View Route:</h2>
            <form action="CreateMapURL.do">
                <table>
                    <tr>
                        <td>Select Journey ID:</td>
                        <td><input type="text" name="journeyID" required/></td>
                    </tr>
                    <tr> 
                        <td><input type="submit"/></td>
                    </tr>
                </table>
            </form>
        </div>

        <%
            String url = (String) (request.getAttribute("mapUrl"));

            if (url != null) {
        %>
        <a href ="<%=url%>" target="_blank"> Journey On Google Maps</a>
        <%}%>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
