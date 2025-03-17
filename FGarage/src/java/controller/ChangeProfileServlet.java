package controller;

import dao.CustomerDAO;
import dto.CustomerDTO;
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
public class ChangeProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        CustomerDTO customer = (CustomerDTO) session.getAttribute("USER");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        
        String custName = request.getParameter("custName");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String cusAddress = request.getParameter("cusAddress");

        
        customer.setCustName(custName);
        customer.setPhone(phone);
        customer.setSex(sex);
        customer.setCusAddress(cusAddress);

        
        CustomerDAO dao = new CustomerDAO();
        boolean success = dao.updateCustomerProfile(customer);

        if (success) {
            session.setAttribute("USER", customer); 
            request.setAttribute("SUCCESS", "Profile updated successfully!");
        } else {
            request.setAttribute("ERROR", "Failed to update profile. Please try again!");
        }

        request.getRequestDispatcher("MainController?action=ChangeProfile").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
