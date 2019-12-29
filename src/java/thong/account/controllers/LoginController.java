/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thong.account.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thong.daos.AccountDAO;

/**
 *
 * @author The Thong
 */
public class LoginController extends HttpServlet {
private static final String ERROR = "index.jsp";
    private static final String ADMIN = "admin/admin.jsp";
    private static final String USER = "user/user.jsp";
    private static final String STAFF = "staff/staff.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            String username = request.getParameter("username");
            String password = request.getParameter("pass");

            AccountDAO dao = new AccountDAO();
            String role = dao.checkLogin(username, password);
            if (role.equals("failed")) {
                request.setAttribute("FAILED", "Username Or Password Is Incorrect");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("USERLOGIN", username);
 
                session.setAttribute("ROLE", role);
//                ShoppingCart shopcart = (ShoppingCart) session.getAttribute("cart");
                if (role.equals("Admin")) {
                    url = ADMIN;
//                    if (shopcart != null) {
//                        session.removeAttribute("cart");
//                    }
                } else if (role.equals("User")) {
                    url = USER;
//                    if (shopcart != null) {
//                        shopcart.setCustomerName(username);
//                    }
                } else if (role.equals("Staff")) {
                    url = STAFF;
                } else {
                    request.setAttribute("ERROR", "Your Role is not supported");
                }
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
