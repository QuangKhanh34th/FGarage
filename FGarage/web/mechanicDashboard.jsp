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
    </head>
    <body>

        <%-- Kiểm tra nếu chưa đăng nhập, chuyển hướng về login.jsp --%>
        <c:if test="${empty sessionScope.USER or sessionScope.ROLE ne 'Mechanic'}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h2>Welcome, ${sessionScope.USER.mechanicName}!</h2>

        <%-- Quản lý Service Tickets --%>
        <h3>Service Ticket Management</h3>
        <ul>
            <li><a href="MainController?action=ViewAllTickets">View and Search Service Ticket</a></li>
            <li><a href="MainController?action=LoadUpdateServiceTicket">Update Service Ticket</a></li>
        </ul>

        <%-- Quản lý Services --%>
        <h3>Service Management</h3>
        <ul>
            <li><a href="MainController?action=ViewAllServices">Manage Services</a></li>
        </ul>

        <%-- Đăng xuất --%>
        <form action="MainController" method="POST">
            <input type="submit" value="Logout" name="action">
        </form>

    </body>
</html>
