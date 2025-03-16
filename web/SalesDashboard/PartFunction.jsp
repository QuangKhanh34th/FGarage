<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, model.Part" %>
<%@page  import="DAO.PartDAO" %>
<%
    PartDAO partDAO = new PartDAO();
    ArrayList<Part> parts = partDAO.getAllParts();
%>
<meta charset="UTF-8">
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
                        Parts Inventory
                    </h1>
                </div>

                <h2>Quản lý Phụ Tùng</h2>

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

                <!-- Form tìm kiếm -->
                <form action="PartServlet" method="GET" accept-charset="UTF-8">
                    <input type="hidden" name="action" value="search">
                    <input type="text" name="partName" placeholder="Nhập tên phụ tùng">
                    <button type="submit">Tìm kiếm</button>
                </form>

                <!-- Hiển thị thông tin phụ tùng tìm thấy -->
                <%
                    ArrayList<Part> searchedParts = (ArrayList<Part>) request.getAttribute("searchedParts");
                    String searchMessage = (String) request.getAttribute("searchMessage");
                    if (searchedParts != null && !searchedParts.isEmpty()) {
                        for (Part part : searchedParts) {
                %>
                <h3>Kết quả tìm kiếm:</h3>
                <p><b>ID:</b> <%= part.getPartID()%></p>
                <p><b>Tên:</b> <%= part.getPartName()%></p>
                <p><b>Giá Nhập:</b> <%= part.getPurchasePrice()%></p>
                <p><b>Giá bán:</b> <%= part.getRetailPrice()%></p>
                <%
                    }
                } else if (searchMessage != null) {
                %>
                <p class="message error"><%= searchMessage%></p>
                <%
                    }
                %>

                <!-- Danh sách phụ tùng -->
                <h3>Danh sách phụ tùng:</h3>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Tên</th>
                        <th>Giá nhập</th>
                        <th>Giá bán</th>
                        <th>Xóa phụ tùng</th>
                        <th>Cập nhật dữ liệu phụ tùng</th>
                    </tr>
                    <%
                        if (parts == null || parts.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="6" class="message error">Lỗi khi tải dữ liệu phụ tùng.</td>
                    </tr>
                    <%
                    } else if (parts.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="6">Không có dữ liệu</td>
                    </tr>
                    <%
                    } else {
                        for (Part part : parts) {
                    %>
                    <tr>
                        <td><%= part.getPartID()%></td>
                        <td><%= part.getPartName()%></td>
                        <td><%= part.getPurchasePrice()%></td>
                        <td><%= part.getRetailPrice()%></td>
                        <td>
                            <!-- Form xóa -->
                            <form action="PartServlet" method="POST" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="partID" value="<%= part.getPartID()%>">
                                <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn xóa phụ tùng này?');">Xóa</button>
                            </form>


                            <!-- Form sửa -->
                        <td>
                            <form action="PartServlet" method="POST" style="display:inline;">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="partID" value="<%= part.getPartID()%>">

                                <label for="partName">Tên phụ tùng:</label>
                                <input type="text" id="partName" name="partName" value="<%= part.getPartName()%>" required>
                                <hr/>
                                <label for="purchasePrice">Giá nhập:</label>
                                <input type="number" id="purchasePrice" name="purchasePrice" step="0.01" min ="0" value="<%= part.getPurchasePrice()%>">
                                <hr/>
                                <label for="retailPrice">Giá bán:</label>
                                <input type="number" id="retailPrice" name="retailPrice" step="0.01" min="0" value="<%= part.getRetailPrice()%>">
                                <hr/>
                                <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn cập nhật thông tin của phụ tùng này?');">
                                    Lưu
                                </button>
                            </form>

                        </td>



                    </tr>
                    <%
                            }
                        }
                    %>
                </table>

                <!-- Form thêm phụ tùng -->
                <h3>Thêm phụ tùng:</h3>
                <form action="PartServlet" method="POST" accept-charset="UTF-8">
                    <input type="hidden" name="action" value="add">
                    <input type="text" name="partName" placeholder="Tên phụ tùng" required>
                    <input type="number" name="purchasePrice" step="0.01" placeholder="Giá nhập" required>
                    <input type="number" name="retailPrice" step="0.01" placeholder="Giá bán" required>
                    <button type="submit">Thêm</button>
                </form>

                <br>
                <a href="home.jsp">Quay lại Trang Chủ</a>            </div>
        </div>
    </body>
</html>
