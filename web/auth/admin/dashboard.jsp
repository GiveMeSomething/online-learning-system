<%--
    Document   : admin-dashboard
    Created on : Jun 6, 2021
    Author     : Hoang Tien Minh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DashBoard</title>
        <!--        Bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${path}/style/styles.css">
        <link rel="stylesheet" type="text/css" href="${path}/style/setting.css">
    </head>
    <body>
        <c:if test="${sessionScope.isTeacher != true}">
            <div id="mySidebar" class="sidebar">
                <button id="closeNav" class="openbtn" onclick="closeNav()" style="display: none; margin-top: -4rem; margin-bottom: 2rem; margin-left: 13rem"><span style="text-transform: uppercase">X</span></button>
                <!--<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>-->
                <a href="${path}/auth/admin" onclick="userRoleOn()">User List</a>
                <hr>
                <a href="${path}/auth/admin/dashboard" style="background: white; color: black">Dashboard</a>
                <hr>
                <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST" >Post List</a>
                <hr>
                <a href="${path}/auth/teacher/subject">Subject List</a>
                <hr>
                <a href="${path}/auth/admin/slider">Slider List</a>
                <hr>
                <a href="${path}/auth/teacher/registration?operation=VIEWALL">Registration List</a>
            </div>
        </c:if>
        <div style="background:#FFFFF0; display:flex;" class="py-2">
            <div>
                <button id="openNav" class="openbtn" onclick="openNav()" style="background: #FFFFF0; color: black">&#9776;</button>  
            </div>
            <div style="justify-content: center; margin-left: 37rem">
                <a class="navbar-brand" style="font-size: 2.5rem;" href="${path}/home">
                    <span style="color:blue">O</span>
                    <span style="color:orange">L</span>
                    <span style="color:green">S</span>
                </a> 
            </div>
            <div style="margin-left: 30rem; margin-top: 1rem">
                <a href="${path}/authenticate?operation=LOGOUT" style="padding-bottom: 5px; padding-top: 5px; border-bottom: 1px solid lightgray">
                    <button class="btn btn-secondary">Log out</button>
                </a>
            </div>
        </div>
        <div class="container container-fluid" style="margin-bottom: 1.5rem">
            <div class="row">
                <div class="d-flex justify-content-center align-items-center">
                    <h2 style="margin-left: 1rem">Dashboard</h2>
                </div>
            </div>

            <div id="total-section" class="d-flex" style="margin-bottom: 1.5rem; ">
                <div id="total-course" 
                     class="d-flex" 
                     style="width: max-content; border-radius: 5px; height: 7.2rem; background: white;  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);margin-right: 2rem">
                    <div style="width: max-content; height: max-content; margin-top: 1.5rem; margin-left: 1rem; border-radius: 10px; background: #EC6853">
                        <i class="fas fa-chalkboard-teacher fa-2x" style="padding: 1rem 1rem"></i>
                    </div>
                    <div style="margin-top: .2rem; padding: 0 2rem 0 1rem">
                        <div style="font-weight: bold">
                            <span style="font-size: 2rem">${totalCourseNow}</span><br>
                            <span style=" font-weight: normal; font-size: 1rem;">
                                Total Course
                            </span>
                        </div>
                        <div style="font-size: .8rem">
                            <span style="font-weight: bold; font-size: 1rem">+${newCourse}</span> new course
                        </div> 
                    </div>
                </div>
                <div id="total-registration" 
                     class="d-flex" 
                     style="width: max-content; border-radius: 5px; height: 7.2rem; background: white;  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);margin-right: 2rem">
                    <div style="width: max-content; height: max-content; margin-top: 1.5rem; margin-left: 1rem; border-radius: 10px; background: #6F6EB8">
                        <i class="fas fa-chart-line fa-2x" style="padding: 1rem 1rem"></i></i>
                    </div>
                    <div style="margin-top: 1rem; padding: 0 2rem 0 1rem">
                        <div>
                            <span style="font-size: 1rem; font-weight: bold">+${cancelRegistration}</span> 
                            <span style="font-weight: normal">New Cancel</span>
                        </div>
                        <div>
                            <span style="font-size: 1rem; font-weight: bold">+${submittedRegistration}</span> 
                            <span style="font-weight: normal">New Submit</span>
                        </div>
                        <div>
                            <span style="font-size: 1rem; font-weight: bold">+${successfulRegistration}</span> 
                            <span style="font-weight: normal">New Success</span>
                        </div>
                    </div>
                </div>
                <div id="total-profit" 
                     class="d-flex" 
                     style="width: max-content; border-radius: 5px; height: 7.2rem; background: white;  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)">
                    <div style="width: max-content; height: max-content; margin-top: 1.5rem; margin-left: 1rem; border-radius: 10px; background: #4EAEE3">
                        <i class="fas fa-dollar-sign fa-2x" style="padding: 1rem 1.5rem"></i>
                    </div>
                    <div style="margin-top: 1rem; padding: 0 2rem 0 1rem">
                        <div style="font-weight: bold">
                            <span style="font-size: 2rem; margin-left: -.2rem">$${totalProfit}</span><br>
                            <span style=" font-weight: normal; font-size: 1rem;">
                                Total Revenue
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div id="chart-section" class="d-flex" style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); border-radius: 5px; ">
                <div id="curve_chart" style="width: 55rem; height: 22rem; "></div>    
                <div id="piechart" style="width: 35rem; height: 22rem;"></div>
            </div>

        </div>

    </body>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
                    google.charts.load('current', {'packages': ['corechart']});
                    google.charts.setOnLoadCallback(drawChartLine);
                    google.charts.setOnLoadCallback(drawChartPie);

                    function drawChartLine() {
                        const today = new Date()
                        const todayMinusOne = new Date(today);
                        const todayMinusTwo = new Date(today);
                        const todayMinusThree = new Date(today);
                        const todayMinusFour = new Date(today);
                        const todayMinusFive = new Date(today);
                        const todayMinusSix = new Date(today);

                        todayMinusOne.setDate(todayMinusOne.getDate() - 1);
                        todayMinusTwo.setDate(todayMinusTwo.getDate() - 2);
                        todayMinusThree.setDate(todayMinusThree.getDate() - 3);
                        todayMinusFour.setDate(todayMinusFour.getDate() - 4);
                        todayMinusFive.setDate(todayMinusFive.getDate() - 5);
                        todayMinusSix.setDate(todayMinusSix.getDate() - 6);



                        var data = google.visualization.arrayToDataTable([
                            ['Year', 'All', 'Success'],
                            [todayMinusSix.toLocaleDateString(), ${totalRegistrationBefore6Day}, ${successBefore6Day}],
                            [todayMinusFive.toLocaleDateString(), ${totalRegistrationBefore5Day}, ${successBefore5Day}],
                            [todayMinusFour.toLocaleDateString(), ${totalRegistrationBefore4Day}, ${successBefore4Day}],
                            [todayMinusThree.toLocaleDateString(), ${totalRegistrationBefore3Day}, ${successBefore3Day}],
                            [todayMinusTwo.toLocaleDateString(), ${totalRegistrationBefore2Day}, ${successBefore2Day}],
                            [todayMinusOne.toLocaleDateString(), ${totalRegistrationBefore1Day}, ${successBefore1Day}],
                            [today.toLocaleDateString(), ${totalRegistrationNow}, ${successNow}],
                        ]);

                        var options = {
                            title: 'Registration Graph',
                            curveType: 'function',
                            legend: {position: 'right'}
                        };

                        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                        chart.draw(data, options);
                    }

                    // Draw the chart and set the chart values
                    function drawChartPie() {
                        var data = google.visualization.arrayToDataTable([
                            ['Category', 'Profit'],
                            ['Software Engineering', ${softwareEngineering}],
                            ['Economy', ${economy}],
                            ['Digital Marketing', ${digitalMarketing}],
                            ['Artificial Intelligence', ${artificialIntelligence}],
                            ['Information Assurance', ${informationAssurance}],
                            ['Language', ${language}]
                        ]);

                        // Optional; add a title and set the width and height of the chart
                        var options = {'title': 'Subject Profit($)'};

                        // Display the chart inside the <div> element with id="piechart"
                        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                        chart.draw(data, options);
                    }
    </script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
            crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="${path}/utilities/tree-module.js"></script>
</html>
