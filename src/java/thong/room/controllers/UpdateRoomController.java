/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thong.room.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thong.daos.RoomDAO;
import thong.dtos.RoomDTO;

/**
 *
 * @author The Thong
 */
public class UpdateRoomController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchRoomController";

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
            String id = request.getParameter("txtRoomName");
            float roomPrice = Float.parseFloat(request.getParameter("txtRoomPrice"));
            String roomDes = request.getParameter("txtRoomDetail");
            int roomQuantity = Integer.parseInt(request.getParameter("txtRoomQuantity"));

            int roomType = Integer.parseInt(request.getParameter("cbTypeRoom"));

            String image = request.getParameter("txtRoomImage");

            RoomDAO dao = new RoomDAO();
            RoomDTO dto = new RoomDTO(id, roomDes, image, roomQuantity, roomType, roomPrice);
            if (roomQuantity > 0) {
                dto.setRoomStatus(true);
            } else {
                dto.setRoomStatus(false);
            }

            if (dao.updateService(dto)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Update Failed");
            }
        } catch (Exception e) {
            log("ERROR at UpdateRoomController: " + e.getMessage());
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
