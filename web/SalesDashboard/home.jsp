<%-- 
    Document   : home
    Created on : Mar 4, 2025, 7:12:14 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="main p-3 d-flex">
                    <div class="text-center mx-auto my-auto">
                        <h1>
                        Welcome back, ${sessionScope.sales.salesName}
                        </h1>
                        <p>Please choose one of the following functions on the sidebar to begin</p>
                        <p class="text-muted">----- or -----</p>
                        <a href="#">Log out</a>
                </div>  
            </div>
        </div>
    </body>
</html>
