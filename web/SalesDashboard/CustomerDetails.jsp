<%-- 
    Document   : CustomerDetail
    Created on : Mar 13, 2025, 8:05:56 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("custInfo") == null) {
        request.getRequestDispatcher("/MainServlet?action=validateState").forward(request, response);
        return; // Important: Prevent further processing of the JSP
    }    
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.custInfo.getCustName()}'s Info</title>
    </head>
    <body style="overflow: hidden">
        <%--Header--%>
        <jsp:include page="header.jsp"/>

        <div class="wrapper">          
            <%--Side bar--%>
            <jsp:include page="sidebar.jsp"/>
            
            <%--Main content--%>
            <div class="main p-3 d-flex">
                <div class="container mt-5">
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
                    <h1 class="mb-4">Customer Details</h1>
                    <c:if test="${not empty sessionScope.custInfo}">
                        <div class="row">
                            <div class="col-md-3">
                                <p><strong>Customer ID:</strong> ${sessionScope.custInfo.getCustID()}</p>
                                <p><strong>Name:</strong> ${sessionScope.custInfo.getCustName()}</p>
                                <p><strong>Phone:</strong> ${sessionScope.custInfo.getPhone()}</p>
                                <p><strong>Address:</strong> ${sessionScope.custInfo.getCusAddress()}</p>
                                <p><strong>Gender:</strong> ${sessionScope.custInfo.getSex()}</p>
                            </div>
                            <div class="col-auto">
                                <img src="placeholder-image.jpg" alt="Customer Picture" class="img-fluid rounded" style="max-width: 200px; max-height: 200px;">
                            </div>
                        </div>
                    </c:if>
                    <div class="mt-4">
                        <a href="${pageContext.request.contextPath}/SalesDashboard/CustomerFunction.jsp" class="btn btn-secondary">Back to Customers</a>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Customer</button>
                    </div>                
                </div>
                
                <%--Modals--%>
                <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteModalLabel">Confirm Deletion</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p>
                                    This action will delete the customer [${sessionScope.custInfo.getCustName()}] 
                                    and their associated information from the database.
                                </p>
                                <p class="mb-5">
                                    Are you sure you want to delete customer [${sessionScope.custInfo.getCustName()}]?
                                </p>
                                <p style="color: red; font-weight: bold">Warning: This action is irreversible</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <a href="${pageContext.request.contextPath}/MainServlet?action=custDel&custId=${sessionScope.custInfo.getCustID()}" class="btn btn-danger">Delete</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                            
        <script>
            window.onload = function() {
                var stateBanner = document.getElementById('stateBanner');
                if (stateBanner) {
                    setTimeout(function() {
                        stateBanner.style.display = 'none';
                        <% session.removeAttribute("error");%>   
                    }, 5000); // Hide after 5 seconds (5000 milliseconds)
                }
            };
        </script>
    </body>
</html>
