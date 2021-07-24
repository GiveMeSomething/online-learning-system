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
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${path}/style/styles.css">
        <link rel="stylesheet" type="text/css" href="${path}/style/setting.css">
    </head>
    <body>
        <div id="mySidebar" class="sidebar">
            <button id="closeNav" class="openbtn" onclick="closeNav()" style="display: none; margin-top: -4rem; margin-bottom: 2rem; margin-left: 13rem"><span style="text-transform: uppercase">X</span></button>
            <!--<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>-->
            <a href="${path}/auth/admin" onclick="userRoleOn()">User List</a>
            <hr>
            <a href="${path}/auth/admin/dashboard">Dashboard</a>
            <hr>
            <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST">Post List</a>
            <hr>
            <a href="${path}/auth/teacher/subject">Subject List</a>
            <hr>
            <a href="${path}/auth/admin/slider" style="background: white; color: black">Slider List</a>
        </div>
        <div style="background:#FFFFF0; display:flex;" class="py-2">
            <div>
                <button id="openNav" class="openbtn" onclick="openNav()" style="background: #FFFFF0; color: black">&#9776;</button>
            </div>
            <div style="justify-content: center; margin-left: 37rem">
                <a class="navbar-brand" style="font-size: 2.5rem;" href="${path}/home">
                    <span style="color:blue">O</span>
                    <span style="color:orange">L</span>
                    <span style="color:green">S</span>
                </a>
            </div>
            <div style="margin-left: 30rem; margin-top: 1rem">
                <a href="${path}/authenticate?operation=LOGOUT" style="padding-bottom: 5px; padding-top: 5px; border-bottom: 1px solid lightgray">
                    <button class="btn btn-secondary">Log out</button>
                </a>
            </div>
        </div>
        <div class="container my-2">
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
                <a href="${path}/auth/admin/slider/addNewSlider.jsp">
                    <button class="btn mb-2" style='background-color: #4caf50'
                            >
                        Add new
                    </button>
                </a>
            </div>
            <div id="slider-table">
                <table id="myTable" class="table table-light table-striped">
                    <thead>
                        <tr>
                            <th>
                                ID
                            </th>
                            <th>
                                Image
                            </th>
                            <th>
                                Title
                            </th>
                            <th >
                                Note
                            </th>
                            <th >
                                Backlink
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
                                <td>${o.backlink}</td>
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
                <c:set var="maxPage" value="${contentSize % 5 == 0 ? contentSize / 5: ((contentSize - contentSize % 5) / 5) + 1}" />
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
    <script type="text/javascript" src="${path}/utilities/tree-module.js"></script>
</html>
