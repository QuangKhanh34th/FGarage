<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sidebar</title>
    </head>
    <body>
        <aside class="sidebar">
            <div class="sidebar-logo">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/MechanicDashboard/home.jsp">
                    <img src="${pageContext.request.contextPath}/MechanicDashboard/garage.png" alt="FGarage Logo">
                    <span>FGarage</span>
                </a>
            </div>
            <ul class="sidebar-nav">
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/MainController?action=ViewAllTickets" class="sidebar-link">
                        <i class="bi bi-ticket-detailed"></i>
                        <span>Service Tickets</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="${pageContext.request.contextPath}/MainController?action=ViewAllServices" class="sidebar-link">
                        <i class="bi bi-wrench"></i>
                        <span>Manage Services</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <i class="bi bi-bell"></i>
                        <span>Notifications (WIP)</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link">
                        <i class="bi bi-gear"></i>
                        <span>Settings (WIP)</span>
                    </a>
                </li>
                <li class="sidebar-item">
            <form id="logoutForm" action="${pageContext.request.contextPath}/MainServlet" method="post">
                <input type="hidden" name="action" value="logout">
            </form>
            <a href="#" id="logoutClickableText" class="sidebar-link logout">
                <i class="bi bi-box-arrow-in-left"></i>
                <span>Logout</span>
            </a>
        </li>
            </ul>
        </aside>
    </body>
</html>