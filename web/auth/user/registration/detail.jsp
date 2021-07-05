<%--
    Document   : registration-detail
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
        <title>Registration Detail</title>
        <!--        Bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${path}/style/subject.css">
    </head>
    <body>
        <div class="container container-fluid">
            <h3 class="mt-2 mb-3">Registration Details</h3>
            <form action="${path}/auth/user/UserCourse"
                  method="POST"
                  class="col-md-7 needs-validation"
                  novalidate>
                <div class="request-info">
                    <input name="previousPage" value="home" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="EDITINFO" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div id="upper" class="d-flex">
                    <div id="upper-left" class="col-11">
                        <div class="form-group">
                            <label for="subjectName">Subject Name</label>
                            <input class="form-control"
                                   disabled
                                   name="subjectName"
                                   type="text"
                                   value="${detail.title}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>  
                        <div class="form-group">
                            <label for="category">Package</label><br>
                            <input class="form-control"
                                   disabled
                                   name="package"
                                   type="text"
                                   id="package" 
                                   value="${detail.packages}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <label for="category">Price</label><br>
                            <input class="form-control"
                                   name="package"
                                   disabled
                                   type="text"
                                   value="${detail.totalCost}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <label for="category">Full name</label><br>
                            <input class="form-control"
                                   name="full-name"
                                   disabled
                                   type="text"
                                   value="${detail.user.name}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <label for="category">Gender</label><br>
                            <input class="form-control"
                                   name="full-name"
                                   disabled
                                   type="text"
                                   value="${detail.user.gender}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <label for="category">Email</label><br>
                            <input class="form-control"
                                   name="email"
                                   disabled
                                   type="text"
                                   value="${detail.user.email}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <label for="category">Mobile</label><br>
                            <input class="form-control"
                                   name="email"
                                   disabled
                                   type="text"
                                   value="${detail.user.mobile}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <label for="category">Valid from</label><br>
                            <input class="form-control"
                                   name="email"
                                   disabled
                                   type="text"
                                   value="${detail.validFrom}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <label for="category">Valid To</label><br>
                            <input class="form-control"
                                   name="email"
                                   disabled
                                   type="text"
                                   value="${detail.validTo}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="d-flex" style="margin-left: -.9rem">
                            <div id="courseStatus" class="col-md-2" style="display: flex">
                                <label style="margin-top: -.5rem; margin-right: .2rem">Status</label>
                                <select name="status" style="height: max-content">
                                    <option value="1" ${detail.status=="PUBLISHED"?"selected":""}>Published</option>
                                    <option value="0" ${detail.status=="UNPUBLISHED"?"selected":""}>Unpublished</option>
                                </select>        
                            </div>
                        </div>
                    </div>
                </div>
                <div id="lower" style="margin-left: 1rem">
                    <div id="button-area">
                        <button type="submit" class="btn btn-secondary">Save</button>
                        <a href="${path}/auth/teacher/subject" class="btn btn-secondary">Back</a>
                    </div>
                </div>
            </form>
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
</html>
