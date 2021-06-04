<%-- 
    Document   : blogDetail
    Created on : May 30, 2021, 1:57:05 AM
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
        <h1>${post.title}</h1>
        <h5>Published on: ${post.updatedDate}</h5>
        <hr style="">
        <div class="container">
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
                <div class="col-8">
                    <article>
                        ${post.description}
                    </article>
                </div>
            </div> 
        </div>
        <h5>Written by: ${post.authorId}</h5>
        <hr>
    </body>
</html>
