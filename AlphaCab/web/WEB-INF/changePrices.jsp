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
<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>
        <jsp:include page="header.jsp"/>
        <h1>Change Price:</h1>

        <div class = "displayInvoices">
            <%
                String tableOfInvoices = (String) request.getAttribute("tableOfInvoices");
                if (!tableOfInvoices.equals("")) {
            %>
            <%=(String) (tableOfInvoices)%>
            <%
                }
            %>
        </div>
        </br>

        <p>Change Invoice Price: </p>
        <div class = "changeInvoicePrices" style = "border: 3px solid black; border-collapse: collapse; width: 300px;">
            <form action="SetPrice.do">
                <table>
                    <tr>
                        <td>Invoice ID:</td>
                        <td><input type="text" name="invoiceID" required/></td>
                    </tr>
                    <tr>
                        <td>Price Per Mile:</td>
                        <td><input type="text" name="pricePerMile" required/></td>
                    </tr>
                    <tr> 
                        <td><input type="submit"/></td>
                    </tr>
                </table>
            </form>
        </div>
        </br>

        <p>The current price per mile is: 
            <b>
                <%=(String) (request.getAttribute("pricing"))%>
            </b>
        </p>

        <div class = "changePrices" style = "border: 3px solid black; border-collapse: collapse; width: 300px;">
            <form action="SetPrice.do">
                <table>
                    <tr>
                        <td>Change Global Mileage Price:</td>
                        <td><input type="text" name="newPrice" required/></td>
                    </tr>
                    <tr> 
                        <td><input type="submit"/></td>
                    </tr>
                </table>
            </form>
        </div></br>
        
        <p>The current VAT Charge is: 
            <b>
                <%=(String) (request.getAttribute("vat"))%>
            </b>
        </p>

        <div class = "changeVAT" style = "border: 3px solid black; border-collapse: collapse; width: 300px;">
            <form action="SetPrice.do">
                <table>
                    <tr>
                        <td>Change VAT Charge:</td>
                        <td><input type="text" name="newVat" required/></td>
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
