<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Service Tickets</title>
</head>
<body>
    

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <c:choose>
        <c:when test="${not empty ticketDetail}">
            <!-- Chi tiết Service Ticket -->
            <h2>Service Ticket Details</h2>
            <p><strong>Date Received:</strong> ${ticketDetail.dateReceived}</p>
            <p><strong>Date Returned:</strong> ${ticketDetail.dateReturned}</p>

            <h3>Car Information</h3>
            <p><strong>Serial Number:</strong> ${ticketDetail.car.serialNumber}</p>
            <p><strong>Model:</strong> ${ticketDetail.car.model}</p>
            <p><strong>Color:</strong> ${ticketDetail.car.colour}</p>
            <p><strong>Year:</strong> ${ticketDetail.car.year}</p>

            <h3>Services Performed</h3>
            <c:choose>
                <c:when test="${empty ticketDetail.services}">
                    <p>No services recorded.</p>
                </c:when>
                <c:otherwise>
                    <table border="1">
                        <tr>
                            <th>Service Name</th>
                            <th>Mechanic</th>
                            <th>Hours</th>
                            <th>Rate</th>
                            <th>Hourly Rate</th>
                            <th>Comment</th>
                        </tr>
                        <c:forEach var="service" items="${ticketDetail.services}">
                            <tr>
                                <td>${service.serviceName}</td>
                                <td>${service.mechanicName}</td>
                                <td>${service.hours}</td>
                                <td><fmt:formatNumber value="${service.rate}" type="number" pattern="#.00"/></td>
                                <td><fmt:formatNumber value="${service.hourlyRate}" type="number" pattern="#.00"/></td>
                                <td>${service.comment}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>

            <h3>Parts Used</h3>
            <c:choose>
                <c:when test="${empty ticketDetail.parts}">
                    <p>No parts used.</p>
                </c:when>
                <c:otherwise>
                    <table border="1">
                        <tr>
                            <th>Part Name</th>
                            <th>Number Used</th>
                            <th>Price</th>
                            <th>Total Cost</th>
                        </tr>
                        <c:forEach var="part" items="${ticketDetail.parts}">
                            <tr>
                                <td>${part.partName}</td>
                                <td>${part.numberUsed}</td>
                                <td><fmt:formatNumber value="${part.price}" type="number" pattern="#.00"/></td>
                                <td><fmt:formatNumber value="${part.numberUsed*part.price}" type="number" pattern="#.00"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>

            <br>
            <a href="MainController?action=CustomerTicket">Back to Service Tickets</a>
            <a href="MainController?action=CustomerDashboard">Dashboard</a>
        </c:when>

        <c:otherwise>
            <h2>Service Tickets</h2>
            <!-- Danh sách Service Ticket -->
            <c:if test="${empty serviceTickets}">
                <p>No service tickets found.</p>
            </c:if>
            <c:if test="${not empty serviceTickets}">
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Date Received</th>
                        <th>Date Returned</th>
                        <th>Car ID</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="ticket" items="${serviceTickets}">
                        <tr>
                            <td>${ticket.serviceTicketID}</td>
                            <td>${ticket.dateReceived}</td>
                            <td>${ticket.dateReturned}</td>
                            <td>${ticket.carID}</td>
                            <td>
                                <a href="CustomerServiceTicketServlet?serviceTicketID=${ticket.serviceTicketID}">
                                    View Details
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
                <a href="MainController?action=CustomerDashboard">Dashboard</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
