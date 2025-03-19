<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Service Ticket Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/MechanicDashboard/style.css">
</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="wrapper">
        <div class="sidebar">
            <jsp:include page="sidebar.jsp"/>
        </div>

        <div class="main p-3">
            <div class="header-container">
                <h1>Service Ticket Management</h1>
            </div>

            <form action="MainController" method="GET" class="search-form">
                <div class="search-group">
                    <label for="searchType">Search by:</label>
                    <select name="searchType" id="searchType" onchange="updateInputType(true)">
                        <option value="custID" ${param.searchType == 'custID' ? 'selected' : ''}>Customer ID</option>
                        <option value="carID" ${param.searchType == 'carID' ? 'selected' : ''}>Car ID</option>
                        <option value="dateReceived" ${param.searchType == 'dateReceived' ? 'selected' : ''}>Date Received</option>
                    </select>
                </div>
                <div class="search-group">
                    <input type="text" name="searchValue" id="searchValue" value="${param.searchValue}" placeholder="Enter value">
                </div>
                <div class="search-group">
                    <input type="hidden" name="action" value="SearchTicket">
                    <button type="submit" class="btn-custom btn-primary">Search</button>
                    <a href="MainController?action=ViewAllTickets" class="btn-custom btn-secondary">View All Tickets</a>
                    
                    <a href="${pageContext.request.contextPath}/MainController?action=UpdateServiceTicket" class="btn-custom btn-warning">Update Service Ticket</a>
                
                </div>
            </form>

            <table class="styled-table centered-table">
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
                                <td colspan="7" class="no-data">No Service Ticket Found</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
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