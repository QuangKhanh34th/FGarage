<%-- 
    Document   : easterEgg
    Created on : Mar 13, 2025, 11:16:16 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OK Cô Vân</title>
    </head>
    <body style="overflow: hidden">
        <%--Header--%>
        <jsp:include page="header.jsp"/>

        <div class="wrapper">          
            <%--Side bar--%>
            <jsp:include page="sidebar.jsp"/>
            
            <%--Main content--%>
            <div class="main p-3 d-flex">
                <div class="container mt-5">
                    <h1 class="mb-4">Teacher Details</h1>
                    <c:if test="${not empty sessionScope.custInfo}">
                        <div class="row">
                            <div class="col-md-3">
                                <p><strong>Teacher ID:</strong> VanTTN</p>
                                <p><strong>Name:</strong> Thân Thị Ngọc Vân</p>
                                <p><strong>Phone:</strong> ???</p>
                                <p><strong>Address:</strong> ???</p>
                                <p><strong>Gender:</strong> Female (?)</p>
                            </div>
                            <div class="col-auto">
                                <img src="Lemo.png" alt="Customer Picture" class="img-fluid rounded" style="max-width: 200px; max-height: 200px;">
                            </div>
                        </div>
                    </c:if>               
                </div>
                        <div class="position-absolute bottom-0 d-flex align-content-center justify-content-center">
                            <p class=" col-6" style="color: red;font-weight: bold; text-align: justify">
                                Disclaimer: Việc cô nhìn thấy trang này nghĩa là cô đang tìm lỗi
                                qua những trường hợp cực kỳ hiếm khi gặp trong việc sử dụng bình thường.
                                Cơ mà lỗi này bọn em cũng đã lường trước rồi nên đây không phải là lỗi,
                                đây là tính năng :)
                            </p>
                        </div>        
            </div>
        </div>
    </body>
</html>
