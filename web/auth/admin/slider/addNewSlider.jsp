<%--
    Document   : addNewSlider
    Created on : Jul 18, 2021
    Author     : Dinh Kong Thanh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <!--        FontAwesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${path}/style/styles.css">
        <style>
            label{
                font-weight: 600
            }
        </style>
    </head>
    <body>
        <div class="container-fluid" style="width:85%;margin:0 auto;font-size: 19px; margin-top: -1rem">
            <h3 class="text-center mt-3">Add New Slider</h3>
            <label for="image" style="margin-left: 5rem; margin-bottom: -2rem">
                Image
            </label>

            <!--UPLOAD SLIDER IMAGE-->
            <div style="width: 85%;margin: 0 auto; margin-bottom: 1.2rem">
                <form class="mt-3" action="${path}/auth/admin/slider?operation=ADDIMAGE" method="POST" enctype="multipart/form-data">
                    <div class="d-flex align-items-center">
                        <label style="background: #6A5ACD;margin-bottom: 0;
                               text-align:center;padding:5px;width: 146px;height:34px;
                               border-radius:5px;color:white;font-size:17px" for="file-upload" class="custom-file-upload">
                            Add Image
                            <input style="color:transparent;opacity: 0;display: none"
                                   type="file" name="photo" value="" id="file-upload" /></label>
                        <div style="margin-left: 10px">
                            <button style="width: 146px;height: 34px;padding: 5px" class="btn btn-success" type="submit" value="Save">
                                Preview Image
                            </button>            
                        </div>
                    </div>
                </form>
            </div>
            <form action="${path}/auth/admin/slider" method="post" style="width: 85%;margin:0 auto;font-size: 19px">
                <div class="request-info">
                    <input name="previousPage" value="home" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="ADDSLIDER" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <input name="image"
                           type="text"
                           class="form-control mb-3"
                           id="image"
                           value="${image}"
                           placeholder="Add Image Here"
                           data-value-missing="Can't be empty"
                           required
                           readonly>
                    <c:if test="${image != null}">
                        <div>
                            <img style="width: 100%;height:350px"
                                 src="${path}/assets/bannerImg/${image}"/> 
                        </div> 
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="title">
                        Title
                    </label>
                    <input name="title"
                           type="text"
                           class="form-control"
                           id="title"
                           value="${title}"
                           data-value-missing="Can't be empty"
                           required>
                </div>
                           <div class="form-group">
                               <label for="title">
                                   Backlink
                               </label>
                               <input name="backlink"
                                      type="text"
                                      class="form-control"
                                      id="backlink"
                                      value=""
                                      data-value-missing="Can't be empty"
                                      required>
                           </div>
                <div class="form-group">
                    <label for="status">
                        Status
                    </label>
                    <select name="status" class="form-control">
                        <option ${status == "ACTIVE" ? "selected":""} value="1">Active</option>
                        <option ${status == "INACTIVE" ? "selected":""} value="0">Inactive</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="notes">
                        Notes
                    </label>
                    <textarea name="notes"
                              type="text"
                              class="form-control"
                              id="notes"
                              value="${note}"
                              placeholder="${note}"
                              data-value-missing="Can't be empty"
                              required></textarea>
                </div>
                <button style="background-color: #FF8C00;color:white"
                        type="submit" class="btn btn-warning">Add Slider</button> 
            </form>
                              <div class="text-right mb-2" style="margin-top: -2.5rem;margin-right: 5rem">
                <a href="${path}/auth/admin/slider">
                    <button class="btn btn-success">Back</button>
                </a>
            </div>

        </div>
    </body>
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
</html>