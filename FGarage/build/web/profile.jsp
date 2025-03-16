<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dto.CustomerDTO" %>
<%
    CustomerDTO customer = (CustomerDTO) session.getAttribute("USER");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Change Profile</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h2>Change Profile</h2>

    <%-- Hiển thị thông báo --%>
    <% if (request.getAttribute("SUCCESS") != null) { %>
        <p style="color:green;"><%= request.getAttribute("SUCCESS") %></p>
    <% } %>
    <% if (request.getAttribute("ERROR") != null) { %>
        <p style="color:red;"><%= request.getAttribute("ERROR") %></p>
    <% } %>

    <form action="ChangeProfileServlet" method="POST">
        <label for="custName">Name:</label>
        <input type="text" id="custName" name="custName" value="<%= customer.getCustName() %>" required><br><br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="<%= customer.getPhone() %>" required><br><br>

        <label for="sex">Sex:</label>
        <select id="sex" name="sex">
            <option value="M" <%= "M".equals(customer.getSex()) ? "selected" : "" %>>Male</option>
            <option value="F" <%= "F".equals(customer.getSex()) ? "selected" : "" %>>Female</option>
        </select><br><br>

        <label for="cusAddress">Address:</label>
        <input type="text" id="cusAddress" name="cusAddress" value="<%= customer.getCusAddress() %>" required><br><br>

        <input type="submit" value="Update">
    </form>

    <a href="MainController?action=CustomerDashboard">Back to Dashboard</a>
</body>
</html>
