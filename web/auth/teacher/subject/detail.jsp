<%--
    Document   : subjectDetail
    Created on : Jun 15, 2021
    Author     : Dinh Kong Thanh
--%>
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
              crossorigin="anonymous"> 
        <link href="${path}/style/subject.css" rel="stylesheet" type="text/css"/> 
    </head>
    <body>        
        <div class="container container-fluid">
            <h3 class="mt-2 mb-3">Subject Details</h3> 
            <div>
                <ul class="nav nav-tabs" id="myTab" role="tablist">

                    <li class="nav-item" role="presentation">

                        <a class="nav-link ${activeId == 1 ? "active":""}"
                           id="overview-tab" 
                           data-toggle="tab" 
                           href="#overview" 
                           role="tab" 
                           aria-controls="overview" 
                           aria-selected="true">
                            Overview
                        </a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link ${activeId == 2 ? "active":""}" 
                           id="dimension-tab" 
                           data-toggle="tab" 
                           href="#dimension" 
                           role="tab" 
                           aria-controls="dimension" 
                           aria-selected="false">
                            Dimension
                        </a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link ${activeId == 3 ? "active":""}" 
                           id="pricepackage-tab" 
                           data-toggle="tab" 
                           href="auth/teacher/subject?operation=LISTPACKAGE&page=1" 
                           role="tab" 
                           aria-controls="pricepackage" 
                           aria-selected="false">
                            Price Package
                        </a>      
                    </li>
                </ul> 
            </div>
            <div class="tab-content" id="myTabContent">

                <div class="tab-pane fade ${activeId == 1 ? "show active":""}" 
                     id="overview" 
                     role="tabpanel" 
                     aria-labelledby="overview-tab">
                    <form action="${path}/auth/teacher/subject"
                          method="POST"
                          class="col-md-7 needs-validation"
                          novalidate>
                        <div class="request-info">
                            <input name="previousPage" value="home" hidden="true" />
                            <div class="invalid-feedback"></div>
                            <input name="operation" value="changeSubjectInformation" hidden="true" />
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
                                        <div id="teacher" class="col-md-2 d-flex" style="margin-left: 2rem; margin-right: 10rem">
                                            <label for="subjectName" style="margin-top: -.5rem; margin-right: .2rem">Owner</label>
                                            <select name="courseOwner" style="width: max-content; height: max-content">
                                            <c:forEach items="${authorList}" var="o">
                                                <option value="${o.id}" <c:if test="${detail.ownerId == o.id}">selected</c:if>>                                                    
                                                    ${o.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <div class="invalid-feedback"></div>
                                    </div>
                                    <div id="courseStatus" class="col-md-2" style="display: flex">
                                        <label style="margin-top: -.5rem; margin-right: .2rem">Status</label>
                                        <select name="status" style="height: max-content">
                                            <option value="1" ${detail.status=="ACTIVE"?"selected":""}>Published</option>
                                            <option value="0" ${detail.status=="INACTIVE"?"selected":""}>Unpublished</option>
                                        </select>           
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
                                <a href="#" class="btn btn-secondary">Back</a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade ${activeId == 2 ? "show active":""}" 
                     id="dimension" 
                     role="tabpanel" 
                     aria-labelledby="dimension-tab">whatup</div>

                <c:if test="${sessionScope.isAdmin  != null && sessionScope.isAdmin == true}">
                    <div class="tab-pane fade ${activeId == 3?"show active":""}" id="pricepackage" role="tabpanel" aria-labelledby="pricepackage-tab">

                        <jsp:include page="/auth/teacher/subject/price-package.jsp"/>
                    </div>
                </c:if>

            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@4.6.0/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
            crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
</html>