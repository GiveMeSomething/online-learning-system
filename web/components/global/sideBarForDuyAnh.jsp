<%--
    Document   : header
    Created on : May 31, 2021, 9:41:47 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Learning System</title>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous"/>
        <link rel="stylesheet" type="text/css" href="${path}/style/styles.css">
        <style>
            .nav-item{
                padding: 0 !important;
            }
            li{
                margin-top: 5%;
            }
        </style>
    </head>
    <body>
        <aside id="colorlib-aside" role="complementary" class="js-fullheight">
            <nav class="navbar navbar-expand-xl navbar-light bg-light">
                <a class="navbar-brand" style="font-size: 2rem;" href="${path}/home">
                    <span style="color:blue">O</span>
                    <span style="color:orange">L</span>
                    <span style="color:green">S</span>
                </a>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav flex-column d-flex align-items-start justify-content-start">
                        <li class="nav-item">
                            <form action="home?operation=SEARCHCOURSE&&searchName=${selectedKeyword}&&count=${count}"
                                  class="d-flex" method="post">
                                <input name="searchCourse" style="width: auto" class="form-control py-2"
                                       type="search" placeholder="Search courses"/>
                                <div class="invalid-feedback"></div>
                            </form>
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
                                <c:otherwise>
                                    <li class="nav-hover nav-item">
                                        <a href="${path}/auth/user/course" class="nav-link active" style="padding-top: 8px; padding-bottom: 8px">
                                            Courses
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:choose>
                            <c:when test="${sessionScope.user == null}">
                                <button type="button" class="btn btn-outline-primary" style="padding-left: 8px; margin-top: 5%" data-toggle="modal" data-target="#login-modal">
                                    Log in
                                </button>
                                <button type="button" class="btn btn-secondary" style="margin-top: 5%; padding-left: 8px" data-toggle="modal" data-target="#register-modal">
                                    Register
                                </button>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${sessionScope.isAdmin != true }">
                                    <li class="nav-item nav-hover">
                                        <a href="${path}/auth/user/UserCourse?operation=" class="nav-link" style="padding-top: 5px; padding-bottom: 5px">
                                            My Registrations
                                        </a>
                                    </li>
                                    <li class="nav-item nav-hover">
                                        <a class="nav-link" href="${path}/auth/user">Account setting</a>
                                    </li>
                                </c:if>
                                <c:if test="${sessionScope.isAdmin != true && sessionScope.isTeacher != true}">
                                    <li class="nav-item nav-hover">
                                        <a class="nav-link" href="${path}/auth/user/course?operation=VIEWMYCOURSE&userId=${user.getId()}" style="padding-bottom: 5px; padding-top: .2rem">My Course</a>
                                    </li>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </nav>
        </aside>
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
    </body>
    <!-- Import if have form input -->
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
</html>
