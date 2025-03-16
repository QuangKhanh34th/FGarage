<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Services</title>
    </head>
    <body>
        <h2>Service List</h2>

        <!-- Hiển thị thông báo -->
        <c:if test="${not empty sessionScope.MESSAGE}">
            <p style="color: green; font-weight: bold;">${sessionScope.MESSAGE}</p>
            <c:remove var="MESSAGE" scope="session"/>
        </c:if>
        <!-- Form thêm dịch vụ mới -->
        <h3>Add New Service</h3>
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="Create">
            Service Name: <input type="text" name="serviceName" size="30" required>
            Hourly Rate: <input type="text" name="hourlyRate" size="10" required>
            <input type="submit" value="Add Service">
        </form>
        </br>
        <!-- Bảng danh sách dịch vụ -->
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <th>Service ID</th>
                <th>Service Name</th>
                <th>Hourly Rate</th>
                <th>Actions</th>
            </tr>

            <c:forEach var="service" items="${SERVICES}">
                <tr>
                    <td align="center">${service.serviceID}</td>
                    <td>
                        <form action="MainController" method="POST">
                            <input type="hidden" name="action" value="Update">
                            <input type="hidden" name="serviceID" value="${service.serviceID}">
                            <input type="text" name="serviceName" value="${service.serviceName}" size="30">
                            </td>
                            <td>
                                <input type="text" name="hourlyRate" value="<fmt:formatNumber value='${service.hourlyRate}' pattern='#0.00'/>" size="15">
                            </td>
                            <td align="center">
                                <input type="submit" value="Update">
                        </form>
                        <form action="MainController" method="POST" style="display: inline;">
                            <input type="hidden" name="action" value="Delete">
                            <input type="hidden" name="serviceID" value="${service.serviceID}">
                            <input type="submit" onclick="return confirm('Are you sure you want to delete this service?');" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br>



        <br>
        <a href="MainController?action=MechanicDashboard">Back to Dashboard</a>
    </body>
</html>
