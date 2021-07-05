<%--
    Document   : slider-detail
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
        <title>Slider Detail</title>
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
        <div class="container-fluid" style="width:85%;margin:0 auto;font-size: 19px">
            <h3 class="text-center mt-3">Slider Details</h3>
            <form action="${path}/auth/admin/slider" method="post" style="width: 85%;margin:0 auto;font-size: 19px">
                <div class="request-info">
                    <input name="previousPage" value="home" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="UPDATESLIDER" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="sliderId">
                        Slider id
                    </label>
                    <input name="sliderId"
                           type="text"
                           class="form-control"
                           id="sliderId"
                           value="${sliderDetail.id}"
                           disabled
                           data-value-missing="Can't be empty"
                           required>
                </div>
                <div class="form-group">
                    <label for="image">
                        Image
                    </label>
                    <input name="image"
                           type="text"
                           class="form-control mb-3"
                           id="image"
                           value="${image}"
                           data-value-missing="Can't be empty"
                           required>
                    <div>
                       <img style="width: 100%;height:350px"
                         src="${path}/assets/bannerImg/${image}"/> 
                    </div>
                    
                </div>
                <div class="form-group">
                    <label for="title">
                        Title
                    </label>
                    <input name="title"
                           type="text"
                           class="form-control"
                           id="title"
                           value="${sliderDetail.title}"
                           data-value-missing="Can't be empty"
                           required>
                </div>
                <div class="form-group">
                    <label for="status">
                        Status
                    </label>
                    <select name="status" class="form-control">
                        <option ${sliderDetail.status == "ACTIVE" ? "selected":""} value="1">Active</option>
                        <option ${sliderDetail.status == "INACTIVE" ? "selected":""} value="0">Inactive</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="notes">
                        Notes
                    </label>
                    <input name="notes"
                           type="text"
                           class="form-control"
                           id="notes"
                           value="${sliderDetail.note}"
                           data-value-missing="Can't be empty"
                           required>
                </div>
                <button style="background-color: #FF8C00;color:white" type="submit" class="btn btn-warning">Update Question</button> 
            </form>
            <!--UPLOAD SLIDER IMAGE-->
            <div style="width: 85%;margin: 0 auto">
                <form class="mt-3" action="${path}/auth/admin/slider?operation=UPLOADIMAGE" method="POST" enctype="multipart/form-data">
                    <div class="d-flex align-items-center">
                        <label style="background: #6A5ACD;margin-bottom: 0;
                               text-align:center;padding:5px;width: 146px;height:34px;
                               border-radius:5px;color:white;font-size:17px" for="file-upload" class="custom-file-upload">
                            Upload Slider Image
                            <input style="color:transparent;opacity: 0;display: none"
                                   type="file" name="photo" value="" id="file-upload" /></label>
                        <div style="margin-left: 10px">
                            <button style="width: 146px;height: 34px;padding: 5px" class="btn btn-success" type="submit" value="Save">
                                Update Slider Image
                            </button>            
                        </div>
                    </div>
                </form>
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
