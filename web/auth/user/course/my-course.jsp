<%--
    Document   : my-course
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
        <title>My course</title>
        <!--        Bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="${path}/style/styles.css">
        <style>
            #pending-course:hover{
                opacity: 1!important
            }
        </style>
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
                                <form action="${path}/home?operation=SEARCHCOURSE"
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
        <div class="container" style="margin-top: 2rem">
            <div>
                <span style="margin-left: 1rem; font-size: 1.2rem"><b>My Course</b></span>
            </div>
            <div  class="row" style="margin-left: .2rem; margin-top: 1rem; ">
                <!--                        1-->
                <c:forEach items="${myCourse}" var="o" varStatus="loop">
                    
                       <c:set var="property" value="${loop.index}"/> 
                  
                    
                   
                    <div class="col-3" 
                         style="height: max-content;
                         margin-bottom: 2rem;">
                        <div class="card" 
                             style="width: 15rem; 
                             box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                            <a href="${path}/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&courseId=${o.id}"><img style="cursor: pointer" src="${o.imageLink}" class="card-img-top" alt="..."></a>
                            <div class="card-body">
                                <div style="height:145px">
                                    <a href="${path}/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&courseId=${o.id}" style="text-decoration: none; color: black"><h5 class="card-title" style="cursor: pointer">${o.courseName}</h5></a>
                                </div>
                                <div class="row"  style="float: right">

                                    <a href="${path}/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&courseId=${o.id}" class="text-decoration-none">
                                        <span style="margin-right: .3rem">To the course</span><i class="fas fa-arrow-right fa-sm"></i>
                                    </a>

                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div>
                <span style="margin-left: 1rem; font-size: 1.2rem"><b>Pending Course</b></span>
            </div>
            <div  class="row" style="margin-left: .2rem; margin-top: 1rem; ">
                <!--                        1-->
                <c:forEach items="${myCourseSucess}" var="o">
                    <div id="pending-course" class="col-3" 
                         style="height: max-content;
                         margin-bottom: 2rem;
                         opacity: .6">
                        <div class="card" 
                             style="width: 15rem; 
                             box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                            <img src="${o.imageLink}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <div style="height:145px">
                                    <h5 class="card-title">${o.courseName}</h5>
                                </div>
                                <div class="row"  style="float: right">
                                    <span style="margin-right: .2rem"><b>Pending...</b></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="text-right mb-2 mt-4">
                <a href="${path}/home">
                    <button class="btn btn-success">Back</button>
                </a>
            </div>
        </div>
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
</html>
