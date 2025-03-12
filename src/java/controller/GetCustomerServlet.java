/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.SalesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import Service.CustomerService;

/**
 *
 * @author ASUS
 */
public class GetCustomerServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Re-fetching customer list info from database if a change in database is found
            boolean dbUpdate = false;
            HttpSession session = request.getSession();
            try {
                 dbUpdate = (boolean) request.getAttribute("dbUpdate");
            } catch (NullPointerException e) {
                System.out.println("dbUpdate not found");
            }
            if (dbUpdate) {
                session.removeAttribute("customerList");
            }
            
            
            //Initializing variables
            CustomerService customerService = new CustomerService();
            String searchTerm = request.getParameter("searchName");
            ArrayList<Customer> allCustomers = (ArrayList<Customer>) session.getAttribute("customerList");
            
            // Get the base customer list, from the database if needed.
            ArrayList<Customer> customers;
            if (allCustomers == null) {
                customers = customerService.getCustomers();
                session.setAttribute("customerList", customers);
            } else {
                customers = allCustomers;
            }

            // Apply the search filter.
            customers = customerService.getCustomers(searchTerm, customers);

            
            
            /*
                Result Pagination
                store all customer data in session but don't use it to show
                instead break the data into multiple pages then pass it to request for viewing
            */
            int page = 1; //Default to page 1
            int recordsPerPage = 10; //Number of rows per page
            
            
            //if "page" get passed from JSP, instead of page 1, we change the page to current page
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            
            ArrayList<Customer> currentCusPageList = customerService.getPaginatedCustomers(customers, page, recordsPerPage);
            int totalPages = customerService.getTotalPages(customers, recordsPerPage);
            
            
            session.setAttribute("currentCusPageList", currentCusPageList);
            session.setAttribute("currentPage", page);
            session.setAttribute("totalPages", totalPages);
            
            
            response.sendRedirect("SalesDashboard/CustomerFunction.jsp");
            
        }
    }
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
