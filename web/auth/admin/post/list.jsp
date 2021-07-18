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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>List Posts</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="${path}/style/setting.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${path}/utilities/tree-module.js"></script>
        <jsp:useBean id="dal" scope="page" class="blog.BlogRepository" />
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Roboto', sans-serif;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 10px;
                margin: 0 0 10px;
                min-width: 100%;
            }
            .table-title h2 {
                margin: 8px 0 0;
                font-size: 22px;
            }
            .search-box {
                position: relative;        
                float: right;
            }
            .search-box input {
                height: 34px;
                border-radius: 20px;
                padding-left: 35px;
                border-color: #ddd;
                box-shadow: none;
            }
            .search-box input:focus {
                border-color: #3FBAE4;
            }
            .search-box i {
                color: #a0a5b1;
                position: absolute;
                font-size: 19px;
                top: 8px;
                left: 10px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child {
                width: 130px;
            }
            table.table td a {
                color: #a0a5b1;
                display: inline-block;
                margin: 0 5px;
            }
            table.table td a.view {
                color: #03A9F4;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #E34724;
            }
            table.table td i {
                font-size: 19px;
            }    
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 95%;
                width: 30px;
                height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 30px !important;
                text-align: center;
                padding: 0;
            }
            .pagination li a:hover {
                color: #666;
            }	
            .pagination li.active a {
                background: #03A9F4;
            }
            .pagination li.active a:hover {        
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 6px;
                font-size: 95%;
            }    
        </style>
    </head>
    <body>
        <div id="mySidebar" class="sidebar">
            <button id="closeNav" class="openbtn" onclick="closeNav()" style="display: none; margin-top: -4rem; margin-bottom: 2rem; margin-left: 13rem"><span style="text-transform: uppercase">X</span></button>
            <!--<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>-->
            <a href="${path}/auth/admin" >User List</a>
            <hr>
            <a href="${path}/auth/admin/dashboard">Dashboard</a>
            <hr>
            <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST" style="background: white; color: black">Post List</a>
            <hr>
            <a href="${path}/auth/teacher/subject">Subject List</a>
            <hr>
            <a href="${path}/auth/admin/slider" >Slider List</a>
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
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-8 d-flex">
                                <h2 style="margin-top: .7rem; color: black; font-weight: normal">Posts List</h2>
                            </div>
                            <div class="col-sm-4">
                                <button style="float: right;" class="btn btn-success">
                                    <a style="color: black; text-decoration: none" href="${path}/auth/admin/post/add.jsp">
                                        Add Post
                                    </a>
                                </button>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <!--<th>Thumbnail </th>-->
                                <th>Title </th>
                                <th>Category</th>
                                <th>Brief info</th>
                                <!--<th>Description </th>-->
                                <th>Feature </th>
                                <th>Status </th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--String postId, String thumbnail, String categoryId, String title, String briefInfo, String description, String feature, String statusId-->
                            <c:forEach var="o" items="${listPost}">
                                <tr>
                                    <td>${o.postId}</td>
                                    <!--<td>${o.thumbnail}</td>-->
                                    <td>${o.title}</td>
                                    <td>
                                        ${dal.getCategoryNameById(o.categoryId)}
                                    </td>
                                    <td>${o.briefInfo}</td>
                                    <!--<td>${o.description}</td>-->
                                    <td>
                                        ${o.feature==1?"ON":"OFF"}
                                    </td>
                                    <td>
                                        ${o.statusId==1?"ACTIVE":"INACTIVE"}
                                    </td>
                                    <td>
                                        <a href="${path}/auth/admin/admin_blog?operation=VIEWPOSTDETAIL&POSTID=${o.postId}" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                                        <a href="${path}/auth/admin/admin_blog?operation=DELETEPOST&POSTID=${o.postId}" onclick="return confirm('Are you sure you want to delete this item?');" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                    <div class="clearfix">
                        <ul class="pagination">
                            <li class="page-item ${page>1?"":"disabled"}"><a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST&page=${page-1}" class="page-link"><i class="fa fa-angle-double-left"></i></a></li>
                                    <c:forEach var="i" begin="1" end="${endPage}">
                                <li class="page-item ${page==i?"active":""}"><a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST&page=${i}" class="page-link">${i}</a></li>
                                </c:forEach>
                            <!--<li class="page-item active"><a href="#" class="page-link">3</a></li>-->
                            <li class="page-item ${page<endPage?"":"disabled"}"><a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST&page=${page+1}" class="page-link"><i class="fa fa-angle-double-right"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>  
        </div>   
    </body>
</html>
