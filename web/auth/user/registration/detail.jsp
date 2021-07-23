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
    </head>
    <body>
        <div class="container">
            <h3 class="mt-2 mb-3">Registration Details</h3>
            <form action="${path}/auth/user/UserCourse"
                  method="POST"
                  class="needs-validation"
                  novalidate>
                <div class="request-info">
                    <input name="previousPage" value="home" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="EDITINFO" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <input hidden="true" name="userId" value="${detail.user.id}">
                <input hidden="true" name="courseId" value="${course.id}">
                <input hidden="true" name="existCourseId" value="${detail.id}">
                <input hidden="true" name="type" value="${type}">
                <div class="row">
                    <div class="col-6">
                        <div class="px-5 user-infor">
                            <div class="form-group">
                                <label for="full-name">Full name</label><br>
                                <input class="form-control"
                                       name="full-name"
                                       ${detail.user.id == sessionScope.user.id || detail.user.id == null?"":"disabled"} 
                                       type="text"
                                       value="${detail.user.name}"
                                       data-value-missing="Can't be empty"
                                       required/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="form-group">
                                <label class="mr-3">Gender</label> 
                                <input name="gender"
                                       ${detail.user.id == sessionScope.user.id || detail.user.id == null?"":"disabled"}
                                       type="radio" id="male"
                                       value="MALE" ${detail.user.gender == "MALE"?"checked":""}
                                       data-value-missing="Can't be empty"
                                       required/> <label for="male">MALE</label> 
                                <input name="gender" class="ml-3"
                                       type="radio" id="female"
                                       ${detail.user.id == sessionScope.user.id || detail.user.id == null?"":"disabled"}
                                       value="FEMALE" ${detail.user.gender == "FEMALE"?"checked":""}
                                       data-value-missing="Can't be empty"
                                       required/> <label for="female">FEMALE</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label><br>
                                <input hidden value="${detail.user.email}" name="existEmail"/>
                                <input class="form-control"
                                       ${detail.user.id == sessionScope.user.id || detail.user.id == null?"":"disabled"}
                                       type="text"
                                       name="email"
                                       value="${detail.user.email}"
                                       data-value-missing="Can't be empty"
                                       required/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="form-group">
                                <label for="category">Mobile</label><br>
                                <input class="form-control"
                                       name="mobile"
                                       ${detail.user.id == sessionScope.user.id || detail.user.id == null?"":"disabled"}
                                       type="text"
                                       value="${detail.user.mobile}"
                                       data-value-missing="Can't be empty"
                                       required/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <c:if test="${sessionScope.isAdmin == true || sessionScope.isTeacher == true}">
                                <div class="form-group">
                                    <label>Registration Status</label><br>
                                    <select name="status" class="form-control">
                                        <option value="1" ${detail.status==1?"selected":""}>Submitted</option>
                                        <option value="0" ${detail.status==0?"selected":""}>Cancelled</option>
                                        <option value="2" ${detail.status==2?"selected":""}>Paid</option>
                                    </select>        
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="px-5 course-infor">
                            <div class="form-group">
                                <label for="subjectName">Subject Name</label>
                                <input class="form-control"
                                       name="subjectName"
                                       type="text"
                                       disabled
                                       <c:if test="${detail.title==null}">
                                           value="${course.courseName}"
                                       </c:if>
                                       <c:if test="${detail.title!=null}">
                                           value="${detail.title}"
                                       </c:if>
                                       data-value-missing="Can't be empty"
                                       required/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="form-group">
                                <label for="package">Package</label><br>
                                <select name="package" class="form-control">
                                    <c:forEach items="${package}" var="p">
                                        <option value="${p.id}" ${p.price == detail.totalCost?'selected':''}>${p.name} - ${p.price}$</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback"></div>
                            </div>
                            <c:if test="${detail.user.id != null}">
                                <div class="form-group">
                                    <label for="category">Valid from</label><br>
                                    <input class="form-control"
                                           name="validFrom"
                                           disabled
                                           type="text"
                                           placeholder="${detail.validFrom}"
                                           data-value-missing="Can't be empty"
                                           required/>
                                    <div class="invalid-feedback"></div>
                                </div>
                                <div class="form-group">
                                    <label for="category">Valid To</label><br>
                                    <input class="form-control"
                                           name="validTo"
                                           disabled
                                           type="text"
                                           value="${detail.validTo}"
                                           data-value-missing="Can't be empty"
                                           required/>
                                    <div class="invalid-feedback"></div>
                                </div>
                            </c:if>   
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 px-5">
                        <div class="note">
                            <label for="note">Note</label>
                            <textarea id="note" class="form-control" name="note">${detail.note}</textarea>
                        </div>
                    </div>
                </div>
                <div class="d-flex mt-3 px-5 justify-content-end" id="button-area">
                    <button type="submit" class="btn btn-primary mr-4">Save</button>
                    <c:choose>
                        <c:when test="${sessionScope.isAdmin == true || sessionScope.isTeacher == true}">
                            <a href="${path}/auth/teacher/registration?operation=VIEWALL" class="btn btn-secondary">Back</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${path}/auth/user/UserCourse?operation=" class="btn btn-secondary">Back</a>
                        </c:otherwise>
                    </c:choose>
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
