<%-- 
    Document   : blogDetail
    Created on : May 30, 2021, 1:57:05 AM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
                  integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
            <title>${requestScope.post.title}</title>
    </head>
    <body>
        <!--header-->
        <jsp:include page="../components/global/navbar.jsp"/>
        <main>
            <div class="container">
                <!-- breadcrumb -->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item"><a href="BlogController">Blog</a></li>
                        <li class="breadcrumb-item" aria-current="page">${requestScope.post.title}</li>
                    </ol>
                </nav>
                <!-- aside left -->
                <div class="row">
                    <div class="col-md-4">
                        <div class="row">
                            <div class="col-12">
                                <h3>Category</h3>
                                <hr>
                                <ul class="list-group list-group-flush shadow-sm">
                                    <c:forEach var="o" items="${hmCategory}">
                                        <a href="PostsByCate?cateId=${o.key}&curPage=1" 
                                           class="list-group-item list-group-item-action 
                                           ${cateId == o.key?"active":""}">${o.value}</a>
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
                    <div class="col-8 bg-light">
                        <article class="p-3">
                            <h1 style="text-align: center;">${post.title}</h1>
                            <h3 class="mt-5" style="text-align: end;">Published date: ${post.updatedDate}</h3>
                            <hr>
                            <p style="white-space: pre-line;">
                                ${post.description}
                            </p>
                            <h3  style="text-align: end;">Written by: ${post.authorId}</h3>
                            <hr>
                        </article>
                    </div>
                </div>
            </div>
        </main>
    </body>
    <!--footer-->
    <jsp:include page="../components/global/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous">
    </script>
    <!-- Import if have form input -->
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
</html>
