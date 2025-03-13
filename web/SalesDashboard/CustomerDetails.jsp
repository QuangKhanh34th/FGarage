<%-- 
    Document   : CustomerDetail
    Created on : Mar 13, 2025, 8:05:56 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.customer.getCustName()}'s Info</title>
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
                    <h1 class="mb-4">Customer Details</h1>
                    <c:if test="${not empty sessionScope.customer}">
                        <div class="row">
                            <div class="col-md-3">
                                <p><strong>Customer ID:</strong> ${sessionScope.customer.getCustID()}</p>
                                <p><strong>Name:</strong> ${sessionScope.customer.getCustName()}</p>
                                <p><strong>Phone:</strong> ${sessionScope.customer.getPhone()}</p>
                                <p><strong>Address:</strong> ${sessionScope.customer.getCusAddress()}</p>
                                <p><strong>Gender:</strong> ${sessionScope.customer.getSex()}</p>
                            </div>
                            <div class="col-auto">
                                <img src="placeholder-image.jpg" alt="Customer Picture" class="img-fluid rounded" style="max-width: 200px; max-height: 200px;">
                            </div>
                        </div>
                    </c:if>
                    <div class="mt-4">
                        <a href="${pageContext.request.contextPath}/SalesDashboard/CustomerFunction.jsp" class="btn btn-secondary">Back to Customers</a>
                        <a href="${pageContext.request.contextPath}/DeleteCustomerServlet?customerId=${sessionScope.customer.getCustID()}" class="btn btn-danger">Delete Customer</a>
                    </div>                
                </div>
            </div>
        </div>
    </body>
</html>
