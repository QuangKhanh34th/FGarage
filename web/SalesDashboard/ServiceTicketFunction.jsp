<%-- 
    Document   : ServiceTicketFuntion
    Created on : Mar 3, 2025, 12:10:44 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Check if the ticket data has been loaded
    boolean globalUpdate = false;
    try {
    globalUpdate = (boolean) session.getAttribute("globalUpdate");
    } catch (NullPointerException e) {
        System.out.println("[ServiceTicketFunction.jsp] globalUpdate not found");
    }
    if (session.getAttribute("currentTicketPageList") == null || globalUpdate ) {
        // If not, forward the request to the GetServiceTicketServlet
        request.getRequestDispatcher("/MainServlet?action=getTicketList").forward(request, response);
        return; // Important: Prevent further processing of the JSP
    }
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Dashboard</title>
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
                        Service Ticket Management
                    </h1>

                    <%-- State Banner --%>
                    <%--check for CRUD state before displaying the banner--%>
                    <c:if test="${not empty sessionScope.error}">
                        <div id="stateBanner"
                             class="alert alert-danger"
                             role="alert"
                             style="margin: 10px auto; /* Increased top/bottom margin */
                             padding: 15px 20px; /* Increased vertical padding */
                             font-size: 1em; /* Default font size */
                             width: 100%; /* Increased width */
                             text-align: left; /* Left-align the  text */
                             border-radius: 5px; /* Rounded corners for a softer look */">
                            ${sessionScope.error}
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.dbCreate}">
                        <div id="stateBanner"
                             class="alert alert-success"
                             role="alert"
                             style="margin: 10px auto;
                             padding: 15px 20px;
                             font-size: 1em;
                             width: 100%;
                             text-align: left;
                             border-radius: 5px;">
                            ${sessionScope.dbCreate}
                        </div>
                    </c:if>

                    <div class="mt-4">
                        <div class="row gy-2 gx-3 mb-2 align-items-center d-flex justify-content-between">
                            <%--Add Ticket button--%>
                            <div class="col-auto">
                                <button class="btn btn-secondary" id="addTicket">
                                    <i class="bi-plus-square"></i> Add Service Ticket
                                </button>
                            </div>
                        </div>

                        <%--Table for display data--%>
                        <table class="table table-striped table-hover"> 
                            <thead>
                                <tr class="table-secondary">
                                    <th><b>#</b></th>    
                                    <th><b>TicketID</b></th> 
                                    <th><b>Date Received</b></th> 
                                    <th><b>Date Returned</b></th>
                                </tr> 
                            </thead>
                            <%-- Fetching the attributes from the session
                                 which was previously set by the servlet  
                                  "getServiceTicketServlet.java" 
                            --%>
                            <tbody class="table-group-divider">
                                <c:forEach var="ticket" items="${sessionScope.currentTicketPageList}" varStatus="status"> 
                                    <tr>
                                        <td>${(sessionScope.ticketCurrentPage - 1) * 10 + status.index + 1}</td>
                                        <%--Pass ticketID and action to MainServlet to redirect to appropriate servlet--%>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/MainServlet?action=ticketView&ticketId=${ticket.getServiceTicketID()}">
                                                ${ticket.getServiceTicketID()}
                                            </a>
                                        </td>
                                        <td>${ticket.getDateReceived()}</td>
                                        <td>${ticket.getDateReturned()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation">
                            <%--
                                All page link in pagination section will pass "page" parameter 
                                for the getServiceTicketServlet to detect what page the user click on
                                to properly give "active" class
                            --%>
                            <ul class="pagination justify-content-center">
                                <%--
                                    if current page is 2 or larger
                                    then display the "arrow left" link, indicate there is a previous page
                                    the user can click it to view the previous page (currentPage - 1)
                                --%>
                                <c:if test="${sessionScope.ticketCurrentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="${pageContext.request.contextPath}/GetServiceTicketServlet?page=${ticketCurrentPage - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <%--
                                    Add a new <li class="page-item"> using a for-loop until index "i"
                                    reached the totalPages (calculated by servlet)
                                    If the added <li> is the current page, give it "active" class to show we are at that page
                                --%>
                                <c:forEach var="i" begin="1" end="${sessionScope.ticketTotalPages}">
                                    <li class="page-item ${i == ticketCurrentPage ? 'active' : ''}">
                                        <a class="page-link" href="${pageContext.request.contextPath}/GetServiceTicketServlet?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <%--
                                    same as "arrow left" link, this time is for the "arrow right"
                                    indicate there is a next page available
                                --%>
                                <c:if test="${sessionScope.ticketCurrentPage < sessionScope.ticketTotalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="${pageContext.request.contextPath}/GetServiceTicketServlet?page=${ticketCurrentPage + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>




            <script>
                window.onload = function () {
                    var stateBanner = document.getElementById('stateBanner');
                    if (stateBanner) {
                        setTimeout(function () {
                            stateBanner.style.display = 'none';
                <% session.removeAttribute("error");%>
                <% session.removeAttribute("dbCreate");%>
                        }, 5000); // Hide after 5 seconds (5000 milliseconds)
                    }
                };

                document.getElementById('addTicket').addEventListener('click', function () {
                    window.location.href = 'AddServiceTicket.jsp'; // Replace with your target URL
                });
            </script>
        </div>
    </body>
</html>
