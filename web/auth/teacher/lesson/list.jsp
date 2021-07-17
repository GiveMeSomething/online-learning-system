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
            <a href="${path}/auth/admin">User List</a>
            <hr>
            <a href="${path}/auth/admin/dashboard">Dashboard</a>
            <hr>
            <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST">Post List</a>
            <hr>
            <a href="${path}/auth/teacher/subject">Subject List</a>
            <hr>
            <a href="${path}/auth/admin/slider">Slider List</a>
            <hr>
            <a href="${path}/auth/teacher/lesson" style="background: white; color: black">Lesson List</a>
        </div>
        <div class="container my-5">
            <div class="row">
                <div class="d-flex justify-content-center align-items-center">
                    <button id="openNav" class="openbtn" onclick="openNav()" style="background: white; color: black">&#9776;</button>
                    <h2>Lesson List</h2>
                </div>
            </div>
            <div class="row">
                <form action="${path}/auth/teacher/lesson" method="POST">
                    <div class="request-info">
                        <input name="previousPage" value="${path}/auth/teacher/lesson" hidden="true" />
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
                                   placeholder="Search lessons"
                                   style="width: 70vh"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                </form>
                <a href="${path}/auth/teacher/lesson?operation=VIEW">
                    <button class="btn btn-primary mx-5 m-2">
                        ADD NEW LESSON
                    </button>
                </a>
            </div>
            <div class="row my-5">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th>Lesson Name</th>
                            <th>Type</th>
                            <th>Status</th>
                            <th class="text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.pageItems}" var="item">
                            <tr>
                                <th scope="row">${item.id}</th>
                                <td>${item.name}</td>
                                <td>${item.lessonType}</td>
                                <td>${item.status}</td>
                                <td class="d-flex align-items-center justify-content-center">
                                    <div class="mx-2">
                                        <c:choose>
                                            <c:when test="${item.status == 'ACTIVE'}">
                                                <a href="${path}/auth/teacher/lesson?operation=UPDATESTATUS&status=INACTIVE&lessonId=${item.id}">
                                                    <button class="btn btn-sm btn-danger px-3 py-2"
                                                            ${sessionScope.isAdmin != null && sessionScope.isAdmin == true ? '': 'disabled'}>
                                                        DEACTIVE
                                                    </button>
                                                </a>
                                            </c:when>
                                            <c:otherwise >
                                                <a href="${path}/auth/teacher/lesson?operation=UPDATESTATUS&status=ACTIVE&lessonId=${item.id}">
                                                    <button class="btn btn-sm btn-primary px-3 py-2"
                                                            ${sessionScope.isAdmin != null && sessionScope.isAdmin == true ? '': 'disabled'}>
                                                        ACTIVE
                                                    </button>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <a href="${path}/auth/teacher/lesson?operation=VIEW&lessonId=${item.id}" class="mx-2">
                                        <button class="btn btn-sm btn-primary px-3 py-2">
                                            View and Edit
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <nav aria-label="Page navigation example">
                <c:set var="contentSize" value="${sessionScope.lessonList.size()}" />
                <c:set var="maxPage" value="${contentSize % 5 == 0 ? contentSize / 5: ((contentSize - contentSize % 5) / 5) + 1}" />
                <c:set var="currentPage" value="${pageContext.request.getParameter('page')}" />
                <c:set var="prevPage" value="${currentPage == null ? 1 : currentPage - 1}" />
                <c:set var="nextPage" value="${currentPage == null ? 2 : currentPage + 1}"/>
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="${path}/auth/teacher/lesson?operation=PAGINATION&page=${prevPage > 0 ? prevPage: 1}">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${maxPage}" varStatus="counter">
                        <li class="page-item">
                            <a class="page-link"
                               href="${path}/auth/teacher/lesson?operation=PAGINATION&page=${counter.index}">
                                ${counter.index}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}/auth/teacher/lesson?operation=PAGINATION&page=${nextPage > maxPage ? maxPage: nextPage}">
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
