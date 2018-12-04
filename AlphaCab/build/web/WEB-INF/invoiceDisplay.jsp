<%-- 
    Document   : invoiceDisplay
    Created on : 25-Nov-2018, 21:55:08
    Author     : Callum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="main.css">
    <title>Invoice</title>
  </head>
  <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
    <jsp:include page="header.jsp"/>
    <h1>Your Invoice</h1>
   
    <p>  
    <%=(String)(request.getAttribute("formalInvoice"))%>
    </p>
  
    <br>
    <jsp:include page="foot.jsp"/>  
  </body>
</html>
