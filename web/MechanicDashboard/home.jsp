<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mechanic Dashboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/MechanicDashboard/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>
    <body>
        <%-- Header --%>
        <jsp:include page="header.jsp"/>

        <div class="wrapper">
            <%-- Sidebar --%>
            <jsp:include page="sidebar.jsp"/>

            <%-- Main content --%>
            <div class="main">
                <div class="container-box">
                    <h1 class="welcome-text">Welcome back, ${sessionScope.mechanic.mechanicName}! 👋</h1>
                    <p class="text-muted">Manage your services efficiently</p>
                    <hr>
                    <hr>
                    <p>Please choose one of the functions on the sidebar to begin.</p>
                    <p class="text-muted">----- or -----</p>

                    <%-- Logout Button --%>
                    <form action="${pageContext.request.contextPath}/MainServlet" method="post">
                        <input type="hidden" name="action" value="logout">
                        <button type="submit" class="btn-logout">
                            Logout
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>