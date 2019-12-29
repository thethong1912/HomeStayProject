/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thong.service.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import thong.daos.ServiceDAO;
import thong.dtos.ServiceDTO;

/**
 *
 * @author The Thong
 */
public class AddServiceController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "/admin/service.jsp";
    private static final String INVALID = "/admin/addService.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url =ERROR;
        try {
            String serName = request.getParameter("txtSerName");
            float serPrice = Float.parseFloat(request.getParameter("txtSerPrice"));
            String serDes = request.getParameter("txtSerDescription");
            int serQuantity = Integer.parseInt(request.getParameter("txtSerQuantity"));
            String image = request.getParameter("txtSerImage");
            int serType = Integer.parseInt(request.getParameter("cbTypeSer"));
            
            ServiceDTO dto = new ServiceDTO(serName, serDes, image, serType, serPrice, true, serQuantity);
            
            
            
            
            ServiceDAO dao = new ServiceDAO();
            if(dao.serviceInsert(dto))
            {
                url = SUCCESS;
            }
            else
            {
                request.setAttribute("ERROR", "Add Failed");
            }
        } catch (Exception e) {
            log("ERROR at AddServiceController: "+e.getMessage());
            if(e.getMessage().contains("Cannot insert duplicate"))
            {
                url = INVALID;
                request.setAttribute("INVALID", "This Service Is Already Inside");
            }
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
