<%-- 
    Document   : error
    Created on : Mar 8, 2025, 8:20:15 PM
    Author     : Horwlt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1 style="color: red;">An error has occurred!</h1>

        <c:if test="${not empty requestScope.ERROR}">
            <h3 style="color: darkred;">Error Message:</h3>
            <p>${requestScope.ERROR}</p>
        </c:if>

    </body>
</html>
