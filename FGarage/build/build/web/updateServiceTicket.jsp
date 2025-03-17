<%-- 
    Document   : updateServiceTicket
    Created on : Mar 14, 2025, 08:00 PM
    Author     : Horwlt
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Service Ticket</title>
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
                width: 900px;
                max-height: 90vh;
                overflow: auto;
            }
            h2 {
                color: #343a40;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            input {
                width: calc(100% - 12px); 
                height: 30px; 
                padding: 5px;
                border-radius: 5px;
                border: 1px solid #ddd;
                text-align: center;
                box-sizing: border-box; 
            }
            button {
                background-color: #007bff;
                color: white;
                border: none;
                cursor: pointer;
                padding: 8px 12px;
                border-radius: 5px;
                transition: 0.3s;
            }
            button:hover {
                background-color: #0056b3;
            }
            .button-group {
                margin-top: 15px;
            }
            .button-group a button {
                width: 180px;
            }
        </style>


    </head>
    <body>
        <div class="container">
            <h2>Update Service Ticket</h2>
            <c:if test="${not empty MESSAGE}">
                <div style="color: ${MESSAGE eq 'Update Successful!' ? 'green' : 'red'}; font-weight: bold;">
                    ${MESSAGE}
                </div>
            </c:if>
            <c:if test="${not empty SERVICE_TICKETS}">
                <form action="MainController?action=UpdateServiceTicket" method="POST">
                    <table>
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

            <br>
            <a href="MainController?action=MechanicDashboard"><button type="button">Back to Dashboard</button></a>
            <a href="MainController?action=ViewAllTickets"><button type="button">View Service Tickets</button></a>
        </div>
    </body>
</html>
