<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dto.CustomerDTO" %>
<c:if test="${empty sessionScope.USER}">
    <c:redirect url="login.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Change Profile</title>
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
            width: 400px;
        }
        h2 {
            color: #343a40;
            font-size: 24px;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            display: block;
            text-align: left;
            margin: 10px 0 5px;
        }
        input, select {
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
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        p {
            font-weight: bold;
        }
        p.success {
            color: green;
        }
        p.error {
            color: red;
        }
        a {
            display: block;
            margin-top: 10px;
            text-decoration: none;
            background-color: #6c757d;
            color: white;
            padding: 10px;
            border-radius: 5px;
            font-size: 16px;
        }
        a:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Change Profile</h2>

        <%-- Hiển thị thông báo --%>
        <c:if test="${not empty requestScope.SUCCESS}">
            <p class="success">${requestScope.SUCCESS}</p>
        </c:if>
        <c:if test="${not empty requestScope.ERROR}">
            <p class="error">${requestScope.ERROR}</p>
         </c:if>

        <form action="ChangeProfileServlet" method="POST">
            <label for="custName">Name:</label>
            <input type="text" id="custName" name="custName" value="${sessionScope.USER.custName}" required>

            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="${sessionScope.USER.phone}" required>

            <label for="sex">Sex:</label>
            <select id="sex" name="sex">
                <option value="M" ${sessionScope.USER.sex == 'M' ? 'selected' : ''}>Male</option>
                <option value="F" ${sessionScope.USER.sex == 'F' ? 'selected' : ''}>Female</option>
            </select>

            <label for="cusAddress">Address:</label>
            <input type="text" id="cusAddress" name="cusAddress" value="${sessionScope.USER.cusAddress}" required>

            <input type="submit" value="Update">
        </form>

        <a href="MainController?action=CustomerDashboard">Back to Dashboard</a>
    </div>
</body>
</html>
