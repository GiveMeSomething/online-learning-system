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
        <title>Quiz Review</title>
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
        <c:set var="pageItem" value="${requestScope.pageItem}" />
        <c:set var="currentQuestion" value="${requestScope.questionNum == null ? 1: requestScope.questionNum + 1}" />
        <c:set var="result" value="${pageItem.get(7).equals('1')}" />
        <c:set var="color" value="${result == true ? 'bg-success': 'bg-danger'}" />
        <jsp:include page="../../../components/global/navbar.jsp" />
        <div class="container my-5">
            <h2 class="my-3">Question Review</h2>
            <div class="row">
                <div class="col-8">
                    <h3>Question no.${currentQuestion}</h3>
                    <h3>
                        ${pageItem.get(0)}
                    </h3>
                    <div class="mt-5">
                        <div class="row my-2">
                            <div class="col-6">
                                <div class="card w-100" style="height: 25vh">
                                    <div class="card-body <c:if test='${pageItem.get(5).equals(pageItem.get(1).trim())}'>${color}</c:if>">
                                        ${pageItem.get(1)}
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="card w-100" style="height: 25vh">
                                    <div class="card-body <c:if test='${pageItem.get(5).equals(pageItem.get(2).trim())}'>${color}</c:if>">
                                        ${pageItem.get(2)}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row my-2">
                            <div class="col-6">
                                <div class="card w-100" style="height: 25vh">
                                    <div class="card-body <c:if test='${pageItem.get(5).equals(pageItem.get(3).trim())}'>${color}</c:if>">
                                        ${pageItem.get(3)}
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="card w-100" style="height: 25vh">
                                    <div class="card-body <c:if test='${pageItem.get(5).equals(pageItem.get(4).trim())}'>${color}</c:if>">
                                        ${pageItem.get(4)}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mt-5">
                            <div class="row my-2">
                                <div class="col-12">
                                    <h4>
                                        <c:choose>
                                            <c:when test="${result}">
                                                You are correct. Point 1.0 / 1.0
                                            </c:when>
                                            <c:otherwise>
                                                You are incorrect. Point 0.0 / 1.0
                                            </c:otherwise>
                                        </c:choose>
                                    </h4>
                                </div>
                            </div>
                            <div class="row my-2">
                                <div class="col-12">
                                    <div class="card w-100">
                                        <div class="card-body">
                                            Explaination: ${pageItem.get(8)}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-4 d-flex align-items-start justify-content-end">
                    <c:forEach begin="0" items="${sessionScope.questionList}" varStatus="counter">
                        <a href="${path}/auth/user/user_quiz?operation=VIEWQUIZREVIEW&questionNum=${counter.index}"
                           class="btn btn-lg btn-outline-success text-decoration-none">
                            ${counter.index + 1}
                        </a>
                    </c:forEach>
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
