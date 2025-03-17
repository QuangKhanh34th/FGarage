<%-- 
    Document   : mechanicDashboard
    Created on : Mar 14, 2025, 08:00 PM
    Author     : Horwlt
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mechanic Dashboard</title>
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
        h2, h3 {
            color: #343a40;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
        input[type="submit"] {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">
        
        <c:if test="${empty sessionScope.USER or sessionScope.ROLE ne 'Mechanic'}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h2>Welcome, ${sessionScope.USER.mechanicName}!</h2>

        
        <h3>Service Ticket Management</h3>
        <ul>
            <li><a href="MainController?action=ViewAllTickets">View and Search Service Ticket</a></li>
            <li><a href="MainController?action=LoadUpdateServiceTicket">Update Service Ticket</a></li>
        </ul>

       
        <h3>Service Management</h3>
        <ul>
            <li><a href="MainController?action=ViewAllServices">Manage Services</a></li>
        </ul>

        
        <form action="MainController" method="POST">
            <input type="submit" value="Logout" name="action">
        </form>
    </div>
</body>
</html>
