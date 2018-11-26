<%-- 
    Document   : changePrices
    Created on : 25-Nov-2018, 22:22:02
    Author     : Callum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="main.css">
    <title>Pricing</title>
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <h1>Change Price:</h1>
    
    <p>The current price per mile is: 
      <b>
        <%=(String)(request.getAttribute("pricing"))%>
      </b>
    </p>
    
    <div class = "changePrices" style = "border: 3px solid black; border-collapse: collapse; width: 300px;">
      <form action="SetPrice.do">
          <table>
              <tr>
                  <td>Change Mileage Price:</td>
                  <td><input type="text" name="newPrice" required/></td>
              </tr>
              <tr> 
                  <td><input type="submit"/></td>
              </tr>
          </table>
      </form>
    </div>
    
    <jsp:include page="foot.jsp"/>
  </body>
</html>
