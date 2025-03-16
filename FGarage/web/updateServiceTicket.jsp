<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Service Ticket</title>
    </head>
    <body>

        <h2>Update Service Ticket</h2>
        <c:if test="${not empty MESSAGE}">
            <div style="color: ${MESSAGE eq 'Update Successful!' ? 'green' : 'red'}; font-weight: bold;">
                ${MESSAGE}
            </div>
        </c:if>
        <c:if test="${not empty SERVICE_TICKETS}">
            <form action="MainController?action=UpdateServiceTicket" method="POST">
                <table border="1">
                    <tr>
                        <th>#</th>
                        <th>Service Ticket ID</th>
                        <th>Service ID</th>
                        <th>Hours</th>
                        <th>Comment</th>
                        <th>Rate</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="ticket" items="${SERVICE_TICKETS}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${ticket.serviceTicketID}</td>
                            <td>${ticket.serviceID}</td>
                            <td>
                                <input type="number" name="hours_${ticket.serviceTicketID}_${ticket.serviceID}" 
                                       value="${ticket.hours}" required>
                            </td>
                            <td>
                                <input type="text" name="comment_${ticket.serviceTicketID}_${ticket.serviceID}" 
                                       value="${ticket.comment}">
                            </td>
                            <fmt:formatNumber var="formattedRate" value="${ticket.rate}" pattern="#.00"/>
                            <td>
                                <input type="number" step="0.01" name="rate_${ticket.serviceTicketID}_${ticket.serviceID}" 
                                       value="${formattedRate}" required>
                            </td>
                            <td>
                                <button type="submit" name="update" 
                                        value="${ticket.serviceTicketID}_${ticket.serviceID}">Update</button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </c:if>

        <p><a href="MainController?action=MechanicDashboard">Back to Dashboard</a></p>
        <p><a href="MainController?action=ViewAllTickets">View Service Tickets</a></p>

    </body>
</html>
