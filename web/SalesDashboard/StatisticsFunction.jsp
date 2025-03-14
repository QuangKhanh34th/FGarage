<%-- 
    Document   : StatisticsFunction
    Created on : Mar 3, 2025, 12:11:24 AM
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Dashboard</title>
        <link rel="stylesheet" href="style">
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
                        Sales Report
                    </h1>
                </div>
                <h2>Statistics of Cars Sold by Year</h2>
                <table>
                    <tr>
                        <th>Year</th>
                        <th>Number of Cars</th>
                    </tr>
                    <%
                        Map<Integer, Integer> carsSoldByYear = (Map<Integer, Integer>) request.getAttribute("carsSoldByYear");
                        if (carsSoldByYear != null && !carsSoldByYear.isEmpty()) {
                            for (Map.Entry<Integer, Integer> cars : carsSoldByYear.entrySet()) {
                    %>
                    <tr>
                        <td><%= cars.getKey()%></td>
                        <td><%= cars.getValue()%></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="2">No data available.</td>
                    </tr>
                    <%
                        }
                    %>
                </table>

                <h2>Best Selling Car Model</h2>
                <table>
                    <tr>
                        <th>Model</th>
                        <th>Total Sales</th>
                    </tr>

                    <%
                        ArrayList<String[]> modelBestSelling = (ArrayList<String[]>) request.getAttribute("modelBestSelling");
                        if (modelBestSelling != null && !modelBestSelling.isEmpty()) {
                            for (String[] model : modelBestSelling) {
                    %>
                    <tr>
                        <td><%= model[0]%></td> <!-- Model -->
                        <td><%= model[1]%></td> <!-- Total Sales -->
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr><td colspan="2">No data available.</td></tr>

                    <%
                        }
                    %>
                </table>

                <!-- Best Used Parts -->
                <h2>Best Used Parts</h2>
                <table>
                    <tr>
                        <th>Part Name</th>
                        <th>Usage </th>
                    </tr>
                    <%
                        // Lấy danh sách phụ tùng được sử dụng nhiều nhất (ArrayList<String>)
                        ArrayList<String[]> bestUsedParts = (ArrayList<String[]>) request.getAttribute("bestUsedParts");
                        if (bestUsedParts != null && !bestUsedParts.isEmpty()) {
                            for (String[] parts : bestUsedParts) {
                    %>

                    <tr>
                        <td><%= parts[0]%></td> <!---Part Name -->
                        <td><%= parts[1]%></td> <!--- Usage--->
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr><td colspan="2">No data available.</td></tr>

                    <%
                        }
                    %>
                </table>

                <!-- Top 3 Mechanics with Most Repairs -->
                <h2>Top 3 Mechanics with Most Repairs</h2>
                <table>
                    <tr>
                        <th>Mechanic</th>
                        <th>Repairs</th>
                    </tr>
                    <%   Map<String, Integer> topMechanics = (Map<String, Integer>) request.getAttribute("topMechanics");
                        if (topMechanics != null && !topMechanics.isEmpty()) {
                            for (Map.Entry<String, Integer> entry : topMechanics.entrySet()) {
                    %>
                    <tr>
                        <td><%= entry.getKey()%></td>
                        <td><%= entry.getValue()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>

                </body>
                </html>