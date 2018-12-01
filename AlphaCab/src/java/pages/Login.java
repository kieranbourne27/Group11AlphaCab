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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

/**
 *
 * @author k4-bourne
 */
public class Login extends HttpServlet {

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
           
        Jdbc jdbc = new Jdbc();
        jdbc.connect((Connection)request.getServletContext().getAttribute("connection"));
        
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");   
        
        if(username.equals("")) {
            request.setAttribute("message", "Username cannot be NULL");
        }
        else if(password.equals("")) {
            request.setAttribute("message", "Password cannot be NULL");
        }
        else {
            HttpSession session = request.getSession(); 
            
            if (jdbc.checkUser(username, password)) {
                setUserSessionAttributes(jdbc, username, session, request, response);
                return;
            } else {
                request.setAttribute("message", "Invalid username or password");
                session.setAttribute("username", null);
            }
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void setUserSessionAttributes(Jdbc jdbc, String username, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userType = jdbc.retrieveUserType(username);
        int expireTime = 20; // In minutes;
        session.setAttribute("dbbean", jdbc);
        session.setAttribute("username", username);
        session.setAttribute("userType", userType);
        session.setMaxInactiveInterval(expireTime * 60);
        
        Cookie userName = new Cookie("username", username);
        userName.setMaxAge(expireTime * 60);
        response.addCookie(userName);
        
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
