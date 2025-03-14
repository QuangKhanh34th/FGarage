<%-- 
    Document   : CustomerFunction
    Created on : Mar 3, 2025, 12:10:04 AM
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Check if the customer data has been loaded
    if (session.getAttribute("currentCusPageList") == null) {
        // If not, forward the request to the GetCustomerServlet
        request.getRequestDispatcher("/MainServlet?action=getCustList").forward(request, response);
        return; // Important: Prevent further processing of the JSP
    }
%>

<!DOCTYPE html>
<meta charset="UTF-8">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Dashboard</title>
    </head>
    <body style="overflow: hidden;">
        <%--Header--%>
        <jsp:include page="header.jsp"/>

        <div class="wrapper">
            <%--Side bar--%>
            <jsp:include page="sidebar.jsp"/>
            
            <%--Main content--%>
            <div class="main p-3">
                <div class="text-center">
                    <h1 class="mt-2">
                        Customer Management
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
                                    border-radius: 5px; /* Optional: Rounded corners for a softer look */">
                            ${sessionScope.error}
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.dbCreate}">
                        <div id="stateBanner"
                             class="alert alert-success"
                             role="alert"
                             style="margin: 10px auto; /* Increased top/bottom margin */
                                    padding: 15px 20px; /* Increased vertical padding */
                                    font-size: 1em; /* Default font size */
                                    width: 100%; /* Increased width */
                                    text-align: left; /* Left-align the  text */
                                    border-radius: 5px; /* Optional: Rounded corners for a softer look */">
                            ${sessionScope.dbCreate}
                        </div>
                    </c:if>
                    
                    <%--Manage Customer container--%>
                    <div class="mt-4">
                        <%--Search and add function--%>
                        <div class="row gy-2 gx-3 mb-2 align-items-center d-flex justify-content-between">
                            <%--search bar for the below table--%>
                            <div class="col-auto">
                                <form action="${pageContext.request.contextPath}/MainServlet" class="d-flex align-items-center">
                                    <div class="me-2">
                                        <input type="hidden" name="action" value="custSearch">
                                        <input name="searchName" type="text" class="form-control" id="customerSearch" placeholder="Customer name">
                                    </div>
                                    
                                    <button type="submit" class="btn btn-secondary">Search</button>                          
                                </form>
                            </div>
                            
                            <%--Add customer button--%>
                            <div class="col-auto">
                                <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#addCustomerModal">
                                    <i class="bi-plus-square"></i> Add Customer
                                </button>
                                
                                <div class="modal fade" id="addCustomerModal" tabindex="-1" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="addCustomerModalLabel">Add New Customer</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form action="${pageContext.request.contextPath}/MainServlet" method="post" accept-charset="UTF-8">
                                                <div class="modal-body">
                                                        <div class="row">
                                                            <div class="col-md-9 mb-3">
                                                                <input type="hidden" name="action" value="custAdd">
                                                                <input type="text" class="form-control" id="custName" name="custNameAdd" placeholder="Customer Name" required>
                                                            </div>
                                                            <div class="col-md-3 mb-3">
                                                                <select class="form-select" id="custSexAdd" name="custSexAdd" required>
                                                                    <option value="" disabled selected>Gender</option>
                                                                    <option value="M">Male</option>
                                                                    <option value="F">Female</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="custPhone" class="form-label">Phone Number</label>
                                                            <input type="number" class="form-control" id="custPhone" name="custPhoneAdd" required max="99999999999999">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="custAddress" class="form-label">Address</label>
                                                            <input type="text" class="form-control" id="custAddress" name="custAddressAdd" required>
                                                        </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Add to database</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <%--Table for display data--%>
                        <table class="table table-striped table-hover"> 
                            <thead>
                                <tr class="table-secondary">
                                 <th><b>#</b></th>    
                                 <th><b>Name</b></th> 
                                 <th><b>Phone number</b></th> 
                                 <th><b>Address</b></th>
                                </tr> 
                            </thead>
                            <%-- Fetching the attributes from the session
                                 which was previously set by the servlet  
                                  "getCustomerServlet.java" 
                            --%>
                            <tbody class="table-group-divider">
                                <c:forEach var="customer" items="${sessionScope.currentCusPageList}" varStatus="status"> 
                                    <tr>
                                        <td>${(sessionScope.currentPage - 1) * 10 + status.index + 1}</td>
                                        <%--Pass custID and action to MainServlet to redirect to appropriate servlet--%>
                                        <td><a href="${pageContext.request.contextPath}/MainServlet?action=custView&custId=${customer.getCustID()}">${customer.getCustName()}</a></td>
                                        <td>${customer.getPhone()}</td>
                                        <td>${customer.getCusAddress()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation">
                            <%--
                                All page link in pagination section will pass "page" parameter 
                                for the getCustomerServlet to detect what page the user click on
                                to properly give "active" class
                            --%>
                            <ul class="pagination justify-content-center">
                                <%--
                                    if current page is 2 or larger
                                    then display the "arrow left" link, indicate there is a previous page
                                    the user can click it to view the previous page (currentPage - 1)
                                --%>
                                <c:if test="${sessionScope.currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="${pageContext.request.contextPath}/GetCustomerServlet?page=${currentPage - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <%--
                                    Add a new <li class="page-item"> using a for-loop until index "i"
                                    reached the totalPages (calculated by servlet)
                                    If the added <li> is the current page, give it "active" class to show we are at that page
                                --%>
                                <c:forEach var="i" begin="1" end="${sessionScope.totalPages}">
                                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                                        <a class="page-link" href="${pageContext.request.contextPath}/GetCustomerServlet?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <%--
                                    same as "arrow left" link, this time is for the "arrow right"
                                    indicate there is a next page available
                                --%>
                                <c:if test="${sessionScope.currentPage < sessionScope.totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="${pageContext.request.contextPath}/GetCustomerServlet?page=${currentPage + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                    
                </div>
                
            </div>
        </div>
                                
        <script>
            window.onload = function() {
                var errorBanner = document.getElementById('stateBanner');
                if (errorBanner) {
                    setTimeout(function() {
                        errorBanner.style.display = 'none';
                        <% session.removeAttribute("error");%>
                        <% session.removeAttribute("dbCreate");%>
                    }, 5000); // Hide after 5 seconds (5000 milliseconds)
                }
            };
        </script>
    </body>
</html>
