/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import Service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AddCustomerServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        
        
            String custName = request.getParameter("custNameAdd");
            System.out.println("custName:" + custName);
            String custSex = request.getParameter("custSexAdd");
            String custPhone = request.getParameter("custPhoneAdd");
            String custAddress = request.getParameter("custAddressAdd");
            
            HttpSession session = request.getSession();
            CustomerService customerService = new CustomerService();
            boolean addStatus = customerService.addCustomer(new Customer(custName, custPhone, custSex, custAddress));
            
            //If add successfully, go to GetCustomerServlet to refresh the data for viewing
            //Else add an error message to session for the jsp to display
            if(!addStatus) {
                session.setAttribute("error", "Error adding Customer: Customer's Name or phone has already exists in database");
                request.getRequestDispatcher("/GetCustomerServlet").forward(request, response);
            } else {
                session.setAttribute("dbCreate", "Customer data added successfully");
                request.setAttribute("dbUpdate", addStatus);
                request.getRequestDispatcher("/GetCustomerServlet").forward(request, response);
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
