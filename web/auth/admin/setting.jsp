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
        <title>Setting Page</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="${path}/style/setting.css">
    </head>
    <body>
        <div id="mySidebar" class="sidebar">
            <!--<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>-->
            <a href="#" onclick="userRoleOn()">User Roles</a>
            <hr>
            <a href="${path}/auth/admin/dashboard" onclick="systemMenuOn()">Dashboard</a>
            <hr>
            <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST" onclick="postCategoryOn()">Post Categories</a>
            <hr>
            <a href="${path}/auth/teacher/subject" onclick="subjectCategoryOn()">Subject Categories</a>
            <hr>
            <a href="${path}/auth/admin/slider" onclick="testTypeOn()">Slider</a>
            <hr>
            <a href="#" onclick="questionLevelOn()">Question Levels</a>
            <hr>
            <a href="#" onclick="lessonTypeOn()">Lesson Types</a>
            <hr>
            <a href="#" onclick="subjectDimensionOn()">Subject Dimension</a>
        </div>
        <div id="main">
            <button id="openNav" class="openbtn" onclick="openNav()">&#9776; Open Sidebar</button>
            <button id="closeNav" class="openbtn" onclick="closeNav()" style="display: none">&#9776; Close Sidebar</button>
            <div id="userRole" style="display: block">
                <!--                                <h1>Welcome to user role</h1>
                                                <p>User Role's content</p>-->
                
            </div>            
            <div id="systemMenu" style="display: none">
<!--                <h1>Welcome to System Menu</h1>
                <p>System Menu's content</p>-->
            </div>          
            <div id="postCategory" style="display: none">
                <h1>Welcome to Post Category</h1>
                <p>Post Category's content</p>
            </div>           
            <div id="subjectCategory" style="display: none">
                <h1>Welcome to Subject Category</h1>
                <p>Subject Category's content</p>
            </div>           
            <div id="testType" style="display: none">
                <h1>Welcome to Test Type</h1>
                <p>Test Type's content</p>
            </div>          
            <div id="questionLevel" style="display: none">
                <h1>Welcome to Question Level</h1>
                <p>Question Level's content</p>
            </div>            
            <div id="lessonType" style="display: none">
                <h1>Welcome to Lesson Type</h1>
                <p>Lesson Type's content</p>
            </div>         
            <div id="subjectDimension" style="display: none">
                <h1>Welcome to Subject Dimension</h1>
                <p>Subject Dimension's content</p>
            </div>
        </div>
        <script>
            function  userRoleOn() {
                var userRole = document.getElementById('userRole');
                var systemMenu = document.getElementById('systemMenu');
                var postCategory = document.getElementById('postCategory');
                var subjectCategory = document.getElementById('subjectCategory');
                var testType = document.getElementById('testType');
                var questionLevel = document.getElementById('questionLevel');
                var lessonType = document.getElementById('lessonType');
                var subjectDimension = document.getElementById('subjectDimension');

                userRole.style.display = 'block';
                systemMenu.style.display = 'none';
                postCategory.style.display = 'none';
                subjectCategory.style.display = 'none';
                testType.style.display = 'none';
                questionLevel.style.display = 'none';
                lessonType.style.display = 'none';
                subjectDimension.style.display = 'none';
            }

            function  systemMenuOn() {
                var userRole = document.getElementById('userRole');
                var systemMenu = document.getElementById('systemMenu');
                var postCategory = document.getElementById('postCategory');
                var subjectCategory = document.getElementById('subjectCategory');
                var testType = document.getElementById('testType');
                var questionLevel = document.getElementById('questionLevel');
                var lessonType = document.getElementById('lessonType');
                var subjectDimension = document.getElementById('subjectDimension');

                userRole.style.display = 'none';
                systemMenu.style.display = 'block';
                postCategory.style.display = 'none';
                subjectCategory.style.display = 'none';
                testType.style.display = 'none';
                questionLevel.style.display = 'none';
                lessonType.style.display = 'none';
                subjectDimension.style.display = 'none';
            }

            function  postCategoryOn() {
                var userRole = document.getElementById('userRole');
                var systemMenu = document.getElementById('systemMenu');
                var postCategory = document.getElementById('postCategory');
                var subjectCategory = document.getElementById('subjectCategory');
                var testType = document.getElementById('testType');
                var questionLevel = document.getElementById('questionLevel');
                var lessonType = document.getElementById('lessonType');
                var subjectDimension = document.getElementById('subjectDimension');

                userRole.style.display = 'none';
                systemMenu.style.display = 'none';
                postCategory.style.display = 'block';
                subjectCategory.style.display = 'none';
                testType.style.display = 'none';
                questionLevel.style.display = 'none';
                lessonType.style.display = 'none';
                subjectDimension.style.display = 'none';
            }

            function  subjectCategoryOn() {
                var userRole = document.getElementById('userRole');
                var systemMenu = document.getElementById('systemMenu');
                var postCategory = document.getElementById('postCategory');
                var subjectCategory = document.getElementById('subjectCategory');
                var testType = document.getElementById('testType');
                var questionLevel = document.getElementById('questionLevel');
                var lessonType = document.getElementById('lessonType');
                var subjectDimension = document.getElementById('subjectDimension');

                userRole.style.display = 'none';
                systemMenu.style.display = 'none';
                postCategory.style.display = 'none';
                subjectCategory.style.display = 'block';
                testType.style.display = 'none';
                questionLevel.style.display = 'none';
                lessonType.style.display = 'none';
                subjectDimension.style.display = 'none';
            }

            function  testTypeOn() {
                var userRole = document.getElementById('userRole');
                var systemMenu = document.getElementById('systemMenu');
                var postCategory = document.getElementById('postCategory');
                var subjectCategory = document.getElementById('subjectCategory');
                var testType = document.getElementById('testType');
                var questionLevel = document.getElementById('questionLevel');
                var lessonType = document.getElementById('lessonType');
                var subjectDimension = document.getElementById('subjectDimension');

                userRole.style.display = 'none';
                systemMenu.style.display = 'none';
                postCategory.style.display = 'none';
                subjectCategory.style.display = 'none';
                testType.style.display = 'block';
                questionLevel.style.display = 'none';
                lessonType.style.display = 'none';
                subjectDimension.style.display = 'none';
            }

            function  questionLevelOn() {
                var userRole = document.getElementById('userRole');
                var systemMenu = document.getElementById('systemMenu');
                var postCategory = document.getElementById('postCategory');
                var subjectCategory = document.getElementById('subjectCategory');
                var testType = document.getElementById('testType');
                var questionLevel = document.getElementById('questionLevel');
                var lessonType = document.getElementById('lessonType');
                var subjectDimension = document.getElementById('subjectDimension');

                userRole.style.display = 'none';
                systemMenu.style.display = 'none';
                postCategory.style.display = 'none';
                subjectCategory.style.display = 'none';
                testType.style.display = 'none';
                questionLevel.style.display = 'block';
                lessonType.style.display = 'none';
                subjectDimension.style.display = 'none';
            }

            function  lessonTypeOn() {
                var userRole = document.getElementById('userRole');
                var systemMenu = document.getElementById('systemMenu');
                var postCategory = document.getElementById('postCategory');
                var subjectCategory = document.getElementById('subjectCategory');
                var testType = document.getElementById('testType');
                var questionLevel = document.getElementById('questionLevel');
                var lessonType = document.getElementById('lessonType');
                var subjectDimension = document.getElementById('subjectDimension');

                userRole.style.display = 'none';
                systemMenu.style.display = 'none';
                postCategory.style.display = 'none';
                subjectCategory.style.display = 'none';
                testType.style.display = 'none';
                questionLevel.style.display = 'none';
                lessonType.style.display = 'block';
                subjectDimension.style.display = 'none';
            }

            function  subjectDimensionOn() {
                var userRole = document.getElementById('userRole');
                var systemMenu = document.getElementById('systemMenu');
                var postCategory = document.getElementById('postCategory');
                var subjectCategory = document.getElementById('subjectCategory');
                var testType = document.getElementById('testType');
                var questionLevel = document.getElementById('questionLevel');
                var lessonType = document.getElementById('lessonType');
                var subjectDimension = document.getElementById('subjectDimension');

                userRole.style.display = 'none';
                systemMenu.style.display = 'none';
                postCategory.style.display = 'none';
                subjectCategory.style.display = 'none';
                testType.style.display = 'none';
                questionLevel.style.display = 'none';
                lessonType.style.display = 'none';
                subjectDimension.style.display = 'block';
            }


            /* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
            function openNav() {
                document.getElementById("openNav").style.display = "none";
                document.getElementById("closeNav").style.display = "block";
                document.getElementById("mySidebar").style.width = "250px";
                document.getElementById("main").style.marginLeft = "250px";
            }

            /* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
            function closeNav() {
                document.getElementById("openNav").style.display = "block";
                document.getElementById("closeNav").style.display = "none";
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