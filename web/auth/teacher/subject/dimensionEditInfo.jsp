<%--
    Document   : dimensionEditInfo
    Created on : Jun 16, 2021
    Author     : Nguyen Khanh Toan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${path}/style/subject.css">
    </head>
    <body>
        <div class="container">
            <h3 class="font-weight-bold text-center mt-5">Edit subject dimesion</h3>
            <form action="subject?dimensionId=${dimensionId}"
                  method="post">
                <div class="request-info">
                    <input name="previousPage" value="home" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="UPDATESUBJECT" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label class="d-block text-left" for="id">
                        Id
                    </label>
                    <input name="id"
                           type="text"
                           value="${dimensionDetail.id}"
                           class="form-control"
                           disabled
                           id="id"
                           data-value-missing="Can't be empty"
                           required>
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label class="d-block text-left" for="type">
                        Type
                    </label>
                    <input name="type"
                           type="text"
                           value="${dimensionDetail.type}"
                           class="form-control"
                           id="type"
                           data-value-missing="Can't be empty"
                           required>
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label class="d-block text-left" for="dimension">
                        Dimension
                    </label>
                    <input name="dimension"
                           type="text" 
                           value="${dimensionDetail.name}"
                           class="form-control"
                           id="dimension"
                           data-value-missing="Can't be empty"
                           required>
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label class="d-block text-left"
                           for="description">
                        Description
                    </label>
                    <textarea rows="5"
                              name="description"
                              type="text"
                              style="font-family: inherit"
                              class="form-control"
                              id="description"
                              data-value-missing="Can't be empty"
                              required>${dimensionDetail.description}</textarea>
                    <div class="invalid-feedback"></div>
                </div>

                <button class="btn" style='background-color: #ff9800' type="submit">
                    Update
                </button> 
            </form>

        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@4.6.0/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
            crossorigin="anonymous">
    </script>
    <script src="${path}/utilities/form-validator.js"></script>
</html>