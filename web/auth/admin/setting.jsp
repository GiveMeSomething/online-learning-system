<%--
    Document   : setting
    Created on : Jun 5, 2021
    Author     : Dinh Kong Thanh
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="style/setting.css">
    </head>
    <body>
        <div id="mySidebar" class="sidebar">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="#" onclick="itOn()">User Roles</a>
            <hr>
            <a href="#" onclick="businessOn()">System Menu</a>
            <hr>
            <a href="#" >Post Categories</a>
            <hr>
            <a href="#" >Subject Categories</a>
            <hr>
            <a href="#" >Test Types</a>
            <hr>
            <a href="#" >Question Levels</a>
            <hr>
            <a href="#" >Lesson Types</a>
            <hr>
            <a href="#" >Subject Dimension</a>
        </div>

        <div id="main">
            <button class="openbtn" onclick="openNav()">&#9776; Open Sidebar</button>

            <div id="it" style="display: block; margin-top: -2rem">
                <jsp:include page="components/home/it.jsp"/>
            </div>
            
            <div id="business" style="display: none; margin-top: -2rem">
                <jsp:include page="components/home/business.jsp"/>
            </div>
        </div>


        <!--                business-->


        <script>
            function itOn() {
                var it = document.getElementById('it');
                var business = document.getElementById('business');


                it.style.display = 'block';
                business.style.display = 'none';

            }
            function businessOn() {
                var it = document.getElementById('it');
                var business = document.getElementById('business');


                it.style.display = 'none';
                business.style.display = 'block';

            }



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
            function myAccFunc() {
                var x = document.getElementById("demoAcc");
                if (x.className.indexOf("w3-show") == -1) {
                    x.className += " w3-show";
                    x.previousElementSibling.className += " w3-black";
                } else {
                    x.className = x.className.replace(" w3-show", "");
                    x.previousElementSibling.className =
                            x.previousElementSibling.className.replace(" w3-black", "");
                }
            }

            function myDropFunc() {
                var x = document.getElementById("demoDrop");
                if (x.className.indexOf("w3-show") == -1) {
                    x.className += " w3-show";
                    x.previousElementSibling.className += " w3-black";
                } else {
                    x.className = x.className.replace(" w3-show", "");
                    x.previousElementSibling.className =
                            x.previousElementSibling.className.replace(" w3-black", "");
                }
            }
        </script>
    </body>
</html>