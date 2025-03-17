<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Service Tickets</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 900px;
        }
        h2, h3 {
            color: #343a40;
            text-align: center;
        }
        p {
            font-size: 16px;
            margin: 5px 0;
            color: #495057;
        }
        strong {
            color: #495057;
        }
        .error-message {
            color: red;
            text-align: center;
            font-weight: bold;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background: white;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .button-container {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 15px;
        }
        .button {
            text-decoration: none;
            background-color: #6c757d;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            font-size: 16px;
            text-align: center;
            transition: background 0.3s ease;
        }
        .button:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <c:if test="${not empty error}">
            <p class="error-message">${error}</p>
        </c:if>

        <c:choose>
            <c:when test="${not empty ticketDetail}">
                
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
                        <table>
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
                        <table>
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

                <div class="button-container">
                    <a href="MainController?action=CustomerTicket" class="button">Back to Service Tickets</a>
                    <a href="MainController?action=CustomerDashboard" class="button">Dashboard</a>
                </div>
            </c:when>

            <c:otherwise>
                <h2>Service Tickets</h2>
                
                <c:if test="${empty serviceTickets}">
                    <p>No service tickets found.</p>
                </c:if>
                <c:if test="${not empty serviceTickets}">
                    <table>
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
                                    <a href="CustomerServiceTicketServlet?serviceTicketID=${ticket.serviceTicketID}" class="button">
                                        View Details
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>

                <div class="button-container">
                    <a href="MainController?action=CustomerDashboard" class="button">Dashboard</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
