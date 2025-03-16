<%-- 
    Document   : CarFunction
    Created on : Mar 3, 2025, 12:10:14 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Check if the customer data has been loaded
    if (session.getAttribute("currentCarPageList") == null) {
        // If not, forward the request to the GetCarServlet
        request.getRequestDispatcher("/MainServlet?action=getCarList").forward(request, response);
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
                    <h1 class="mt-2">
                        Cars Inventory
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
                    <c:if test="${not empty sessionScope.dbDelete}">
                        <div id="stateBanner"
                             class="alert alert-success"
                             role="alert"
                             style="margin: 10px auto;
                             padding: 15px 20px;
                             font-size: 1em;
                             width: 100%;
                             text-align: left;
                             border-radius: 5px;">
                            ${sessionScope.dbDelete}
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.dbEdit}">
                        <div id="stateBanner"
                             class="alert alert-success"
                             role="alert"
                             style="margin: 10px auto;
                             padding: 15px 20px;
                             font-size: 1em;
                             width: 100%;
                             text-align: left;
                             border-radius: 5px;">
                            ${sessionScope.dbEdit}
                        </div>
                    </c:if>


                    <%--Manage Car container--%>
                    <div class="mt-4">
                        <%--Search and add function--%>
                        <div class="row gy-2 gx-3 mb-2 align-items-center d-flex justify-content-between">
                            <%--search bar for the below table--%>
                            <div class="col-auto">
                                <form action="${pageContext.request.contextPath}/MainServlet" class="d-flex align-items-center">
                                    <div class="col-5" style="padding-right: 7px">
                                        <select class="form-select" id="inputType" name="searchCriteria">
                                            <option selected disabled>Search criteria</option>
                                            <option value="carSerialNumber">Serial Number</option>
                                            <option value="carModel">Model</option>
                                            <option value="carYear">Year</option>
                                        </select>
                                    </div>
                                    <div class="me-2 col-6" id="searchInputContainer">
                                        <input type="hidden" name="action" value="carSearch">
                                        <input name="carSearch" type="text" class="form-control" id="carSearch" placeholder="Select search criteria" disabled>
                                    </div>
                                    <button type="submit" class="btn btn-secondary col-auto">Search</button>
                                </form>
                            </div>

                            <%--Add customer button--%>
                            <div class="col-auto">
                                <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#addCarModal">
                                    <i class="bi-plus-square"></i> Add Car
                                </button>

                                <div class="modal fade" id="addCarModal" tabindex="-1" aria-labelledby="addCarModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="addCarModal">Add New Car</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form action="${pageContext.request.contextPath}/MainServlet" method="post" accept-charset="UTF-8">
                                                <div class="modal-body">
                                                    <div class="mb-3">
                                                        <input type="hidden" name="action" value="carAdd">
                                                        <input type="text" class="form-control" id="carSerial" name="carSerialAdd" placeholder="Serial Number" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <input type="text" class="form-control" id="carModel" name="carModelAdd" placeholder="Car Model" required>
                                                    </div>


                                                    <div class="row"> 
                                                        <div class="col-md-6 mb-3">
                                                            <select class="form-select" id="yearSelect" name="carYearAdd" required>
                                                                <option value="" selected disabled>Year</option>
                                                                <%
                                                                    int currentYear = java.time.Year.now().getValue();
                                                                    for (int i = currentYear; i >= currentYear - 100; i--) {
                                                                        out.println("<option value='" + i + "'>" + i + "</option>");
                                                                    }
                                                                %>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6 mb-3">
                                                            <select class="form-select" id="carColourAdd" name="carColourAdd" required>
                                                                <option value="" disabled selected>Colour</option>
                                                                <option value="Black">Black</option>
                                                                <option value="Gray">Gray</option>
                                                                <option value="Silver">Silver</option>
                                                                <option value="Tan">Tan</option>
                                                                <option value="White">White</option>
                                                            </select>
                                                        </div>
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
                                    <th><b>Serial Number</b></th> 
                                    <th><b>Model</b></th> 
                                    <th><b>Year</b></th>
                                </tr> 
                            </thead>
                            <%-- Fetching the attributes from the session
                                 which was previously set by the servlet  
                                  "getCustomerServlet.java" 
                            --%>
                            <tbody class="table-group-divider">
                                <c:forEach var="car" items="${sessionScope.currentCarPageList}" varStatus="status"> 
                                    <tr>
                                        <td>${(sessionScope.currentPage - 1) * 10 + status.index + 1}</td>
                                        <%--Pass custID and action to MainServlet to redirect to appropriate servlet--%>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/MainServlet?action=carView&carId=${car.getCarID()}">
                                                ${car.getSerialNumber()}
                                            </a>
                                        </td>
                                        <td>${car.getModel()}</td>
                                        <td>${car.getYear()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation">
                            <%--
                                All page link in pagination section will pass "page" parameter 
                                for the getCarServlet to detect what page the user click on
                                to properly give "active" class
                            --%>
                            <ul class="pagination justify-content-center">
                                <%--
                                    if current page is 2 or larger
                                    then display the "arrow left" link, indicate there is a previous page
                                    the user can click it to view the previous page (currentPage - 1)
                                    Apply searchCriteria and searchQuery on subsequent visits if searches has been initialized by the user
                                --%>
                                <c:if test="${sessionScope.currentPage > 1}">
                                    <li class="page-item">
                                        <c:url var="prevPageUrl" value="/GetCarServlet">
                                            <c:param name="page" value="${currentPage - 1}"/>
                                            <c:if test="${not empty sessionScope.searchCriteria}"><c:param name="searchCriteria" value="${sessionScope.searchCriteria}"/></c:if>
                                            <c:if test="${not empty sessionScope.searchQuery}"><c:param name="carSearch" value="${sessionScope.searchQuery}"/></c:if>
                                        </c:url>
                                        <a class="page-link" href="${prevPageUrl}" aria-label="Previous">
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
                                        <c:url var="pageUrl" value="/GetCarServlet">
                                            <c:param name="page" value="${i}"/>
                                            <c:if test="${not empty sessionScope.searchCriteria}"><c:param name="searchCriteria" value="${sessionScope.searchCriteria}"/></c:if>
                                            <c:if test="${not empty sessionScope.searchQuery}"><c:param name="carSearch" value="${sessionScope.searchQuery}"/></c:if>
                                        </c:url>
                                        <a class="page-link" href="${pageUrl}">${i}</a>
                                    </li>
                                </c:forEach>
                                <%--
                                    same as "arrow left" link, this time is for the "arrow right"
                                    indicate there is a next page available
                                --%>
                                <c:if test="${sessionScope.currentPage < sessionScope.totalPages}">
                                    <li class="page-item">
                                        <c:url var="nextPageUrl" value="/GetCarServlet">
                                            <c:param name="page" value="${currentPage + 1}"/>
                                            <c:if test="${not empty sessionScope.searchCriteria}"><c:param name="searchCriteria" value="${sessionScope.searchCriteria}"/></c:if>
                                            <c:if test="${not empty sessionScope.searchQuery}"><c:param name="carSearch" value="${sessionScope.searchQuery}"/></c:if>
                                        </c:url>
                                        <a class="page-link" href="${nextPageUrl}" aria-label="Next">
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
            window.onload = function () {
                var stateBanner = document.getElementById('stateBanner');
                if (stateBanner) {
                    setTimeout(function () {
                        stateBanner.style.display = 'none';
            <% session.removeAttribute("error");%>
            <% session.removeAttribute("dbCreate");%>
            <% session.removeAttribute("dbDelete");%>
            <% session.removeAttribute("dbEdit");%>
                    }, 5000); // Hide after 5 seconds (5000 milliseconds)
                }
            };

            document.addEventListener('DOMContentLoaded', function () {
                const inputTypeSelect = document.getElementById('inputType');
                const searchInputContainer = document.getElementById('searchInputContainer');
                let carSearchInput = document.getElementById('carSearch'); // Initial input

                inputTypeSelect.addEventListener('change', function () {
                    const selectedValue = this.value;

                    // Remove existing text input (if the current input is serial number or model)
                    // or select input (if the current input is year select)
                    while (searchInputContainer.firstChild) {
                        searchInputContainer.removeChild(searchInputContainer.firstChild);
                    }

                    // Re-add hidden input
                    const hiddenAction = document.createElement('input');
                    hiddenAction.type = 'hidden';
                    hiddenAction.name = 'action';
                    hiddenAction.value = 'carSearch';
                    searchInputContainer.appendChild(hiddenAction);

                    //generate input field based on selected search criteria
                    if (selectedValue === 'carYear') {
                        // Create select for year
                        const yearSelect = document.createElement('select');
                        yearSelect.className = 'form-select';
                        yearSelect.name = 'carSearch';

                        const currentYear = new Date().getFullYear();
                        for (let i = currentYear; i >= currentYear - 100; i--) {
                            const option = document.createElement('option');
                            option.value = i;
                            option.textContent = i;
                            yearSelect.appendChild(option);
                        }
                        searchInputContainer.appendChild(yearSelect);
                    } else {
                        // Create text input
                        carSearchInput = document.createElement('input');
                        carSearchInput.name = 'carSearch';
                        carSearchInput.type = 'text';
                        carSearchInput.className = 'form-control';
                        carSearchInput.disabled = false;

                        if (selectedValue === 'carSerialNumber') {
                            carSearchInput.placeholder = 'Enter Serial Number';
                        } else if (selectedValue === 'carModel') {
                            carSearchInput.placeholder = 'Enter Model';
                        } else {
                            carSearchInput.placeholder = 'Enter value';
                        }
                        searchInputContainer.appendChild(carSearchInput);
                    }
                });
            });
        </script>
    </body>
</html>
