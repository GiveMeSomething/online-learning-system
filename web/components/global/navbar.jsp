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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Learning System</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous"/>
        <link rel="stylesheet" type="text/css" href="${path}/skeleton/styles.css">

    </head>
    <body>
        <section id="navbar">
            <div class="container-fluid d-flex justify-content-center my-3">
                <nav class="navbar navbar-expand-xl navbar-light bg-light" style="margin: auto;">
                    <a class="navbar-brand" style="font-size: 2rem;" href="#">
                        <span style="color:blue">O</span>
                        <span style="color:orange">L</span>
                        <span style="color:green">S</span>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav d-flex align-items-center">
                            <li class="nav-item">
                                <form action="searchController?cID=${sessionScope.cateID}" class="d-flex" method="post">
                                    <input name="searchCourse" class="form-control py-2"
                                           type="search" placeholder="Search courses">
                                    <div class="invalid-feedback"></div>
                                </form>
                            </li>
                            <li class="nav-item dropdown nav-hover">
                                <a class="nav-link dropdown-toggle"
                                   href="#" id="navbarDropdown"
                                   role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Categories
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li>
                                        <a class="dropdown-item"
                                           href="CourseListController?cID=1">
                                            Software Engineering
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item"
                                           href="CourseListController?cID=2">
                                            Economy
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item"
                                           href="CourseListController?cID=3">
                                            Digital Marketing
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item"
                                           href="CourseListController?cID=4">
                                            Artificial Intelligence
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item"
                                           href="CourseListController?cID=5">
                                            Information Assurance
                                        </a>
                                    </li>
                                    <li>
                                        <a class="dropdown-item"
                                           href="CourseListController?cID=6">
                                            Language
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page" href="#">Blogs</a>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page" href="#">Courses</a>
                            </li>
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
                                                <a href="#" id="shopping-cart" style="border-radius: 25px; padding: 12px 12px;;color: lightslategray">
                                                    <i class="fas fa-shopping-cart fa-lg"></i>
                                                </a>
                                                <ul id="setting-dropdown-ul" >
                                                    <li id="setting-dropdown-li">
                                                        <a href="#" style="border-radius: 25px; padding: 12px 12px; color: lightslategray" id="setting">
                                                            <i class="fas fa-cog fa-lg"></i>
                                                        </a>
                                                        <ul id="setting-dropdown-sub-ul">
                                                            <li id="li-top">
                                                                <a href="#" style="padding-top: 5px; padding-bottom: 5px">My course</a>
                                                            </li>
                                                            <li id="li-middle">
                                                                <a href="#">Account setting</a>
                                                            </li>
                                                            <li id="li-middle">
                                                                <a href="#" style="padding-bottom: 5px; padding-top: 5px; border-bottom: 1px solid lightgray">Log out</a>
                                                            </li>
                                                            <c:if test="${sessionScope.acc.isAdmin == true}">
                                                                <li id="li-bottom">
                                                                    <a href="#" style="padding-bottom: 5px">Management</a>
                                                                </li>
                                                            </c:if>
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
                                        <a href="/forget-password">Forget password</a>
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
    <!-- Import if have form input -->
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
</html>
