<%--
    Document   : courseDetail.jsp
    Created on : Jun 13, 2021
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous"/>
        <link rel="stylesheet" type="text/css" href="${path}/style/styles.css">
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
        <div>
            <section id="brief-content">
                <div id="brief-content-upper" class=" bg-dark d-flex" style="height: 19.5rem">
                    <div id="brief-info" style="flex: 50; margin-left: 12rem; margin-top: 1.5rem">
                        <h3 class="text-light" style="margin-bottom: .4rem; font-weight: bolder">${detail.courseName}</h3>
                        <p class="text-light" style="font-size: 1.2rem; margin-bottom: 0">${detail.tag}</p>
                        <div class="d-flex mt-1" style="margin-bottom: -.8rem">
                            <p class="font-weight-bold" style="font-size: larger; margin-right: 6px; color:white">
                                $<fmt:formatNumber type="number" maxFractionDigits="2" value="${detail.price * 0.8}" />
                            </p>
                            <p style="text-decoration: line-through; font-size: small;color: white" class="card-text mr-2">$${detail.price}</p>
                        </div>
                        <p style="font-size: 0.8rem" class="text-light"><span style="color:#2996A9 ">#</span>
                            <span id="course-detail-category" href="#" style="color: #2996A9; text-decoration: none">
                                ${detail.category}
                            </span>
                        </p>
                    </div>
                    <div id="brief-image">
                        <img
                            style="margin-right: 12rem; margin-top:1.5rem;width: 20rem; float: right; border-radius: 5px 5px 0px 0px;box-shadow: 1px 1px 7px black"
                            src="${detail.imageLink}"
                            />
                        <div style="width: 320px;height:217px; border-radius:0px 0px 5px 5px;background: black; margin-top: 50px;box-shadow: 1px 1px 7px black">
                            <div style="margin-top: 2rem; margin-left: 3rem">
                                <a href="#"
                                   class="btn px-sm-5 py-2">
                                    <button data-toggle="modal" data-target="#course-register-modal" type="button" class="btn"
                                            style="background: #007791; color: white; font-weight: bold;">
                                        Register Now
                                    </button>
                                </a>
                                <a href="${path}/auth/user/UserCourse?operation=GIFT&courseId=${detail.id}"
                                   class="btn px-sm-5 py-2">
                                    <button type="button" class="btn"
                                            style="background: #007791; color: white; font-weight: bold;">
                                        Gift this course
                                    </button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section id="main-content" >
                <div id="main-content-lower" class="d-flex">
                    <div>${detail.description}</div>
                    <div id="main-content-lower-right"  style="flex: 30;margin-left: 3.5rem">
                        <section id="sider-course">
                            <div class="mt-4" style="margin-left: -1rem">
                                <h4 style="font-weight: bolder; margin-left: .8rem; margin-bottom: 1rem">Featured Course</h4>
                                <c:forEach items="${siderCourse}" var="o">
                                    <div style="width: 500px; display: flex; margin-left: .8rem">
                                        <div style=" margin-right: 10px;height: 45px; margin-bottom: 5px">
                                            <a href="course?courseId=${o.id}">
                                                <img src="${o.imageLink}" style="width: 5rem; margin-right: 5px; margin-bottom: 10px;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                                            </a>
                                        </div>
                                        <div style="height: 45px; width: 250px">
                                            <a class="sider-course-hover" href="course?courseId=${o.id}" style="margin-right: 5px">
                                                ${o.courseName}
                                            </a>
                                        </div>
                                        <div style="margin-top: 2px; margin-left: 5px">
                                            <a class="sider-course-hover" href="course?courseId=${o.id}">
                                                <i class="far fa-arrow-alt-circle-right"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div>
                                        <hr style="margin-left: .8rem; width: 380px">
                                    </div>
                                </c:forEach>
                            </div>
                        </section>

                        <section id="contact-link" >
                            <h4 style="font-weight: bolder; margin-bottom: 1rem">Contact us</h4>
                            <div class="d-flex">
                                <i class="fab fa-facebook fa-lg"></i>
                                <i class="fab fa-instagram fa-lg"></i>
                                <i class="fab fa-twitter-square fa-lg"></i>
                                <i class="fas fa-envelope fa-lg"></i>
                                <i class="fas fa-phone-square-alt fa-lg"></i>
                            </div>
                        </section>
                    </div>
                </div>
            </section>
            <section id="model-container">
                <div class="container">
                    <div class="modal fade" id="course-register-modal" tabindex="-1" role="dialog">
                        <form action="${path}/course?operation=REGISTER&courseId=${pageContext.request.getParameter('courseId')}"
                              method="POST"
                              class="needs-validation"
                              novalidate>
                            <div class="request-info">
                                <input name="previousPage" value="/course?courseId=${pageContext.request.getParameter('courseId')}" hidden="true" />
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Login</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div>
                                            <label for="fullname">Full Name</label>
                                            <input class="form-control"
                                                   name="fullname"
                                                   type="text"
                                                   id="fullname"
                                                   placeholder="Enter your full name"
                                                   data-value-missing="Can't be empty"
                                                   required
                                                   value="${sessionScope.user.name}"
                                                   ${sessionScope.user != null ? 'readonly': ''}/>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div>
                                            <label for="register-email">Email address</label>
                                            <input class="form-control"
                                                   name="email"
                                                   type="email"
                                                   id="register-email"
                                                   placeholder="Enter email"
                                                   data-value-missing="Can't be empty"
                                                   required
                                                   value="${sessionScope.user.email}"
                                                   ${sessionScope.user != null ? 'readonly': ''}/>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div>
                                            <label for="register-mobile">Mobile</label>
                                            <input class="form-control"
                                                   name="mobile"
                                                   type="text"
                                                   id="register-mobile"
                                                   name="mobile"
                                                   placeholder="Enter mobile phone number"
                                                   data-value-missing="Can't be empty"
                                                   required
                                                   value="${sessionScope.user.mobile}"
                                                   ${sessionScope.user != null ? 'readonly': ''}/>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="my-2">
                                                    <label>Gender</label>
                                                    <select name="gender" class="form-control" ${sessionScope.user != null ? 'disabled': ''}>
                                                        <option value="MALE"
                                                                ${
                                                                (sessionScope.user != null && sessionScope.user.gender.toString() == 'MALE') ||
                                                                    sessionScope.user == null ? 'selected': ''
                                                                }>
                                                            MALE
                                                        </option>
                                                        <option value="FEMALE"
                                                                ${sessionScope.user != null && sessionScope.user.gender.toString() == 'FEMALE'}>
                                                            FEMALE
                                                        </option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-6">
                                                <div class="my-2">
                                                    <label for="price-package">Price Packages</label>
                                                    <select name="pricepkg" id="price-package" class="form-control">
                                                        <c:forEach items="${requestScope.coursePackages}" var="package">
                                                            <option value="${package.id}"><b>${package.name}:</b> ${package.price}</option>
                                                        </c:forEach>
                                                    </select>
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
    <script type="text/javascript" src="${path}/utilities/form-validatior.js"></script>
</html>
