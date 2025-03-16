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

        // Kiểm tra nếu chưa đăng nhập, chuyển về login.jsp
        if (session == null || !"Mechanic".equals(session.getAttribute("ROLE"))) {
            response.sendRedirect("MainController?action=Home");
            return;
        }
        try {
            // ✅ Lấy đối tượng Mechanic từ session
            MechanicDTO mechanic = (MechanicDTO) session.getAttribute("USER");
            
            // ✅ Lấy mechanicID từ DTO
            String mechanicID = mechanic.getMechanicID();

            // ✅ Gọi DAO để lấy danh sách Service Tickets của mechanic này
            ServiceMechanicDAO dao = new ServiceMechanicDAO();
            List<ServiceMechanicDTO> list = dao.getServiceMechanicByMechanicID(mechanicID);
            

            // ✅ Đưa danh sách vào request để hiển thị trong JSP
            request.setAttribute("SERVICE_TICKETS", list);
            // ✅ Chuyển hướng đến trang updateServiceMechanic.jsp
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
