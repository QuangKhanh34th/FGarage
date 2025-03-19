package controller;

import DAO.CustomerDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;

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
        Customer customer = (Customer) session.getAttribute("customer");
        CustomerDAO customerDAO = new CustomerDAO();

        if (customer != null) {
            String method = request.getMethod(); // Lấy phương thức HTTP (GET hoặc POST)

            if (method.equalsIgnoreCase("GET")) {
                // Xử lý yêu cầu GET (hiển thị form)
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("MainController?action=UpdateProfile").forward(request, response);
            } else if (method.equalsIgnoreCase("POST")) {
                // Xử lý yêu cầu POST (cập nhật dữ liệu)
                String custName = request.getParameter("custName");
                String phone = request.getParameter("phone");
                String sex = request.getParameter("sex");
                String cusAddress = request.getParameter("cusAddress");

                customer.setCustName(custName);
                customer.setPhone(phone);
                customer.setSex(sex);
                customer.setCusAddress(cusAddress);

                boolean success = customerDAO.updateCustomerProfile(customer);

                if (success) {
                    request.setAttribute("SUCCESS", "Profile updated successfully!");
                    session.setAttribute("customer", customer); // Cập nhật session với thông tin mới
                } else {
                    request.setAttribute("ERROR", "Failed to update profile. Please try again!");
                }
                request.setAttribute("customer", customer); // Đặt customer vào request để hiển thị lại form
                request.getRequestDispatcher("MainController?action=UpdateProfile").forward(request, response);
            }
        } else {
            response.sendRedirect("Login/index.jsp");
        }
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
