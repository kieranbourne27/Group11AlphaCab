/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
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
 * @author me-aydin
 */
public class UserServLet extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        
        if(session == null){
            response.sendRedirect("index.jsp");
            return;
        }
                
        response.setContentType("text/html;charset=UTF-8");

        if ((Connection) request.getServletContext().getAttribute("connection") == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }
        
        switch(request.getParameter("tbl")){
            case "List":
                listUsersAndUserTypes(session, request, response);
                break;
            case "Bookings":
                retrieveBookings(session, request, response);
                break;
            case "NewUser":
                request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
                break;
            case "Update":
                request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
                break;
            case "Login":
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                break;
            case "PendingDemands":
                retrieveRequestedDemands(session, request, response);
                break;
            case "CreateBooking":
                request.setAttribute("username", session.getAttribute("username"));
                request.getRequestDispatcher("/WEB-INF/requestBooking.jsp").forward(request, response);
                break;
            case "ViewBookings":
                viewBookings(session, request, response);
                break;
            case "Invoices":
                showInvoices(session, request, response);
                break;
            case "SetPrice":
                setPrice(session, request, response);
                break;
            case "DriverJourneys":
                driverJourney(session, request, response);
                break;
            case "Delete":
                request.setAttribute("msg", "del");
                obtainPresentUsers(session, request, response);
                break;
            case "Reporting":
                request.getRequestDispatcher("/WEB-INF/reporting.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
                break;
        }
    }
    
    private void obtainPresentUsers(HttpSession session, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");
        String query = "SELECT username FROM users";
        String[] userNames = requestQueryWithStringArray(session, query);
        
        if(userNames[0] != null){
            request.setAttribute("userNames", userNames);
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        }else{
            request.setAttribute("message", "Could not retrieve usernames.");
            request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
        }
    }

    private void driverJourney(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String msg = "No journeys";
        String qry = "select jid, journey.registration, destination, distance, date, time from journey"
                + " join drivers on journey.registration = drivers.registration where drivers.name = '"
                + session.getAttribute("username") + "'";
        String journeyResult = requestDemands(session, msg, qry);
        
        request.setAttribute("journeyQuery", journeyResult);
        
        request.getRequestDispatcher("/WEB-INF/driverJourneys.jsp").forward(request, response);
    }

    private void setPrice(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String qry = "SELECT Price, Vat FROM pricing";
        String[] priceResult = requestQueryWithStringArray(session, qry);
        
        if(priceResult[0] != null){
            String results = getInvoices(session);
            
            if(!results.equals("")){
                request.setAttribute("tableOfInvoices", results);
                request.setAttribute("pricing", priceResult[0]);
                request.setAttribute("vat", priceResult[1]);
                request.getRequestDispatcher("/WEB-INF/changePrices.jsp").forward(request, response);
            }else{
                request.setAttribute("message", "Sorry the pricing is not available right now.");
            request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("message", "Sorry the pricing is not available right now.");
            request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
        }
    }
    
    private String getInvoices(HttpSession session){
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");
        String query = "Select id, customername, driverreg, mileage, date, time, price FROM invoices";
        String tableOfInvoices = "";
        try {
            tableOfInvoices = jdbc.retrieve(query);
        } catch (SQLException ex) {
            Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tableOfInvoices;
    }

    private void showInvoices(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "No Invoices";
        String qry = "select id, "
                + "CUSTOMERNAME, "
                + "DRIVERREG, "
                + "MILEAGE, "
                + "DATE, "
                + "TIME, "
                + "PRICE "
                + "FROM INVOICES where CUSTOMERNAME ='" + session.getAttribute("username") + "'";
        String demandResult = requestDemands(session, msg, qry);
        
        request.setAttribute("invoiceTable", demandResult);
        request.getRequestDispatcher("/WEB-INF/invoices.jsp").forward(request, response);
    }

    private void viewBookings(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "No bookings";
        String qry = "select name, address, destination, date, time, status  from demands "
                + "where booked_by = '" + session.getAttribute("username") + "'";
        String journeyQry = "select jrny.destination, jrny.distance, jrny.date, jrny.time, jrny.registration from demands "
                + "join journey jrny on jrny.id = demands.id where demands.booked_by = '" + session.getAttribute("username") + "'";
        
        String result = requestDemands(session, msg, qry);
        request.setAttribute("query", result);
        
        String journeysResult = requestDemands(session, msg, journeyQry);
        request.setAttribute("journeyQuery", journeysResult);
        
        request.getRequestDispatcher("/WEB-INF/viewBookings.jsp").forward(request, response);
    }

    private void retrieveRequestedDemands(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "No bookings";
        String qry = "select id, name, address, destination, date, time, status from demands";
        String demandResult = requestDemands(session, msg, qry);
        
        String driverMsg = "No drivers available";
        String driverQry = "select registration, name from drivers";
        String driverResult = requestDemands(session, driverMsg, driverQry);
        
        request.setAttribute("demandQuery", demandResult);
        request.setAttribute("driverQuery", driverResult);
        request.getRequestDispatcher("/WEB-INF/pendingDemands.jsp").forward(request, response);
    }

    private void retrieveBookings(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "No bookings";
        String qry = "select id, name, address, destination, date, time, status from demands";
        String result = requestDemands(session, msg, qry);
        
        request.setAttribute("query", result);
        request.getRequestDispatcher("/WEB-INF/bookings.jsp").forward(request, response);
    }

    private void listUsersAndUserTypes(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String msg = "No users";
        String qry = "select username, usertype from users";
        String result = requestDemands(session, msg, qry);
        
        request.setAttribute("query", result);
        request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
    }

    private String requestDemands(HttpSession session, String msg, String qry) {
        try {
            Jdbc dbBean = (Jdbc) session.getAttribute("dbbean");
            msg = dbBean.retrieve(qry);
        } catch (SQLException ex) {
            Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg;
    }
    
    private String[] requestQueryWithStringArray(HttpSession session, String qry) {
      String[] result = null;
      Jdbc dbBean = (Jdbc) session.getAttribute("dbbean");
      result = dbBean.retrieveQueryWithStringArray(qry);

      return result;
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
