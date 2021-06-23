<%--
    Document   : detail
    Created on : Jun 13, 2021
    Author     : Hoang Tien Minh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <title>Quiz Detail</title>
    </head>

    <body>
        <div class="container">
            <ul class="nav nav-tabs" id="quiz-detail" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="overview-tab" data-toggle="tab" href="#overview" role="tab"
                       aria-controls="overview" aria-selected="true">Overview</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="setting-tab" data-toggle="tab" href="#setting" role="tab"
                       aria-controls="setting" aria-selected="false">Setting</a>
                </li>
            </ul>
            <!-- content -->
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                    <form action="${path}/auth/teacher/quiz" method="POST" class="needs-validatation" novalidate>
                        <div class="request-info">
                            <input name="previousPage" value="/auth/teacher/subject/quiz/detail.jsp" hidden="true" />
                            <div class="invalid-feedback"></div>
                            <input name="operation" value="ADDQUIZOVERVIEW" hidden="true" />
                            <div class="invalid-feedback"></div>
                        </div>
                        <input name="quizId" value="${quiz.id}" hidden/>
                        <div class="form-row">
                            <div class="mb-3 col-md-12">
                                <label for="quiz-name">Quiz name</label>
                                <input class="form-control" name="quiz-name" type="text" id="quiz-name"
                                       placeholder="Enter Quiz Name" value="${quiz.quizName}"
                                       data-value-missing="Can't be empty" required />
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="mb-3 col-md-12">
                                <label for="subject">Subject</label>
                                <select class="custom-select" id="subject" name="subject-name" 
                                        required>
                                    <c:forEach items="${course}" var="o">
                                        <option value="${o.key}" 
                                                ${o.key == quiz.subjectId?"selected":""}>${o.value}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="mb-3 col-md-5">
                                <label for="exam-level">Exam Level</label>
                                <select class="custom-select" id="exam-level" name="exam-level" required>
                                    <option value="EASY" ${quiz.level == "EASY"?"selected":""}>Easy</option>
                                    <option value="MEDIUM" ${quiz.level == "MEDIUM"?"selected":""}>Medium</option>
                                    <option value="HARD" ${quiz.level == "HARD"?"selected":""}>Hard</option>
                                </select>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="col-md-2"></div>
                            <div class="col-md-5 mb-3">
                                <label for="duration">Duration (minutes)</label>
                                <input type="text" name="duration" 
                                       class="form-control" value="${quiz.duration}" 
                                       id="duration" placeholder="Exam Duration"
                                       data-value-missing="Can't be empty" required>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="mb-3 col-md-5">
                                <label for="quiz-type">Quiz-type</label>
                                <select class="custom-select" id="quiz-type" name="quiz-type" required>
                                    <option selected value="SIMULATION" ${quiz.quizType == "SIMULATION"?"selected":""}>Simulation</option>
                                    <option value="QUIZ" ${quiz.quizType == "QUIZ"?"selected":""}>Lesson Quiz</option>
                                </select>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="col-md-2"></div>
                            <div class="col-md-5 mb-3">
                                <label for="pass-rate">Pass Rate (%)</label>
                                <input type="text" name="pass-rate" class="form-control" id="pass-rate" placeholder="Pass rate"
                                       value="${quiz.passRate}"
                                       data-value-missing="Can't be empty" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="description">Description</label>
                            <textarea class="form-control" id="description" name="description" required>${quiz.description}</textarea>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-row">
                            <button class="btn btn-primary col-md-1" type="submit">Submit</button>
                            <div class="col-md-10"></div>
                            <a role="button" class="btn btn-secondary col-md-1 ">Cancel</a>
                        </div>
                    </form>
                </div>
                <!-- setting -->
                <div class="tab-pane fade" id="setting" role="tabpanel" aria-labelledby="setting-tab">
                    <form action="${path}/auth/teacher/quiz" method="POST" class="needs-validatation" novalidate>
                        <div class="request-info">
                            <input name="previousPage" value="/auth/teacher/subject/quiz/detail.jsp" hidden="true" />
                            <div class="invalid-feedback"></div>
                            <input name="operation" value="ADDQUIZSETTING" hidden="true" />
                            <div class="invalid-feedback"></div>
                        </div>
                        <input value="${quiz.subjectId}" hidden name="subject"/>
                        <input value="${quiz.id}" hidden name="quizId"/>
                        <div class="form-row">
                            <div class="mb-3">
                                <label for="total-question">Total Questions</label>
                                <input class="form-control" value="${questionNumber == null?50:questionNumber}" type="text" 
                                       name="total-question" id="total-question"
                                       data-value-missing="Can't be empty" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <span class="col-md-2">Question type</span>
                            <div class="custom-control custom-radio col-md-2">
                                <input type="radio" checked="true" class="custom-control-input" id="topic" name="type" required>
                                <label class="custom-control-label" for="topic">Topic</label>
                            </div>
                            <div class="custom-control custom-radio mb-3 col-md-2">
                                <input type="radio" class="custom-control-input" id="group" name="type" required>
                                <label class="custom-control-label" for="group">Group</label>
                            </div>
                            <div class="custom-control custom-radio mb-3 col-md-2">
                                <input type="radio" class="custom-control-input" id="domain" name="type" required>
                                <label class="custom-control-label" for="domain">Domain</label>
                                <div class="invalid-feedback">More example invalid feedback text</div>
                            </div>
                        </div>
                        <p>Choose number of Questions corresponding their group</p>
                        <c:forEach  begin="1" end="3" var="i" varStatus="status">
                            <div class="form-row">
                                <div class="mb-3 col-md-7">
                                    <select class="custom-select" id="group-name" name="dimension-name" required>
                                        <c:forEach items="${dimension}" var="o">
                                            <option selected value="${o.key}">${o.value}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback"></div>
                                </div>
                                <div class="col-md-1"></div>
                                <div class="col-md-2">
                                    <input type="text" class="form-control" name="number-of-question" placeholder="Number of questions">
                                </div>
                                <div class="col-md-1"></div>
                                <div class="col-md-1">
                                    <button type="button" class="btn btn-light">${not status.last ? "Delete":"Add"}</button>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="form-row">
                            <button class="btn btn-primary col-md-1" type="submit">Save</button>
                            <div class="col-md-10"></div>
                            <a role="button" class="btn btn-secondary col-md-1 ">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- boostrap -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
                integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
        crossorigin="anonymous"></script>
        <script src="${path}/utilities/form-validator.js"></script>
    </body>

</html>
