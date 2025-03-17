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
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 300px;
        }
        h2 {
            margin-bottom: 20px;
            color: #343a40;
        }
        label {
            font-weight: bold;
            display: block;
            text-align: left;
            margin-bottom: 5px;
        }
        select, input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            padding: 10px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        p.error {
            color: red;
            margin-top: 10px;
        }
    </style>
    <script>
        function toggleForm() {
            var role = document.getElementById("role").value;
            var nameInput = document.getElementById("name");
            var phoneDiv = document.getElementById("customerFields");
            var phoneInput = document.getElementById("phone");

            nameInput.value = "";
            phoneInput.value = "";

            phoneDiv.style.display = (role === "Mechanic") ? "none" : "block";
        }

        window.onload = function () {
            toggleForm();
        };
    </script>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form action="LoginServlet" method="POST">
            <label for="role">Login as:</label>
            <select id="role" name="role" onchange="toggleForm()">
                <option value="Mechanic" ${requestScope.role == 'Mechanic' ? 'selected' : ''}>Mechanic</option>
                <option value="Customer" ${requestScope.role == 'Customer' ? 'selected' : ''}>Customer</option>
            </select>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <div id="customerFields" style="display:none;">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone">
            </div>

            <input type="submit" value="Login">
        </form>

        <c:if test="${not empty ERROR}">
            <p class="error">${ERROR}</p>
        </c:if>
    </div>
</body>
</html>
