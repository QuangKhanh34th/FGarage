/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PartDAO;
import model.Part;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PartServlet extends HttpServlet {

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
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            
            String action = request.getParameter("action");
            PartDAO partDAO = new PartDAO();
            if ("search".equals(action)) {
                String searchName = request.getParameter("partName");
                if (searchName != null && !searchName.trim().isEmpty()) {
                    try {
                        ArrayList<Part> searchedParts = partDAO.getPartByName(searchName);
                        if (searchedParts != null && !searchedParts.isEmpty()) {
                            request.setAttribute("searchedParts", searchedParts);
                        } else {
                            request.setAttribute("searchMessage", "Không tìm thấy phụ tùng nào.");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PartServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    request.setAttribute("searchMessage", "Vui lòng nhập tên phụ tùng để tìm kiếm.");
                }
            }
            ArrayList<Part> parts = partDAO.getAllParts();
            request.setAttribute("parts", parts);
            
            request.getRequestDispatcher(
                    "parts.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        System.out.println("Nhận request với action: " + action);
        PartDAO partDAO = new PartDAO();
        try {
            if ("add".equals(action)) {
                String partName = request.getParameter("partName");
                String purchasePriceStr = request.getParameter("purchasePrice");
                String retailPriceStr = request.getParameter("retailPrice");

                if (partName == null || partName.trim().isEmpty()
                        || purchasePriceStr == null || purchasePriceStr.trim().isEmpty()
                        || retailPriceStr == null || retailPriceStr.trim().isEmpty()) {
                    request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin.");
                    request.getRequestDispatcher("parts.jsp").forward(request, response);
                    return;
                }

                try {
                    double purchasePrice = Double.parseDouble(purchasePriceStr);
                    double retailPrice = Double.parseDouble(retailPriceStr);

                    Part part = new Part(0, partName, purchasePrice, retailPrice);
                    partDAO.addPart(part);
                    request.getSession().setAttribute("message", "Thêm phụ tùng thành công.");
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("errorMessage", "Giá nhập vào không hợp lệ.");
                }
                ArrayList<Part> parts = partDAO.getAllParts();
                request.setAttribute("parts", parts);
                request.getRequestDispatcher("parts.jsp").forward(request, response);

            } else if (action.equals("update")) {
                int partID = Integer.parseInt(request.getParameter("partID"));
                String partName = request.getParameter("partName");
                double purchasePrice = Double.parseDouble(request.getParameter("purchasePrice"));
                double retailPrice = Double.parseDouble(request.getParameter("retailPrice"));
                Part part = new Part(partID, partName, purchasePrice, retailPrice);
                partDAO.updatePart(part);
                request.getSession().setAttribute("message", "Cập nhật phụ tùng thành công");
                 ArrayList<Part> parts = partDAO.getAllParts();
                request.setAttribute("parts", parts);
                request.getRequestDispatcher("parts.jsp").forward(request, response);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("partID"));
                partDAO.deletePart(id);
                request.getSession().setAttribute("message", "Xóa phụ tùng thành công!");
                ArrayList<Part> parts = partDAO.getAllParts();
                request.setAttribute("parts", parts);
                request.getRequestDispatcher("parts.jsp").forward(request, response);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PartServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("errorMessage", "Lỗi cơ sở dữ liệu: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(PartServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().setAttribute("errorMessage", "Lỗi: " + ex.getMessage());
        }
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
