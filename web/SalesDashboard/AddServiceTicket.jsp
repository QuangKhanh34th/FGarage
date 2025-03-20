<%-- 
    Document   : ServiceTicketFunction
    Created on : Mar 18, 2025, 8:26:05 PM
    Author     : ASUS
--%>

<%@page import="model.Car"%>
<%@page import="Service.CarService"%>
<%@page import="Service.CustomerService"%>
<%@page import="DAO.CustomerDAO"%>
<%@page import="model.Customer"%>
<%@page import="model.Mechanic"%>
<%@page import="DAO.MechanicDAO"%>
<%@page import="DAO.ServiceModelDAO"%>
<%@page import="model.ServiceModel"%>
<%@page import="java.util.ArrayList, model.Part"%>
<%@page import="DAO.PartDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    PartDAO partDAO = new PartDAO();
    ArrayList<Part> parts = partDAO.getAllParts();
    ArrayList<ServiceModel> services = ServiceModelDAO.getAllServiceModels();
    session.setAttribute("serviceList", services);
    ArrayList<Mechanic> mechanics = MechanicDAO.getAllMechanics();
%>
<%
    // Check if the data has been loaded
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Car> cars = new ArrayList<>();
    CustomerService cs = new CustomerService();
    CarService carService = new CarService();
    if (session.getAttribute("currentCusPageList") == null) {
        customers = cs.getCustomers();
        session.setAttribute("customerList", customers);
    }
    if (session.getAttribute("currentCarPageList") == null) {
        cars = carService.getCars();
        session.setAttribute("carList", cars);
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
                        Create Service Ticket
                    </h1>
                </div>

                <div class="container mt-4">

                    <form id="serviceTicketForm" action="${pageContext.request.contextPath}/MainServlet" method="post">
                        <input type="hidden" name="action" value="serviceTicketAdd">
                        <div class="mb-3">
                            <label for="customerId" class="form-label">Customer:</label>
                            <select id="customerId" name="customerId" class="form-select" required>
                                <option value="">Select Customer</option>
                                <c:forEach var="customer" items="${sessionScope.customerList}">
                                    <option value="${customer.custID}">${customer.custID} - ${customer.custName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="carId" class="form-label">Car:</label>
                            <select id="carId" name="carId" class="form-select" required>
                                <option value="">Select Car</option>
                                <c:forEach var="car" items="${sessionScope.carList}">
                                    <option value="${car.carID}">${car.serialNumber} - ${car.getModel()} (${car.getYear()})</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="dateReceived" class="form-label">Date Received:</label>
                                <input type="date" id="dateReceived" name="dateReceived" class="form-control" required>
                            </div>

                            <div class="col-md-6">
                                <label for="dateReturned" class="form-label">Date Returned:</label>
                                <input type="date" id="dateReturned" name="dateReturned" class="form-control" required>
                            </div>
                        </div>
                        <div class="mb-4">
                            <h4>Add Service</h4>
                            <div class="row">
                                <div class="col-md-6">
                                    <label for="serviceId" class="form-label">Service:</label>
                                    <select id="serviceId" class="form-select" required>
                                        <option value="">Select Service</option>
                                        <%
                                            for (ServiceModel service : services) {
                                                out.println("<option value='" + service.getServiceID() + "'>" + service.getServiceName() + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label for="serviceHours" class="form-label">Hours:</label>
                                    <input type="number" id="serviceHours" class="form-control" min="0">
                                </div>
                                <div class="col-md-3">
                                    <button type="button" id="addServiceButton" class="btn btn-primary mt-4">Add Service</button>
                                </div>
                            </div>

                            <div id="addedServicesText" style="display: none;">
                                <strong>Added Services:</strong>
                            </div>
                            <div id="addedServicesTable" style="display: none;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Service ID</th>
                                            <th>Service Name</th>
                                            <th>Hours</th>
                                            <th>Remove</th>
                                        </tr>
                                    </thead>
                                    <tbody id="addedServicesTableBody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="mb-4">
                            <h4>Add Parts</h4>
                            <div class="row">
                                <div class="col-md-6">
                                    <label for="partId" class="form-label">Part:</label>
                                    <select id="partId" class="form-select" required>
                                        <option value="">Select Part</option>
                                        <%
                                            for (Part part : parts) {
                                                out.println("<option value='" + part.getPartID() + "'>" + part.getPartName() + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label for="partQuantity" class="form-label">Quantity:</label>
                                    <input type="number" id="partQuantity" class="form-control" min="1">
                                </div>
                                <div class="col-md-3">
                                    <button type="button" id="addPartButton" class="btn btn-primary mt-4">Add Part</button>
                                </div>
                            </div>
                            <div id="addedPartsText" style="display: none;">
                                <strong>Added Parts:</strong>
                            </div>
                            <div id="addedPartsTable" style="display: none;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Part ID</th>
                                            <th>Part Name</th>
                                            <th>Quantity</th>
                                            <th>Remove</th>
                                        </tr>
                                    </thead>
                                    <tbody id="addedPartsTableBody">
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="mb-4">
                            <h4>Assign Mechanic</h4>
                            <div class="row">
                                <div class="col-md-6">
                                    <label for="mechanicId" class="form-label">Mechanic:</label>
                                    <select id="mechanicId" name="mechanicId" class="form-select" required>
                                        <option value="">Select Mechanic</option>
                                        <%
                                            for (Mechanic mechanic : mechanics) {
                                                out.println("<option value='" + mechanic.getMechanicID() + "'>" + mechanic.getMechanicID() + " - " + mechanic.getMechanicName() + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="mechanicComment" class="form-label">Comment (Optional):</label>
                                    <textarea id="mechanicComment" name="mechanicComment" class="form-control"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-auto">
                                <button id="returnToTicketList" class="btn btn-secondary">Return to Ticket List</button>
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-success">Create Service Ticket</button>
                            </div>


                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>


    <script>
        document.getElementById('returnToTicketList').addEventListener('click', function () {
            window.location.href = 'ServiceTicketFunction.jsp'; // Replace with your target URL
        });

        document.addEventListener('DOMContentLoaded', function () {
            let addedPartsList = []; // Temporary list to store added parts
            const addedServices = []; // Temporary list for services



            // Handle Add Service Button
            document.getElementById('addServiceButton').addEventListener('click', function () {
                const serviceId = document.getElementById('serviceId').value;
                const serviceHours = parseInt(document.getElementById('serviceHours').value);
                const serviceSelect = document.getElementById('serviceId');
                const serviceName = serviceSelect.options[serviceSelect.selectedIndex].text;

                if (serviceId && !isNaN(serviceHours)) { // Check if hours is a valid number
                    const existingServiceIndex = addedServices.findIndex(s => s.serviceId === serviceId);

                    if (existingServiceIndex !== -1) {
                        addedServices[existingServiceIndex].hours += serviceHours;
                    } else {
                        addedServices.push({serviceId, serviceName, hours: serviceHours});
                    }

                    updateAddedServicesTable();
                    document.getElementById('serviceHours').value = '';
                } else {
                    alert('Please enter a valid number for service hours.');
                }
            });

            // Handle Add Part Button
            document.getElementById('addPartButton').addEventListener('click', function () {
                const partId = document.getElementById('partId').value;
                const partName = document.getElementById('partId').options[document.getElementById('partId').selectedIndex].text;
                const quantity = parseInt(document.getElementById('partQuantity').value);

                if (partId && !isNaN(quantity)) { // Check if quantity is a valid number
                    const existingPartIndex = addedPartsList.findIndex(p => p.partId === partId);

                    if (existingPartIndex !== -1) {
                        addedPartsList[existingPartIndex].quantity += quantity;
                    } else {
                        addedPartsList.push({partId, partName, quantity});
                    }

                    updateAddedPartsTable();
                    document.getElementById('partQuantity').value = '';
                } else {
                    alert('Please enter a valid number for part quantity.');
                }
            });

            // Update Added Services Table
            function updateAddedServicesTable() {
                const tableBody = document.getElementById('addedServicesTableBody');
                tableBody.innerHTML = '';

                addedServices.forEach((service, index) => {
                    const row = tableBody.insertRow();
                    row.insertCell().textContent = service.serviceId;
                    row.insertCell().textContent = service.serviceName;
                    row.insertCell().textContent = service.hours;

                    const removeButtonCell = row.insertCell();
                    const removeButton = document.createElement('button');
                    removeButton.textContent = 'Remove';
                    removeButton.classList.add('btn', 'btn-danger', 'btn-sm');
                    removeButton.addEventListener('click', () => {
                        addedServices.splice(index, 1);
                        updateAddedServicesTable();
                    });
                    removeButtonCell.appendChild(removeButton);
                });

                const servicesText = document.getElementById('addedServicesText');
                if (addedServices.length > 0) {
                    servicesText.style.display = 'block';
                    document.getElementById('addedServicesTable').style.display = 'block';
                } else {
                    servicesText.style.display = 'none';
                    document.getElementById('addedServicesTable').style.display = 'none';
                }
            }

            // Update Added Parts Table
            function updateAddedPartsTable() {
                const tableBody = document.getElementById('addedPartsTableBody');
                tableBody.innerHTML = '';

                addedPartsList.forEach((part, index) => {
                    const row = tableBody.insertRow();
                    row.insertCell().textContent = part.partId;
                    row.insertCell().textContent = part.partName;
                    row.insertCell().textContent = part.quantity;

                    const removeButtonCell = row.insertCell();
                    const removeButton = document.createElement('button');
                    removeButton.textContent = 'Remove';
                    removeButton.classList.add('btn', 'btn-danger', 'btn-sm');
                    removeButton.addEventListener('click', () => {
                        addedPartsList.splice(index, 1);
                        updateAddedPartsTable();
                    });
                    removeButtonCell.appendChild(removeButton);
                });

                const partsText = document.getElementById('addedPartsText');
                if (addedPartsList.length > 0) {
                    partsText.style.display = 'block';
                    document.getElementById('addedPartsTable').style.display = 'block';
                } else {
                    partsText.style.display = 'none';
                    document.getElementById('addedPartsTable').style.display = 'none';
                }
            }

            function prepareFormData() {
                const form = document.getElementById('serviceTicketForm');
                const dateReceived = document.getElementById('dateReceived').value;
                const dateReturned = document.getElementById('dateReturned').value;

                // Date Validation
                if (dateReceived && dateReturned) {
                    if (new Date(dateReturned) < new Date(dateReceived)) {
                        alert("Date Returned cannot be earlier than Date Received.");
                        event.preventDefault(); // Prevent form submission
                        return;
                    }
                }

                // Check if Services or Parts are Empty
                if (addedServices.length === 0 || addedPartsList.length === 0) { // Changed condition
                    if (addedServices.length === 0 && addedPartsList.length === 0) {
                        alert("Please add at least one service and one part to the service ticket.");
                    } else if (addedServices.length === 0) {
                        alert("Please add at least one service to the service ticket.");
                    } else {
                        alert("Please add at least one part to the service ticket.");
                    }
                    event.preventDefault(); // Prevent form submission
                    return;
                }

                // Add Services to Form
                addedServices.forEach((service, index) => {
                    const serviceIdInput = document.createElement('input');
                    serviceIdInput.type = 'hidden';
                    serviceIdInput.name = `serviceIds[${index}]`; // Array-like naming
                    serviceIdInput.value = service.serviceId;
                    form.appendChild(serviceIdInput);

                    const serviceHoursInput = document.createElement('input');
                    serviceHoursInput.type = 'hidden';
                    serviceHoursInput.name = `serviceHours[${index}]`;
                    serviceHoursInput.value = service.hours;
                    form.appendChild(serviceHoursInput);
                });

                // Add Parts to Form
                addedPartsList.forEach((part, index) => {
                    const partIdInput = document.createElement('input');
                    partIdInput.type = 'hidden';
                    partIdInput.name = `partIds[${index}]`;
                    partIdInput.value = part.partId;
                    form.appendChild(partIdInput);

                    const partQuantityInput = document.createElement('input');
                    partQuantityInput.type = 'hidden';
                    partQuantityInput.name = `partQuantities[${index}]`;
                    partQuantityInput.value = part.quantity;
                    form.appendChild(partQuantityInput);
                });
            }

            document.getElementById('serviceTicketForm').addEventListener('submit', prepareFormData);


            // Initially hide the table and text
            document.getElementById('addedServicesTable').style.display = 'none';
            document.getElementById('addedServicesText').style.display = 'none';
        });


    </script>
</body>
</html>
