/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import com.UserServLet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
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
 * @author c2-sellick
 */
public class CreateMapURL extends HttpServlet {

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
        
        String driverReg = getDriversRegistration(jdbc, request, session);
        
        if(!driverReg.equals("")){
            String demandID = getDemandID(jdbc, request, driverReg);
            
            if(!demandID.equals("")){
                String[] originAndDestination = getAddresses(jdbc, request, demandID);
                
                if(originAndDestination[0] != null){
                    String mapUrl = createStringForGooglemaps(originAndDestination);
                    
                    if(!mapUrl.equals("")){
                        recreateJourneyTable(jdbc, request, session);
                        request.setAttribute("mapUrl", mapUrl);
                        request.getRequestDispatcher("/WEB-INF/driverJourneys.jsp").forward(request, response);
                    }
                }
            }
        }
    }
    
    private String getDriversRegistration(Jdbc jdbc, HttpServletRequest request, HttpSession session){
        String drivername = (String)session.getAttribute("username");
        String query = "SELECT registration FROM drivers WHERE name = '" + drivername +"'";
        String result = "";
        
        String[] results = jdbc.RunQuery(query);
        
        if(results[0] != null){
            result = results[0];
        }
        
        return result;
    }
    
    private String getDemandID(Jdbc jdbc, HttpServletRequest request, String driverReg){
        // Create Query to retrieve Demand ID.
        String result = "";
        String journeyID = (String)request.getParameter("journeyID");
        
        if(journeyID != null){
            String query = "SELECT id FROM journey WHERE jid = " + journeyID + " AND registration = '" + driverReg + "'";
            String[] results = jdbc.RunQuery(query);
            
            if(results[0] != null){
                result = results[0];
            }
        }else{
            request.setAttribute("message", "Invalid ID");
        }
        
        return result;
    }
    
    private String[] getAddresses(Jdbc jdbc, HttpServletRequest request, String demandID){
        String query = "SELECT address, destination FROM demands WHERE id = " + demandID;
        String[] results = jdbc.RunQuery(query);
        
        return results;
    }
    
    private String createStringForGooglemaps(String[] addresses){
        String result = "https://www.google.com/maps/dir/?api=1&origin=";
        result = addAddressToExistingString(addresses[0], result) + "&destination=";
        result = addAddressToExistingString(addresses[1], result);
        result += "&travelmode=driving";
        
        return result;
    }
    
    private String addAddressToExistingString(String address, String existingString){
        for (int i = 0; i < address.length(); i++) {
            if(address.charAt(i) == ','){
                existingString += '+';
            }else{
                existingString += address.charAt(i);
            }
        }
        
        return existingString;
    }
    
    private void recreateJourneyTable(Jdbc jdbc, HttpServletRequest request, HttpSession session){
        String msg = "No journeys";
        String qry = "select jid, journey.registration, destination, distance, date, time from journey"
                + " join drivers on journey.registration = drivers.registration where drivers.name = '"
                + session.getAttribute("username") + "'";
        String journeyResult = requestDemands(jdbc, msg, qry);

        request.setAttribute("journeyQuery", journeyResult);
    }
    
    private String requestDemands(Jdbc jdbc, String msg, String qry) {
        try {
            msg = jdbc.retrieve(qry);
        } catch (SQLException ex) {
            Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg;
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
