<%-- 
    Document   : login
    Created on : Mar 12, 2025, 10:20:15 PM
    Author     : Horwlt
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script>
        function toggleForm() {
            var role = document.getElementById("role").value;
            var nameInput = document.getElementById("name");
            var phoneDiv = document.getElementById("customerFields");
            var phoneInput = document.getElementById("phone");

            // Reset name và phone khi đổi role
            nameInput.value = "";
            phoneInput.value = "";

            if (role === "Mechanic") {
                phoneDiv.style.display = "none"; // Ẩn phone nếu là Mechanic
            } else {
                phoneDiv.style.display = "block"; // Hiện phone nếu là Customer
            }
        }

        window.onload = function () {
            var role = document.getElementById("role").value;
            var phoneDiv = document.getElementById("customerFields");

            // Hiển thị hoặc ẩn ô phone dựa vào role
            if (role === "Customer") {
                phoneDiv.style.display = "block";
            } else {
                phoneDiv.style.display = "none";
            }
        };
    </script>
</head>
<body>
    <h2>Login</h2>
    <form action="LoginServlet" method="POST">
        <label for="role">Login as:</label>
        <select id="role" name="role" onchange="toggleForm()">
            <option value="Mechanic" ${requestScope.role == 'Mechanic' ? 'selected' : ''}>Mechanic</option>
            <option value="Customer" ${requestScope.role == 'Customer' ? 'selected' : ''}>Customer</option>
        </select>
        <br><br>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br><br>

        <div id="customerFields" style="display:none;">
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone">
            <br><br>
        </div>

        <input type="submit" value="Login">
    </form>

    <%-- Hiển thị thông báo lỗi bằng JSTL --%>
    <c:if test="${not empty ERROR}">
        <p style="color:red;">${ERROR}</p>
    </c:if>
</body>
</html>
