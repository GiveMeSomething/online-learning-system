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
        <link rel="stylesheet" href="${path}/style/subject.css">
        <title>Add Subject</title>
    </head>
    <body id="new-subject-body" style="background-color: hsl(120,0%,90%); margin-top: 50px">
        <div class="container">
            <h1 class="text-info">New Subject</h1>
            <figure class="figure" style="position: absolute; top: 124px; right: 231px;">
                <img id="my-img" class="rounded" alt="preview image" src="${path}/assets/preview_image.jpg" 
                     height="250px" width="400px"/>
                <figcaption class="figure-caption text-center mt-2">Preview Subject thumbnail</figcaption>
            </figure>
            <form action="${path}/auth/teacher/subject" method="POST" 
                  class="needs-validatation p-3" style="background-color: hsl(42, 80%, 75%)" 
                  enctype="multipart/form-data" novalidate>
                <div class="request-info">
                    <input name="previousPage" value="${path}/auth/teacher/subject" hidden="true" />
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
                <input type="file" onchange="previewFile()" accept="image/*" id="myfile" name="thumbnail" size="50" required="true"><br><br>
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
                    <div class="input-group mb-3 col-md-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="owner">Owner</label>
                        </div>
                        <select class="custom-select" id="owner" name="owner" required>
                            <c:forEach items="${owner}" var="o">
                                <option value="${o.key}">${o.value}</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="input-group mb-3 col-md-2">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="status">Status</label>
                        </div>
                        <select class="custom-select" id="status" name="status" required>
                            <option value="PUBLISHED">Published</option>
                            <option value="UNPUBLISHED">Unpublished</option>
                        </select>
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="custom-control col-md-2 custom-checkbox mb-3 ml-4 mt-2">
                        <input type="checkbox" class="custom-control-input" name="featured" 
                               id="featured" value="true" checked required>
                        <label class="custom-control-label" for="featured">Featured subject</label>
                        <div class="invalid-feedback"></div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" name="description" placeholder="Content" required></textarea>
                    <div class="invalid-feedback"></div>
                </div>
                <button class="btn btn-primary" type="submit">Submit</button>
                <a role="button" class="btn btn-primary" href="${path}/auth/teacher/subject">Cancel</a>
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
        <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
        <script type="text/javascript" src="${path}/ckeditor/ckeditor/ckeditor.js"></script>
        <script type="text/javascript">
                    function previewFile() {
                        const preview = document.querySelector('img');
                        const file = document.querySelector('input[type=file]').files[0];
                        const reader = new FileReader();

                        reader.addEventListener("load", function () {
                            // convert image file to base64 string
                            preview.src = reader.result;
                        }, false);

                        if (file) {
                            reader.readAsDataURL(file);
                        }
                    }
                    CKEDITOR.replace('description');
        </script>
    </body>
</html>