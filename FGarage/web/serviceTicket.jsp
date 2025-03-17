<%-- 
    Document   : serviceTicketManagement
    Created on : Mar 14, 2025, 08:00 PM
    Author     : Horwlt
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Service Ticket Management</title>
        <meta charset="UTF-8">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: flex-start; 
                min-height: 100vh; 
                margin: 0;
                padding: 20px 0;
                overflow-y: auto; 
            }
            .container {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                width: 800px;
                max-width: 90%;
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
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            input, select, button {
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
        </style>

    </head>
    <body>
        <div class="container">

            <c:if test="${empty sessionScope.USER or sessionScope.ROLE ne 'Mechanic'}">
                <c:redirect url="login.jsp"/>
            </c:if>

            <h2>Service Ticket List</h2>


            <form action="MainController" method="GET">
                <label for="searchType">Search by:</label>
                <select name="searchType" id="searchType" onchange="updateInputType(true)">
                    <option value="custID" ${param.searchType == 'custID' ? 'selected' : ''}>Customer ID</option>
                    <option value="carID" ${param.searchType == 'carID' ? 'selected' : ''}>Car ID</option>
                    <option value="dateReceived" ${param.searchType == 'dateReceived' ? 'selected' : ''}>Date Received</option>
                </select>
                <input type="text" name="searchValue" id="searchValue" value="${param.searchValue}" placeholder="Enter value">
                <input type="hidden" name="action" value="SearchTicket">
                <button type="submit">Search</button>
                <a href="MainController?action=ViewAllTickets"><button type="button">View All Tickets</button></a>
            </form>


            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Service Ticket ID</th>
                        <th>Date Received</th>
                        <th>Date Returned</th>
                        <th>Customer ID</th>
                        <th>Car ID</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty SERVICE_TICKET_LIST}">
                            <c:forEach var="ticket" items="${SERVICE_TICKET_LIST}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${ticket.serviceTicketID}</td>
                                    <td>${ticket.dateReceived}</td>
                                    <td>${ticket.dateReturned}</td>
                                    <td>${ticket.custID}</td>
                                    <td>${ticket.carID}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" style="text-align:center; color:red;">No Service Ticket Found</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <br>
            <a href="MainController?action=MechanicDashboard"><button type="button">Back to Dashboard</button></a>
            <a href="MainController?action=LoadUpdateServiceTicket"><button type="button">Update Service Ticket</button></a>
        </div>

        <script>
            function updateInputType(resetValue) {
                var searchType = document.getElementById("searchType").value;
                var searchInput = document.getElementById("searchValue");

                searchInput.type = (searchType === "dateReceived") ? "date" : "text";

                if (resetValue) {
                    searchInput.value = "";
                }
            }

            updateInputType(false);
        </script>
    </body>
</html>