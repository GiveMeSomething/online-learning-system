<%--
    Document   : slider-list
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
        <title>Slider List</title>
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
        <div class="container my-5">
            <div class="row">
                <div class="d-flex justify-content-center align-items-center">
                    <h2 style="margin-left: 1rem">Slider List</h2>
                </div>
            </div>
            <div class="row">
                <form action="${path}/auth/admin/slider?operation=FILTERSLIDER"
                      method="POST">
                    <div class="my-2 d-flex justify-content-center align-items-center gap-3">
                        <div>
                            <input class="form-control"
                                   name="keyword"
                                   type="text"
                                   id="keyword"
                                   value="${requestScope.selectedKeyword != null ? requestScope.selectedKeyword: ''}"
                                   placeholder="Search subjects"
                                   style="width: 70vh; margin-left: 1rem"/>
                        </div>
                        <div id="status" class="d-flex justify-content-center align-items-center m-2">
                            <div class="mx-2">Status</div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input
                                    type="radio"
                                    id="active"
                                    name="status"
                                    class="custom-control-input"
                                    value="ACTIVE"
                                    ${requestScope.selectedStatus == "ACTIVE" ? 'checked': ''}>
                                <label class="custom-control-label" for="active">ACTIVE</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="inactive"
                                       name="status"
                                       class="custom-control-input"
                                       value="INACTIVE"
                                       ${requestScope.selectedStatus == "INACTIVE" ? 'checked': ''}>
                                <label class="custom-control-label" for="inactive">INACTIVE</label>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="custom-control custom-radio custom-control m-2">
                                <input type="radio"
                                       id="all"
                                       name="status"
                                       class="custom-control-input"
                                       value=""
                                       ${requestScope.selectedStatus == null || requestScope.selectedStatus == '' ? 'checked': ''}>
                                <label class="custom-control-label" for="all">Both</label>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                    <!--                    <div class="add-lesson">
                                            <a role="button" class="btn btn-success px-3 py-2" 
                                               href="path}/auth/teacher/subject?operation=TONEWSUBJECT">
                                                Add new Subject
                                            </a>
                                        </div>-->
                </form>
            </div>
            <div style='text-align: left' id="add-new-slider">
                <button class="btn mb-2" style='background-color: #4caf50'
                        data-toggle="modal" data-target="#AddSubject">
                    Add new
                </button>
                <div class="modal fade" id="AddSubject">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="font-weight-bold">Add new slider</h5>
                                <button data-dismiss="modal" class="close">&times;</button>
                            </div>
                            <div class="modal-body">
                                <form action="${path}/auth/admin/slider?operation=ADDSLIDER"
                                      method="post" class="needs-validatation" 
                                      enctype="multipart/form-data" 
                                      novalidate>
                                    <div class="form-group">
                                        <label class="d-block text-left" for="image">
                                            Image
                                        </label>
                                        <input name="image"
                                               type="text"
                                               class="form-control"
                                               id="image"
                                               data-value-missing="Can't be empty"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label class="d-block text-left" for="title">
                                            Title
                                        </label>
                                        <input name="title"
                                               type="text"
                                               class="form-control"
                                               id="title"
                                               data-value-missing="Can't be empty"
                                               required>
                                    </div>
                                    <div class="form-group">
                                        <label class="d-block text-left"
                                               for="note">
                                            Note
                                        </label>
                                        <textarea rows="5"
                                                  name="note"
                                                  type="text"
                                                  class="form-control"
                                                  id="note"
                                                  data-value-missing="Can't be empty"
                                                  required></textarea>
                                    </div>
                                    <div class="form-row">
                                        <label for="status" style="margin-right: .5rem; margin-left: .2rem">Status</label>
                                        <select class="custom-select" id="status" name="status" style="width: max-content; margin-bottom: 1rem" required>
                                            <option value="ACTIVE">ACTIVE</option>
                                            <option value="INACTIVE">INACTIVE</option>
                                        </select>
                                        <div class="invalid-feedback"></div>
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
            <div id="slider-table">

                <table id="myTable" class="table table-light table-striped">
                    <thead>
                        <tr>
                            <th>
                                ID
                            </th>
                            <th>
                                image
                            </th>
                            <th>
                                title
                            </th>
                            <th > 
                                note
                            </th>
                            <th >
                                Status
                            </th>
                            <th >
                                Action
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.pageItems}" var="o">
                            <tr>
                                <td>${o.id}</td>
                                <td><img src="${path}/assets/bannerImg/${o.image}" style="width: 50%"/></td>
                                <td>${o.title}</td>
                                <td>${o.note}</td>
                                <td>${o.status}</td>
                                <td>
                                    <a href="${path}/auth/admin/slider?operation=VIEWDETAIL&sliderId=${o.id}" style="width: 5rem; font-size: 10px; font-weight: bold">
                                        <button class="btn btn-secondary mb-2">
                                            View&Edit
                                        </button>
                                    </a>
                                    <a href="${path}/auth/admin/slider?operation=DELETESLIDER&sliderId=${o.id}">
                                        <button class="btn btn-danger mb-2 px-4">
                                            Delete
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <nav aria-label="Page navigation example">
                <c:set var="contentSize" value="${sessionScope.sliderList.size()}" />
                <c:set var="maxPage" value="${((contentSize - contentSize % 5) / 5) + 1}" />
                <c:set var="currentPage" value="${pageContext.request.getParameter('page')}" />
                <c:set var="prevPage" value="${currentPage == null ? 1 : currentPage - 1}" />
                <c:set var="nextPage" value="${currentPage == null ? 2 : currentPage + 1}"/>
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="${path}/auth/admin/slider?operation=PAGINATION&page=${prevPage > 0 ? prevPage: 1}">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${maxPage}" varStatus="counter">
                        <li class="page-item">
                            <a class="page-link"
                               href="${path}/auth/admin/slider?operation=PAGINATION&page=${counter.index}">
                                ${counter.index}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}/auth/admin/slider?operation=PAGINATION&page=${nextPage > maxPage ? maxPage: nextPage}">
                            Next
                        </a>
                    </li>
                </ul>
            </nav>
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
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
</html>
