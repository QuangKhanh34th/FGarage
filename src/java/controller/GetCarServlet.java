/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Service.CarService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Car;

/**
 *
 * @author ASUS
 */
public class GetCarServlet extends HttpServlet {

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
        //Re-fetching customer list info from database if a change in database is found
        boolean dbUpdate = false;
        HttpSession session = request.getSession();
        try {
            //get dbUpdate attribute set by other Servlets
            //if is set, the returned value should always be true
            dbUpdate = (boolean) request.getAttribute("dbUpdate");
        } catch (NullPointerException e) {
            System.out.println("[GetCarServlet.java] Warning: dbUpdate not found");
        }

        if (dbUpdate) {
            session.removeAttribute("carList");
        }

        //Initialize variables
        String searchCriteria = null;
        String searchQuery = null;
        try {
            searchCriteria = request.getParameter("searchCriteria");
            searchQuery = request.getParameter("carSearch");
        } catch (NullPointerException error) {
            System.out.println("[GetCarServlet.java] Warning: cannot find \"SearchCriteria\", \"SearchCriteria\"");
        }
        CarService carService = new CarService();
        ArrayList<Car> allCars = (ArrayList<Car>) session.getAttribute("carList");

        // Get the base list from the database if needed (mostly in the case of initial load or dbUpdate is found)
        ArrayList<Car> cars;
        if (allCars == null) {
            cars = carService.getCars();
            session.setAttribute("carList", cars);
        } else {
            cars = allCars;
        }

        // Check if new search parameters are provided
        if (request.getParameter("searchCriteria") != null) {
            searchCriteria = request.getParameter("searchCriteria");
            session.setAttribute("searchCriteria", searchCriteria);
        }
        if (request.getParameter("carSearch") != null) {
            searchQuery = request.getParameter("carSearch");
            session.setAttribute("searchQuery", searchQuery);
        }
        //Apply search filters
        cars = carService.getCars(searchCriteria, searchQuery, allCars);
        
        // Store search parameters in session
        session.setAttribute("searchCriteria", searchCriteria);
        session.setAttribute("searchQuery", searchQuery);
        
        
        //Result Pagination
        int page = 1; //Default to page 1
        int recordsPerPage = 10; //Number of rows per page

        //if "page" get passed from JSP, instead of page 1, we change the page to current page
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ArrayList<Car> currentCarPageList = carService.getPaginatedCars(cars, page, recordsPerPage);
        int totalPages = carService.getTotalPages(cars, recordsPerPage);

        session.setAttribute("currentCarPageList", currentCarPageList);
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", totalPages);

        response.sendRedirect(request.getContextPath() + "/SalesDashboard/CarFunction.jsp");

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
