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
        <div class="container-fluid">
            <div class="d-flex justify-content-center align-items-center row mt-5">
                <div class="col-md-10 align-self-center col-lg-10">
                    <div class="border">
                        <div class="question bg-white p-3 border-bottom">
                            <div class="d-flex flex-row justify-content-between align-items-center mcq">
                                <h4>MCQ Quiz</h4>
                                <div class="">
                                    <span>(5 of 20)</span>
                                    <span class="timer bg-info p-2 ml-4"><i class="fal fa-hourglass-half"></i> 00:10:29</span>
                                </div>
                            </div>
                        </div>
                        <c:forEach items="${question}" var="q">
                            <div class="question bg-white p-3 border-bottom d-flex flex-column">
                                <div class="answer">
                                    <div class="d-flex flex-row align-items-center bg-warning mb-3 question-title">
                                        <h3 class="text-danger pl-3">Q.</h3>
                                        <h5 class="mt-1 ml-2">${q.content}</h5>
                                    </div>
                                    <div class="ans ml">
                                        <label class="radio" for="a1"> 
                                            <input type="radio" id="a1" name="q1" value="brazil"> <span>${q.option1}</span>
                                        </label>
                                    </div>
                                    <div class="ans ml-2">
                                        <label class="radio"> <input type="radio" id="a2" name="q1" value="Germany"> <span>${q.option2}</span>
                                        </label>
                                    </div>
                                    <div class="ans ml-2">
                                        <label class="radio"> <input type="radio" id="a3" name="q1" value="Indonesia"> <span>${q.option3}</span>
                                        </label>
                                    </div>
                                    <div class="ans ml-2">
                                        <label class="radio"> <input type="radio" id="a4" name="q1" value="Russia"> <span>${q.option4}</span>
                                        </label>
                                    </div>
                                </div>
                                <div class="other-function d-flex flex-row justify-content-end mt-3">
                                    <label class="check order-2">
                                        <input type="checkbox" name="mark" id="mark" value="false"/><span><i class="far fa-bookmark"></i> Mark For Review</span>
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
                                    <button class="btn btn-primary d-flex align-items-center btn-danger mx-1" type="button">
                                        <i class="fa fa-angle-left mt-1 mr-1"></i>&nbsp;previous
                                    </button>
                                    <button class="btn btn-primary border-success align-items-center btn-success" type="button">
                                        Next
                                        <i class="fa fa-angle-right ml-2"></i>
                                    </button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
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
                                            <a class="nav-link active" id="all-tab" data-toggle="tab" href="#all" role="tab" aria-controls="all" aria-selected="true">All Questions</a>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <a class="nav-link" id="unanswered-tab" data-toggle="tab" href="#unanswered" role="tab" aria-controls="unanswered" aria-selected="false">Unanswered</a>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <a class="nav-link" id="marked-tab" data-toggle="tab" href="#marked" role="tab" aria-controls="marked" aria-selected="false">Marked</a>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <a class="nav-link" id="answered-tab" data-toggle="tab" href="#answered" role="tab" aria-controls="answered" aria-selected="false">Answered</a>
                                        </li>
                                    </ul>  
                                </div>
                                <div class="col-4">
                                    <div class="othernav">
                                        <a class="endtestlink" href="https://cmshn.fpt.edu.vn/mod/quiz/summary.php?attempt=185905&amp;cmid=130222">Finish attempt ...</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade" id="unanswered" role="tabpanel" aria-labelledby="unanswered-tab">...</div>
                            <div class="tab-pane fade" id="marked" role="tabpanel" aria-labelledby="marked-tab">...</div>
                            <div class="tab-pane fade" id="answered" role="tabpanel" aria-labelledby="answered-tab">...</div>
                            <div class="tab-pane fade show active" id="all" role="tabpanel" aria-labelledby="all-tab">
                                <section data-region="blocks-column" class="d-print-none">
                                    <section id="mod_quiz_navblock"
                                             class=" block block__fake  card mb-3"
                                             role="navigation"
                                             data-block="_fake"
                                             aria-labelledby="instance-0-header"
                                             >
                                        <div class="card-body p-3">
                                            <h5 id="instance-0-header" class="card-title d-inline"><span id="mod_quiz_navblock_title">Quiz navigation</span></h5>
                                            <div class="card-text content mt-3">
                                                <div class="qn_buttons clearfix multipages">
                                                    <c:forEach begin="1" end="${questionSize}" var="q">
                                                        <a class="qnbutton notyetanswered free btn btn-secondary" id="quiznavbutton2" title="Not yet answered" data-quiz-page="1">
                                                            <span class="thispageholder"></span>
                                                            <span class="trafficlight"></span>
                                                            <span class="accesshide">Question </span>${q}<span class="accesshide"> <span class="flagstate"></span></span>
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
    </body>
</html>
