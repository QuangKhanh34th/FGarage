/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import dao.MechanicDAO;
import dto.CustomerDTO;
import dto.MechanicDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Horwlt
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String role = request.getParameter("role");  // Lấy role từ form
        String name = request.getParameter("name");  // Lấy name từ form
        String phone = request.getParameter("phone"); // Lấy phone (nếu có)

        try {
            HttpSession session = request.getSession(); // Tạo session để lưu user

            if ("Mechanic".equals(role)) { // Nếu là Mechanic
                MechanicDAO dao = new MechanicDAO();
                MechanicDTO mechanic = dao.checkLogin(name);

                if (mechanic != null) {
                    session.setAttribute("USER", mechanic);
                    session.setAttribute("ROLE", "Mechanic");
                    response.sendRedirect("MainController?action=MechanicDashboard"); // Thành công -> Mechanic Dashboard
                    return;
                }

            } else if ("Customer".equals(role)) { // Nếu là Customer
                CustomerDAO dao = new CustomerDAO();
                CustomerDTO customer = dao.checkLogin(name, phone);

                if (customer != null) {
                    session.setAttribute("USER", customer);
                    session.setAttribute("ROLE", "Customer");
                    session.setAttribute("custID", customer.getCustID());
                    response.sendRedirect("MainController?action=CustomerDashboard"); // Thành công -> Customer Dashboard
                    return;
                }
            }

            // Nếu đăng nhập thất bại -> Giữ nguyên role, reset name & phone
            request.setAttribute("ERROR", "Invalid login. Please try again!");
            request.setAttribute("role", role); // Giữ nguyên role để JSP hiển thị đúng
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Internal Server Error");
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
