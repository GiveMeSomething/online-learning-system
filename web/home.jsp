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
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="${path}/style/styles.css">
    </head>
    <body>
        <section id="navbar">
            <div class="container-fluid d-flex justify-content-center my-3">
                <nav class="navbar navbar-expand-xl navbar-light bg-light" style="margin: auto;">
                    <a class="navbar-brand" style="font-size: 2rem;" href="${path}/home">
                        <span style="color:blue">O</span>
                        <span style="color:orange">L</span>
                        <span style="color:green">S</span>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav d-flex align-items-center">
                            <li class="nav-item">
                                <form action="${path}/home?operation=SEARCHCOURSE&&searchName=${sessionScope.searchName}"
                                      class="d-flex" method="post">
                                    <input name="searchCourse" class="form-control py-2"
                                           type="search" placeholder="Search courses"/>
                                    <div class="invalid-feedback"></div>
                                </form>
                            </li>
                            <li class="nav-item dropdown nav-hover">
                                <a class="nav-link dropdown-toggle active" role="button" id="navbarDropdownButton" data-toggle="dropdown">
                                    Categories
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownButton">
                                    <c:forEach items="${requestScope.categoryList}" var="o">
                                        <a class="dropdown-item"
                                           href="${path}/course?cID=${o.id}">
                                            ${o.categoryName}
                                        </a>
                                    </c:forEach>
                                </div>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page" href="${path}/blog">Blogs</a>
                            </li>
                            <c:if test="${sessionScope.isAdmin != true}">
                                <c:choose>
                                    <c:when test="${sessionScope.isTeacher == true}">
                                        <li class="nav-hover nav-item">
                                            <a href="${path}/auth/teacher/subject" class="nav-link" style="padding-top: 8px; padding-bottom: 8px">
                                                Management
                                            </a>
                                        </li>
                                    </c:when>
                                </c:choose>
                            </c:if>
                            <li class="gap-3">
                                <div style="margin-top: 4.5px; white-space: nowrap">
                                    <c:choose>
                                        <c:when test="${sessionScope.user == null}">
                                            <button type="button" class="btn btn-outline-primary py-2 px-3 mx-2" data-toggle="modal" data-target="#login-modal">
                                                Log in
                                            </button>
                                            <button type="button" class="btn btn-secondary py-2 px-3 mx-2" data-toggle="modal" data-target="#register-modal">
                                                Register
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="">
                                                <a href="#" id="shopping-cart" style="border-radius: 25px; padding: 12px 12px;color: lightslategray">

                                                </a>
                                                <ul id="setting-dropdown-ul" style="margin-left: -1rem">
                                                    <li id="setting-dropdown-li">
                                                        <a href="#" style="border-radius: 25px; padding: 12px 12px; color: lightslategray" id="setting">
                                                            <i class="fas fa-cog fa-lg"></i>
                                                        </a>
                                                        <ul id="setting-dropdown-sub-ul">

                                                            <c:if test="${sessionScope.isAdmin != true }">
                                                                <li id="li-top">
                                                                    <a href="${path}/auth/user/UserCourse?operation=" style="padding-top: 5px; padding-bottom: 5px">
                                                                        My Registrations
                                                                    </a>
                                                                </li>
                                                                <li id="li-middle">
                                                                    <a href="${path}/auth/user">Account setting</a>
                                                                </li>
                                                                <c:if test="${sessionScope.isAdmin != true && sessionScope.isTeacher == true}">
                                                                    <li id="li-bottom">
                                                                        <a href="${path}/auth/admin" style="padding-bottom: 5px">Management</a>
                                                                    </li>
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${sessionScope.isAdmin != true && sessionScope.isTeacher != true}">
                                                                <li id="li-bottom">
                                                                    <a href="${path}/auth/user/course?operation=VIEWMYCOURSE&userId=${user.getId()}" style="padding-bottom: 5px; padding-top: .2rem">My Course</a>
                                                                </li>
                                                            </c:if>
                                                            <c:if test="${sessionScope.isAdmin == true}">
                                                                <li id="li-bottom">
                                                                    <a href="${path}/auth/admin" style="padding-bottom: 5px">Management</a>
                                                                </li>
                                                            </c:if>
                                                            <li id="li-middle">
                                                                <a href="${path}/authenticate?operation=LOGOUT" style="padding-bottom: 5px; padding-top: 5px; border-bottom: 1px solid lightgray">Log out</a>
                                                            </li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <!--modal-->
            <div class="container">
                <div class="modal fade" id="login-modal" tabindex="-1" role="dialog">
                    <form action="${path}/authenticate"
                          method="POST"
                          class="needs-validation"
                          novalidate>
                        <div class="request-info">
                            <input name="previousPage" value="home" hidden="true" />
                            <div class="invalid-feedback"></div>
                            <input name="operation" value="LOGIN" hidden="true" />
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Login</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div>
                                        <label for="login-email">Email address</label>
                                        <input class="form-control"
                                               name="email"
                                               type="email"
                                               id="login-email"
                                               placeholder="Enter email"
                                               data-value-missing="Can't be empty"
                                               required />
                                        <div class="invalid-feedback"></div>
                                    </div>
                                    <div>
                                        <label for="login-password">Password</label>
                                        <input class="form-control"
                                               name="password"
                                               type="password"
                                               id="login-password"
                                               name="password"
                                               placeholder="Enter password"
                                               data-value-missing="Can't be empty"
                                               required />
                                        <div class="invalid-feedback"></div>
                                    </div>
                                    <div class="sd-flex justify-content-end">
                                        <a href="nauth/resetPassword1.jsp">Forget password</a>
                                    </div>
                                    <div>
                                        <!-- Trigger when wrong email or password (use Javascript) -->
                                        <p class="access-denied d-none" >Wrong email or password</p>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Login</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal fade" id="register-modal" tabindex="-1" role="dialog">
                    <form action="${path}/authenticate"
                          method="POST"
                          class="needs-validation"
                          novalidate>
                        <div class="request-info">
                            <input name="previousPage" value="home" hidden="true" />
                            <div class="invalid-feedback"></div>
                            <input name="operation" value="REGISTER" hidden="true" />
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Register</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="my-2">
                                        <label for="register-email">Email address</label>
                                        <input class="form-control"
                                               name="email"
                                               type="email"
                                               id="register-email"
                                               placeholder="Enter email"
                                               pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}"
                                               required
                                               data-value-missing="Can't be empty"
                                               data-pattern-mismatch="Not a valid email"/>
                                        <div class="invalid-feedback"></div>
                                    </div>
                                    <div class="my-2">
                                        <label for="register-password">Password</label>
                                        <input class="form-control"
                                               name="password"
                                               type="password"
                                               id="register-password"
                                               placeholder="Enter password"
                                               required
                                               data-value-missing="Can't be empty" />
                                        <div class="invalid-feedback"></div>
                                    </div>
                                </div>
                                <!-- Use modal footer to seperate parts of form -->
                                <div class="modal-footer d-block">
                                    <div class="my-2">
                                        <label for="fullname">Full name</label>
                                        <input class="form-control"
                                               name="fullname"
                                               type="text"
                                               id="fullname"
                                               placeholder="Enter full name"
                                               required
                                               data-value-missing="Can't be empty"/>
                                        <div class="invalid-feedback"></div>
                                    </div>
                                    <div class="my-2">
                                        <label for="mobile">Contact</label>
                                        <input
                                            class="form-control"
                                            name="mobile"
                                            type="text"
                                            placeholder="Mobile number"
                                            required
                                            pattern="^\d{10}$"
                                            data-value-missing="Can't be empty"
                                            data-pattern-mismatch="Not a valid phonenumber"/>
                                        <div class="invalid-feedback"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="my-2">
                                                <label>Gender</label>
                                                <div class="custom-control custom-radio custom-control">
                                                    <input type="radio" id="male" name="gender" class="custom-control-input" value="MALE" checked>
                                                    <label class="custom-control-label" for="male">Male</label>
                                                    <div class="invalid-feedback"></div>
                                                </div>
                                                <div class="custom-control custom-radio custom-control">
                                                    <input type="radio" id="female" name="gender" class="custom-control-input" value="FEMALE">
                                                    <label class="custom-control-label" for="female">Female</label>
                                                    <div class="invalid-feedback"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <div class="my-2">
                                                <label>Register as</label>
                                                <div class="custom-control custom-radio custom-control">
                                                    <input type="radio" id="student" name="role" class="custom-control-input" value="STUDENT" checked>
                                                    <label class="custom-control-label" for="student">Student</label>
                                                    <div class="invalid-feedback"></div>
                                                </div>
                                                <div class="custom-control custom-radio custom-control">
                                                    <input type="radio" id="teacher" name="role" class="custom-control-input" value="TEACHER">
                                                    <label class="custom-control-label" for="teacher">Teacher</label>
                                                    <div class="invalid-feedback"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Register</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <c:if test="${requestScope.errorMessage != null}">
            <div class="d-flex w-100 align-items-center justify-content-end">
                <h5>${requestScope.errorMessage}</h5>
            </div>
        </c:if>
        <!--  Banner-->

        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active banner-margin-top" >

                    <div style="height: 100%; position: absolute; width: 100%; background: #000000">
                        <div class="banner-sub-information text-center p-3" style="margin-top: 10rem; margin-left: 26rem">
                            <b class="text-light banner-primary" style="font-size: 2.5rem">Welcome to OLS</b><br>
                            <span class="text-light banner-secondary">Let’s build a more connected, inclusive and flexible future together.</span><br>
                        </div>
                    </div>

                    <img class="d-block w-100" style="background-color: #000000;
                         height: 70vh;
                         opacity: 0.5;
                         background-blend-mode: overlay;" src="https://www.wellnessretreatsindia.com/wp-content/uploads/2017/09/nature-banner-1.jpg" alt="">

                </div>
                <c:forEach items="${sliderList}" var="o">    
                    <div class="carousel-item banner-margin-top" >

                        <div class="banner-information bg-dark mx-lg-5">
                            <div class="banner-sub-information p-3">
                                <b class="text-light banner-primary">${o.title}</b><br>
                                <span class="text-light banner-secondary">Let’s build a more connected, inclusive and flexible future together.</span><br>
                                <a href="${o.backlink}">
                                    <button class="btn btn-secondary">Learn more</button>
                                </a>
                            </div>
                        </div>
                        <a href="${o.backlink}">
                            <img class="d-block w-100" style="height: 70vh" src="${path}/assets/bannerImg/${o.image}" alt="">
                        </a>


                    </div>

                </c:forEach> 
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
    <script type="text/javascript" src="${path}/utilities/home-suggested-course.js"></script>
    <script type="text/javascript" src="${path}/utilities/form-validatior.js"></script>
</html>
