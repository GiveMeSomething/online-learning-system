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
            <c:if test="${requestScope.errorMessage != null}">
                <div class="d-flex w-100 align-items-center justify-content-end">
                    <h5>${requestScope.errorMessage}</h5>
                </div>
            </c:if>
            <div class="row">
                <div class="d-flex justify-content-center align-items-center">
                    <h2>Quiz List</h2>
                </div>
            </div>
            <div class="row">
                <form action="${path}/auth/teacher/quiz" method="POST">
                    <div class="request-info">
                        <input name="previousPage" value="${path}/auth/teacher/quiz" hidden="true" />
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
                                   placeholder="Search quiz"
                                   style="width: 70vh"/>
                        </div>
                        <div id="test-type" class="d-flex justify-content-center align-items-center m-2">
                            <div class="mx-2">Test Type</div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input
                                    type="radio"
                                    id="simulation"
                                    name="quizType"
                                    class="custom-control-input"
                                    value="SIMULATION"
                                    ${requestScope.selectedType == "SIMULATION" ? 'checked': ''}>
                                <label class="custom-control-label" for="simulation">Simulation</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="test"
                                       name="quizType"
                                       class="custom-control-input"
                                       value="TEST"
                                       ${requestScope.selectedType == "TEST" ? 'checked': ''}>
                                <label class="custom-control-label" for="test">Test</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="quiz"
                                       name="quizType"
                                       class="custom-control-input"
                                       value="QUIZ"
                                       ${requestScope.selectedType == "QUIZ" ? 'checked': ''}>
                                <label class="custom-control-label" for="quiz">Quiz</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="all"
                                       name="quizType"
                                       class="custom-control-input"
                                       value=""
                                       ${requestScope.selectedType == null || requestScope.selectedType == '' ? 'checked': ''}>
                                <label class="custom-control-label" for="all">All</label>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                    <div class="add-lesson">
                        <a role="button" class="btn btn-success px-3 py-2"
                           href="${path}/auth/user/user_quiz?operation=VIEW&subjectId=${requestScope.subjectId}">
                            Add New Quiz
                        </a>
                    </div>
                </form>
            </div>
            <div class="row my-5">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th>Name</th>
                            <th>Subject</th>
                            <th>Level</th>
                            <th>Questions</th>
                            <th>Duration (min)</th>
                            <th>Pass Rate (%)</th>
                            <th>Type</th>
                            <th class="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.pageItems}" var="item">
                            <tr>
                                <th scope="row">${item.id}</th>
                                <td>${item.quizName}</td>
                                <td>${item.subjectName}</td>
                                <td>${item.level}</td>
                                <td>${item.questionNum}</td>
                                <td>${item.duration}</td>
                                <td>${item.passRate}</td>
                                <td>${item.quizType}</td>
                                <td>
                                    <a href="${path}/auth/user/user_quiz?operation=VIEW&quizId=${item.id}&subjectId=${subjectId}"
                                       class="d-flex align-items-center justify-content-center">
                                        <button class="btn btn-primary">
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
                <c:set var="contentSize" value="${sessionScope.quizList.size()}" />
                <c:set var="maxPage" value="${contentSize % 10 == 0 ? contentSize / 10: ((contentSize - contentSize % 10) / 10) + 1}" />
                <c:set var="currentPage" value="${pageContext.request.getParameter('page')}" />
                <c:set var="prevPage" value="${currentPage == null ? 1 : currentPage - 1}" />
                <c:set var="nextPage" value="${currentPage == null ? 2 : currentPage + 1}"/>
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="${path}/auth/teacher/quiz?subjectId=${requestScope.subjectId}&operation=PAGINATION&page=${prevPage > 0 ? prevPage: 1}">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${maxPage}" varStatus="counter">
                        <li class="page-item">
                            <a class="page-link"
                               href="${path}/auth/teacher/quiz?subjectId=${requestScope.subjectId}&operation=PAGINATION&page=${counter.index}">
                                ${counter.index}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}/auth/teacher/quiz?subjectId=${requestScope.subjectId}&operation=PAGINATION&page=${nextPage > maxPage ? maxPage: nextPage}">
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
