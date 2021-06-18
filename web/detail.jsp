<%--
    Document   : subjectDetail
    Created on : Jun 15, 2021
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
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${path}/style/subject.css">
    </head>
    <body>        
        <div class="container container-fluid">
            <h3 class="mt-2 mb-3">Subject Details</h3> 
            <div>
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link ${activeId == 1 ? "active":""}"
                           id="overview-tab" 
                           data-toggle="tab"
                           href="#overview"
                           role="tab" 
                           aria-controls="overview"
                           aria-selected="true">
                            Overview
                        </a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link ${activeId == 2 ? "active":""}"
                           id="dimension-tab"
                           data-toggle="tab"
                           href="#dimension"
                           role="tab"
                           aria-controls="dimension"
                           aria-selected="false">
                            Dimension
                        </a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link ${activeId == 3 ? "active":""}"
                           id="pricepackage-tab"
                           data-toggle="tab"
                           href="#pricepackage"
                           role="tab"
                           aria-controls="pricepackage"
                           aria-selected="false">
                            Price package
                        </a>
                    </li>
                </ul> 
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade ${activeId == 1 ? "show active":""}"
                     id="overview"
                     role="tabpanel"
                     aria-labelledby="overview-tab">
                    wazzup
                </div>
                <div class="tab-pane fade ${activeId == 2 ? "show active":""}"
                     id="dimension"
                     role="tabpanel"
                     aria-labelledby="dimension-tab">      
                    <div style='text-align: right'>
                        <button class="btn mb-2" style='background-color: #4caf50'
                                data-toggle="modal" data-target="#AddSubject">
                            Add new
                        </button>
                        <div class="modal fade" id="AddSubject">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="font-weight-bold">Add new subject dimesion</h5>
                                        <button data-dismiss="modal" class="close">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${path}/subject?operation=ADDSUBJECT"
                                              method="post">
                                            <div class="form-group">
                                                <label class="d-block text-left" for="type">
                                                    Type
                                                </label>
                                                <input name="type" type="text" class="form-control"
                                                       id="type">
                                            </div>
                                            <div class="form-group">
                                                <label class="d-block text-left" for="dimension">
                                                    Dimension
                                                </label>
                                                <input name="dimension" type="text" class="form-control"
                                                       id="dimension">
                                            </div>
                                            <div class="form-group">
                                                <label class="d-block text-left"
                                                       for="description">
                                                    Description
                                                </label>
                                                <textarea rows="5" name="description" type="text" class="form-control"
                                                          id="description"></textarea>
                                            </div>
                                            <div class="modal-footer myModalFooter">
                                                <button class="btn btn-success" type="submit">
                                                    Add
                                                </button> 
                                                <button data-dismiss="modal" class="btn btn-danger">
                                                    Close
                                                </button>
                                            </div>
                                        </form>


                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>


                    <table class="table table-bordered">
                        <thead>
                            <tr style='background-color: rgb(204,204,204)'>
                                <th style='border-bottom: 0px;width:5%' scope="col">#</th>
                                <th style='border-bottom: 0px;width:10%' scope="col">Type</th>
                                <th style='border-bottom: 0px;width:70%' scope="col">Dimension</th>
                                <th style='border-bottom: 0px' scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listDimension}" var="o">
                                <tr>
                                    <th scope="row">${o.id}</th>
                                    <td>${o.type}</td>
                                    <td>${o.name}</td>
                                    <td>
                                        <a style="color:white;" href="${path}/subject?operation=GETDIMENSIONDETAIL&&dimensionId=${o.id}">
                                            <button class="btn mb-2" style='background-color: #ff9800;color:white'>
                                                Edit
                                            </button>
                                        </a>
                                        <a href="${path}/subject?operation=DELETESUBJECT&&courseId=1&&dimensionId=${o.id}">
                                            <button class="btn btn-danger mb-2">
                                                Delete
                                            </button>
                                        </a>

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="tab-pane fade ${activeId == 3 ? "show active":""}"
                     id="pricepackage"
                     role="tabpanel"
                     aria-labelledby="pricepackage-tab">
                    what's up
                </div>
            </div>
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
</html>