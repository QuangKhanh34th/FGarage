<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/MechanicDashboard/style.css">
    </head>
    <body>
        <header>
            <nav class="navbar fixed-top">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/MechanicDashboard/home.jsp">
                    <img src="${pageContext.request.contextPath}/MechanicDashboard/logo.png" alt="Logo" class="logo">
                    Mechanic Dashboard
                </a>
                <form action="${pageContext.request.contextPath}/MainServlet" method="post">
                    <input type="hidden" name="action" value="logout">
                    <button type="submit" class="btn-logout">Logout</button>
                </form>
            </nav>
        </header>
    </body>
</html>