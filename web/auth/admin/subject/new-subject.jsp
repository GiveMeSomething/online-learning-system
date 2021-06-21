<%--
    Document   : new-subject
    Created on : Jun 13, 2021
    Author     : Hoang Tien Minh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Page used by Administrator(s) to create new Subject-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set value="${pageContext.request.contextPath}" var="path" />
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <title>Add Subject</title>
    </head>

    <body>
        <div class="container">
            <h1>New Subject</h1>
            <form action="/auth/admin/subject" method="POST" class="needs-validatation" enctype="multipart/form-data" novalidate>
                <div class="request-info">
                    <input name="previousPage" value="home.jsp" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="ADDNEWSUBJECT" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-row">
                    <div class="mb-3 col-md-6">
                        <label for="subject-name">Subject Name</label>
                        <input class="form-control" name="subject-name" type="text" id="subject-name"
                               placeholder="Enter Subject Name" data-value-missing="Can't be empty" required />
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <label for="myfile">Select a file:</label>
                <input type="file" id="myfile" name="thumbnail" size="50"><br><br>
                <div class="form-row">
                    <div class="mb-3 col-md-6">
                        <label for="category">Category</label>
                        <select class="custom-select" id="category"  name="category" required>
                            <c:forEach items="${category}" var="c">
                                <option value="${c.id}">${c.categoryName}</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="mb-3 col-md-6">
                        <label for="owner">Owner</label>
                        <select class="custom-select" id="owner"  name="owner" required>
                            <c:forEach items="${owner}" var="o">
                                <option value="${o.key}">${o.value}</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input" name="featured" 
                               id="featured" value="true" checked required>
                        <label class="custom-control-label" for="featured">Featured subject</label>
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="mb-3">
                        <label for="status">Status</label>
                        <select class="custom-select" id="status" name="status" required>
                            <option value="PUBLISHED">Published</option>
                            <option value="UNPUBLISHED">Unpublished</option>
                        </select>
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" name="description" placeholder="Content" required></textarea>
                    <div class="invalid-feedback"></div>
                </div>
                <button class="btn btn-primary" type="submit">Submit</button>
                <a role="button" class="btn btn-primary">Cancel</a>
            </form>
        </div>
        <!-- boostrap -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
                integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
        crossorigin="anonymous"></script>
        <script src="${path}/utilities/form-validator.js"></script>
    </body>

</html>