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
    </head>
    <body>
        <c:choose>
            <c:when test="${empty USER or ROLE ne 'Customer'}">
                <c:redirect url="login.jsp"/>
            </c:when>
            <c:otherwise>
                <h2>Welcome, ${USER.custName}!</h2>

                <ul>
                    <li><a href="MainController?action=ChangeProfile">Update Profile</a></li>
                    <li><a href="MainController?action=CustomerTicket">View My Service Tickets</a></li>
                    <li><a href="MainController?action=Logout">Logout</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </body>
</html>
