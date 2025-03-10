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
            //Initializing variables
            HttpSession session = request.getSession();
            
            //remove comment to use dummy data.
            //ArrayList<Customer> allCustomers = createDummyCustomerList();
            ArrayList<Customer> allCustomers = SalesDAO.getAllCustomers();
            session.setAttribute("customerList", allCustomers);
            
            
            /*
                Result Pagination
                store all customer data in session but don't use it to show
                instead break the data into multiple pages then pass it to request for viewing
            */
            int page = 1; // Default to page 1
            int recordsPerPage = 10; // Number of rows per page
            
            //if "page" get passed from JSP, instead of page 1, we change the page to current page
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            int totalCustomers = allCustomers.size();
            /*
                Calculating the total number of pages needed to display all records
                Initial formula: totalCustomers / recordsPerPage
                    Type cast the formula to use "double" value for precise calculation
                    wrap the formula in math.ceil() to round the number to nearest integer (ex: 3.5 -> 4)
                    Type cast the result back to integer value
            */
            int totalPages = (int) Math.ceil((double) totalCustomers / recordsPerPage);
            
            /*  
                Calculating start and end index to divide the list into sublist for display
                Example (startIndex):
                If page = 1 and recordsPerPage = 10, then startIndex = (1 - 1) * 10 = 0. (The first record)
                If page = 2 and recordsPerPage = 10, then startIndex = (2 - 1) * 10 = 10. (The 11th record)
            */
            int startIndex = (page - 1) * recordsPerPage;
            
            /*
                using Math.min() to ensure the last page doesn't exceed the total number of customer
                Example:
                If startIndex = 30, recordsPerPage = 10, totalCustomers = 35
                Then endIndex = Math.min(30 + 10, 35) = 35
            */
            int endIndex = Math.min(startIndex + recordsPerPage, totalCustomers);
            
            //using calculated data(start, end, totalPage) to break customer data into the data of the current page number
            ArrayList<Customer> currentCusPageList = new ArrayList<>(allCustomers.subList(startIndex, endIndex));
            if (currentCusPageList != null)
            System.out.println("currentCusPageList first customer:" + currentCusPageList.get(0).getCustName());
            
                session.setAttribute("currentCusPageList", currentCusPageList);
                session.setAttribute("currentPage", page);
                session.setAttribute("totalPages", totalPages);
                response.sendRedirect("SalesDashboard/CustomerFunction.jsp");
            
        }
    }
    
    private ArrayList<Customer> createDummyCustomerList() {
        ArrayList<Customer> list = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Customer c = new Customer();
            c.setCustID(i);
            c.setCustName("customer " + i);
            c.setPhone("123-456-" + i);
            c.setCusAddress("address " + i);
            list.add(c);
        }
        return list;
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
