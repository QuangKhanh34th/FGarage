<%-- 
    Document   : CustomerFunction
    Created on : Mar 3, 2025, 12:10:04 AM
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
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
            <div class="main p-3">
                <div class="text-center">
                    <h1>
                        Customer Management
                    </h1>
                    <%--Manage Customer container--%>
                    <div>
                        <%--Search and add function--%>
                        <div class="row gy-2 gx-3 mb-2 align-items-center d-flex justify-content-between">
                            <%--search bar for the below table--%>
                            <div class="col-auto">
                                <form class="d-flex align-items-center">
                                    <div class="me-2">
                                        <input type="password" class="form-control" id="customerSearch" placeholder="Customer name">
                                    </div>
                                    
                                    <button type="submit" class="btn btn-secondary">Search</button>                          
                                </form>
                            </div>
                            
                            <%--Add customer button--%>
                            <div class="col-auto">
                                <button class="btn btn-secondary">
                                    <i class="bi-plus-square"></i> Add Customer
                                </button>  
                            </div>
                        </div>
                        
                        <%--Table for display data--%>
                        <table class="table table-striped table-hover"> 
                            <thead>
                                <tr class="table-secondary">
                                 <th><b>#</b></th>    
                                 <th><b>Name</b></th> 
                                 <th><b>Phone number</b></th> 
                                 <th><b>Address</b></th>
                                </tr> 
                            </thead>
                            <%-- Fetching the attributes from the session
                                 which was previously set by the servlet  
                                  "getAllCustomerServlet.java" 
                            --%>
                            <tbody class="table-group-divider"> 
                            <%
                                ArrayList<Customer> customerList = (ArrayList<Customer>)session.getAttribute("customerList");
                                int count=1;
                                if (customerList == null) {
                                    request.getRequestDispatcher("/MainServlet?action=getCustList").forward(request, response);
                                    return;
                                }
                                for(Customer customer: customerList)
                            {%> 
                            <%-- Arranging data in tabular form 
                            --%>

                               <tr>
                                   <td><%=count%></td> 
                                   <td><a href="#"><%=customer.getCustName()%></a></td> 
                                   <td><%=customer.getPhone()%></td> 
                                   <td><%=customer.getCusAddress()%></td> 
                               </tr> 
                               <%count++;}%> 
                            </tbody> 
                        </table>
                    </div>
                    
                </div>
                
            </div>
        </div>
    </body>
</html>
