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
        <title>404 LOL</title>
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
            <h3 class="mt-2 mb-3">Subject Details</h3>
            <form action="${path}/auth/teacher/subject"
                  method="POST"
                  class="col-md-7 needs-validation"
                  novalidate>
                <div class="request-info">
                    <input name="previousPage" value="home" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="CHANGESUBJECTINFORMATION" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div id="upper" class="d-flex">
                    <div id="upper-left" class="col-11">
                        <div class="form-group">
                            <label for="subjectName">Subject Name</label>
                            <input class="form-control"
                                   name="subjectName"
                                   type="text"
                                   id="id" 
                                   placeholder="Textbox"
                                   value="${detail.courseName}"
                                   data-value-missing="Can't be empty"
                                   required/>
                            <div class="invalid-feedback"></div>
                        </div>  
                        <div class="form-group">
                            <label for="category">Category</label><br>
                            <select name="categoryBox" style="width: 509.3px; height: 38px">
                                <c:forEach items="${categoryList}" var="o">
                                    <option value="${o.id}" <c:if test="${detail.category==o.categoryName}">selected</c:if>>                                                    
                                        ${o.categoryName}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="d-flex" style="margin-left: -.9rem">
                            <div id="checkbox" class="col-md-2 mr-3 d-flex">
                                <input type="checkbox" id="featuredSubject" name="featuredSubject" <c:if test="${detail.feature == true}">checked</c:if>>
                                    <label for="featuredSubject" style="margin-top: -0.5rem; margin-left: 0.2rem">Featured subject</label>
                                </div>
                                <div id="teacher" class="col-md-2 d-flex" style="margin-right: 8.5rem">
                                    <label for="subjectName" style="margin-top: -.5rem; margin-right: .2rem">Owner</label>
                                <c:if test="${sessionScope.isAdmin  != null && sessionScope.isAdmin == true}">
                                    <select name="courseOwner" style="width: max-content; height: max-content">
                                        <c:forEach items="${authorList}" var="o">
                                            <option value="${o.id}" <c:if test="${detail.ownerId == o.id}">selected</c:if>>                                                    
                                                ${o.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </c:if>

                                <c:if test="${sessionScope.isAdmin != true}">
                                    <select name="courseOwner" style="width: max-content; height: max-content" disabled>
                                        <c:forEach items="${authorList}" var="o">
                                            <option value="${o.id}" <c:if test="${detail.ownerId == o.id}">selected</c:if>>                                                    
                                                ${o.name}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </c:if>


                                <div class="invalid-feedback"></div>
                            </div>
                            <div id="courseStatus" class="col-md-2" style="display: flex">
                                <label style="margin-top: -.5rem; margin-right: .2rem">Status</label>
                                <c:if test="${sessionScope.isAdmin  != null && sessionScope.isAdmin == true}">
                                    <select name="status" style="height: max-content">

                                        <option value="1" ${detail.status=="PUBLISHED"?"selected":""}>Published</option>
                                        <option value="0" ${detail.status=="UNPUBLISHED"?"selected":""}>Unpublished</option>
                                    </select>     
                                </c:if>


                                <c:if test="${sessionScope.isAdmin != true}">
                                    <select name="status" style="height: max-content" disabled>

                                        <option value="1" ${detail.status=="PUBLISHED"?"selected":""}>Published</option>
                                        <option value="0" ${detail.status=="UNPUBLISHED"?"selected":""}>Unpublished</option>
                                    </select>     
                                </c:if>

                            </div>
                        </div>
                    </div>
                    <div id="upper-right" class="col-1">
                        <img style="width: 25rem; margin-left: 3.5rem" src="${detail.imageLink}">
                    </div>
                </div>
                <div id="lower" style="margin-left: 1rem">
                    <div id="description-area">
                        <label>Description</label><br>
                        <textarea name="description" style="width: 62.1rem; height: 6rem" >${detail.description}</textarea>
                    </div>
                    <div id="button-area">
                        <button type="submit" class="btn btn-secondary">Submit</button>
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
