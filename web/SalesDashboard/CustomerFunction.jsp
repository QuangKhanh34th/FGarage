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
                        Customer Management
                    </h1>
                    <%--Manage Customer container--%>
                    <div>
                        <%--Search and add function--%>
                        <div class="row gy-2 gx-3 mb-2 align-items-center d-flex justify-content-between">
                            <%--search bar for the below table--%>
                            <div class="col-auto">
                                <form class="d-flex align-items-center">
                                    <div class="me-2">
                                        <input type="text" class="form-control" id="customerSearch" placeholder="Customer name">
                                    </div>
                                    
                                    <button type="submit" class="btn btn-secondary">Search</button>                          
                                </form>
                            </div>
                            
                            <%--Add customer button--%>
                            <div class="col-auto">
                                <button class="btn btn-secondary">
                                    <i class="bi-plus-square"></i> Add Customer
                                </button>  
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
