<%-- 
    Document   : ServicesFunction
    Created on : Mar 9, 2025, 3:24:59 PM
    Author     : ASUS
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mechanic Dashboard</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: flex-start; 
                min-height: 100vh; 
                margin: 0;
                padding: 20px 0;
                overflow-y: auto; 
            }
            .container {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                width: 800px;
                max-width: 90%;
            }
            h2, h3 {
                color: #343a40;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            input, button {
                padding: 8px;
                margin: 5px;
                border-radius: 5px;
                border: 1px solid #ddd;
            }
            button {
                background-color: #007bff;
                color: white;
                border: none;
                cursor: pointer;
            }
            button:hover {
                background-color: #0056b3;
            }
            .button-container {
                margin-top: 15px;
            }
    </style>
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
                    Service Management
                </h1>
            </div>
            <div class="container">
                <h2>Service List</h2>


                <c:if test="${not empty sessionScope.MESSAGE}">
                    <p class="message success">${sessionScope.MESSAGE}</p>
                    <c:remove var="MESSAGE" scope="session"/>
                </c:if>


                <h3>Add New Service</h3>
                <form action="MainController" method="POST">
                    <input type="hidden" name="action" value="Create">
                    <input type="text" name="serviceName" placeholder="Service Name" size="30" required>
                    <input type="text" name="hourlyRate" placeholder="Hourly Rate" size="10" required>
                    <button type="submit">Add Service</button>
                </form>


                <table>
                    <tr>
                        <th>Service ID</th>
                        <th>Service Name</th>
                        <th>Hourly Rate</th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="service" items="${SERVICES}">
                        <tr>
                            <td>${service.serviceID}</td>
                            <td>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="action" value="Update">
                                    <input type="hidden" name="serviceID" value="${service.serviceID}">
                                    <input type="text" name="serviceName" value="${service.serviceName}" size="25">
                                    </td>
                                    <td>
                                        <input type="text" name="hourlyRate" 
                                               value="<fmt:formatNumber value='${service.hourlyRate}' pattern='#0.00'/>" size="10">
                                    </td>
                                    <td>
                                        <button type="submit">Update</button>
                                </form>
                                <form action="MainController" method="POST" style="display: inline;">
                                    <input type="hidden" name="action" value="Delete">
                                    <input type="hidden" name="serviceID" value="${service.serviceID}">
                                    <button type="submit" onclick="return confirm('Are you sure you want to delete this service?');">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
