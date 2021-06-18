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
            <div>
                <h1 id="user-list-title" style="padding-bottom: 15px;">
                    MANAGE QUESTIONS
                </h1>
                <form action="question?operation=SEARCHQUESTION&&index=${tag}&&cID=${sessionScope.categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=${sessionScope.alpha}"
                      class="d-flex" method="POST">
                    <input name="searchQuestion" class="form-control py-2"
                           type="search" placeholder="Search questions"/>
                    <div>
                        <button class="btn btn-dark" type="submit">Search</button>
                    </div>
                    <div class="invalid-feedback"></div>
                </form>
            </div>
            <!--Filter-->
<!--            <div class="col-2">
                <h3>Filter</h3>
                <hr/>
                <div class="d-flex">
                    <div class="accordion" id="accordionExample">
                        <div class="accordion-item">
                            <h4 class="accordion-button"
                                type="button" data-toggle="collapse"
                                data-target="#collapseOne">
                                Level<i class="fa fa-angle-down" style="margin-left:50%"></i></h4>
                            <div id="collapseOne" class="accordion-collapse collapse show"
                                 data-parent="#accordionExample">
                                <input ${sessionScope.level == "HARD" ? "checked":""} name="level" type='radio' value="HARD"/>
                                <div style='margin-top: -20px;margin-left:21px'>
                                    <span>Hard</span>
                                </div>
                                <input ${sessionScope.level == "MEDIUM" ? "checked":""} name="level" type='radio' value="MEDIUM"/>
                                <div style='margin-top: -20px;margin-left:21px'>
                                    <span>Medium</span>
                                </div>
                                <input ${sessionScope.level == "EASY" ? "checked":""} name="level" type='radio' value="EASY">
                                <div style='margin-top: -20px;margin-left:21px'>
                                    <span>Easy</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="accordion" id="filterAlpha">
                        <div class="accordion-item">
                            <h4 class="accordion-button"
                                type="button" data-toggle="collapse"
                                data-target="#filterA">
                                Status<i class="fa fa-angle-down" style="margin-left:26%"></i></h4>
                            <div id="filterA" class="accordion-collapse collapse show"
                                 data-parent="#filterAlpha">
                                <input ${sessionScope.status == "ACTIVE" ? "checked":""} name="status" type='radio' value="ACTIVE"/>
                                <div style='margin-top: -20px;margin-left:21px'>
                                    <span>active</span>
                                </div>
                                <input ${sessionScope.status == "INACTIVE" ? "checked":""} name="status" type='radio' value="INACTIVE">
                                <div style='margin-top: -20px;margin-left:21px'>
                                    <span>inactive</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>-->
            <!--                                Queston board-->
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
                        <c:forEach items="${questionList}" var="o">
                            <tr>
                                <td>${o.id}</td>
                                <td>${o.course}</td>
                                <td>${o.lesson_name}</td>
                                <td>${o.dimension_name}</td>
                                <td>${o.content}</td>
                                <td>${o.level}</td>
                                <td>${o.status}</td>
                                <td>
                                    <button> <a href="viewuser?uid=${o.id}">View</a> </button>
                                    <button>
                                        <a href="edituser?uid=${o.id}">Edit</a>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!--                                pagination-->
            <div class="clearfix text-center">
                <ul class="pagination justify-content-center mb-5">
                    <li class="page-item">
                        <a style="color:#005b96" href="#" class="page-link">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${end}" var="i">
                        <li class="page-item page-item-paging ${tag == i?"active":""}">
                            <a class="btn btn-light" style=""
                               href="question?index=${i}&&subjectId="
                               >
                                ${i}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a style="color:#005b96" href="#" class="page-link">
                            Next
                        </a>
                    </li>
                </ul>
            </div>
        </div>
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
