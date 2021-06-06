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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
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
                        <c:forEach var="o" items="${hmCategory}">
                            <a href="PostsByCate?cateId=${o.key}&curPage=1">${o.value}</a>
                        </c:forEach>
                    </div>
                    <div class="lastest-post">
                        <h2>Top 3 latest posts</h2>
                        <hr>
                        <c:forEach var="o" items="${latest}">
                            <a href="BlogDetail?id=${o.value.postId}">${o.value.title}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-lg-8">
                    <c:forEach var="o" items="${hmPost}">
                        <div class="blog" ${o.statusId == 1?"":"style='display:none;'"}>
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
                        <c:if test="${requestScope.flag == 0}">
                            <li class="page-item ${curPage == i?"active":""}">
                                <a class="page-link" href="BlogController?curPage=${i}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${requestScope.flag == 1}">
                            <li class="page-item ${curPage == i?"active":""}">
                                <a class="page-link" href="BlogController?curPage=${i}&flag=1&cateId=${requestScope.cateId}">${i}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </body>
</html>
