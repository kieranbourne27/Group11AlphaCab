/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

/**
 *
 * @author c2-sellick
 */
public class AssignDriver extends HttpServlet {

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
    Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

    String[] queryForDemand = new String[1];
    String[] queryForDriver = new String[1];
    queryForDemand[0] = (String) request.getParameter("demandID");
    queryForDriver[0] = (String) request.getParameter("driverReg");
    String mileage = (String)request.getParameter("mileage");

    if (jdbc == null) {
      request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
    }

    if (queryForDemand[0].equals("") || queryForDemand[0].equals("") || mileage.equals("")) {
      request.setAttribute("message", "Fields cannot be cannot be NULL");
    } else if (jdbc.existsInDemands(queryForDemand[0])) {
      if (jdbc.existsInDriver(queryForDriver[0])) {
        String[] query = {queryForDemand[0], queryForDriver[0], mileage};
        String[] dateTime = jdbc.getTimeFromDemands(queryForDemand[0]);

        if (!dateTime.equals("")) {
          String[] checkQuery = {queryForDriver[0], dateTime[0], dateTime[1]};
          
          if (!jdbc.doesTimeExistInJourney(checkQuery)) {
            if (jdbc.insertJourney(query)) {
              createInvoice(Integer.parseInt(queryForDemand[0]), jdbc, request);
              request.setAttribute("message", request.getAttribute("message") + "</br>Journey was added");
              request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
            } else {
              request.setAttribute("message", query[0] + " was not added.");
            }
          }else{
            request.setAttribute("message", "Sorry, that driver is busy. Please try another driver");
            request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
          }
        }
      }
    }

    request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
  }
  
  private static void createInvoice(int demandID, Jdbc jdbc, HttpServletRequest request){
    String queryForJourneyID = "Select MAX(JID) FROM JOURNEY";
    String queryForJourneyData = "Select * FROM JOURNEY where JID =";
    String queryForDemands = "Select NAME FROM DEMANDS where id = " + demandID;
    String queryForPricing = "Select PRICE FROM PRICING";
    
    // Query the database.
    String journeyID = jdbc.retrieveQueryWithStringArray(queryForJourneyID)[0];
    String[] journeyDetails = jdbc.retrieveQueryWithStringArray(queryForJourneyData + journeyID);
    String customerName = jdbc.retrieveQueryWithStringArray(queryForDemands)[0];
    String pricingPerMile = jdbc.retrieveQueryWithStringArray(queryForPricing)[0];
    
    if(journeyDetails.length == 0 || customerName.equals("") || pricingPerMile.equals("")){
      // Set error message.
      request.setAttribute("message","Invoice was not created");
    } else{
      // Create new invoice.
      int price = Integer.valueOf(pricingPerMile);
      int distance =  Integer.valueOf(journeyDetails[3]);
      int totalPrice = price * distance;
      String travelCost = String.valueOf(totalPrice);
      String[] createInvoiceQuery = {
        journeyDetails[0],  // JID
        customerName,       // Customer Name
        journeyDetails[4],  // Drivers Reg
        journeyDetails[3],  // Mileage
        journeyDetails[5],  // Date
        journeyDetails[6],  // Time
        travelCost          // Price
      } ;
      
      if(jdbc.insertInvoice(createInvoiceQuery)){
        request.setAttribute("message","Invoice created");
      }
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
