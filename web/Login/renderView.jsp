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
    <body>
        <%--Header--%>
        <jsp:include page="header.jsp"/>

        <div class="wrapper">          
            <%--Side bar--%>
            <jsp:include page="sidebar.jsp"/>
            
            <%--Main content--%>
            <div class="main p-3 d-flex" style="background-image: url('Lemo.png'); background-size: 150px 150px;">
                <div class="container mt-5 d-flex justify-content-center">
                    <c:if test="${not empty sessionScope.custInfo}">
                        <div class="row">
                            <div class="col-md-3 justify-content-center align-content-center">
                                <h1 class="mb-4">Teacher Details</h1>
                                <p><strong>Teacher ID:</strong> VanTTN</p>
                                <p><strong>Name:</strong> Thân Thị Ngọc Vân</p>
                                <p><strong>Phone:</strong> ???</p>
                                <p><strong>Address:</strong> ???</p>
                                <p><strong>Gender:</strong> Female (?)</p>
                            </div>
                            <div class="col-md-9 justify-content-center">
                                <video controls autoplay width="640" height="360">
                                    <source src="Rick-Roll.mp4" type="video/mp4">
                                    Your browser does not support the video tag.
                                </video>
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
