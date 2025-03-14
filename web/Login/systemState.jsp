<%-- 
    Document   : error
    Created on : Mar 2, 2025, 11:07:17 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uh oh, something wrong</title>
        <style>
            body {
    margin: 0; /* Remove default body margins */
    padding: 0; /* Remove default body padding */
    background-image: url('error.png'); /* Replace with your image path */
    background-size: cover; /* Cover the entire viewport */
    background-repeat: no-repeat; /* Prevent image repetition */
    background-position: center center; /* Center the image */
    height: 100vh; /* Set body height to 100% of viewport height */
}
        </style>
    </head>
    <body>
        <p>i</p>
    <script type="text/javascript">
        setTimeout(function() {
            window.location.href = "<%= request.getContextPath() %>/MainServlet?action=renderView";
        }, 3000); // 5000 milliseconds = 5 seconds
    </script>
</body>
</html>
