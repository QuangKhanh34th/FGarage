<%-- 
    Document   : CarDetails
    Created on : Mar 16, 2025, 8:06:00 PM
    Author     : ASUS
--%>

<%@page import="model.Car"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    if (session.getAttribute("carInfo") == null) {
        request.getRequestDispatcher("/MainServlet?action=validateState").forward(request, response);
        return; // Important: Prevent further processing of the JSP
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.carInfo.getModel()} (${sessionScope.carInfo.getYear()}) 's Info</title>
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
                            <h1 class="col-4">Car Details</h1>
                            <div class="col-8 text-end">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">Edit Car</button>
                            </div>
                        </div>
                        <c:if test="${not empty sessionScope.carInfo}">
                            <div class="row d-flex justify-content-between">
                                <div class="col-md-4">
                                    <p><strong>Car ID:</strong> ${sessionScope.carInfo.getCarID()}</p>
                                    <p><strong>Serial Number:</strong> ${sessionScope.carInfo.getSerialNumber()}</p>
                                    <p><strong>Model:</strong> ${sessionScope.carInfo.getModel()}</p>
                                    <p><strong>Colour:</strong> ${sessionScope.carInfo.getColour()}</p>
                                    <p><strong>Year:</strong> ${sessionScope.carInfo.getYear()}</p>
                                </div>
                                <div class="col-auto">
                                    <img src="car-placeholder-image.jpg" alt="Car Picture" class="img-fluid rounded" style="max-width: 200px; max-height: 200px;">
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
                                <a href="${pageContext.request.contextPath}/SalesDashboard/CarFunction.jsp" class="btn btn-secondary me-2">Back to Cars</a>
                                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Car</button>
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
                                    This action will delete the Car [${sessionScope.carInfo.getModel()} ${sessionScope.carInfo.getYear()}] 
                                    and their associated information from the database.
                                </p>
                                <p class="mb-5">
                                    Are you sure you want to delete customer [${sessionScope.carInfo.getModel()} ${sessionScope.carInfo.getYear()}]?
                                </p>
                                <p style="color: red; font-weight: bold">Warning: This action is irreversible</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <a href="${pageContext.request.contextPath}/MainServlet?action=carDel&carId=${sessionScope.carInfo.getCarID()}" class="btn btn-danger">Delete</a>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editModalLabel">Edit Car</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form id="editCarForm" action="${pageContext.request.contextPath}/MainServlet" method="post" accept-charset="UTF-8">
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="carSerialEdit" class="form-label">Serial Number</label>
                                        <input type="text" class="form-control" id="carSerialEdit" name="carSerialEdit" value="${sessionScope.carInfo.getSerialNumber()}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="carModelEdit" class="form-label">Model</label>
                                        <input type="text" class="form-control" id="carModelEdit" name="carModelEdit" value="${sessionScope.carInfo.getModel()}" required>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="carYearEdit" class="form-label">Year</label>
                                            <select class="form-select" id="carYearEdit" name="carYearEdit" required>
                                                <option value="" disabled>Year</option>
                                                <%
                                                    int currentYear = java.time.Year.now().getValue();
                                                    Car target = (Car) session.getAttribute("carInfo");
                                                    for (int i = currentYear; i >= currentYear - 100; i--) {
                                                        out.println("<option value='" + i + "'" + (i == Integer.parseInt(String.valueOf(target.getYear())) ? " selected" : "") + ">" + i + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="carColourEdit" class="form-label">Colour</label>
                                            <select class="form-select" id="carColourEdit" name="carColourEdit" required>
                                                <option value="" disabled>Colour</option>
                                                <option value="Black" ${sessionScope.carInfo.getColour() == 'Black' ? 'selected' : ''}>Black</option>
                                                <option value="Gray" ${sessionScope.carInfo.getColour() == 'Gray' ? 'selected' : ''}>Gray</option>
                                                <option value="Silver" ${sessionScope.carInfo.getColour() == 'Silver' ? 'selected' : ''}>Silver</option>
                                                <option value="Tan" ${sessionScope.carInfo.getColour() == 'Tan' ? 'selected' : ''}>Tan</option>
                                                <option value="White" ${sessionScope.carInfo.getColour() == 'White' ? 'selected' : ''}>White</option>
                                            </select>
                                        </div>
                                    </div>
                                    <input type="hidden" name="carIdEdit" value="${sessionScope.carInfo.getCarID()}">
                                    <input type="hidden" name="action" value="carEdit">
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
