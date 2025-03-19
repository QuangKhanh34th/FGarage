<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Service Ticket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/MechanicDashboard/style.css">
</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="wrapper">
        <div class="sidebar">
            <jsp:include page="sidebar.jsp"/>
        </div>
        <div class="main p-3">
            <div class="container">
                <h2>Update Service Ticket</h2>
                <c:if test="${not empty MESSAGE}">
                    <div style="color: ${MESSAGE eq 'Update Successful!' ? 'green' : 'red'}; font-weight: bold;">
                        ${MESSAGE}
                    </div>
                </c:if>
                <c:if test="${not empty SERVICE_TICKETS}">
                    <form action="${pageContext.request.contextPath}/MainController?action=UpdateServiceTicket" method="POST">
                        <table class="update-table">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Service Ticket ID</th>
                                    <th>Service ID</th>
                                    <th>Hours</th>
                                    <th>Comment</th>
                                    <th>Rate</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="ticket" items="${SERVICE_TICKETS}" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${ticket.serviceTicketID}</td>
                                        <td>${ticket.serviceID}</td>
                                        <td>
                                            <input type="number" name="hours_${ticket.serviceTicketID}_${ticket.serviceID}" 
                                                   value="${ticket.hours}" required class="form-control">
                                        </td>
                                        <td>
                                            <input type="text" name="comment_${ticket.serviceTicketID}_${ticket.serviceID}" 
                                                   value="${ticket.comment}" class="form-control">
                                        </td>
                                        <fmt:formatNumber var="formattedRate" value="${ticket.rate}" pattern="#.00"/>
                                        <td>
                                            <input type="number" step="0.01" name="rate_${ticket.serviceTicketID}_${ticket.serviceID}" 
                                                   value="${formattedRate}" required class="form-control">
                                        </td>
                                        <td>
                                            <button type="submit" name="update" 
                                                    value="${ticket.serviceTicketID}_${ticket.serviceID}" class="btn-update">Update</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>