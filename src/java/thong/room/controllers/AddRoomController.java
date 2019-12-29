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
public class AddRoomController extends HttpServlet {

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
    private static final String SUCCESS = "/admin/room.jsp";
    private static final String INVALID = "/admin/addRoom.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url =ERROR;
        try {
            String roomName = request.getParameter("txtRoomName");
            float roomPrice = Float.parseFloat(request.getParameter("txtRoomPrice"));
            String roomDes = request.getParameter("txtRoomDetail");
            int roomQuantity = Integer.parseInt(request.getParameter("txtRoomQuantity"));
            String image = request.getParameter("txtRoomImage");
            int roomType = Integer.parseInt(request.getParameter("cbTypeRoom"));
            
            RoomDTO dto = new RoomDTO(roomName, roomDes, image, roomQuantity, roomType, true, roomPrice);
            
            
            
            
            RoomDAO dao = new RoomDAO();
            if(dao.serviceInsert(dto))
            {
                url = SUCCESS;
            }
            else
            {
                request.setAttribute("ERROR", "Add Failed");
            }
        } catch (Exception e) {
            log("ERROR at AddRoomController: "+e.getMessage());
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
