<%-- 
    Document   : InvoiceFunction
    Created on : Mar 9, 2025, 8:01:03 PM
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
            <div class="main p-3">
                <div class="text-center">
                    <h1>
                        Invoices
                    </h1>
                </div>
                <p>Work in progress</p>
            </div>
        </div> 
    </body>
</html>
