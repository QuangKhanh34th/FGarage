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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                    <div class="col-6">
                        <div class="row mb-4">
                            <h1 class="col-4">Customer Details</h1>
                            <div class="col-8 text-end">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">Edit Customer</button>
                            </div>
                        </div>
                        <c:if test="${not empty sessionScope.custInfo}">
                            <div class="row d-flex justify-content-between">
                                <div class="col-md-4">
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
                        <div class="row mt-4 justify-content-between">
                            <c:set var="referer" value="${header['referer']}" />
                            <div class="col-auto">
                                <c:if test="${not empty referer and fn:contains(referer, 'ServiceTicketDetails.jsp')}">
                                    <a href="${pageContext.request.contextPath}/MainServlet?action=ticketView&ticketId=${sessionScope.ticketInfo.getServiceTicketID()}" class="btn btn-secondary me-2">Back to Ticket Details</a>
                                </c:if>
                            </div>
                            <div class="col-auto">
                                <a href="${pageContext.request.contextPath}/SalesDashboard/CustomerFunction.jsp" class="btn btn-secondary me-2">Back to Customers</a>
                                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Customer</button>
                            </div>
                        </div>
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


                <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editModalLabel">Edit Customer</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form id="editCustomerForm" action="${pageContext.request.contextPath}/MainServlet" method="post" accept-charset="UTF-8">
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-9 mb-3">
                                            <%--Send the action and custID for servlet to detect the right customer--%>
                                            <input type="hidden" name="action" value="custEdit">
                                            <input type="hidden" name="custId" value="${sessionScope.custInfo.getCustID()}">
                                            <input type="text" class="form-control" id="custName" name="custName" placeholder="Customer Name" value="${sessionScope.custInfo.getCustName()}" required>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <select class="form-select" id="custSex" name="custSex" required>
                                                <option value="M" ${sessionScope.custInfo.getSex() == 'Male' ? 'selected' : ''}>Male</option>
                                                <option value="F" ${sessionScope.custInfo.getSex() == 'Female' ? 'selected' : ''}>Female</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="custPhone" class="form-label">Phone Number</label>
                                        <input type="number" class="form-control" id="custPhone" name="custPhone" value="${sessionScope.custInfo.getPhone()}" required max="99999999999999">
                                    </div>
                                    <div class="mb-3">
                                        <label for="custAddress" class="form-label">Address</label>
                                        <input type="text" class="form-control" id="custAddress" name="custAddress" value="${sessionScope.custInfo.getCusAddress()}" required>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


            </div>
        </div>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">Edit Customer</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editCustomerForm" action="${pageContext.request.contextPath}/MainServlet" method="post" accept-charset="UTF-8">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-9 mb-3">
                                <input type="hidden" name="action" value="custEdit">
                                <input type="hidden" name="custId" value="${sessionScope.custInfo.getCustID()}">
                                <input type="text" class="form-control" id="custName" name="custName" placeholder="Customer Name" value="${sessionScope.custInfo.getCustName()}" required>
                            </div>
                            <div class="col-md-3 mb-3">
                                <select class="form-select" id="custSex" name="custSex" required>
                                    <option value="M" ${sessionScope.custInfo.getSex() == 'M' ? 'selected' : ''}>Male</option>
                                    <option value="F" ${sessionScope.custInfo.getSex() == 'F' ? 'selected' : ''}>Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="custPhone" class="form-label">Phone Number</label>
                            <input type="number" class="form-control" id="custPhone" name="custPhone" value="${sessionScope.custInfo.getPhone()}" required max="99999999999999">
                        </div>
                        <div class="mb-3">
                            <label for="custAddress" class="form-label">Address</label>
                            <input type="text" class="form-control" id="custAddress" name="custAddress" value="${sessionScope.custInfo.getCusAddress()}" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="showConfirmationModal()">Save Changes</button>
                    </div>
                </form>
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
                }, 5000); // Hide after 5 seconds (5000 milliseconds)
            }
        };
    </script>
</body>
</html>
