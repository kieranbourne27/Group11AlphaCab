/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.io.IOException;
import java.sql.Connection;
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
public class NewUser extends HttpServlet {

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
        String[] query = captureUserData(request);
        String userType = (String) request.getParameter("userType");

        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);

        if (dbBean == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        } 
        else if (query[0].equals("")) 
        {
            request.setAttribute("message", "Username cannot be NULL");
        } 
        else if (dbBean.exists(query[0])) 
        {
            request.setAttribute("message", query[0] + " is already taken as username");
        } 
        else 
        {
            if(query[2] == null){
                createUser(dbBean, query, request, session, response);  
            }else if (query[2].equals("driver")) {
                if(query[3].equals("")){
                    request.setAttribute("message", "Registration was left empty."); 
                    request.getRequestDispatcher("/WEB-INF/portal.jsp").forward(request, response);
                }else{
                    createUser(dbBean, query, request, session, response);
                }
            }else{
                createUser(dbBean, query, request, session, response);
            }
        }
        
        if (session.getAttribute("username") == null)
        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } 
        else 
        {
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        }
    }

    private void createUser(Jdbc dbBean, String[] query, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException, ServletException {
        if (dbBean.insertUser(query)) {
            if (query[2].equals("driver")) {
                addDriver(request, query, dbBean);
            }
            
            request.setAttribute("message", query[0] + " is added");
            if (session.getAttribute("username") != null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        else {
            request.setAttribute("message", query[0] + " was not added.");
        }
    }

    private String[] captureUserData(HttpServletRequest request) {
        String[] query = new String[4];
        query[0] = (String) request.getParameter("username");
        query[1] = (String) request.getParameter("password");
        query[2] = (String) request.getParameter("userType");
        query[3] = (String) request.getParameter("regNumber");

        return query;
    }
    
    private void addDriver(HttpServletRequest request, String[] attributes, Jdbc jdbc){
       String query = "INSERT INTO drivers VALUES ('" + attributes[3] + "','" + attributes[0] + "')";
       jdbc.updateTableWithQuery(query);
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
