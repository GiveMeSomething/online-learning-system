<%-- 
    Document   : blog
    Created on : May 29, 2021, 1:52:31 PM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Latest Blog Post</h1>
            <div class="row">
                <div class="col-4">
                    <form method="POST" action="../">
                        <input type="text" placeholder="Post searching">
                        <input type="submit">
                    </form>
                    <div class="category">
                        <h2>Category</h2>
                        <hr>
                        <c:forEach var="o" items="${hmCat}">
                            <h6>${o.value}</h6>
                        </c:forEach>
                    </div>
                    <div class="lastest-post">
                        <h2>Top 3 latest posts</h2>
                        <hr>
                        <c:forEach var="o" items="${latest}">
                            <h6>${o.value.title}</h6>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-lg-8">
                    <c:forEach var="o" items="${hmPost}">
                        <div class="blog">
                            <a href="BlogDetail?id=${o.postId}">
                                <div class="row">
                                    <h3>${o.title}</h3>
                                </div>
                                <div class="row">
                                    <img src="${o.thumbnail}" class="col-4" alt="thumbnail" width="100" height="100">
                                    <div class="col-lg-8">
                                        <p class="desc">${o.briefInfo}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <nav>
                <ul class="pagination">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${requestScope.nOfPage}" var="i">
                        <li class="page-item ${curPage == i?"active":""}">
                            <a class="page-link" href="BlogPagination?curPage=${i}">${i}</a>
                        </li>
                        </c:forEach>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </body>
</html>
