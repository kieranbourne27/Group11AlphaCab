/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import com.UserServLet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

/**
 *
 * @author Callum
 */
public class InvoiceViewer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Jdbc jdbc = new Jdbc();
        jdbc.connect((Connection) request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", jdbc);

        String invoiceID = (String) request.getParameter("invoiceID");
        String queryForInvoices = "Select * FROM INVOICES where "
                + "ID=" + invoiceID
                + " AND CUSTOMERNAME ='" + session.getAttribute("username") + "'";
        String queryForVAT = "SELECT vat FROM pricing";

        String[] invoiceData = requestDemands(session, queryForInvoices);
        String[] invoiceVat = jdbc.retrieveQueryWithStringArray(queryForVAT);

        if (invoiceData[0] != null && invoiceVat[0] != null) {
            createInvoiceFormat(invoiceData, request, invoiceVat[0]);
            request.getRequestDispatcher("/WEB-INF/invoiceDisplay.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Sorry that invoice number is invalid.");
            request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
        }
    }

    private String[] requestDemands(HttpSession session, String qry) {
        String[] result = null;
        try {
            Jdbc dbBean = (Jdbc) session.getAttribute("dbbean");
            result = dbBean.selectInvoices(qry);
        } catch (SQLException ex) {
            Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    private void createInvoiceFormat(String[] invoiceData, HttpServletRequest request, String vat) {
        String vatMultiplier = "1." + vat;
        DecimalFormat decimalF = new DecimalFormat("#.##");
        double vatMulti = Double.parseDouble(vatMultiplier);
        double totalCost = Integer.parseInt(invoiceData[7]) * vatMulti;
        
        String formalInvoice
                = "Dear " + invoiceData[2] + ","
                + "</br>Thank you for your custom. Your booking information: </br>"
                + "Day: " + invoiceData[5] + ".</br>"
                + "Time: " + invoiceData[6] + ".</br></br>"
                + "The registration number of your taxi is: " + invoiceData[3] + ".</br>"
                + "For your journey, the cost of the taxi will be £" + invoiceData[7].toString() + ".</br>"
                + "The VAT for the journey is " + vat + "%, making the total cost: £" + decimalF.format(totalCost) + ".</br>"
                + "</br>Thank you again for your custom, </br>"
                + "AlphaCab.";

        request.setAttribute("formalInvoice", formalInvoice);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
