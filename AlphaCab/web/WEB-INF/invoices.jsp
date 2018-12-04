<%-- 
    Document   : invoices
    Created on : 25-Nov-2018, 20:41:05
    Author     : Callum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="main.css">
    <title>Invoices</title>
  </head>
  <body>
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
    <jsp:include page="header.jsp"/>
    <h1>Invoice List:</h1>
    
    <div class = "displayInvoices">
            <%=(String)(request.getAttribute("invoiceTable"))%>
    </div>
    
    </br>
    <div class = "invoicesSelector" style = "border: 3px solid black; border-collapse: collapse; width: 300px;">
            <form action="InvoiceViewer.do">
                <table>
                    <tr>
                        <td>Invoice ID:</td>
                        <td><input type="text" name="invoiceID" required/></td>
                    </tr>
                    <tr> 
                        <td><input type="submit"/></td>
                    </tr>
                </table>
            </form>
    </div>
    <br>
    <jsp:include page="foot.jsp"/>
  </body>
</html>
