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
        <style>
            .sidebar {
                width: 250px;
                background: #34495e;
                color: white;
                min-height: 100vh;
                padding: 20px;
                position: fixed;
                top: 60px;
                left: 0;
                transition: all 0.3s;
                display: flex;
                flex-direction: column;
            }

            .sidebar-logo {
                font-size: 1.6rem;
                font-weight: bold;
                text-align: center;
                margin-top: 20px;
                margin-bottom: 20px;
                color: white;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .sidebar-logo img {
                width: 24px;
                height: 24px;
                margin-right: 8px;
            }

            .sidebar-nav {
                list-style: none;
                padding: 0;
                width: 100%;
            }

            .sidebar-item {
                margin: 10px 0;
                text-align: center;
            }

            .sidebar-link {
                color: white;
                text-decoration: none;
                font-size: 16px;
                padding: 12px;
                display: block;
                border-radius: 5px;
                transition: background 0.3s ease;
            }

            .sidebar-link:hover {
                background: #5d6d
            }

            .sidebar-link.logout:hover {
                background-color: #c0392b; /* Màu đỏ khi hover */
                color: white; /* Màu chữ trắng khi hover */
            }
        </style>

    </head>
    <body>
        <aside id="sidebar">
            <div class="sidebar-logo">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/MechanicDashboard/home.jsp">
                    <img src="${pageContext.request.contextPath}/MechanicDashboard/garage.png" alt="FGarage Logo">
                    <span>FGarage</span>
                </a>
            </div>
            <ul class="sidebar-nav">
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/MainController?action=CustomerTicket" class="sidebar-link">
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
                    <a href="${pageContext.request.contextPath}/MainController?action=ChangeProfile" class="sidebar-link">
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
    </body>
</html>
