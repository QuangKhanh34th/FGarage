<%-- 
    Document   : ServiceTicketFunction
    Created on : Mar 9, 2025, 8:01:18 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h2, h3 {
                color: #343a40;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            input, button {
                padding: 8px;
                margin: 5px;
                border-radius: 5px;
                border: 1px solid #ddd;
            }
            button {
                background-color: #007bff;
                color: white;
                border: none;
                cursor: pointer;
            }
            button:hover {
                background-color: #0056b3;
            }
            .button-container {
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <%--Header--%>
        <jsp:include page="header.jsp"/>

        <div class="wrapper">
            <%--Side bar--%>
            <jsp:include page="sidebar.jsp"/>

            <%--Main content--%>
            <div class="main p-3">
                <div class="text-center">
                    <h1>
                        Service Tickets
                    </h1>
                </div>
                <div class="container">
                    <c:if test="${not empty error}">
                        <p class="error-message">${error}</p>
                    </c:if>

                    <c:choose>
                        <c:when test="${not empty ticketDetail}">
                            <div class="button-container">
                                <a href="MainController?action=CustomerTicket" class="button">Back to Service Tickets</a>
                            </div>
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
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
