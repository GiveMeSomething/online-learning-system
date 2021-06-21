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
        <link rel="stylesheet" href="${path}/style/styles.css">
    </head>
    <body>
        <div class="container my-5">
            <div class="row">
                <div class="d-flex justify-content-center align-items-center">
                    <h2>Lessons List</h2>
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
            </div>
            <div class="row my-5">
                <c:forEach items="${requestScope.pageItems}" var="item">
                    <div class="col-2 d-flex align-items-center justify-content-center">
                        <h3>
                            ${item.id}
                        </h3>
                    </div>
                    <div class="col-6">
                        <h3>${item.lessonName} (${item.lessonType})</h3>
                        <div>
                            <p>Status: ${item.status}</p>
                        </div>
                    </div>
                    <div class="col-3 d-flex align-items-center justify-content-center m-2">
                        <a href="/auth/teacher/lesson?operation=VIEW&lessonId=${item.id}" class="m-2">
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
                            <a href="${path}/auth/teacher/lesson?operation=VIEW&lessonId=${item.id}">
                                <button class="btn btn-sm btn-primary px-3 py-2">
                                    View and Edit
                                </button>
                            </a>
                        </a>
                    </div>
                </c:forEach>
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
</html>
