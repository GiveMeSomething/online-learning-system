<%--
    Document   : home
    Created on : May 22, 2021, 10:11:29 AM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Learning System</title>
        <!--        Bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="./style/styles.css">
    </head>
    <body>
        <jsp:include page="/components/global/navbar.jsp"/>
        <c:if test="${requestScope.errorMessage != null}">
            <div class="d-flex w-100 align-items-center justify-content-end">
                <h5>${requestScope.errorMessage}</h5>
            </div>
        </c:if>
        <!--  Banner-->
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active banner-margin-top" >
                    <div class="banner-information bg-dark mx-lg-5">
                        <div class="banner-sub-information p-3">
                            <b class="text-light banner-primary">This is your moment. Let’s study.</b><br>
                            <span class="text-light banner-secondary">Let’s build a more connected, inclusive and flexible future together.</span><br>
                            <button class="btn btn-secondary">Learn more</button>
                        </div>
                    </div>
                    <img class="d-block w-100" style="height: 70vh" src="${path}/assets/bannerImg/banner1.jpg" alt="First slide">
                </div>
                <div class="carousel-item banner-margin-top">
                    <div class="banner-information bg-dark mx-lg-5">
                        <div class="banner-sub-information p-3">
                            <b class="text-light banner-primary">Study anywhere with your laptop.</b><br>
                            <span class="text-light banner-secondary">Let’s create a knowledgeable world.</span><br>
                            <button class="btn btn-secondary">Learn more</button>
                        </div>
                    </div>
                    <img class="d-block w-100" style="height: 70vh" src="${path}/assets/bannerImg/banner2_1.jpg" alt="Second slide">
                </div>
                <div class="carousel-item banner-margin-top">
                    <div class="banner-information bg-dark mx-lg-5">
                        <div class="banner-sub-information p-3">
                            <b class="text-light banner-primary">Learn from the expert from all over the world.</b><br>
                            <span class="text-light banner-secondary">Widen your knowledge.</span><br>
                            <button class="btn btn-secondary">Learn more</button>
                        </div>
                    </div>
                    <img class="d-block w-100" style="height: 70vh" src="${path}/assets/bannerImg/banner3_1.jpg" alt="Third slide">
                </div>
            </div>
            <a class="carousel-control-prev banner-btn" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon " aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next banner-btn" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!--  Banner-->
        <section id="introduction" style="height: 3.5rem">
            <div class="row mx-3" style="height: 3.5rem; margin-left: 10.5rem!important">
                <div class="col-6 col-md-4 mt-2">
                    <i class="far fa-check-circle banner-icon-sz fa-2x" style="height: 1.5rem"></i>
                    <div class="d-flex flex-column introduction-detail-left">
                        <span class="introduction-primary">Plenty of online courses</span>
                        <span class="introduction-secondary">Explore our online course now</span>
                    </div>
                </div>
                <div class="col-6 col-md-4 mt-2">
                    <i class="far fa-comment banner-icon-sz fa-2x" ></i>
                    <div class="d-flex flex-column introduction-detail">
                        <span class="introduction-primary">Expert instruction</span>
                        <span class="introduction-secondary">Find your instructor</span>
                    </div>
                </div>
                <div class="col-6 col-md-4 mt-2">
                    <i class="fas fa-history banner-icon-sz fa-2x"></i>
                    <div class="d-flex flex-column introduction-detail">
                        <span class="introduction-primary">Life time access</span>
                        <span class="introduction-secondary">Learn on your schedule</span>
                    </div>
                </div>
            </div>
        </section>
        <section id="subject-course-list">
            <div id="subject-course-list-main" class="d-flex mx-lg-5">
                <div id="subject-course-list-left" class="p-5 col-lg-4 bg-light mt-5" style="height: 15rem">
                    <h5>The nation's largest selection of courses</h5>
                    <p>Choose from over 100000 online video courses with new additions published every month</p>
                </div>
                <div id="subject-course-list-right" class="col-lg-7" style="margin-left: 4rem">
                    <div id="myDIV" style="margin-left:-0.5rem">
                        <button class="btn1 active1" onclick="itOn()" style="outline: none">IT&Software</button>
                        <button class="btn1 " onclick="businessOn()"style="outline: none">Business</button>
                        <button class="btn1" onclick="marketingOn()"style="outline: none">Marketing</button>
                        <button class="btn1" onclick="aiOn()"style="outline: none">AI</button>
                        <button class="btn1" onclick="iaOn()"style="outline: none">Information assurance</button>
                        <button class="btn1" onclick="languageOn()"style="outline: none">Language</button>
                    </div>
                    <!--                it-->
                    <div id="it" style="display: block; margin-top: -2rem">
                        <jsp:include page="/components/home/it.jsp"/>
                    </div>
                    <!--                business-->
                    <div id="business" style="display: none; margin-top: -2rem">
                        <jsp:include page="/components/home/business.jsp"/>
                    </div>
                    <!--                marketing-->
                    <div id="marketing" style="display: none; margin-top: -2rem">
                        <jsp:include page="/components/home/marketing.jsp"/>
                    </div>
                    <!--                ai-->
                    <div id="ai" style="display: none; margin-top: -2rem">
                        <jsp:include page="/components/home/ai.jsp"/>
                    </div>
                    <!--                information assurance-->
                    <div id="ia" style="display: none; margin-top: -2rem">
                        <jsp:include page="/components/home/ia.jsp" />
                    </div>
                    <!--                language-->
                    <div id="language" style="display: none; margin-top: -2rem">
                        <jsp:include page="/components/home/language.jsp"/>
                    </div>
                    <div id="myDIV" style="margin-left: -0.5rem">
                        <a class="btn2 " style="outline: none; font-weight: normal; font-size: 20px">Some of the best</a>
                    </div>
                    <!--                students are viewing-->
                    <div  style="display: block; margin-top: -2rem">
                        <jsp:include page="/components/home/featuredCourse.jsp"/>
                    </div>
                </div>
        </section>
        <jsp:include page="/components/global/footer.jsp"/>
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
        // Add active class to the current button (highlight it)
        var header = document.getElementById("myDIV");
        var btns = header.getElementsByClassName("btn1");
        for (var i = 0; i < btns.length; i++) {
            btns[i].addEventListener("click", function () {
                var current = document.getElementsByClassName("active1");
                current[0].className = current[0].className.replace(" active1", "");
                this.className += " active1";
            });
        }

        //display and none
        function itOn() {
            var it = document.getElementById('it');
            var business = document.getElementById('business');
            var marketing = document.getElementById('marketing');
            var ai = document.getElementById('ai');
            var ia = document.getElementById('ia');
            var language = document.getElementById('language');

            it.style.display = 'block';
            business.style.display = 'none';
            marketing.style.display = 'none';
            ai.style.display = 'none';
            ia.style.display = 'none';
            language.style.display = 'none';
        }
        function businessOn() {
            var it = document.getElementById('it');
            var business = document.getElementById('business');
            var marketing = document.getElementById('marketing');
            var ai = document.getElementById('ai');
            var ia = document.getElementById('ia');
            var language = document.getElementById('language');

            it.style.display = 'none';
            business.style.display = 'block';
            marketing.style.display = 'none';
            ai.style.display = 'none';
            ia.style.display = 'none';
            language.style.display = 'none';
        }

        function marketingOn() {
            var it = document.getElementById('it');
            var business = document.getElementById('business');
            var marketing = document.getElementById('marketing');
            var ai = document.getElementById('ai');
            var ia = document.getElementById('ia');
            var language = document.getElementById('language');

            it.style.display = 'none';
            business.style.display = 'none';
            marketing.style.display = 'block';
            ai.style.display = 'none';
            ia.style.display = 'none';
            language.style.display = 'none';
        }

        function aiOn() {
            var it = document.getElementById('it');
            var business = document.getElementById('business');
            var marketing = document.getElementById('marketing');
            var ai = document.getElementById('ai');
            var ia = document.getElementById('ia');
            var language = document.getElementById('language');

            it.style.display = 'none';
            business.style.display = 'none';
            marketing.style.display = 'none';
            ai.style.display = 'block';
            ia.style.display = 'none';
            language.style.display = 'none';
        }

        function iaOn() {
            var it = document.getElementById('it');
            var business = document.getElementById('business');
            var marketing = document.getElementById('marketing');
            var ai = document.getElementById('ai');
            var ia = document.getElementById('ia');
            var language = document.getElementById('language');

            it.style.display = 'none';
            business.style.display = 'none';
            marketing.style.display = 'none';
            ai.style.display = 'none';
            ia.style.display = 'block';
            language.style.display = 'none';
        }

        function languageOn() {
            var it = document.getElementById('it');
            var business = document.getElementById('business');
            var marketing = document.getElementById('marketing');
            var ai = document.getElementById('ai');
            var ia = document.getElementById('ia');
            var language = document.getElementById('language');

            it.style.display = 'none';
            business.style.display = 'none';
            marketing.style.display = 'none';
            ai.style.display = 'none';
            ia.style.display = 'none';
            language.style.display = 'block';
        }
    </script>
</html>
