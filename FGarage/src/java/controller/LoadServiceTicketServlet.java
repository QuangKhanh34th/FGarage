package controller;

import dao.ServiceMechanicDAO;
import dto.MechanicDTO;
import dto.ServiceMechanicDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoadServiceTicketServlet", urlPatterns = {"/LoadServiceTicketServlet"})
public class LoadServiceTicketServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        
        if (session == null || !"Mechanic".equals(session.getAttribute("ROLE"))) {
            response.sendRedirect("MainController?action=Home");
            return;
        }
        try {
            
            MechanicDTO mechanic = (MechanicDTO) session.getAttribute("USER");
            
            
            String mechanicID = mechanic.getMechanicID();

            
            ServiceMechanicDAO dao = new ServiceMechanicDAO();
            List<ServiceMechanicDTO> list = dao.getServiceMechanicByMechanicID(mechanicID);
            

            
            request.setAttribute("SERVICE_TICKETS", list);
            
            request.getRequestDispatcher("updateServiceTicket.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
