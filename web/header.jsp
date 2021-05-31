<%-- 
    Document   : header
    Created on : May 31, 2021, 9:41:47 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Webpage Skeleton</title>
    </head>
    <body>

        <section id="navbar">
            <div class="container-fluid d-flex justify-content-center my-3">
                <nav class="navbar navbar-expand-xl navbar-light bg-light" style="margin: auto;">
                    <a class="navbar-brand" style="font-size: 2rem;" href="#">
                        <span style="color:blue">O</span>
                        <span style="color:orange">L</span>
                        <span style="color:green">S</span>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav d-flex align-items-center">
                            <li class="nav-item">
                                <form class="d-flex">
                                    <input class="form-control py-2" type="search" placeholder="Search courses">
                                </form>
                            </li>
                            <li class="nav-item dropdown nav-hover">
                                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Categories</a>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="#">Software Engineering</a>
                                    <a class="dropdown-item" href="#">Economy</a>
                                    <a class="dropdown-item" href="#">Digital Marketing</a>
                                    <a class="dropdown-item" href="#">Artificial Intelligence</a>
                                    <a class="dropdown-item" href="#">Information Assurance</a>
                                    <a class="dropdown-item" href="#">Language</a>
                                </div>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page" href="#">Blogs</a>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page" href="#">Courses</a>
                            </li>
                            <li class="nav-item gap-3">
                                <div style="margin-top: 4.5px">
                                    <c:if test="${sessionScope.acc != null}">
                                        <a type="button" class="btn btn-outline-primary py-2 px-3 mx-2">
                                            Log in
                                        </a>
                                        <a type="button" class="btn btn-secondary py-2 px-3 mx-2">
                                            Sign in
                                        </a>
                                    </c:if>
                                </div>
                                <div style="margin-top: 4.5px">
                                    <c:if test="${sessionScope.acc == null}">
                                        <a href="#" id="shopping-cart" style="border-radius: 25px; padding: 12px 12px;;color: lightslategray"><i class="fas fa-shopping-cart fa-lg"></i></a>                                                                              

                                        <ul id="setting-dropdown-ul" >
                                            <li id="setting-dropdown-li">
                                                <a href="#" style="border-radius: 25px; padding: 12px 12px; color: lightslategray" id="setting"> <i class="fas fa-cog fa-lg"></i></a>
                                                <ul id="setting-dropdown-sub-ul">
                                                    <li id="li-top"><a href="#" style="padding-top: 5px; padding-bottom: 5px">My course</a></li>
                                                    <li id="li-middle"><a href="#">Account setting</a></li>
                                                    <li id="li-middle"><a href="#" style="padding-bottom: 5px; padding-top: 5px; border-bottom: 1px solid lightgray">Log out</a></li>

                                                    <c:if test="${sessionScope.acc.isAdmin == true}">
                                                        <li id="li-bottom"><a href="#" style="padding-bottom: 5px">Management</a></li>
                                                        </c:if>
                                                </ul>
                                            </li>
                                        </ul>

                                    </c:if>

                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </section>
    </body>

</html>
