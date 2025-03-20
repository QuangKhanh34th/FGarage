/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Service.ServiceTicketService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ServiceTicket;

/**
 *
 * @author ASUS
 */
public class GetServiceTicketServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        boolean globalUpdate = false;
        try {
            globalUpdate = (boolean) session.getAttribute("globalUpdate");
        } catch (NullPointerException e) {
            System.out.println("globalUpdate not found");
        }

        if (globalUpdate) {
            session.removeAttribute("globalUpdate");
            session.removeAttribute("ticketList");
        }

        ServiceTicketService ticketService = new ServiceTicketService();
        ArrayList<ServiceTicket> allTickets = (ArrayList<ServiceTicket>) session.getAttribute("ticketList");

        ArrayList<ServiceTicket> tickets;
        if (allTickets == null) {
            tickets = ticketService.getTickets();
            session.setAttribute("ticketList", tickets);
        } else {
            tickets = allTickets;
        }

        //Result Pagination
        int page = 1; //Default to page 1
        int recordsPerPage = 10; //Number of rows per page

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ArrayList<ServiceTicket> currentTicketPageList = ticketService.getPaginatedServiceTickets(tickets, page, recordsPerPage);
        int totalPages = ticketService.getTotalPages(tickets, recordsPerPage);

        session.setAttribute("currentTicketPageList", currentTicketPageList);
        session.setAttribute("ticketCurrentPage", page);
        session.setAttribute("ticketTotalPages", totalPages);

        response.sendRedirect(request.getContextPath() + "/SalesDashboard/ServiceTicketFunction.jsp");
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
