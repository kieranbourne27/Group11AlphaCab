/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
 * @author k4-bourne
 */
public class Reporting extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);
        
        String date = request.getParameter("date");
        
        String[] queryResults = dbBean.retrieveReportingInformation(date);
        createReportFormat(queryResults, request, date);
        operationsReport(request, dbBean, date);
        request.getRequestDispatcher("/WEB-INF/reporting.jsp").forward(request, response);
    }
    
    private void createReportFormat(String[] queryResults, HttpServletRequest request, String date) {
        String report = "";
        if (queryResults.length != 0) {
            report = "<b><u>Information summary for " + date + "</u></b><br/><br/>The total turnover for today is: £" + queryResults[0] + "<br/>"
                + "The number of customers served today is: " + queryResults[1] + "<br/>";
        } else {
            report = "There is no information to report on for this day.";
        }
        
        request.setAttribute("dailyReport", report);
    }
    
    private void operationsReport(HttpServletRequest request, Jdbc dbBean, String date) throws IOException, ServletException {
        try {
            String msg = "No operations";
            String qry = "SELECT CUSTOMERNAME, JRNY.DESTINATION, PRICE FROM INVOICES " +
                    "JOIN JOURNEY JRNY ON JRNY.JID = INVOICES.JID " +
                    "WHERE INVOICES.DATE = '" + date + "'";
            
            String journeyResult = dbBean.retrieve(qry);
                    
            request.setAttribute("reportingQuery", journeyResult);
        } catch (SQLException ex) {
            Logger.getLogger(Reporting.class.getName()).log(Level.SEVERE, null, ex);
        }
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
