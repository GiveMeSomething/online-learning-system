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
        <c:if test="${requestScope.errorMessage != null}">
            <div class="d-flex w-100 align-items-center justify-content-end">
                <h5>${requestScope.errorMessage}</h5>
            </div>
        </c:if>
        <div class="container my-5">
            <div class="row">
                <div class="d-flex justify-content-center align-items-center">
                    <h2>Subjects List</h2>
                </div>
            </div>
            <div class="row">
                <form action="${path}/auth/teacher/subject" method="POST"
                      class="needs-validation" novalidate>
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
                                   placeholder="Search subjects"
                                   style="width: 70vh"/>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div id="category" class="d-flex justify-content-center align-items-center m-2" >
                            <div class="mx-2">Categories</div>
                            <select class="custom-select custom-select-sm" name="category">
                                <option value="" selected>All</option>
                                <c:forEach items="${requestScope.categories}" var="category">
                                    <option value="${category.categoryId}">${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="status" class="d-flex justify-content-center align-items-center m-2">
                            <div class="mx-2">Status</div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio" id="active" name="status" class="custom-control-input" value="ACTIVE">
                                <label class="custom-control-label" for="active">ACTIVE</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio" id="inactive" name="status" class="custom-control-input" value="INACTIVE">
                                <label class="custom-control-label" for="inactive">INACTIVE</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio" name="status" class="custom-control-input" value="Both">
                                <label class="custom-control-label" for="all">Both</label>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                </form>
            </div>
            <div class="row my-5">
                <c:forEach items="${requestScope.pageItems}" var="item">
                    <div class="col-2 d-flex align-items-center justify-content-center">
                        <h3>
                            ${item.get(0)}
                        </h3>
                    </div>
                    <div class="col-6">
                        <h3>${item.get(1)} (${item.get(2)})</h3>
                        <div>
                            <h4>Number of lessons: ${item.get(3)}</h4>
                            <p>Owner: ${item.get(4)}</p>
                            <p>Status: ${item.get(5)}</p>
                        </div>
                    </div>
                    <div class="col-3 d-flex align-items-center justify-content-center m-2">
                        <a href="/auth/teacher/subject">
                            <button class="btn btn-sm btn-success px-3 py-2">
                                ACTIVE
                            </button>
                        </a>
                        <a href="/auth/teacher/subject?operation=VIEW&subjectId=1" class="m-2">
                            <button class="btn btn-sm btn-primary px-3 py-2">
                                View and Edit
                            </button>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <nav aria-label="Page navigation example">
                <c:set var="contentSize" value="${sessionScope.subjectList.size()}" />
                <c:set var="maxPage" value="${((contentSize - contentSize % 5) / 5) + 1}" />
                <c:set var="prevPage" value="${pageContext.request.getParameter('page') - 1}" />
                <c:set var="nextPage" value="${pageContext.request.getParameter('page') + 1}" />
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="${path}/auth/teacher/subject?page=${prevPage > 0 ? prevPage: 1}">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${maxPage}" varStatus="counter">
                        <li class="page-item">
                            <a class="page-link" href="${path}/auth/teacher/subject?page=${counter.index}">
                                ${counter.index}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link" href="${path}/auth/teacher/subject?page=${nextPage > maxPage ? nextPage: maxPage}">Next</a>
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
