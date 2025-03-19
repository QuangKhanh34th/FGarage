<%-- 
    Document   : ProfileFunction
    Created on : Mar 9, 2025, 8:27:45 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        h2 {
            color: #343a40;
            font-size: 24px;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
            display: block;
            text-align: left;
            margin: 10px 0 5px;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            padding: 10px;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        p {
            font-weight: bold;
        }
        p.success {
            color: green;
        }
        p.error {
            color: red;
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
                        Profile
                    </h1>
                </div>
                <div class="container">
                    <h2>Change Profile</h2>

                    <%-- Hiển thị thông báo --%>
                    <c:if test="${not empty requestScope.SUCCESS}">
                        <p class="success">${requestScope.SUCCESS}</p>
                    </c:if>
                    <c:if test="${not empty requestScope.ERROR}">
                        <p class="error">${requestScope.ERROR}</p>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/ChangeProfileServlet" method="POST">
                        <label for="custName">Name:</label>
                        <input type="text" id="custName" name="custName" value="${sessionScope.customer.custName}" required>

                        <label for="phone">Phone:</label>
                        <input type="text" id="phone" name="phone" value="${sessionScope.customer.phone}" required>

                        <label for="sex">Sex:</label>
                        <select id="sex" name="sex">
                            <option value="M" ${sessionScope.customer.sex == 'M' ? 'selected' : ''}>Male</option>
                            <option value="F" ${sessionScope.customer.sex == 'F' ? 'selected' : ''}>Female</option>
                        </select>
                        <label for="cusAddress">Address:</label>
                        <input type="text" id="cusAddress" name="cusAddress" value="${sessionScope.customer.cusAddress}" required>

                        <input type="submit" value="Update">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
