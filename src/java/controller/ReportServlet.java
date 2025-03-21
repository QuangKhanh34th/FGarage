/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportDAO reportDAO = new ReportDAO();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("Report Servlet invoked");
        try {
            // Lấy thống kê số lượng xe bán theo năm
            Map<Integer, Integer> carsSoldByYear = reportDAO.getCarsSoldByYear();
            request.setAttribute("carsSoldByYear", carsSoldByYear);

            ArrayList<Integer> availableYears = new ArrayList<>(carsSoldByYear.keySet());
            Collections.sort(availableYears); // Sắp xếp tăng dần
            request.setAttribute("availableYears", availableYears);
            String yearParam = request.getParameter("year");
            int selectedYear = (yearParam != null) ? Integer.parseInt(yearParam) : (availableYears.isEmpty() ? 0 : availableYears.get(0));
            request.setAttribute("selectedYear", selectedYear);
            ArrayList<String[]> salesRevenue = reportDAO.getSalesRevenueByYear(selectedYear);
            request.setAttribute("salesRevenue", salesRevenue);

            // Lấy danh sách mẫu xe bán chạy nhất (List<String> thay vì Map<String, Integer>)
            ArrayList<String[]> modelBestSelling = reportDAO.getBestSellingCarModels();
            request.setAttribute("modelBestSelling", modelBestSelling);

            // Lấy danh sách phụ tùng được sử dụng nhiều nhất (List<String>)
            ArrayList<String[]> bestUsedParts = reportDAO.getBestUsedParts();
            request.setAttribute("bestUsedParts", bestUsedParts);

            // Lấy danh sách top 3 thợ máy sửa chữa nhiều nhất
            Map<String, Integer> topMechanics = reportDAO.getTopMechanics();
            request.setAttribute("topMechanics", topMechanics);

            // In ra console để kiểm tra
            System.out.println("---------------------------");
            System.out.println("carsSoldByYear: " + carsSoldByYear);
            System.out.println("bestSellingModels: " + modelBestSelling);
            System.out.println("bestUsedParts: " + bestUsedParts);
            System.out.println("topMechanics: " + topMechanics);

            // Chuyển dữ liệu đến trang JSP hiển thị
            request.getRequestDispatcher("SalesDashboard/StatisticsFunction.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi lấy dữ liệu báo cáo.");
            request.getRequestDispatcher("SalesDashboard/StatisticFunction.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
