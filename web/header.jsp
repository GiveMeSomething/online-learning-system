<%-- 
    Document   : header
    Created on : May 27, 2021, 7:35:56 AM
    Author     : Nguyen Khanh Toan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section id="navbar">
            <div class="container-fluid d-flex justify-content-center my-3">
                <nav class="navbar navbar-expand-xl navbar-light bg-light" style="margin: auto;">
                    <a class="navbar-brand" style="font-size: 2rem;" href="Home_Controller">
                        <span style="color:blue">O</span>
                        <span style="color:orange">L</span>
                        <span style="color:green">S</span>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav d-flex align-items-center">
                            <li class="nav-item">
                                <form action="searchController?cID=${sessionScope.cateID}" 
                                      class="d-flex" method="post">
                                    <input name="searchCourse" class="form-control py-2"
                                           type="search" placeholder="Search courses">
                                </form>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" 
                                   href="#" id="navbarDropdown"
                                   role="button" data-bs-toggle="dropdown" 
                                   aria-expanded="false">
                                    Categories
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <c:forEach items="${listC}" var="o">
                                        <li>
                                            <a class="dropdown-item"
                                               href="CourseListController?cID=${o.id}">
                                                ${o.categoryName}
                                            </a>
                                        </li> 
                                    </c:forEach>
                                </ul>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page"
                                   href="userProfile">
                                    Blogs
                                </a>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page" href="#">Courses</a>
                            </li>
                            <li class="nav-item gap-3">
                                <button type="button" class="btn btn-outline-primary py-2 px-3 mx-2">
                                    Log in
                                </button>
                                <button type="button" class="btn btn-secondary py-2 px-3 mx-2">
                                    Sign in
                                </button>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </section>
    </body>
</html>
