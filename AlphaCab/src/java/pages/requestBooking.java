/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class requestBooking extends HttpServlet {
    
    private final String STARTADDRESS = "startAddress";
    
    private final String DESTINATIONADDRESS = "destAddress";
    
    private final String DATE = "date";
    
    private final String TIME = "pickupTime";

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
            Jdbc jdbc = new Jdbc();
            jdbc.connect((Connection)request.getServletContext().getAttribute("connection"));
            
            String [] query = new String[5];
            query[0] = (String)session.getAttribute("username");
            query[1] = (String)request.getParameter(STARTADDRESS);
            query[2] = (String)request.getParameter(DESTINATIONADDRESS);
            query[3] = (String)request.getParameter(DATE);
            query[4] = (String)request.getParameter(TIME);
            
            if(query[0] == null || query[0].equals("") ) {
                request.setAttribute("message", "No fields can be left blank.");
            } 
            else {
                if(jdbc.insertBooking(query)){
                    request.setAttribute("message", "Thank you " + 
                            query[0] + " " + query[1] + 
                            ", your booking was successful.");
                    
                    jdbc.closeAll();
                    request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
                }else{
                    request.setAttribute("message", "Sorry "+ query[0] + " " + 
                            query[1] + " was not added.");
                }
            }
            request.getRequestDispatcher("/requestBooking.jsp").forward(request, response);
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
