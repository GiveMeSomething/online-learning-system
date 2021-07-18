<%--
    Document   : register-success
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
        <title>Manage</title>
        <!--        Bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${path}/style/styles.css">
        <link rel="stylesheet" type="text/css" href="${path}/style/setting.css">
    </head>
    <body>
        <div id="mySidebar" class="sidebar">
            <button id="closeNav" class="openbtn" onclick="closeNav()" style="display: none; margin-top: -4rem; margin-bottom: 2rem; margin-left: 13rem"><span style="text-transform: uppercase">X</span></button>
            <!--<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>-->
            <a href="${path}/auth/admin" onclick="userRoleOn()">User List</a>
            <hr>
            <a href="${path}/auth/admin/dashboard" >Dashboard</a>
            <hr>
            <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST">Post List</a>
            <hr>
            <a href="${path}/auth/teacher/subject" style="background: white; color: black">Subject List</a>
            <hr>
            <a href="${path}/auth/admin/slider" >Slider List</a>
        </div>
        <c:if test="${requestScope.errorMessage != null}">
            <div class="d-flex w-100 align-items-center justify-content-end">
                <h5>${requestScope.errorMessage}</h5>
            </div>
        </c:if>
        <div class="container my-5">
            <div class="row">
                <div class="d-flex justify-content-center align-items-center">
                    <button id="openNav" class="openbtn" onclick="openNav()" style="background: white; color: black">&#9776;</button>
                    <h2>Subjects List</h2>
                </div>
            </div>
            <div class="row">
                <form action="${path}/auth/teacher/subject" method="POST">
                    <div class="request-info">
                        <input name="previousPage" value="${path}/auth/teacher/subject" hidden="true" />
                        <div class="invalid-feedback"></div>
                        <input name="operation" value="FILTER" hidden="true" />
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="my-2 d-flex justify-content-center align-items-center gap-3">
                        <div>
                            <input class="form-control"
                                   name="keyword"
                                   type="text"
                                   id="keyword"
                                   value="${requestScope.selectedKeyword != null ? requestScope.selectedKeyword: ''}"
                                   placeholder="Search subjects"
                                   style="width: 70vh"/>
                        </div>
                        <div id="category" class="d-flex justify-content-center align-items-center m-2" >
                            <label for="category-select" class="mx-2">Categories</label>
                            <select class="form-control" name="category" style="width: 20vh">
                                <option value="-1" ${requestScope.selectedCategory == null ? 'selected': ''}>All</option>
                                <c:forEach items="${requestScope.categories}" var="category">
                                    <option value="${category.id}" ${requestScope.selectedCategory == category.id ? 'selected': ''}>
                                        ${category.categoryName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="status" class="d-flex justify-content-center align-items-center m-2">
                            <div class="mx-2">Status</div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input
                                    type="radio"
                                    id="active"
                                    name="status"
                                    class="custom-control-input"
                                    value="ACTIVE"
                                    ${requestScope.selectedStatus == "ACTIVE" ? 'checked': ''}>
                                <label class="custom-control-label" for="active">ACTIVE</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="inactive"
                                       name="status"
                                       class="custom-control-input"
                                       value="INACTIVE"
                                       ${requestScope.selectedStatus == "INACTIVE" ? 'checked': ''}>
                                <label class="custom-control-label" for="inactive">INACTIVE</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="all"
                                       name="status"
                                       class="custom-control-input"
                                       value=""
                                       ${requestScope.selectedStatus == null || requestScope.selectedStatus == '' ? 'checked': ''}>
                                <label class="custom-control-label" for="all">Both</label>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                    <div class="add-lesson">
                        <a role="button" class="btn btn-success px-3 py-2"
                           href="${path}/auth/teacher/subject?operation=TONEWSUBJECT">
                            Add new Subject
                        </a>
                    </div>
                </form>
            </div>
            <div class="row my-5">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th>Title</th>
                            <th>Category</th>
                            <th>Lessons</th>
                            <th>Owner</th>
                            <th>Status</th>
                            <th class="text-center">
                                Actions
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.pageItems}" var="item">
                            <tr>
                                <th scope="row">${item.get(0)}</th>
                                <td>${item.get(1)}</td>
                                <td>${item.get(2)}</td>
                                <td>${item.get(3)}</td>
                                <td>${item.get(4)}</td>
                                <td>${item.get(5)}</td>
                                <td class="d-flex align-items-center justify-content-center">
                                    <a href="${path}/auth/teacher/subject?operation=VIEW&subjectId=${item.get(0)}" class="mx-1">
                                        <button type="button" class="btn btn-primary">
                                            View and Edit
                                        </button>
                                    </a>
                                    <a href="${path}/auth/teacher/question?subjectId=${item.get(0)}&operation=MANAGEQUESTION" class="mx-1">
                                        <button type="button" class="btn btn-success">
                                            Question Bank
                                        </button>
                                    </a>
                                    <a href="${path}/auth/teacher/lesson?subjectId=${item.get(0)}" class="mx-1">
                                        <button type="button" class="btn btn-success">
                                            Manage Lesson
                                        </button>
                                    </a>
                                    <a href="${path}/auth/teacher/quiz?subjectId=${item.get(0)}" class="mx-1">
                                        <button type="button" class="btn btn-success">
                                            Manage Quiz
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <nav aria-label="Page navigation example">
                <c:set var="contentSize" value="${sessionScope.subjectList.size()}" />
                <c:set var="maxPage" value="${contentSize % 10 == 0 ? contentSize / 10: ((contentSize - contentSize % 10) / 10) + 1}" />
                <c:set var="currentPage" value="${pageContext.request.getParameter('page')}" />
                <c:set var="prevPage" value="${currentPage == null ? 1 : currentPage - 1}" />
                <c:set var="nextPage" value="${currentPage == null ? 2 : currentPage + 1}"/>
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="${path}/auth/teacher/subject?operation=PAGINATION&page=${prevPage > 0 ? prevPage: 1}">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${maxPage}" varStatus="counter">
                        <li class="page-item">
                            <a class="page-link"
                               href="${path}/auth/teacher/subject?operation=PAGINATION&page=${counter.index}">
                                ${counter.index}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}/auth/teacher/subject?operation=PAGINATION&page=${nextPage > maxPage ? maxPage: nextPage}">
                            Next
                        </a>
                    </li>
                </ul>
            </nav>
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
    <script type="text/javascript" src="${path}/utilities/tree-module.js"></script>
</html>
