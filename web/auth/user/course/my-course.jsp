<%--
    Document   : my-course
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
        <title>My course</title>
        <!--        Bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="${path}/style/styles.css">
        <style>
            #pending-course:hover{
                opacity: 1!important
            }
        </style>
    </head>
    <body>
        <jsp:include page="/components/global/navbar.jsp"/>
        <div class="container" style="margin-top: 2rem">
            <div>
                <span style="margin-left: 1rem; font-size: 1.2rem"><b>My Course</b></span>
            </div>
            <div  class="row" style="margin-left: .2rem; margin-top: 1rem; ">
                <!--                        1-->
                <c:forEach items="${myCourse}" var="o">
                    <div class="col-3" 
                         style="height: max-content;
                         margin-bottom: 2rem;">
                        <div class="card" 
                             style="width: 15rem; 
                             box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                            <a href="${path}/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&courseId=${o.id}"><img style="cursor: pointer" src="${o.imageLink}" class="card-img-top" alt="..."></a>
                            <div class="card-body">
                                <div style="height:145px">
                                    <a href="${path}/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&courseId=${o.id}" style="text-decoration: none; color: black"><h5 class="card-title" style="cursor: pointer">${o.courseName}</h5></a>
                                </div>
                                <div class="row"  style="float: right">

                                    <a href="${path}/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&courseId=${o.id}" class="text-decoration-none">
                                        <span style="margin-right: .3rem">To the course</span><i class="fas fa-arrow-right fa-sm"></i>
                                    </a>

                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div>
                <span style="margin-left: 1rem; font-size: 1.2rem"><b>Pending Course</b></span>
            </div>
            <div  class="row" style="margin-left: .2rem; margin-top: 1rem; ">
                <!--                        1-->
                <c:forEach items="${myCourseSucess}" var="o">
                    <div id="pending-course" class="col-3" 
                         style="height: max-content;
                         margin-bottom: 2rem;
                         opacity: .6">
                        <div class="card" 
                             style="width: 15rem; 
                             box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                            <img src="${o.imageLink}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <div style="height:145px">
                                    <h5 class="card-title">${o.courseName}</h5>
                                </div>
                                <div class="row"  style="float: right">
                                    <span style="margin-right: .2rem"><b>Pending...</b></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="text-right mb-2 mt-4">
                <a href="${path}/home">
                    <button class="btn btn-success">Back</button>
                </a>
            </div>
        </div>
        <jsp:include page="/components/global/footer.jsp"/>
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
