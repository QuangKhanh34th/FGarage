package controller;

import DAO.ServiceMechanicDAO;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mechanic;

@WebServlet(name = "UpdateServiceTicketServlet", urlPatterns = {"/UpdateServiceTicketServlet"})
public class UpdateServiceTicketServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String updateValue = request.getParameter("update");
            if (updateValue != null) {
                String[] parts = updateValue.split("_");
                int serviceTicketID = Integer.parseInt(parts[0]);
                int serviceID = Integer.parseInt(parts[1]);

                int hours = Integer.parseInt(request.getParameter("hours_" + serviceTicketID + "_" + serviceID));
                String comment = request.getParameter("comment_" + serviceTicketID + "_" + serviceID);
                if (comment != null && comment.trim().isEmpty()) {
                    comment = null;
                }
                double rate = Double.parseDouble(request.getParameter("rate_" + serviceTicketID + "_" + serviceID));

                HttpSession session = request.getSession();
                Enumeration<String> attributeNames = session.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    String name = attributeNames.nextElement();
                    Object value = session.getAttribute(name);
                    System.out.println("Session Attribute: " + name + " = " + value);
                }
                Mechanic mechanic = (Mechanic) session.getAttribute("mechanic");
                long mechanicID = mechanic.getMechanicID();

                ServiceMechanicDAO dao = new ServiceMechanicDAO();
                boolean success = dao.updateServiceMechanic(serviceTicketID, serviceID, mechanicID, hours, comment, rate);

                if (success) {
                    request.setAttribute("MESSAGE", "Update Successful!");
                } else {
                    request.setAttribute("MESSAGE", "Update Failed!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("MainController?action=LoadUpdateServiceTicket").forward(request, response);
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
