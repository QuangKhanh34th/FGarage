<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>FGarage</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <div class="text-center" style="background-color: #bbbcc2; padding:5px; margin-bottom: 15px;">
            <h1>FGarage</h1>
        </div>

        <div class="container w-25">
            <div class="col">
                <form action="MainServlet" method="post" accept-charset="UTF-8">
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
                    
                </form>
        </div>
    </div>


        <script src="loginScript.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
