<%--
    Document   : quiz-handler
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
        <!--        Bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="${path}/style/quiz-handler.css">
        <title>Quiz</title>
    </head>
    <body>
        <c:set var="maxPage" value="${questionSize}" />
        <c:set var="currentPage" value="${pageContext.request.getParameter('page')}" />
        <c:set var="getCurrentPage" value="${currentPage == null ? 1:currentPage}" />
        <div class="container-fluid">
            <div class="d-flex justify-content-center align-items-center row mt-5">
                <div class="col-md-10 align-self-center col-lg-10">
                    <div class="border">
                        <div class="question bg-white p-3 border-bottom">
                            <div class="d-flex flex-row justify-content-between align-items-center mcq">
                                <h4>MCQ Quiz</h4>
                                <div>
                                    <span>(${currentPage == null ? 1:currentPage} of ${maxPage})</span>
                                    <input hidden id="testTime" value="${sessionScope.testTime}"/>
                                    <input hidden id="startTime" value="${sessionScope.time}"/>
                                    <span class="timer bg-info p-2 ml-4">
                                        <i class="fal fa-hourglass-half"></i>
                                        <span id="timer"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <form method="POST" action="${path}/auth/user/user_quiz">
                            <c:set var="prevPage" value="${currentPage == null ? 1 : currentPage - 1}" />
                            <c:set var="nextPage" value="${currentPage == null ? 2 : currentPage + 1}"/>
                            <div class="request-info">
                                <input name="previousPage" value="${path}/auth/user/user_quiz" hidden="true" />
                                <div class="invalid-feedback"></div>
                                <input name="operation" value="QUIZHANDLE" hidden="true" />
                                <div class="invalid-feedback"></div>
                            </div>
                            <c:forEach items="${question}" var="q" varStatus="counter">
                                <div class="question bg-white p-3 border-bottom d-flex flex-column">
                                    <div class="answer">
                                        <div class="d-flex flex-row align-items-center bg-warning mb-3 question-title">
                                            <h3 class="text-danger pl-3">Q.</h3>
                                            <h5 class="mt-1 ml-2">${q.content}</h5>
                                        </div>
                                        <input hidden ${sessionScope.answer['0']}/>
                                        <div class="ans ml-2">
                                            <label class="radio" for="a1">
                                                <input type="radio" id="a1"
                                                       ${sessionScope.answer[getCurrentPage] == q.option1?"checked":""}
                                                       name="q${getCurrentPage}" value="${q.option1}"> <span>${q.option1}</span>
                                            </label>
                                        </div>
                                        <div class="ans ml-2" for="a2">
                                            <label class="radio">
                                                <input type="radio"
                                                       id="a2"
                                                       ${sessionScope.answer[getCurrentPage] == q.option2?"checked":""}
                                                       name="q${getCurrentPage}"
                                                       value="${q.option2}"> <span>${q.option2}</span>
                                            </label>
                                        </div>
                                        <div class="ans ml-2" for="a3">
                                            <label class="radio">
                                                <input type="radio"
                                                       ${sessionScope.answer[getCurrentPage] == q.option3?"checked":""}
                                                       id="a3" name="q${getCurrentPage}" value="${q.option3}"> <span>${q.option3}</span>
                                            </label>
                                        </div>
                                        <div class="ans ml-2" for="a4">
                                            <label class="radio">
                                                <input type="radio" id="a4"
                                                       ${sessionScope.answer[getCurrentPage] == q.option4?"checked":""}
                                                       name="q${getCurrentPage}" value="${q.option4}"> <span>${q.option4}</span>
                                            </label>
                                        </div>
                                        <c:if test="${not empty q.option5}">
                                            <div class="ans ml-2" for="a5">
                                                <label class="radio">
                                                    <input type="radio" id="a5"
                                                           ${sessionScope.answer[getCurrentPage] == q.option5?"checked":""}
                                                           name="q${getCurrentPage}" value="${q.option5}"> <span>${q.option5}</span>
                                                </label>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="other-function d-flex flex-row justify-content-end mt-3">
                                        <label class="check order-2">
                                            <input onclick="this.form.submit()" type="checkbox" ${sessionScope.marked[getCurrentPage] == true? "checked":""} name="mark" id="mark" value="false"/>
                                            <span><i class="far fa-bookmark"></i>
                                                Mark For Review
                                            </span>
                                        </label>
                                        <div>
                                            <button type="button" class="btn btn-outline-danger mr-2 order-1" data-toggle="modal" data-target="#peek-at-answer">
                                                <i class="fas fa-eye"></i> Peek at answer
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex flex-row bg-white align-items-center justify-content-between">
                                    <div>
                                        <button type="button" class="btn btn-primary mx-3" data-toggle="modal" data-target="#review">Review Progress</button>
                                    </div>
                                    <div class="d-flex flex-row justify-content-end align-items-center p-3 bg-white">
                                        <c:if test="${not empty currentPage && currentPage != 1}">
                                            <button class="btn btn-primary d-flex align-items-center btn-danger mx-1"
                                                    type="submit"
                                                    formmethod="POST"
                                                    formaction="${path}/auth/user/user_quiz?operation=QUIZHANDLE&page=${prevPage > 0 ? prevPage: 1}">
                                                <i class="fa fa-angle-left mt-1 mr-1"></i>&nbsp;previous
                                            </button>
                                        </c:if>
                                        <c:choose>
                                            <c:when test="${getCurrentPage == maxPage}">
                                                <button type="button"
                                                        id="score-btn"
                                                        class="btn btn-success mr-2 order-1" 
                                                        data-toggle="modal" data-target="#score-exam">
                                                    Score Exam Now
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-primary border-success align-items-center btn-success"
                                                        type="submit"
                                                        formmethod="POST"
                                                        formaction="${path}/auth/user/user_quiz?operation=QUIZHANDLE&page=${nextPage > maxPage ? maxPage: nextPage}">
                                                    Next
                                                    <i class="fa fa-angle-right ml-2"></i>
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <input hidden name="thisPage" value="${getCurrentPage}"/>
                                <input hidden name="page" value="${getCurrentPage}"/>
                                <input hidden name="nextPage" value="${nextPage > maxPage ? maxPage: nextPage}"/>
                            </c:forEach>
                            <!--score exam-->
                            <div class="modal fade" id="score-exam" tabindex="-1">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header p-3">
                                            <h4 class="modal-title">Score Exam</h4>
                                            <c:if test="${requestScope.errorMessage != null}">
                                                <div class="d-flex w-100 align-items-center justify-content-end">
                                                    <h5>${requestScope.errorMessage}</h5>
                                                </div>
                                            </c:if>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body p-5">
                                            Do you want to submit now ?<br/>
                                            <b>Please carefully check again before submitting.</b>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" data-dismiss="modal" class="btn btn-outline-dark">Back</button>
                                            <button class="btn btn-primary border-success align-items-center btn-success"
                                                    type="submit"
                                                    formmethod="POST"
                                                    formaction="${path}/auth/user/user_quiz?operation=SUBMITQUIZ&page=${getCurrentPage}">
                                                Score
                                                <i class="fa fa-angle-right ml-2"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--peek at answer-->
        <div class="modal fade" id="peek-at-answer" tabindex="-1">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-header p-3">
                        <h3 class="modal-title">PEEK AT ANSWER</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body p-5">
                        <c:forEach items="${question}" var="q">
                            The correct Answer is<span class="text-info text-uppercase"> ${q.answer}</span>
                            <hr>
                            <p>Explaination: ${q.explaination}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <!--review-->
        <div class="modal fade" id="review" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="flex-column">
                            <h2 class="modal-title" id="exampleModalLabel">Review Progress</h2>
                            <h6>Review before scoring exam</h6>
                        </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-8">
                                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                                        <li class="nav-item" role="presentation">
                                            <a class="nav-link active" id="all-tab"
                                               data-toggle="tab" href="#all"
                                               role="tab" aria-controls="all"
                                               aria-selected="true">All Questions</a>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <a class="nav-link" id="unanswered-tab"
                                               data-toggle="tab" href="#unanswered"
                                               role="tab" aria-controls="unanswered"
                                               aria-selected="false">
                                                <i style="color: grey" class="far fa-square"></i><span style="color: grey"> Unanswered</span></a>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <a class="nav-link" id="marked-tab"
                                               data-toggle="tab" href="#marked" role="tab" aria-controls="marked" aria-selected="false">
                                                <i style="color: red" class="far fa-bookmark"></i><span style="color: red"> Marked</span></a>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <a class="nav-link" id="answered-tab"
                                               data-toggle="tab" href="#answered"
                                               role="tab" aria-controls="answered"
                                               aria-selected="false"><i style="color: grey" class="fas fa-square"></i> Answered</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-4">
                                    <div class="other-nav">
                                        <button type="button" id="score-exam-btn"
                                                class="btn btn-outline-success mr-2 order-1"
                                                data-toggle="modal" data-target="#score-exam">
                                            Score Exam Now
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--content-->
                        <div class="tab-content" id="myTabContent">
                            <!--unanswered question-->
                            <div class="tab-pane fade" id="unanswered" role="tabpanel" aria-labelledby="unanswered-tab">
                                <section data-region="blocks-column" class="d-print-none">
                                    <section id="mod_quiz_navblock"
                                             class=" block block__fake  card mb-3"
                                             role="navigation"
                                             data-block="_fake"
                                             aria-labelledby="instance-0-header">
                                        <div class="card-body p-3">
                                            <h5 id="instance-0-header" class="card-title d-inline">
                                                <span id="mod_quiz_navblock_title">Quiz navigation</span></h5>
                                            <div class="card-text content mt-3">
                                                <div class="qn_buttons clearfix multipages">
                                                    <c:forEach begin="1" end="${maxPage}" var="q">
                                                        <c:set var="page">${q}</c:set>
                                                        <c:if test="${empty sessionScope.answer[page]}">
                                                            <a class="qnbutton notyetanswered free btn btn-outline-secondary"
                                                               id="quiznavbutton${q}"
                                                               title="answered"
                                                               href="${path}/auth/user/user_quiz?operation=QUIZHANDLE&thisPage=${q}&page=${q}${sessionScope.marked[page] == true?"&mark=value":""}">
                                                                ${q}
                                                                <span class="accesshide">
                                                                    <span class="flagstate"></span>
                                                                </span>
                                                            </a>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </section>
                            </div>
                            <!--marked-->
                            <div class="tab-pane fade" id="marked" role="tabpanel" aria-labelledby="marked-tab">
                                <section data-region="blocks-column" class="d-print-none">
                                    <section id="mod_quiz_navblock"
                                             class=" block block__fake  card mb-3"
                                             role="navigation"
                                             data-block="_fake"
                                             aria-labelledby="instance-0-header">
                                        <div class="card-body p-3">
                                            <h5 id="instance-0-header" class="card-title d-inline">
                                                <span id="mod_quiz_navblock_title">Quiz navigation</span></h5>
                                            <div class="card-text content mt-3">
                                                <div class="qn_buttons clearfix multipages">
                                                    <c:forEach items="${sessionScope.marked}" var="q">
                                                        <c:if test="${q.value == true}">
                                                            <a class="qnbutton notyetanswered free btn btn-outline-secondary"
                                                               id="quiznavbutton2"
                                                               title="answered"
                                                               href="${path}/auth/user/user_quiz?operation=QUIZHANDLE&thisPage=${q.key}&page=${q.key}&mark=${q.key}">
                                                                ${q.key}
                                                                <span class="accesshide">
                                                                    <span class="flagstate"></span>
                                                                </span>
                                                            </a>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </section>
                            </div>
                            <!--answered question-->
                            <div class="tab-pane fade" id="answered" role="tabpanel" aria-labelledby="answered-tab">
                                <section data-region="blocks-column" class="d-print-none">
                                    <section id="mod_quiz_navblock"
                                             class=" block block__fake  card mb-3"
                                             role="navigation"
                                             data-block="_fake"
                                             aria-labelledby="instance-0-header">
                                        <div class="card-body p-3">
                                            <h5 id="instance-0-header" class="card-title d-inline">
                                                <span id="mod_quiz_navblock_title">Quiz navigation</span></h5>
                                            <div class="card-text content mt-3">
                                                <div class="qn_buttons clearfix multipages">
                                                    <c:forEach items="${sessionScope.answer}" var="q">
                                                        <c:if test="${not empty q.value}">
                                                            <a class="qnbutton answered free btn btn-outline-secondary"
                                                               id="quiznavbutton2"
                                                               title="answered"
                                                               href="${path}/auth/user/user_quiz?operation=QUIZHANDLE&thisPage=${q.key}&page=${q.key}${sessionScope.marked[q.key] == true?"&mark=value":""}">
                                                                ${q.key}
                                                                <span class="accesshide">
                                                                    <span class="flagstate"></span>
                                                                </span>
                                                            </a>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </section>
                            </div>
                            <!--all-->
                            <div class="tab-pane fade show active" id="all" role="tabpanel" aria-labelledby="all-tab">
                                <section data-region="blocks-column" class="d-print-none">
                                    <section id="mod_quiz_navblock"
                                             class=" block block__fake  card mb-3"
                                             role="navigation"
                                             data-block="_fake"
                                             aria-labelledby="instance-0-header">
                                        <div class="card-body p-3">
                                            <h5 id="instance-0-header" class="card-title d-inline">
                                                <span id="mod_quiz_navblock_title">Quiz navigation</span></h5>
                                            <div class="card-text content mt-3">
                                                <div class="qn_buttons clearfix multipages">
                                                    <c:forEach begin="1" end="${maxPage}" var="q" varStatus="count">
                                                        <c:set var="a">${q}</c:set>
                                                        <a class="qnbutton ${not empty sessionScope.answer[a]?'answered':'notyetanswered'} free btn btn-outline-secondary"
                                                           id="quiznavbutton2"
                                                           title="answered"
                                                           href="${path}/auth/user/user_quiz?operation=QUIZHANDLE&thisPage=${q}&page=${q}${sessionScope.marked[a] == true?"&mark=value":""}">
                                                            ${q}
                                                            <c:if test="${sessionScope.marked[a] == true}">
                                                                <span class="accesshide">
                                                                    <span class="flagstate"><i class="far fa-bookmark"></i></span>
                                                                </span>
                                                            </c:if>
                                                        </a>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
        <script type="text/javascript">
            $(document).ready(function () {
                var testTime = parseInt($('#testTime').val());
                var startTime = $('#startTime').val();

                var d1 = new Date(startTime),
                        countDownDate = new Date(d1);
                countDownDate.setMinutes(d1.getMinutes() + testTime);
                // Update the count down every 1 second
                var x = setInterval(function () {

                    // Get today's date and time
                    var now = new Date().getTime();

                    // Find the distance between now and the count down date
                    var distance = countDownDate.getTime() - now;

                    // Time calculations for days, hours, minutes and seconds
                    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                    // Output the result in an element with id="demo"
                    if (hours < 10) {
                        hours = "0" + hours;
                    }
                    if (seconds < 10) {
                        seconds = "0" + seconds;
                    }
                    if (minutes < 10) {
                        minutes = "0" + minutes;
                    }
                    document.getElementById("timer").innerHTML = hours + ":"
                            + minutes + ":" + seconds;

                    // If the count down is over, write some text
                    if (distance < 0) {
                        clearInterval(x);
                        document.getElementById("timer").innerHTML = "EXPIRED";
                    }
                }, 1000);
            });

            $('#score-exam-btn').click(function () {
                $('#review').modal('hide');
                $('#score-exam').modal('show');
            });
        </script>
    </body>
</html>
