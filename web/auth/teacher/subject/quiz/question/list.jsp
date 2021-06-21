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
            <form action="${path}/auth/teacher/question?operation=SEARCHQUESTION"
                  method="POST">
                <div id="search-section" class="d-flex my-sm-1">
                    <input name="keyword"                
                           type="text" 
                           id="keyword"
                           value="${requestScope.selectedKeyword != null ? requestScope.selectedKeyword: ''}"
                           placeholder="Search questions"
                           style="border-radius: 3px; border: 1px solid lightslategrey; margin-right: .2rem "/>
                    <div>
                        <button class="btn btn-dark" type="submit">Search</button>
                    </div>
                </div>
                <div style=" width: 100%;
                     height: auto;
                     float: left;
                     margin-bottom: 10px;">
                    <span style="float: left; margin-left: 10px;font-weight: bold">Level</span>
                    <span style="float: left; margin-left: 10px;">
                        <input type="checkbox" onclick="this.form.submit()" name="level" value="HARD"> Hard <br>
                    </span>
                    <span style="float: left; margin-left: 10px;">
                        <input type="checkbox" onclick="this.form.submit()" name="level" value="MEDIUM"> Medium <br>
                    </span>
                    <span style="float: left; margin-left: 10px; border-right: 2px solid black;
                          padding-right: 10px;">
                        <input type="checkbox" onclick="this.form.submit()" name="level" value="EASY"> Easy <br>
                    </span>
                    <span style="padding-left: 10px; float: left; padding-right: 10px;font-weight: bold">Status</span>
                    <span style="float: left;padding-right: 10px;">
                        <input type="checkbox" onclick="this.form.submit()" name="status" value="ACTIVE"> Active <br>
                    </span>
                    <span>
                        <input type="checkbox" onclick="this.form.submit()" name="status" value="INACTIVE"> Inactive<br>
                    </span>
                    <span style="padding-left: 10px; float: left; padding-right: 10px; font-weight: bold">Dimension</span>
                    <!--                    dimension list filter-->
                    <c:forEach items="${requestScope.dimensionList}" var="o" >
                        <span style="float: left;padding-right: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name="dimension" value="${o.dimension_name}"> ${o.dimension_name} <br>
                        </span>
                    </c:forEach>
                </div>
            </form> 
            <div id="question-table">
                <table id="myTable" class="table table-light table-striped">
                    <thead>
                        <tr>
                            <th >
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
                                    <button class="btn btn-secondary">
                                        <a href="#" class="btn btn-secondary" style="width: 5rem; font-size: 10px; font-weight: bold">View&Edit</a>
                                    </button>
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
