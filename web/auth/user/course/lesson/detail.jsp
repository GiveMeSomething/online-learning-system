<%--
    Document   : lessonDetail
    Created on : Jun 28, 2021
    Author     : Nguyen Khanh Toan
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lesson Detail</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="${path}/style/lesson.css">
        <style>
            #sidenav a.active{
                background-color: rgb(182,285,297)
            }
            .lessonDiv.active{
                background-color: rgb(182,285,297)
            }

            .sidebarMini .sidebarMini__content .switch {
                width: 50px;
                height: 25px;
                margin: 0 10px;
                position: relative;
            }
            .sidebarMini .sidebarMini__content .switch input {
                display: none;
            }
            .sidebarMini .sidebarMini__content .switch input:checked + .slider::before {
                transform: translateX(22px);
            }
            .sidebarMini .sidebarMini__content .switch .slider {
                background-color: #cccccc;
                position: absolute;
                top: 0;
                right: 0;
                left: 0;
                bottom: 0;
                border-radius: 34px;
                cursor: pointer;
            }
            .sidebarMini .sidebarMini__content .switch .slider::before {
                content: "";
                width: 18px;
                height: 18px;
                background-color: #fff;
                border-radius: 50%;
                display: inline-block;
                position: absolute;
                left: 4px;
                bottom: 4px;
                transition: 0.5s;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <button class="mt-3" style="width:40px;height:40px;background-color: black;border:1px solid black;color:white" class="w3-button w3-teal w3-xlarge right mr-3" onclick="w3_open()">☰</button>
            <div class="row">
                <div class="col-3" style="padding:0">
                    <div class="w3-sidebar w3-collapse" id="sidenav" style="top: 118px;">
                        <div id="leftmenuinner" style="padding-left:0">
                            <div id="leftmenuinnerinner" style="background-color:white;">
                                <!--<a href='javascript:void(0)' onclick='close_menu()' class='w3-button w3-hide-large w3-large w3-display-topright' style='right:16px;padding:3px 12px;font-weight:bold;'>&times;</a>-->
                                <div class="d-flex align-items-center justify-content-between">
                                    <h2 class="left">Course content</h2>
                                    <button class="mr-2" onclick="w3_close()" style="background-color: black;color:white;width:28px;height:28px;border-radius: 50%;text-align: center;align-items: center;line-height:0px;font-size: 20px" class="w3-bar-item w3-large">&times;</button>

                                </div>
                                <hr/>
                                <c:forEach items="${lessonList}" var="o">
                                    <a style="height:60px;color:black;font-size:20px"
                                       class="${lessonId == o.id ? "active":""}"
                                       target="_top"
                                       href="${path}/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&lessonId=${o.id}&&courseId=${sessionScope.courseId}">
                                        <div class="d-flex justify-content-between align-items-center">
                                            ${o.id}. ${o.name}
                                            <input ${o.status == 'ACTIVE' ? "checked":""} value="${o.status}${o.id}" name="doneLesson" class="mr-3" type="checkbox" style="width:18px;height:18px"/>
                                        </div>
                                        <c:if test="${(o.id < 9 || o.id == 9) && o.id % 4 == 1}">
                                            <small id="lesson1"></small>
                                        </c:if>
                                        <c:if test="${(o.id < 9 || o.id == 9) && o.id % 4 == 2}">
                                            <small id="lesson2"></small>
                                        </c:if>
                                        <c:if test="${(o.id < 9 || o.id == 9) && o.id % 4 == 3}">
                                            <small id="lesson3"></small>
                                        </c:if> 
                                        <c:if test="${(o.id > 9) && o.id % 4 == 2}">
                                            <small id="lesson1"></small>
                                        </c:if>
                                        <c:if test="${(o.id > 9) && o.id % 4 == 3}">
                                            <small id="lesson2"></small>
                                        </c:if>
                                        <c:if test="${(o.id > 9) && o.id % 4 == 0}">
                                            <small id="lesson3"></small>
                                        </c:if> 
                                    </a>


                                </c:forEach>
                            </div>
                        </div>
                    </div>

                </div>

                <c:if test="${lessonDetail.lessonType != 'QUIZ'}">
                    <div class="col-9 text-left" style="margin-top:-3%"
                         id="lessonRight">
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active"
                                   style="color:black"
                                   id="home-tab"
                                   data-toggle="tab"
                                   href="#home"
                                   role="tab" 
                                   aria-controls="home"
                                   aria-selected="true">
                                    Video
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"
                                   style="color:black"
                                   id="profile-tab"
                                   data-toggle="tab"
                                   href="#profile"
                                   role="tab"
                                   aria-controls="profile"
                                   aria-selected="false">
                                    Content
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content mt-3 mb-3" id="myTabContent">
                            <div class="tab-pane fade show active"
                                 id="home"
                                 role="tabpanel"
                                 aria-labelledby="home-tab">
                                <iframe width="800" height="500"
                                        id="myVideo"
                                        src="${lessonDetail.videoLink}"
                                        title="YouTube video player" 
                                        frameborder="0"
                                        allow="accelerometer;autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                        allowfullscreen
                                        ></iframe>

                                <div class="d-flex mt-3 align-items-center" style="justify-content: flex-start">
                                    <a href="${path}/auth/user/course/lesson?operation=PREVIOUSLESSON&&lessonId=${sessionScope.lessonIdSession - 1}&&courseId=${sessionScope.courseId}">
                                        <button ${disabled} ${sessionScope.lessonIdSession == minIdLesson ? "disabled":""} style="background-color: #f6a208;color:white" class="btn mr-3">
                                            < Previous lesson
                                        </button>
                                    </a>
                                    <a href="${path}/auth/user/course/lesson?operation=NEXTLESSON&&lessonId=${sessionScope.lessonIdSession + 1}&&courseId=${sessionScope.courseId}">
                                        <button ${disabledNext} ${sessionScope.lessonIdSession == maxIdLesson ? "disabled":""} style="background-color: #f6a208;color:white" class="btn">
                                            Next lesson >
                                        </button> 
                                    </a>
                                    <div class="sidebarMini ml-5">
                                        <div class="sidebarMini__content">
                                            <div class="d-flex">
                                                <span class="font-weight-bold">Autoplay</span>
                                                <label id="switch" class="switch" for="checkbox">
                                                    <input type="checkbox" name="" id="checkbox">
                                                    <div class="slider"></div>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="tab-pane fade"
                                 id="profile"
                                 role="tabpanel"
                                 style="padding-right:20%"
                                 aria-labelledby="profile-tab">
                                ${lessonDetail.htmlContent}
                                <div class="d-flex mt-3 align-items-center" style="justify-content: flex-start">
                                    <a href="${path}/auth/user/course/lesson?operation=PREVIOUSLESSON&&lessonId=${sessionScope.lessonIdSession - 1}">
                                        <button ${disabled} ${sessionScope.lessonIdSession == minIdLesson ? "disabled":""} style="background-color: #f6a208;color:white" class="btn mr-3">
                                            < Previous lesson
                                        </button>
                                    </a>
                                    <a href="${path}/auth/user/course/lesson?operation=NEXTLESSON&&lessonId=${sessionScope.lessonIdSession + 1}">
                                        <button ${disabledNext} ${sessionScope.lessonIdSession == maxIdLesson ? "disabled":""} style="background-color: #f6a208;color:white" class="btn">
                                            Next lesson >
                                        </button> 
                                    </a>

                                </div>
                            </div>
                        </div>
                        <a href="${path}/auth/user/course/lesson?operation=VIEWUSERLESSON&&courseId=${sessionScope.courseId}">
                            <button class="btn btn-success">Back</button>
                        </a>
                    </div>
                </c:if>

                <c:if test="${lessonDetail.lessonType == 'QUIZ'}">
                    <div class="col-9 text-left">
                        <!--Chi sẽ code chức năng quiz lesson tại đây-->
                    </div>
                </c:if>
                ABCD
                
                <c:forEach items="${allLesson}" var="o">
                    <c:if test="${(o.id < 9 || o.id == 9) && o.id % 4 == 1}">
                        <div id="player1" style="display:none"></div>
                    </c:if>
                    <c:if test="${(o.id < 9 || o.id == 9) && o.id % 4 == 2}">
                        <div id="player2" style="display:none"></div>
                    </c:if>
                    <c:if test="${(o.id < 9 || o.id == 9) && o.id % 4 == 3}">
                        <div id="player3" style="display:none"></div>
                    </c:if> 
                    <c:if test="${(o.id > 9) && o.id % 4 == 2}">
                        <div id="player1" style="display:none"></div>
                    </c:if>
                    <c:if test="${(o.id > 9) && o.id % 4 == 3}">
                        <div id="player2" style="display:none"></div>
                    </c:if>
                    <c:if test="${(o.id > 9) && o.id % 4 == 0}">
                        <div id="player3" style="display:none"></div>
                    </c:if> 


                </c:forEach>
            </div>
        </div>
    </body>
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
    <script>
        $(function () {
            $("input[name$='doneLesson']").click(function () {
                var value = $(this).val();
                window.location.assign("${path}/auth/user/course/lesson?operation=DONELESSON&&lessonId=" + value);
            });
        });
    </script>
    <script>
        function w3_open() {
            document.getElementById("sidenav").style.display = "block";
        }
    </script>
    <script>
        function w3_close() {
            document.getElementById("sidenav").style.display = "none";
        }
    </script>

    <script>
        var toggleBtn = document.querySelector('.sidebarMini__button');
        var sidebarMini = document.querySelector('.sidebarMini');
        var switchBtn = document.querySelector('#checkbox');
        switchBtn.addEventListener('click', function () {
            var img = document.getElementById('myVideo').src;
            if (img === '${lessonDetail.videoLink}') {
                document.getElementById('myVideo').src = '${lessonDetail.videoLink}?autoplay=1&mute=1';
            } else {
                document.getElementById('myVideo').src = '${lessonDetail.videoLink}';
            }

        });
    </script>
    <script>
        var tag = document.createElement('script');
        tag.src = "https://www.youtube.com/iframe_api";
        var firstScriptTag = document.getElementsByTagName('script')[0];
        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
        function onYouTubeIframeAPIReady() {
        <c:forEach begin="1" end="${lessonList.size()-1}" var="i">
            <c:set var="property" value="videoId"/>
            player = new YT.Player("player${i}", {
                height: '390',
                width: '640',
                videoId: '${lessonList.get(i-1).getVideoLink().substring(30)}',
                playerVars: {
                    'autoplay': 0,
                    'controls': 1
                },
                events: {
                    'onReady': function (event) {
                        time = event.target.getDuration() / 60;
                        document.getElementById("lesson${i}").innerHTML = Math.round(time) + " min";
                    }}
            });
        </c:forEach>
        }









    </script>
</html>


