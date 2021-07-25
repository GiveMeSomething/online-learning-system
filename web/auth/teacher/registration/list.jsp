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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage</title>
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
        <c:if test="${sessionScope.isTeacher != true}">
            <div id="mySidebar" class="sidebar">
                <button id="closeNav" class="openbtn" onclick="closeNav()" style="display: none; margin-top: -4rem; margin-bottom: 2rem; margin-left: 13rem"><span style="text-transform: uppercase">X</span></button>
                <!--<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>-->
                <a href="${path}/auth/admin" >User List</a>
                <hr>  
                <a href="${path}/auth/admin/dashboard">Dashboard</a>
                <hr>   
                <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST">Post List</a>
                <hr>                                                             
                <a href="${path}/auth/teacher/subject">Subject List</a>
                <hr>
                <a href="${path}/auth/admin/slider">Slider List</a>
                <hr>
                <a href="${path}/auth/teacher/registration?operation=VIEWALL" style="background: white; color: black">Registration List</a>
            </div>
        </c:if>
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
        <c:set var="contentSize" value="${sessionScope.registrationList.size()}" />
        <c:set var="maxPage" value="${((contentSize - contentSize % 5) / 5) + 1}" />
        <c:set var="currentPage" value="${pageContext.request.getParameter('page') == null ? 1 : pageContext.request.getParameter('page')}" />
        <c:set var="prevPage" value="${currentPage == null ? 1 : currentPage - 1}" />
        <c:set var="nextPage" value="${currentPage == null ? 2 : currentPage + 1}"/>
        <div class="container mb-5">
            <div class="row">
                <div class="col-12 d-flex justify-content-between flex-row">
                    <h2>Registration List</h2>
                    <h2 class="text-danger ml-auto">${mess}</h2>
                </div>
            </div>
            <form action="${path}/auth/teacher/registration" method="POST" style="min-height: 70vh">
                <div class="row">
                    <div class="request-info">
                        <input name="previousPage" value="${path}/auth/teacher/subject" hidden="true" />
                        <div class="invalid-feedback"></div>
                        <input name="operation" value="FILTER" hidden="true" />
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="my-2 d-flex justify-content-center align-items-center gap-3">
                        <div>
                            <input class="form-control"
                                   name="keyword"
                                   type="text"
                                   id="keyword"
                                   value="${requestScope.selectedKeyword != null ? requestScope.selectedKeyword: ''}"
                                   placeholder="Search subjects"
                                   style="width: 70vh"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                    <div class="d-flex">
                        <div class="m-2">
                            <label for="valid-from">From</label>
                            <input type="date"
                                   class="form-control"
                                   id="valid-from"
                                   name="validFrom"
                                   value="${requestScope.selectedFrom != null ? requestScope.selectedFrom: ''}"/>
                        </div>
                        <div class="m-2">
                            <label for="valid-to">To</label>
                            <input type="date"
                                   class="form-control"
                                   id="valid-from"
                                   name="validTo"
                                   value="${requestScope.selectedTo != null ? requestScope.selectedTo: ''}"/>
                        </div>
                        <div class="m-2">
                            <label for="order-by">Order By</label>
                            <select
                                class="custom-select"
                                id="order-by"
                                name="orderBy">
                                <option value="">Default</option>
                                <option value="email">Email</option>
                                <option value="registration_time">Registration Time</option>
                                <option value="title">Subject</option>
                                <option value="name">Package</option>
                                <option value="cost">Total Cost</option>
                                <option value="registration_status">Status</option>
                                <option value="valid_from">Valid From</option>
                                <option value="valid_to">Valid To</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row my-5">
                    <table class="table">
                        <thead>
                        <th>Id</th>
                        <th>Email</th>
                        <th>Registration Time</th>
                        <th>Subject</th>
                        <th>Package</th>
                        <th>Cost</th>
                        <th>Status</th>
                        <th>Valid From</th>
                        <th>Valid To</th>
                        <th>Action</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.pageItems}" var="item" varStatus="counter">
                                <tr>
                                    <th scope="row">${(currentPage - 1) * 5 + counter.index + 1}</th>
                                    <td>${item.get(0)}</td>
                                    <td>${item.get(1)}</td>
                                    <td>${item.get(2)}</td>
                                    <td>${item.get(3)}</td>
                                    <td>${item.get(4)}</td>
                                    <td>${item.get(5)}</td>
                                    <td>${item.get(6)}</td>
                                    <td>${item.get(7)}</td>
                                    <td>
                                        <a href="${path}/auth/user/UserCourse?operation=VIEWDETAIL&userId=${item.get(8)}&courseId=${item.get(9)}">
                                            <button type="button" class="btn btn-primary">
                                                View Details
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
            <nav aria-label="Page navigation example">

                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}/auth/teacher/registration?operation=PAGINATION&page=${prevPage > 0 ? prevPage: 1}">
                            Previous
                        </a>
                    </li>
                    <c:forEach begin="1" end="${maxPage}" varStatus="counter">
                        <li class="page-item">
                            <a class="page-link"
                               href="${path}/auth/teacher/registration?operation=PAGINATION&page=${counter.index}">
                                ${counter.index}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link"
                           href="${path}/auth/teacher/registration?operation=PAGINATION&page=${nextPage > maxPage ? maxPage: nextPage}">
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
  <script type="text/javascript" src="${path}/utilities/tree-module.js"></script>
</html>
