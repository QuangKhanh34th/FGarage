<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Service Ticket Management</title>
        <meta charset="UTF-8">
    </head>
    <body>

        <%-- Kiểm tra session: Chỉ mechanic mới được truy cập --%>
        <c:if test="${empty sessionScope.USER or sessionScope.ROLE ne 'Mechanic'}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <h2>Service Ticket List</h2>

        <%-- Form tìm kiếm --%>
        <form action="MainController" method="GET">
            <label for="searchType">Search by:</label>
            <select name="searchType" id="searchType" onchange="updateInputType(true)">
                <option value="custID" ${param.searchType == 'custID' ? 'selected' : ''}>Customer ID</option>
                <option value="carID" ${param.searchType == 'carID' ? 'selected' : ''}>Car ID</option>
                <option value="dateReceived" ${param.searchType == 'dateReceived' ? 'selected' : ''}>Date Received</option>
            </select>

            <input type="text" name="searchValue" id="searchValue" value="${param.searchValue}" placeholder="Enter value">

            <input type="hidden" name="action" value="SearchTicket">
            <input type="submit" value="Search">
            <a href="MainController?action=ViewAllTickets"><button type="button">View All Tickets</button></a>
        </form>

        <%-- Hiển thị danh sách Service Ticket --%>
        <table border="1">
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
                            <td colspan="7" style="text-align:center; color:red;">No Service Ticket Found</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>

        <br>
        <a href="MainController?action=MechanicDashboard"><button type="button">Back to Dashboard</button></a>
        <a href="MainController?action=LoadUpdateServiceTicket"><button type="button">Update Service Ticket</button></a>
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
