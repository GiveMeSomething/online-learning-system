<%--
    Document   : index
    Created on : May 29, 2021
    Author     : Hoang Tien Minh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="previousPage" value="/index.jsp" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Learning System</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${path}/style/global.css" />
    </head>
    <body>
        <section class="container" id="main">
            <div class="d-flex flex-column align-items-center justify-content-center" style="height: 100vh">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-outline-primary py-2 px-3 mx-2" data-toggle="modal" data-target="#login-modal">
                    Log in
                </button>
                <button type="button" class="btn btn-secondary py-2 px-3 mx-2" data-toggle="modal" data-target="#register-modal">
                    Register
                </button>
                <div class="modal fade" id="login-modal" tabindex="-1" role="dialog">
                    <form action="${path}/authenticate"
                          method="POST"
                          class="needs-validation"
                          novalidate>
                        <div class="request-info">
                            <input name="previousPage" value="index.jsp" hidden="true" />
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
                            <input name="previousPage" value="index.jsp" hidden="true" />
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
                                                </div>
                                                <div class="custom-control custom-radio custom-control">
                                                    <input type="radio" id="female" name="gender" class="custom-control-input" value="FEMALE">
                                                    <label class="custom-control-label" for="female">Female</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <div class="my-2">
                                                <label>Register as</label>
                                                <div class="custom-control custom-radio custom-control">
                                                    <input type="radio" id="student" name="role" class="custom-control-input" value="STUDENT" checked>
                                                    <label class="custom-control-label" for="student">Student</label>
                                                </div>
                                                <div class="custom-control custom-radio custom-control">
                                                    <input type="radio" id="teacher" name="role" class="custom-control-input" value="TEACHER">
                                                    <label class="custom-control-label" for="teacher">Teacher</label>
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
