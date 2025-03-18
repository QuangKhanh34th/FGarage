/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ServiceTicketDAO;
import model.ServiceTicketDTO;
import model.ServiceTicketDetailDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Horwlt
 */
public class CustomerServiceTicketServlet extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String custID = (String) session.getAttribute("custID");

        if (custID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ServiceTicketDAO dao = new ServiceTicketDAO();
        List<ServiceTicketDTO> tickets = dao.getServiceTicketsByCustomerID(custID);
        request.setAttribute("serviceTickets", tickets);
        
        String serviceTicketIDParam = request.getParameter("serviceTicketID");

        if (serviceTicketIDParam != null) {
            try {
                int serviceTicketID = Integer.parseInt(serviceTicketIDParam);
                ServiceTicketDetailDTO ticketDetail = dao.getServiceTicketInfo(serviceTicketID);

                if (ticketDetail != null) {
                    ticketDetail.setServices(dao.getServiceDetails(serviceTicketID));
                    ticketDetail.setParts(dao.getPartDetails(serviceTicketID));
                    request.setAttribute("ticketDetail", ticketDetail);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("ERROR", "Invalid Service Ticket ID.");
            }
        }

        request.getRequestDispatcher("customerServiceTicket.jsp").forward(request, response);
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
