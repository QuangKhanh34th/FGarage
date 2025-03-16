package controller;

import dao.ServiceTicketDAO;
import dto.ServiceTicketDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SearchServiceTicketServlet", urlPatterns = {"/SearchServiceTicketServlet"})
public class SearchServiceTicketServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Kiểm tra nếu chưa đăng nhập, chuyển về login.jsp
        if (session == null || !"Mechanic".equals(session.getAttribute("ROLE"))) {
            response.sendRedirect("MainController?action=Home");
            return;
        }

        try {
            String searchType = request.getParameter("searchType"); // Lấy loại tìm kiếm
            String searchValue = request.getParameter("searchValue"); // Lấy giá trị tìm kiếm

            ServiceTicketDAO dao = new ServiceTicketDAO();
            List<ServiceTicketDTO> ticketList;

            if (searchValue == null || searchValue.trim().isEmpty()) {
                ticketList = dao.getAllServiceTickets(); // Nếu không nhập giá trị tìm kiếm, hiển thị tất cả
            } else {
                ticketList = dao.searchServiceTickets(searchType, searchValue); // Gọi DAO để tìm kiếm
            }

            request.setAttribute("SERVICE_TICKET_LIST", ticketList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Điều hướng về trang serviceTicket.jsp để hiển thị kết quả
        request.getRequestDispatcher("MainController?action=ServiceTicket").forward(request, response);
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
