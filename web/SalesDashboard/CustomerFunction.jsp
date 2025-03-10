<%-- 
    Document   : CustomerFunction
    Created on : Mar 3, 2025, 12:10:04 AM
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
                    <%--Manage Customer container--%>
                    <div class="mt-4">
                        <%--Search and add function--%>
                        <div class="row gy-2 gx-3 mb-2 align-items-center d-flex justify-content-between">
                            <%--search bar for the below table--%>
                            <div class="col-auto">
                                <form action="${pageContext.request.contextPath}/GetCustomerServlet" class="d-flex align-items-center">
                                    <div class="me-2">
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
                                            <form>
                                                <div class="modal-body">
                                                        <div class="row">
                                                            <div class="col-md-9 mb-3">
                                                                <input type="text" class="form-control" id="custName" name="custNameAdd" placeholder="Customer Name">
                                                            </div>
                                                            <div class="col-md-3 mb-3">
                                                                <select class="form-select" id="custSexAdd" name="custSexAdd">
                                                                    <option value="" selected>Gender</option>
                                                                    <option value="M">Male</option>
                                                                    <option value="F">Female</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="custPhone" class="form-label">Phone Number</label>
                                                            <input type="text" class="form-control" id="custPhone">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="custAddress" class="form-label">Address</label>
                                                            <input type="text" class="form-control" id="custAddress">
                                                        </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Save changes</button>
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
                                        <td><a href="#">${customer.getCustName()}</a></td>
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
    </body>
</html>
