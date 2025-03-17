package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "error.jsp"; // Mặc định nếu có lỗi xảy ra.

        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "Home";
            }

            switch (action) {
                case "Home":
                    url = "login.jsp";
                    break;
                case "Login":
                    url = "LoginServlet";
                    break;
                case "Logout":
                    url = "LogoutServlet";
                    break;
                case "MechanicDashboard":
                    url = "mechanicDashboard.jsp";
                    break;
                case "CustomerDashboard":
                    url = "customerDashboard.jsp";
                    break;
                case "ServiceTicket":
                    url = "serviceTicket.jsp";
                    break;
                case "ViewAllTickets":
                    url = "ServiceTicketServlet";
                    break;
                case "SearchTicket":
                    url = "SearchServiceTicketServlet";
                    break;
                case "LoadUpdateServiceTicket":
                    url = "LoadServiceTicketServlet";
                    break;
                case "UpdateServiceTicket":
                    url = "UpdateServiceTicketServlet";
                    break;

                //Manage Services
                case "ViewAllServices":
                case "Create":
                case "Update":
                case "Delete":
                    url = "ServiceServlet";
                    break;
                case "ChangeProfile":
                    url = "profile.jsp";
                    break;
                case "CustomerTicket":
                    url = "CustomerServiceTicketServlet";
                    break;
                default:
                    url = "error.jsp";
                    break;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "MainController for handling navigation";
    }
}
