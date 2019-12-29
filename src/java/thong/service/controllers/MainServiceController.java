/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thong.service.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author The Thong
 */
public class MainServiceController extends HttpServlet {
     private static final String ERROR = "error.jsp";
    private static final String INSERT = "AddServiceController";
    private static final String EDIT = "EditServiceController";
    private static final String SEARCH = "SearchServiceController";
    private static final String UPDATE = "UpdateServiceController";
    private static final String DELETE = "DeleteServiceController";
    private static final String LOGOUT = "LogoutServiceController";
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
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            System.out.println("action "+action);
            if(action.equals("Search"))
            {
                url = SEARCH;
            }
            else if(action.equals("Insert"))
            {
                url = INSERT;
            }
            else if(action.equals("Edit"))
            {
                url = EDIT;
            }
            else if(action.equals("Logout"))
            {
                url = LOGOUT;
            }
            else if(action.equals("Update"))
            {
                System.out.println("1");
                url = UPDATE;
            }
            else if(action.equals("Delete"))
            {
                url = DELETE;
            }
            else
            {
                request.setAttribute("ERROR", "Action is not supported");
            }
                    
        } catch (Exception e) {
            log("ERROR at MainServiceController: "+e.getMessage());
        }
        finally
        {
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
