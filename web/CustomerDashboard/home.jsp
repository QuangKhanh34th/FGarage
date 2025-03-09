<%-- 
    Document   : home
    Created on : Mar 9, 2025, 5:45:07 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Dashboard</title>
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
                        Welcome back, ${sessionScope.customer.custName}
                        </h1>
                        <p>Please choose one of the following functions on the sidebar to begin</p>
                        <p class="text-muted">----- or -----</p>
                        <form action="${pageContext.request.contextPath}/MainServlet" method="post">
                            <input type="hidden" name="action" value="logout">
                            <button 
                                type="submit"
                                class="btn btn-secondary my-2 my-sm-0">
                            Logout
                            </button>
                        </form>
                </div>  
            </div>
        </div>
    </body>
</html>
