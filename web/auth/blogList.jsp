<%-- 
    Document   : blog
    Created on : May 29, 2021, 1:52:31 PM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" 
              crossorigin="anonymous">
        <title>Blog</title>
    </head>
    <body>
        <jsp:include page="../components/global/navbar.jsp"/>
        <main>
            <div class="container">
                <!-- breadcrumb -->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Blog</li>
                    </ol>
                </nav>
                <div class="row">
                    <!-- left -->
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-12">
                                <h3>Category</h3>
                                <hr>
                                <ul class="list-group list-group-flush shadow-sm">
                                    <c:forEach var="o" items="${hmCategory}">
                                        <a href="PostsByCate?cateId=${o.key}&curPage=1" 
                                           class="list-group-item list-group-item-action ${cateId==o.key?"active":""}">${o.value}</a>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <h3 class="mt-4">Top 3 Latest Posts</h3>
                            <div class="col-12">
                                <ul class="list-group list-group-flush shadow-sm">
                                    <c:forEach items="${latest}" var="o">
                                        <a href="BlogDetail?id=${o.value.postId}" 
                                           class="list-group-item list-group-item-action">${o.value.title}</a>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- right -->
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-12">
                                <c:forEach items="${hmPost}" var="o">
                                    <div class="d-flex position-relative my-3 py-3 bg-light shadow-lg">
                                        <img src="${o.thumbnail}" class="mx-5" style="width: 200px; height: 200px" alt="thumbnail">
                                        <div>
                                            <h5 class="mt-0">${o.title}</h5>
                                            <p>${o.briefInfo}</p>
                                            <a href="BlogDetail?id=${o.postId}" class="stretched-link">Read more</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <nav aria-label="Blog-pagination">
                                    <ul class="pagination justify-content-center">
                                        <c:if test="${requestScope.flag == 0}">
                                            <li class="page-item ${curPage == 1?"disabled":""}">
                                                <a class="page-link" href="BlogController?curPage=${curPage - 1}" 
                                                   tabindex="-1">Previous</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.flag == 1}">
                                            <li class="page-item ${curPage == 1?"disabled":""}">
                                                <a class="page-link" 
                                                   href="BlogController?curPage=${curPage - 1}&flag=1&cateId=${requestScope.cateId}" tabindex="-1">Previous</a>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${requestScope.nOfPage}" var="i">
                                            <c:if test="${requestScope.flag == 0}">
                                                <li class="page-item ${curPage == i?"active":""}">
                                                    <a class="page-link" href="BlogController?curPage=${i}">${i}</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${requestScope.flag == 1}">
                                                <li class="page-item ${curPage == i?"active":""}">
                                                    <a class="page-link" 
                                                       href="BlogController?curPage=${i}&flag=1&cateId=${requestScope.cateId}">${i}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${requestScope.flag == 0}">        
                                            <li class="page-item ${curPage == nOfPage?"disabled":""}">
                                                <a class="page-link" href="BlogController?curPage=${curPage + 1}">Next</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.flag == 1}">        
                                            <li class="page-item ${curPage == nOfPage?"disabled":""}">
                                                <a class="page-link" 
                                                   href="BlogController?curPage=${curPage + 1}&flag=1&cateId=${requestScope.cateId}">Next</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <!--footer-->
        <jsp:include page="../components/global/footer.jsp"/>
    </body>
    <!--javascript-->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous">
    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>
    <!-- Import if have form input -->
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>

</html>