<%-- 
    Document   : header
    Created on : Mar 5, 2025, 10:45:45 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/SalesDashboard/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/SalesDashboard/bootstrap-icons-1.11.3/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/SalesDashboard/salesStyle.css">

        <style>
            body {
                padding-top: 50px;
            }

            @media (max-width: 979px) {
                body {
                    padding-top: 50px;
                }
            }
        </style>
    </head>
    <body>
        <header class="container">
            <nav class="navbar fixed-top navbar-dark bg-dark">
                <a class="navbar-brand" href="home.jsp" style="margin-left: 20px;">
                    Sales Dashboard
                </a>
                <a href="#" class="btn btn-secondary my-2 my-sm-0" style="margin-right: 20px;">Log out</a>
            </nav>
        </header>
    </body>
</html>
