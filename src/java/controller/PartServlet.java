/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.PartDAO;
import model.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PartServlet extends HttpServlet {

    PartDAO partDAO = new PartDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            String action = request.getParameter("action");
            partDAO = new PartDAO();
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
        } catch (SQLException ex) {
            Logger.getLogger(PartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher(
                "PartFunction.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        System.out.println("Nhận request với action: " + action);
        try {
            if ("add".equals(action)) {
                String partName = request.getParameter("partName");
                String purchasePriceStr = request.getParameter("purchasePrice");
                String retailPriceStr = request.getParameter("retailPrice");

                if (partName == null || partName.trim().isEmpty()
                        || purchasePriceStr == null || purchasePriceStr.trim().isEmpty()
                        || retailPriceStr == null || retailPriceStr.trim().isEmpty()) {
                    request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin.");
                    request.getRequestDispatcher("PartFunction.jsp").forward(request, response);
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
                request.getRequestDispatcher("PartFunction.jsp").forward(request, response);

            } else if (action.equals("update")) {
                try {
                    int partID = Integer.parseInt(request.getParameter("partID"));
                    String partName = request.getParameter("partName");
                    double purchasePrice = Double.parseDouble(request.getParameter("purchasePrice"));
                    double retailPrice = Double.parseDouble(request.getParameter("retailPrice"));

                    // Kiểm tra dữ liệu đầu vào
                    System.out.println("partID: " + partID);
                    System.out.println("partName: " + partName);
                    System.out.println("purchasePrice: " + purchasePrice);
                    System.out.println("retailPrice: " + retailPrice);

                    Part part = new Part(partID, partName, purchasePrice, retailPrice);
                    partDAO.updatePart(part);

                    // Kiểm tra xem có cập nhật được không
                    System.out.println("Updated part with ID: " + partID);

                    request.getSession().setAttribute("message", "Cập nhật phụ tùng thành công");

                    // Chuyển hướng về trang để load lại dữ liệu từ database
                    response.sendRedirect("PartFunction.jsp");

                } catch (Exception e) {
                    e.printStackTrace();
                    request.getSession().setAttribute("error", "Lỗi khi cập nhật phụ tùng");
                    response.sendRedirect("PartFunction.jsp");
                }
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("partID"));
                partDAO.deletePart(id);
                request.getSession().setAttribute("message", "Xóa phụ tùng thành công!");
                ArrayList<Part> parts = partDAO.getAllParts();
                request.setAttribute("parts", parts);
                request.getRequestDispatcher("PartFunction.jsp").forward(request, response);

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
