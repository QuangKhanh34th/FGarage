package controller;

import dao.ServiceDAO;
import dto.ServiceDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        ServiceDAO serviceDAO = new ServiceDAO();

        try {
            if ("ViewAllServices".equals(action)) {
                List<ServiceDTO> services = serviceDAO.getAllServices();
                request.setAttribute("SERVICES", services);
                request.getRequestDispatcher("manageServices.jsp").forward(request, response);

            } else if ("Create".equals(action)) {
                String serviceName = request.getParameter("serviceName");
                double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));

                ServiceDTO newService = new ServiceDTO(0, serviceName, hourlyRate);
                boolean success = serviceDAO.addService(newService);

                request.getSession().setAttribute("MESSAGE", success ? "Service added successfully!" : "Failed to add service.");
                response.sendRedirect("MainController?action=ViewAllServices");

            } else if ("Update".equals(action)) {
                int serviceID = Integer.parseInt(request.getParameter("serviceID"));
                String serviceName = request.getParameter("serviceName");
                double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));

                ServiceDTO updatedService = new ServiceDTO(serviceID, serviceName, hourlyRate);
                boolean success = serviceDAO.updateService(updatedService);

                request.getSession().setAttribute("MESSAGE", success ? "Service updated successfully!" : "Failed to update service.");
                response.sendRedirect("MainController?action=ViewAllServices");

            } else if ("Delete".equals(action)) {
                int serviceID = Integer.parseInt(request.getParameter("serviceID"));
                boolean success = serviceDAO.deleteService(serviceID);

                request.getSession().setAttribute("MESSAGE", success ? "Service deleted successfully!" : "Failed to delete service.");
                response.sendRedirect("MainController?action=ViewAllServices");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Something went wrong!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
