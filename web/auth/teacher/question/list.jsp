<%--
    Document   : list
    Created on : Jun 13, 2021
    Author     : Hoang Tien Minh
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
        <link rel="stylesheet" href="${path}/style/question.css">
    </head>
    <body>
        <div class="container container-fluid">
            <h1 id="user-list-title" style="padding-bottom: 15px;">
                MANAGE QUESTIONS
            </h1>
            <div class="row">
                <form action="${path}/auth/teacher/question?operation=SEARCHQUESTION"
                      method="POST">
                    <div class="my-2 d-flex justify-content-center align-items-center gap-1">
                        <div>
                            <input class="form-control"
                                   name="keyword"
                                   type="text"
                                   id="keyword"
                                   onchange="resetRadio()"
                                   value="${requestScope.selectedKeyword != null ? requestScope.selectedKeyword: ''}"
                                   placeholder="Search subjects"
                                   style="width: 40vh"/>
                        </div>
                        <div id="dimension" class="d-flex justify-content-center align-items-center m-2" >
                            <label for="category-select" class="mx-2">Dimension</label>
                            <select onchange="resetRadio()" class="form-control" name="dimension" style="width: 20vh">
                                <option value="" ${requestScope.selectedDimension == null ? 'selected': ''}>All</option>
                                <c:forEach items="${requestScope.dimensionList}" var="o">
                                    <option value="${o.dimension_name}" ${requestScope.selectedDimension == o.dimension_name ? 'selected': ''}>
                                        ${o.dimension_name}
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

                        </div>
                        <div id="level" class="d-flex justify-content-center align-items-center m-2">
                            <div class="mx-2">Level</div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input
                                    type="radio"
                                    id="hard"
                                    name="level"
                                    class="custom-control-input"
                                    value="HARD"
                                    ${requestScope.selectedLevel == "HARD" ? 'checked': ''}>
                                <label class="custom-control-label" for="hard">HARD</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="medium"
                                       name="level"
                                       class="custom-control-input"
                                       value="MEDIUM"
                                       ${requestScope.selectedLevel == "MEDIUM" ? 'checked': ''}>
                                <label class="custom-control-label" for="medium">MEDIUM</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="easy"
                                       name="level"
                                       class="custom-control-input"
                                       value="EASY"
                                       ${requestScope.selectedLevel == "EASY" ? 'checked': ''}>
                                <label class="custom-control-label" for="easy">EASY</label>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                </form> 
            </div>
            <div id="question-table">

                <table id="myTable" class="table table-light table-striped">
                    <thead>
                        <tr>
                            <th>
                                ID
                            </th>
                            <th>
                                Course
                            </th>
                            <th>
                                Lesson
                            </th>
                            <th > 
                                Dimension
                            </th>
                            <th >
                                Content
                            </th>
                            <th >
                                Level
                            </th>
                            <th >
                                Status
                            </th>
                            <th >
                                Action
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.pageItems}" var="o">
                            <tr>
                                <td>${o.id}</td>
                                <td>${o.course}</td>
                                <td>${o.lesson_name}</td>
                                <td>${o.dimension_name}</td>
                                <td>${o.content}</td>
                                <td>${o.level}</td>
                                <td>${o.status}</td>
                                <td>
                                    <a href="${path}/auth/teacher/question?operation=VIEW&&questionId=${o.id}" style="width: 5rem; font-size: 10px; font-weight: bold">
                                        <button class="btn btn-secondary mb-2">
                                            View&Edit
                                        </button>
                                    </a>
                                    <a href="${path}/auth/teacher/question?operation=DELETEQUESTION&questionId=${o.id}">
                                        <button class="btn btn-danger mb-2" style="padding: 5px 25px">
                                            Delete
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <nav aria-label="Page navigation example">
                <c:set var="contentSize" value="${sessionScope.questionList.size()}" />
                <c:set var="maxPage" value="${((contentSize - contentSize % 5) / 5) + 1}" />
                <c:set var="currentPage" value="${pageContext.request.getParameter('page')}" />
                <c:set var="prevPage" value="${currentPage == null ? 1 : currentPage - 1}" />
                <c:set var="nextPage" value="${currentPage == null ? 2 : currentPage + 1}"/>
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="${path}/auth/teacher/question?operation=PAGINATION&page=${prevPage > 0 ? prevPage: 1}">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${maxPage}" varStatus="counter">
                        <li class="page-item">
                            <a class="page-link"
                               href="${path}/auth/teacher/question?operation=PAGINATION&page=${counter.index}">
                                ${counter.index}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}/auth/teacher/question?operation=PAGINATION&page=${nextPage > maxPage ? maxPage: nextPage}">
                            Next
                        </a>
                    </li>
                    <jsp:include page="/auth/teacher/question/ImportQuestion.jsp"/>
                </ul>
            </nav>
            <div>
                <a href="${path}/auth/teacher/subject" class="m-2 btn btn-light" style="margin-top: -6rem!important;margin-left: 69rem!important; border-radius: none!important">
                    Back
                </a>
            </div>
        </div>
        <script>
            function resetRadio() {
                document.getElementById("active").checked = false;
                document.getElementById("inactive").checked = false;
                document.getElementById("hard").checked = false;
                document.getElementById("medium").checked = false;
                document.getElementById("easy").checked = false;
            }
        </script>
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
