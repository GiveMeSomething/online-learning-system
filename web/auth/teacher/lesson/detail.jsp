<%--
    Document   : detail
    Created on : Jun 13, 2021
    Author     : Hoang Tien Minh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <title>Lesson Detail</title>
    </head>
    <body>
        <div class="container">
            <h1>Lesson Detail</h1>
            <form action="${path}/auth/teacher/lesson" method="POST" class="needs-validatation" novalidate>
                <div class="request-info">
                    <input name="previousPage" value="${path}/auth/teacher/lesson/detail.jsp" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="ADDNEWLESSON" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-row">
                    <div class="mb-3 col-md-5">
                        <input hidden="true" value="${lesson.id}" name="lessonId"/>
                        <input hidden="true" value="${subjectId}" name="subjectId"/>
                        <label for="lesson-name">Lesson Name</label>
                        <input class="form-control" name="lesson-name" value="${lesson.name}" type="text" id="lesson-name"
                               placeholder="Enter Lesson Name" data-value-missing="Can't be empty" required />
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="mb-3 col-md-5">
                        <label for="order">Order</label>
                        <input class="form-control" name="order" value="${lesson.order}" type="text" id="order"
                               placeholder="Enter order of lesson" data-value-missing="Can't be empty" required />
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="mb-3 col-md-5">
                        <label for="courses">Topic</label>
                        <input hidden name="course" value="${course.id}">
                        <select class="custom-select" disabled="true" id="courses" required>
                            <c:forEach items="${courses}" var="c">
                                <option value="${c.key}" ${course.id == c.key ? "selected":""}>${c.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="mb-3 col-md-5">
                        <label for="type">Type</label>
                        <select class="custom-select" id="type" name="type" required>
                            <option value="SUBJECT_TOPIC" ${lesson.lessonType == "SUBJECT_TOPIC"?"selected":""}>Subject Topic</option>
                            <option value="LESSON" ${lesson.lessonType == "LESSON"?"selected":""}>Lesson</option>
                            <option value="QUIZ" ${lesson.lessonType == "QUIZ"?"selected":""}>Quiz</option>
                        </select>
                    </div>
                </div>
                <!--Display when edit lesson-->
                <c:choose>
                    <c:when test="${lesson.lessonType == 'SUBJECT_TOPIC'}">
                        <div id="subject" style="display: block"  aria-labelledby="subject-tab">
                            <br>
                        </div>
                    </c:when>
                    <c:when test="${lesson.lessonType == 'LESSON'}">
                        <div id="lesson" style="display: block">
                            <div class="mb-3">
                                <label for="link">Video link</label>
                                <input type="text" name="video-link" value="${lesson.videoLink}" id="video-link" data-value-missing="Can't be empty"
                                       class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="html-content">Content</label>
                                <textarea class="form-control ckeditor" id="html-content" name="html-content" required>${lesson.htmlContent}</textarea>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${lesson.lessonType == 'QUIZ'}">
                        <div  id="quiz" style="display: block" aria-labelledby="quiz-tab">
                            <div class="mb-3">
                                <label for="quiz">Quiz</label>
                                <select class="custom-select" name="quiz" id="quiz" required>
                                    <c:forEach items="${quiz}" var="q">
                                        <option ${lesson.quizId == q.key?"selected":""} value="${q.key}">${q.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="html-content">Content</label>
                                <textarea class="form-control ckeditor" id="html-content" name="html-quiz" required>${lesson.htmlContent}</textarea>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
                <!--Can choose when add new lesson-->
                <div id="subject" style="display: none"  aria-labelledby="subject-tab">
                    <br>
                </div>
                <div id="lesson" style="display: none">
                    <div class="mb-3">
                        <label for="link">Video link</label>
                        <input type="text" name="video-link" value="${lesson.videoLink}" id="video-link" data-value-missing="Can't be empty"
                               class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="html-content">Content</label>
                        <textarea class="form-control ckeditor" id="html-content" name="html-content" required>${lesson.htmlContent}</textarea>
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div  id="quiz" style="display: none" aria-labelledby="quiz-tab">
                    <div class="mb-3">
                        <label for="quiz">Quiz</label>
                        <select class="custom-select" name="quiz" id="quiz" required>
                            <c:forEach items="${quiz}" var="q">
                                <option ${lesson.quizId == q.key?"selected":""} value="${q.key}">${q.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="html-content">Content</label>
                        <textarea class="form-control ckeditor" id="html-content" name="html-quiz" required>${lesson.htmlContent}</textarea>
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="form-row">
                    <button class="btn btn-primary col-md-1" type="submit">Submit</button>
                    <div class="col-md-10"></div>
                    <a role="button" href="${path}/auth/teacher/lesson?subjectId=${subjectId}" class="btn btn-secondary col-md-1 ">Cancel</a>
                </div>
            </form>
        </div>
        <!-- boostrap -->
        <script>
            document.getElementById("type").onchange = function ()
            {

                if (this.value === "SUBJECT_TOPIC")
                {
                    //do this function
                    document.getElementById("subject").style.display = "block"
                    document.getElementById("quiz").style.display = "none"
                    document.getElementById("lesson").style.display = "none"
                } else if (this.value == "QUIZ")
                {
                    //do this function
                    document.getElementById("subject").style.display = "none"
                    document.getElementById("quiz").style.display = "block"
                    document.getElementById("lesson").style.display = "none"
                } else if (this.value == "LESSON")
                {
                    document.getElementById("subject").style.display = "none"
                    document.getElementById("quiz").style.display = "none"
                    document.getElementById("lesson").style.display = "block"
                }
            };
        </script>
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
        <script type="text/javascript" src="${path}/ckeditor/ckeditor/ckeditor.js"></script>
        <script>
            CKEDITOR.replace('html-content');            
        </script>
    </body>
</html>
