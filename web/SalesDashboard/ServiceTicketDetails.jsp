<%-- 
    Document   : ServiceTicketDetails
    Created on : Mar 18, 2025, 9:14:58 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("ticketInfo") == null) {
        request.getRequestDispatcher("/MainServlet?action=validateState").forward(request, response);
        return; // Important: Prevent further processing of the JSP
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Info</title>
    </head>
    <body style="overflow: hidden;">
        <%--Header--%>
        <jsp:include page="header.jsp"/>

        <div class="wrapper">
            <%--Side bar--%>
            <jsp:include page="sidebar.jsp"/>

            <%--Main content--%>
            <div class="main p-3">
                <div class="col-6">
                    <div class="row mb-4">
                        <h1 class="col-4">Ticket Details</h1>
                    </div>
                    <c:if test="${not empty sessionScope.ticketInfo}">
                        <div class="row d-flex justify-content-between">
                            <div class="col-md-6">
                                <p><strong>Ticket ID:</strong> ${sessionScope.ticketInfo.getServiceTicketID()}</p>
                                <p><strong>Date Received:</strong> ${sessionScope.ticketInfo.getDateReceived()}</p>
                                <p><strong>Date Returned:</strong> ${sessionScope.ticketInfo.getDateReturned()}</p>
                                <p><strong>Customer ID:</strong> 
                                    <c:if test="${empty sessionScope.ticketInfo.getCustID()}">
                                        Removed from database
                                    </c:if>

                                    <c:if test="${not empty sessionScope.ticketInfo.getCustID()}">
                                        ${sessionScope.ticketInfo.getCustID()}
                                        <a href="${pageContext.request.contextPath}/MainServlet?action=custView&custId=${sessionScope.ticketInfo.getCustID()}">(See Customer Detail)</a>
                                    </c:if>
                                </p>
                                <p><strong>Car ID: </strong>
                                    <c:if test="${empty sessionScope.ticketInfo.getCarID()}">
                                        Removed from database
                                    </c:if>
                                    <c:if test="${not empty sessionScope.ticketInfo.getCarID()}">
                                        ${sessionScope.ticketInfo.getCarID()}
                                        <a href="${pageContext.request.contextPath}/MainServlet?action=carView&carId=${sessionScope.ticketInfo.getCarID()}">(See Car Detail)</a>
                                    </c:if>
                                </p>
                            </div>
                        </div>
                    </c:if>
                    <div class="row mt-4 justify-content-start">
                        <div class="col-auto">
                            <a href="${pageContext.request.contextPath}/SalesDashboard/ServiceTicketFunction.jsp" class="btn btn-secondary me-2">Back to Service Tickets</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
