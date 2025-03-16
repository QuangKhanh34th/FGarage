<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Service Ticket Detail</title>
</head>
<body>
    <h2>Service Ticket Detail</h2>
    
    <h3>Ticket Information</h3>
    <p><strong>Service Ticket ID:</strong> ${ticketDetail.serviceTicketID}</p>
    <p><strong>Date Received:</strong> <fmt:formatDate value="${ticketDetail.dateReceived}" pattern="yyyy-MM-dd" /></p>
    <p><strong>Date Returned:</strong> 
        <c:choose>
            <c:when test="${not empty ticketDetail.dateReturned}">
                <fmt:formatDate value="${ticketDetail.dateReturned}" pattern="yyyy-MM-dd" />
            </c:when>
            <c:otherwise>Not returned</c:otherwise>
        </c:choose>
    </p>
    
    <h3>Car Information</h3>
    <p><strong>Serial Number:</strong> ${ticketDetail.car.serialNumber}</p>
    <p><strong>Model:</strong> ${ticketDetail.car.model}</p>
    <p><strong>Color:</strong> ${ticketDetail.car.colour}</p>
    <p><strong>Year:</strong> ${ticketDetail.car.year}</p>
    
    <h3>Services Performed</h3>
    <c:if test="${not empty ticketDetail.services}">
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
                    <td><fmt:formatNumber value="${service.rate}" type="currency" /></td>
                    <td><fmt:formatNumber value="${service.hourlyRate}" type="currency" /></td>
                    <td>${service.comment}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty ticketDetail.services}">
        <p>No services performed.</p>
    </c:if>
    
    <h3>Parts Used</h3>
    <c:if test="${not empty ticketDetail.parts}">
        <table border="1">
            <tr>
                <th>Part Name</th>
                <th>Number Used</th>
                <th>Price</th>
            </tr>
            <c:forEach var="part" items="${ticketDetail.parts}">
                <tr>
                    <td>${part.partName}</td>
                    <td>${part.numberUsed}</td>
                    <td><fmt:formatNumber value="${part.price}" type="currency" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty ticketDetail.parts}">
        <p>No parts used.</p>
    </c:if>
    
    <br>
    <a href="viewTickets.jsp">Back to Tickets</a>
</body>
</html>