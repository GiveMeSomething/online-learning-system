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
        <title>404 LOL</title>
        <!--        Bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous">
        <link rel="stylesheet" href="${path}/style/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style>

        </style>
    </head>
    <jsp:useBean id="dao" scope="page" class="course.CourseRepository" />
    <body>
        <div style=" width: 70%;margin: auto;margin-top: 50px; margin-left: 100px;">

            <div class="row">
                <h6>Quiz &nbsp;•&nbsp;30 min</h6>
            </div>
            <div class="row">
                <h3 class="card-title" style="margin-top: 20px;">
                    ${dao.getCourseName(sessionScope.courseId)}
                </h3>
            </div>
            <div class="row" style="margin-top: 100px;">
                <div class="col-md-6">
                    <h6>Submit your assignment</h6>
                </div>
                <div class="col-md-6">
                    <button type="button" class="btn btn-primary" style="float: right;">
                      
                        <a style="color: white; text-decoration: none;" href="${path}/auth/user/user_quiz?operation=VIEWQUIZHANDLE&quizId=1">
                         Start
                        </a>
                        

                    </button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <p class="fw-bold" style="margin-top: 2px;">
                        Receive grade
                    </p>
                    <p class="fw-bold">
                        TO PASS
                        <span style="font-weight: normal;"> 80% or higher
                    </p> </span>
                </div>
                <div class="col-md-6" style="margin-top: 10px;">
                    <p style="font-weight: bold; color: red;float: right; padding-left: 10px;" >
                        ${ketquacuoicung==null?"--":ketquacuoicung*100}%
                    </p>
                    <p class="fw-bold" style="float: right;">Grade:
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <button type="button" class="btn btn-primary"> 
                        <a style="color: white; text-decoration: none;"href="${path}/auth/teacher/quiz?operation=VIEWQUIZREVIEW&quizId=${quizId}">
                        View Feedback
                    </a>
                    </button>
                </div>
                <div class="col-md-6">
                    <span style="color: red; background-color: white; float: right;">
                        <a href="">
                            Try again
                        </a>
                    </span>
                </div>
            </div>
                    
            <div class="d-flex align-items-center" style="justify-content: flex-end; margin-top: 50px;">
                                    <a href="${path}/auth/user/course/lesson?operation=PREVIOUSLESSON&&lessonId=${sessionScope.lessonIdSession - 1}&&courseId=${sessionScope.courseId}">
                                        <button ${disabled} ${sessionScope.lessonIdSession == minIdLesson ? "disabled":""} style="background-color: #f6a208;color:white" class="btn mr-3">
                                            < Previous lesson
                                        </button>
                                    </a>
                                    <a href="${path}/auth/user/course/lesson?operation=NEXTLESSON&&lessonId=${sessionScope.lessonIdSession + 1}&&courseId=${sessionScope.courseId}">
                                        <button ${disabledNext} ${sessionScope.lessonIdSession == maxIdLesson ? "disabled":""} style="background-color: #f6a208;color:white" class="btn">
                                            Next lesson >
                                        </button> 
                                    </a>
                                    
                                </div>
        </div>
                    
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous">
    </script>

</html>