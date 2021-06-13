<%--
    Document   : blog
    Created on : May 29, 2021, 1:52:31 PM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
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
        <jsp:include page="../../components/global/navbar.jsp"/>
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
                                <form class="form-inline" method="POST" action="blog">
                                    <div class="request-info">
                                        <input name="previousPage" value="blogList.jsp" hidden="true" />
                                        <div class="invalid-feedback"></div>
                                        <input name="operation" value="SearchByTitle" hidden="true" />
                                        <div class="invalid-feedback"></div>
                                    </div>
                                    <input class="form-control mr-sm-2" type="search" name="title" 
                                           value="${title}" placeholder="Search" aria-label="Search">
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <h3>Category</h3>
                                <hr>
                                <ul class="list-group list-group-flush shadow-sm">
                                    <c:forEach var="o" items="${hmCategory}">
                                        <a href="blog?operation=postByCategory&cateId=${o.key}&curPage=1" 
                                           class="list-group-item list-group-item-action ${categoryId==o.key?"active":""}">${o.value}</a>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <h3 class="mt-4">Top 3 Latest Posts</h3>
                            <div class="col-12">
                                <ul class="list-group list-group-flush shadow-sm">
                                    <c:forEach items="${latest}" var="o">
                                        <a href="blog?operation=blogDetail&id=${o.value.postId}" 
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
                                        <img src="${o.thumbnail}" class="mx-5" style="width: 8rem; height: 8rem" alt="thumbnail">
                                        <div>
                                            <h5 class="mt-0">${o.title}</h5>
                                            <p>${o.briefInfo}</p>
                                            <a href="blog?operation=blogDetail&id=${o.postId}" class="stretched-link">Read more</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <nav aria-label="Blog-pagination">
                                    <ul class="pagination justify-content-center">
                                        <c:if test="${requestScope.categoryId == null && title == null}">
                                            <li class="page-item ${curPage == 1?"disabled":""}">
                                                <a class="page-link" href="blog?curPage=${curPage - 1}" 
                                                   tabindex="-1">Previous</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.categoryId != null}">
                                            <li class="page-item ${curPage == 1?"disabled":""}">
                                                <a class="page-link" 
                                                   href="blog?curPage=${curPage - 1}&operation=postByCategory&cateId=${requestScope.categoryId}" 
                                                   tabindex="-1">Previous</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.title != null && categoryId == null}">
                                            <li class="page-item ${curPage == 1?"disabled":""}">
                                                <a class="page-link" 
                                                   href="blog?curPage=${curPage - 1}&operation=SearchByTitle&title=${title}" 
                                                   tabindex="-1">Previous</a>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${requestScope.nOfPage}" var="i">
                                            <c:if test="${requestScope.categoryId == null && title == null}">
                                                <li class="page-item ${curPage == i?"active":""}">
                                                    <a class="page-link" href="blog?curPage=${i}">${i}</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${requestScope.categoryId != null}">
                                                <li class="page-item ${curPage == i?"active":""}">
                                                    <a class="page-link" 
                                                       href="blog?curPage=${i}&operation=postByCategory&cateId=${requestScope.categoryId}">${i}</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${requestScope.categoryId == null && title != null}">
                                                <li class="page-item ${curPage == i?"active":""}">
                                                    <a class="page-link" 
                                                       href="blog?curPage=${i}&operation=SearchByTitle&title=${title}">${i}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${requestScope.categoryId == null && title == null}">        
                                            <li class="page-item ${curPage == nOfPage?"disabled":""}">
                                                <a class="page-link" href="blog?curPage=${curPage + 1}">Next</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.categoryId != null}">        
                                            <li class="page-item ${curPage == nOfPage?"disabled":""}">
                                                <a class="page-link" 
                                                   href="blog?curPage=${curPage + 1}&operation=postByCategory&cateId=${requestScope.categoryId}">Next</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${requestScope.categoryId == null && title != null}">        
                                            <li class="page-item ${curPage == nOfPage?"disabled":""}">
                                                <a class="page-link" 
                                                   href="blog?curPage=${curPage + 1}&operation=SearchByTitle&title=${title}">Next</a>
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
        <jsp:include page="../../components/global/footer.jsp"/>
    </body>
    <!--javascript-->
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
    <!-- Import if have form input -->
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>

</html>
