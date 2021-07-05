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
        <title>Add Post</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            .form-select {
                width: 100%;
                border-radius: 5px;
            }
        </style>
        <jsp:useBean id="dal" scope="page" class="blog.BlogRepository" />
    </head>
    <body>
        <form action="${path}/auth/admin/admin_blog?operation=ADDPOST" method="POST">
            <div class="container p-3 my-3 bg-dark text-white">
                <div class="row">
                    <div class="col-sm-6">
                        <h3>Add POST</h3>
                        <div class="form-group">
                            <label for="usr">Category </label>
                            <select class="form-select" name="category">
                                <c:forEach var="o" items="${dal.allCategory}">
                                    <option value="${o.id}">${o.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="usr">Title </label>
                            <input type="text"  name="titlepost" class="form-control" id="usr">
                        </div>

                        <div class="form-group">
                            <label for="comment">Description:</label>
                            <textarea class="form-control" rows="5" id="comment" name="description"></textarea>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox" class="form-check-input" name="feature" value="ON">Feature
                            </label>
                            <select class="form-select" style="float: right;width: 50%;" name="status">
                                <option value="1">Active</option>
                                <option value="0">Inactive</option>
                            </select>
                            <p class="text-primary" style="float: right; margin-right: 10px;">Status</p>
                        </div>
                        <button type="submit" class="btn btn-success" style="margin-top: 40px; width: 100px;">Save</button>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="usr"> Thumbnail Link</label>
                            <input type="text"  name="thumbnail" class="form-control" id="usr">
                        </div>
                        <div class="form-group" style="">
                            <label for="comment">Brief Information:</label>
                            <textarea class="form-control" rows="5" name="brief" id="comment"></textarea>
                        </div>
                        <button type="button" class="btn btn-secondary" style="margin-top: 180px; width: 100px;float: right;">
                            <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST" style="color: white;text-decoration: none;">
                                Cancel
                            </a>
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
