/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
//import java.io.PrintWriter;
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

        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");

        if ((Connection) request.getServletContext().getAttribute("connection") == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        if (request.getParameter("tbl").equals("List")) {
            String msg = "No users";
            String qry = "select username, usertype from users";
            String result = RequestDemands(session, msg, qry);

            request.setAttribute("query", result);
            request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("Bookings")) {
            String msg = "No bookings";
            String qry = "select id, name, address, destination, date, time, status from demands";
            String result = RequestDemands(session, msg, qry);

            request.setAttribute("query", result);
            request.getRequestDispatcher("/WEB-INF/bookings.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("NewUser")) {
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("Update")) {
            request.getRequestDispatcher("/WEB-INF/passwdChange.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("Login")) {
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("PendingDemands")) {
            String msg = "No bookings";
            String qry = "select id, name, address, destination, date, time, status from demands";
            String demandResult = RequestDemands(session, msg, qry);

            String driverMsg = "No drivers available";
            String driverQry = "select registration, name from drivers";
            String driverResult = RequestDemands(session, driverMsg, driverQry);

            request.setAttribute("demandQuery", demandResult);
            request.setAttribute("driverQuery", driverResult);
            request.getRequestDispatcher("/WEB-INF/pendingDemands.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("CreateBooking")) {
            request.setAttribute("username", session.getAttribute("username"));
            request.getRequestDispatcher("/WEB-INF/requestBooking.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("ViewBookings")) {
            String msg = "No bookings";
            String qry = "select name, address, destination, date, time, status  from demands "
                    + "where booked_by = '" + session.getAttribute("username") + "'";
            String journeyQry = "select jrny.destination, jrny.distance, jrny.date, jrny.time, jrny.registration from demands "
                    + "join journey jrny on jrny.id = demands.id where demands.booked_by = '" + session.getAttribute("username") + "'";

            String result = RequestDemands(session, msg, qry);
            request.setAttribute("query", result);

            String journeysResult = RequestDemands(session, msg, journeyQry);
            request.setAttribute("journeyQuery", journeysResult);

            request.getRequestDispatcher("/WEB-INF/viewBookings.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("Invoices")) {

            String msg = "No Invoices";
            String qry = "select id, "
                    + "CUSTOMERNAME, "
                    + "DRIVERREG, "
                    + "MILEAGE, "
                    + "DATE, "
                    + "TIME, "
                    + "PRICE "
                    + "FROM INVOICES where CUSTOMERNAME ='" + session.getAttribute("username") + "'";
            String demandResult = RequestDemands(session, msg, qry);

            request.setAttribute("invoiceTable", demandResult);
            request.getRequestDispatcher("/WEB-INF/invoices.jsp").forward(request, response);
        } else if(request.getParameter("tbl").equals("SetPrice")){
          String qry = "select Price From PRICING";
          String[] priceResult = RequestQueryWithStringArray(session, qry);
          
          if(priceResult[0] != null){
            request.setAttribute("pricing", priceResult[0]);
            request.getRequestDispatcher("/WEB-INF/changePrices.jsp").forward(request, response);
          }else{
            request.setAttribute("message", "Sorry the pricing is not available right now.");
            request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
          }
        } else if (request.getParameter("tbl").equals("DriverJourneys")) {
            String msg = "No journeys";
            String qry = "select journey.registration, destination, distance, date, time from journey"
                    + " join drivers on journey.registration = drivers.registration where drivers.name = '"
                    + session.getAttribute("username") + "'";
            String journeyResult = RequestDemands(session, msg, qry);

            request.setAttribute("journeyQuery", journeyResult);
            
            request.getRequestDispatcher("/WEB-INF/driverJourneys.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "del");
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        }
    }

    private String RequestDemands(HttpSession session, String msg, String qry) {
        try {
            Jdbc dbBean = (Jdbc) session.getAttribute("dbbean");
            msg = dbBean.retrieve(qry);
        } catch (SQLException ex) {
            Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg;
    }
    
    private String[] RequestQueryWithStringArray(HttpSession session, String qry) {
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