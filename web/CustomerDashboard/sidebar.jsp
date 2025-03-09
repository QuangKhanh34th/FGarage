<%-- 
    Document   : sidebarTest2
    Created on : Mar 4, 2025, 5:05:18 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/SalesDashboard/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/SalesDashboard/bootstrap-icons-1.11.3/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CustomerDashboard/salesStyle.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CustomerDashboard/customerStyle.css">

    </head>
    <body>
        <aside id="sidebar">
            <div class="d-flex">
                <button class="toggle-btn" type="button">
                    <i class="bi bi-list"></i>
                </button>
                <div class="sidebar-logo">
                    <a href="home.jsp">FGarage</a>
                </div>
            </div>
            <ul class="sidebar-nav">
                <li class="sidebar-item">
                    <a href="ServiceTicketFunction.jsp" class="sidebar-link">
                        <i class="bi bi-ticket-detailed"></i>
                        <span style="font-weight: bold;">Service Tickets</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="InvoiceFunction.jsp" class="sidebar-link">
                        <i class="bi bi-receipt-cutoff"></i>
                        <span style="font-weight: bold;">Invoices</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="ProfileFunction.jsp" class="sidebar-link">
                        <i class="bi bi-person"></i>
                        <span style="font-weight: bold;">Profile</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <i class="bi bi-bell"></i>
                        <span>Notification (WIP)</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <i class="bi bi-gear"></i>
                        <span>Setting (WIP)</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <%--Clickable text as form to prevent showing logout action on adress bar--%>
                    <form id="logoutForm" action="${pageContext.request.contextPath}/MainServlet" method="post">
                        <input type="hidden" name="action" value="logout">
                    </form>
                    <a href="#" id="logoutClickableText" class="sidebar-link">
                        <i class="bi bi-box-arrow-in-left"></i>
                        <span style="font-weight: bold;">Logout</span>
                    </a>
                </li>
            </ul>   
        </aside>

    
    <script src="${pageContext.request.contextPath}/SalesDashboard/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/SalesDashboard/salesScript.js"></script>

    </body>
</html>
