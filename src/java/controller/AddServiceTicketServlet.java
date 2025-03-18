/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.PartDAO;
import DAO.ServiceTicketDAO;
import Service.ServiceModelService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Part;
import model.ServiceModel;
import model.ServiceTicket;

/**
 *
 * @author ASUS
 */
public class AddServiceTicketServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        //Receive form information
        String custID = request.getParameter("customerId");
        String carID = request.getParameter("carId");
        long mechanicID = Long.parseLong(request.getParameter("mechanicId"));
        String dateReceived = request.getParameter("dateReceived");
        String dateReturned = request.getParameter("dateReturned");
        String mechanicComment = request.getParameter("mechanicComment");

        
        HttpSession session = request.getSession();

        // Retrieve Added Services
        ArrayList<ServiceModel> serviceList = (ArrayList<ServiceModel>) session.getAttribute("serviceList");
        ServiceModelService smService = new ServiceModelService();
        String[] serviceIds = request.getParameterValues("serviceIds[]");
        String[] serviceHours = request.getParameterValues("serviceHours[]");

        ArrayList<ServiceModel> addedServices = new ArrayList<>();
        if (serviceIds != null && serviceHours != null) {
            for (int i = 0; i < serviceIds.length; i++) {
                String serviceId = serviceIds[i];
                int hours = Integer.parseInt(serviceHours[i]);
                
                
                //process service data
                ServiceModel service = smService.getServiceById(serviceId, serviceList);
                service.setHours(hours);
                addedServices.add(service);
            }
        }

        // Retrieve Added Parts
        PartDAO partDAO = new PartDAO();
        String[] partIds = request.getParameterValues("partIds[]");
        String[] numbersUsed = request.getParameterValues("partQuantities[]");

        ArrayList<Part> addedParts = new ArrayList<>();
        if (partIds != null && numbersUsed != null) {
            for (int i = 0; i < partIds.length; i++) {
                int partID = Integer.parseInt(partIds[i]);
                int numberUsed = Integer.parseInt(numbersUsed[i]);
                
                //process part data
                Part part = partDAO.getPartByID(partID).get(0);
                part.setNumberUsed(numberUsed);
                addedParts.add(part);
            }
        }
        
        //set data in session for get ticket detail
        session.setAttribute("ticketServices", addedServices);
        session.setAttribute("ticketParts", addedParts);
        
        //Logging details
        System.out.println("\n[AddServiceTicketServlet.java] FORM SUBMISSION DETAILS");
        System.out.println("CustomerID: " + custID);
        System.out.println("CarID: " + carID);
        System.out.println("MechanicID: " + mechanicID);
        System.out.println("Date Received: " + dateReceived);
        System.out.println("Date Returned: " + dateReturned);
        System.out.println("\n===Added services===");
        int count=0;
        for (ServiceModel addedService : addedServices) {
            System.out.println("addedService" + "[" + count + "]: " + addedService.getServiceID()+ "||" + addedService.getHours());
            count++;
        }
        System.out.println("====================\n");
        System.out.println("===Added parts===");
        count=0;
        for (Part addedPart : addedParts) {
            System.out.println("addedPart" + "[" + count + "]: " + addedPart.getPartID()+ "||" + addedPart.getNumberUsed());
            count++;
        }
        System.out.println("=================");
        System.out.println("[AddServiceTicketServlet.java] END DETAILS\n");
        
        ServiceTicket serviceTicket = new ServiceTicket(dateReceived, dateReturned, custID, carID);
        /*
            Add the service ticket to database (not implemented yet since this will add the information to many tables)
            
        */
        boolean addStatus = ServiceTicketDAO.addServiceTicket(serviceTicket, addedServices, addedParts, mechanicID, mechanicComment) !=0;
        if(!addStatus) {
            session.setAttribute("error", "Error adding Service Ticket: Database Integrity issues");
            request.getRequestDispatcher("/GetServiceTicketServlet").forward(request, response);
        } else {
            session.setAttribute("dbCreate", "Service Ticket created successfully");
            request.setAttribute("dbUpdate", addStatus);
            request.getRequestDispatcher("/GetServiceTicketServlet").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddServiceTicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddServiceTicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
