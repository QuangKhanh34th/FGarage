<%-- 
    Document   : InvoiceFunction
    Created on : Mar 3, 2025, 12:11:09 AM
    Author     : ASUS
--%>


<%@page import="DAO.SalesInvoiceDAO"%>
<%@page import="model.SalesInvoice"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    SalesInvoiceDAO salesInvoiceDAO = new SalesInvoiceDAO();
    ArrayList<SalesInvoice> salesInvoiceList = salesInvoiceDAO.getAllInvoice();
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Dashboard</title>
    </head>
    <body>
        <%--Header--%>
        <jsp:include page="header.jsp"/>

        <div class="wrapper">
            <%--Side bar--%>
            <jsp:include page="sidebar.jsp"/>

            <%--Main content--%>
            <div class="main p-3">
                <div class="text-center">
                    <h1>
                        Invoice Management
                    </h1>
                </div>

                <!-- Hiển thị thông báo -->
                <%
                    String message = (String) session.getAttribute("message");
                    String errorMessage = (String) session.getAttribute("errorMessage");
                    if (message != null) {
                %>
                <div class="message success"><%= message%></div>
                <%
                        session.removeAttribute("message");
                    }
                    if (errorMessage != null) {
                %>
                <div class="message error"><%= errorMessage%></div>
                <%
                        session.removeAttribute("errorMessage");
                    }
                %>
                <form action="SalesInvoiceServlet" method="POST">
                    <input type="hidden" name="action" value="add">
                    <label>Mã nhân viên:</label>
                    <input type="number" name="salesID" required>
                    <label>Mã khách hàng:</label>
                    <input type="number" name="custID" required>
                    <label>Mã xe:</label>
                    <input type="number" name="carID" required>
                    <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn tạo hóa đơn này?');">Tạo hóa đơn</button>
                </form>

                <h3>Danh sách hóa đơn</h3>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Ngày lập</th>
                        <th>Nhân viên</th>
                        <th>Khách hàng</th>
                        <th>Xe</th>
                    </tr>
                    <%
                        if (salesInvoiceList == null || salesInvoiceList.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="5" class="message error">Lỗi khi tải dữ liệu hóa đơn.</td>
                    </tr>
                    <%
                    } else if (salesInvoiceList.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="6">Không có dữ liệu</td>
                    </tr>
                    <%
                    } else {
                        salesInvoiceList = salesInvoiceDAO.getAllInvoice();
                        if (salesInvoiceList != null && !salesInvoiceList.isEmpty()) {
                            for (SalesInvoice salesInvoice : salesInvoiceList) {
                    %>
                    <tr>
                        <td><%= salesInvoice.getInvoiceID()%></td>
                        <td><%= salesInvoice.getInvoiceDate()%></td>
                        <td><%= salesInvoice.getSalesID()%></td>
                        <td><%= salesInvoice.getCustID()%></td>
                        <td><%= salesInvoice.getCarID()%></td>

                    </tr>
                    <%
                                }
                            }
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
