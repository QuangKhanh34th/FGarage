<%-- 
    Document   : index
    Created on : Mar 5, 2025, 5:43:43 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FGarage</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/SalesDashboard/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Login/mystyle.css">
    </head>
    <body>
<div class="text-center" style="background-color: #bbbcc2; padding:5px; margin-bottom: 15px;">
            <h1>FGarage</h1>
        </div>

        <div class="container w-25">
            <div class="col">
                <form action="MainServlet" method="get" accept-charset="utf-8">
                    <div class="mb-3">
                        <label for="target">Choose a login method</label>
                        <select class="form-select" name="target" id="target">
                            <option value="">Select...</option>
                            <option value="customer">Customer</option>
                            <option value="sales">Sales</option>
                            <option value="mechanic">Mechanic</option>
                        </select>
                    </div>
                    
                    <div id="customer" class="inv">
                        <div class="form-floating mb-3">
                            <input class="form-control" id="cusName" name="cusName" placeholder="Full name" type="text" />
                            <label for="cusName">Full Name</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input class="form-control" id="phone" name="cusPhone" placeholder="Phone number" type="text" />
                            <label for="phone">Phone number</label>
                        </div>

                        <div class="text-center">
                            <input class="btn btn-secondary" type="submit" name="action" value="Login">
                        </div>
                    </div>

                    <div id="sales" class="inv">
                        <div class="form-floating mb-3">
                            <input class="form-control" id="salesName" name="salesName" placeholder="Full name" type="text" />
                            <label for="salesName">Full Name</label>
                        </div>

                        <div class="text-center">
                            <input class="btn btn-secondary" type="submit" name="action" value="Login">
                        </div>
                    </div>

                    <div id="mechanic" class="inv">
                        <div class="form-floating mb-3">
                            <input class="form-control" id="mechanicName" name="mechanicName" placeholder="Full name" type="text" />
                            <label for="mechanicName">Full Name</label>
                        </div>
                        
                        <div class="text-center">
                            <input class="btn btn-secondary" type="submit" name="action" value="Login">
                        </div>
                    </div>
                    <p id="error" style="color: red; font-weight: bold">${requestScope.error}</p>
                </form>
            </div>
        </div>


        <script src="${pageContext.request.contextPath}/Login/loginScript.js"></script>
        <script src="${pageContext.request.contextPath}/SalesDashboard/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
