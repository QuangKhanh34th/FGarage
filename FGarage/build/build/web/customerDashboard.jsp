<%-- 
    Document   : customerDashboard
    Created on : Mar 5, 2025, 10:37:38 PM
    Author     : Horwlt
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
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
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }
        h2 {
            color: #343a40;
            font-size: 24px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 15px 0;
        }
        a {
            display: block;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            padding: 10px;
            border-radius: 5px;
            font-size: 16px;
            transition: 0.3s;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <c:choose>
        <c:when test="${empty USER or ROLE ne 'Customer'}">
            <c:redirect url="login.jsp"/>
        </c:when>
        <c:otherwise>
            <div class="container">
                <h2>Welcome, ${USER.custName}!</h2>
                <ul>
                    <li><a href="MainController?action=ChangeProfile">Update Profile</a></li>
                    <li><a href="MainController?action=CustomerTicket">View My Service Tickets</a></li>
                    <li><a href="MainController?action=Logout">Logout</a></li>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
