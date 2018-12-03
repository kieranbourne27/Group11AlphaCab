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
 * @author Callum
 */
public class SetPrice extends HttpServlet {

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

        String attribute = (String) request.getParameter("invoiceID");
        String vatAttribute = (String) request.getParameter("newVat");
        String priceAttribute = (String) request.getParameter("newPrice");

        if (attribute != null) {
            updateInvoicePrice(request, jdbc, response);
        } else if (vatAttribute != null) {
            updateTheVatCharge(request, jdbc, response);
        } else if (priceAttribute != null) {
            changeGlobalPrice(request, jdbc, response);
        }
    }

    private void changeGlobalPrice(HttpServletRequest request, Jdbc jdbc, HttpServletResponse response) throws ServletException, IOException {
        String newPrice = (String) request.getParameter("newPrice");
        String query = "Select Price FROM PRICING";

        if (!newPrice.equals("")) {
            String[] queryData = {newPrice};
            jdbc.updatePrices(queryData);
            String[] result = jdbc.retrieveQueryWithStringArray(query);

            if (result[0] != null) {
                if (result[0].equals(newPrice)) {
                    request.setAttribute("message", "The price is now set to: " + newPrice + ".");
                    request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Error occured setting new price.");
                    request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("message", "Invalid data was entered.");
            request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
        }
    }

    private void updateInvoicePrice(HttpServletRequest request, Jdbc jdbc, HttpServletResponse response) throws IOException, ServletException {
        String invoiceID = (String) request.getParameter("invoiceID");
        String pricePerMile = (String) request.getParameter("pricePerMile");

        String queryForMileage = "SELECT mileage FROM invoices WHERE id = " + invoiceID;
        String[] mileage = jdbc.retrieveQueryWithStringArray(queryForMileage);

        if (mileage[0] != null) {
            String journeyMiles = mileage[0];
            String newMileageCost = String.valueOf(Integer.parseInt(journeyMiles) * Integer.parseInt(pricePerMile));
            String query = "UPDATE invoices Set price=" + newMileageCost + " WHERE id = " + invoiceID;
            jdbc.updateTableWithQuery(query);
            request.setAttribute("message", "Price updated for invoice ID = " + invoiceID + ".");
        } else {
            request.setAttribute("message", "Invalid mileage retrieved.");
        }
        request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
    }

    private void updateTheVatCharge(HttpServletRequest request, Jdbc jdbc, HttpServletResponse response) throws ServletException, IOException {
        String vatAttribute = (String) request.getParameter("newVat");
        String query = "UPDATE pricing Set vat =" + vatAttribute;
        
        jdbc.updateTableWithQuery(query);
        
        request.setAttribute("message", "VAT Charge was updated to " + vatAttribute + "%.");
        request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);        
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
