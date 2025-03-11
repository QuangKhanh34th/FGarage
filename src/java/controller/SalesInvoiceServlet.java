/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SalesInvoiceDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SalesInvoice;

public class SalesInvoiceServlet extends HttpServlet {

    private SalesInvoiceDAO salesInvoiceDAO = new SalesInvoiceDAO();
    ArrayList<SalesInvoice> salesInvoiceList = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            try {
                BigDecimal salesID = new BigDecimal(request.getParameter("salesID"));
                BigDecimal carID = new BigDecimal(request.getParameter("carID"));
                BigDecimal custID = new BigDecimal(request.getParameter("custID"));

                salesInvoiceDAO.addSalesInvoice(salesID, carID, custID);
                request.getSession().setAttribute("message", "Tạo hóa đơn thành công!");
                salesInvoiceList = salesInvoiceDAO.getAllInvoice();
                System.out.println("Số lượng hóa đơn lấy được: " + salesInvoiceList.size());
                request.setAttribute("salesInvoiceList", salesInvoiceList);
                request.getRequestDispatcher("InvoiceFunction.jsp").forward(request, response);
            } catch (SQLException e) {
                request.getSession().setAttribute("errorMessage", "Lỗi khi tạo hóa đơn: " + e.getMessage());
                response.sendRedirect("SalesInvoiceServlet"); // Chuyển hướng đến trang lỗi
            }
        }
    }

    /*else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("invoiceID"));
                invoiceDAO.deleteInvoice(id);
                request.getSession().setAttribute("message", "Xóa thành công!");   
            } catch (SQLException ex) {
                Logger.getLogger(PartServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getSession().setAttribute("errorMessage", "Lỗi cơ sở dữ liệu: " + ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(PartServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getSession().setAttribute("errorMessage", "Lỗi: " + ex.getMessage());
            }
        }*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<SalesInvoice> salesInvoiceList = salesInvoiceDAO.getAllInvoice();
            System.out.println("Số lượng hóa đơn lấy được: " + salesInvoiceList.size());
            request.setAttribute("salesInvoiceList", salesInvoiceList);
            request.getRequestDispatcher("InvoiceFunction.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Lỗi khi tải hóa đơn", e);
        }
    }
}
