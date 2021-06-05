<%--
    Document   : setting
    Created on : Jun 5, 2021
    Author     : Dinh Kong Thanh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style/setting.css">
    </head>
    <body>
        <div id="mySidebar" class="sidebar">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="#">About</a>
            <a href="#">Services</a>
            <a href="#">Clients</a>
            <a href="#">Contact</a>
        </div>

        <div id="main">
            <button class="openbtn" onclick="openNav()">&#9776; Open Sidebar</button>
            <jsp:include page="components/global/navbar.jsp"></jsp:include> 
                <h2>Collapsed Sidebar</h2>
                <p>Content...</p>
            <jsp:include page="components/global/footer.jsp"></jsp:include>
        </div>

        <script>
            /* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
            function openNav() {
                document.getElementById("mySidebar").style.width = "250px";
                document.getElementById("main").style.marginLeft = "250px";
            }

            /* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
            function closeNav() {
                document.getElementById("mySidebar").style.width = "0";
                document.getElementById("main").style.marginLeft = "0";
            }
        </script>
    </body>
</html>