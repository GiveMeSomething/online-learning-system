<%--
    Document   : blog
    Created on : May 29, 2021, 1:52:31 PM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Blog List</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Abril+Fatface&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" href="${path}/style/blog.css"/>
        <style>
            .page-link{
                padding: 0 !important;
                border-bottom-right-radius: 50% !important;
                border-top-right-radius: 50% !important;
                border-bottom-left-radius: 50% !important;
                border-top-left-radius: 50% !important;
            }
        </style>
    </head>
    <body>
        <div id="colorlib-page">
            <a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
            <aside id="colorlib-aside" role="complementary" class="js-fullheight">
                <nav id="colorlib-main-menu" 
                     role="navigation"
                     class="navbar navbar-expand-xl " 
                     style="margin: auto;">
                    <a class="navbar-brand" style="font-size: 2rem;" href="${path}/home">
                        <span style="color:blue">O</span>
                        <span style="color:orange">L</span>
                        <span style="color:green">S</span>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav flex-column d-flex align-items-center">
                            <li class="nav-item">
                                <form action="course?operation=SEARCHCOURSE&&index=${tag}&&cID=${sessionScope.categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=${sessionScope.alpha}"
                                      class="d-flex" method="post">
                                    <input name="searchCourse" class="form-control py-2"
                                           type="search" placeholder="Search courses"/>
                                    <div class="invalid-feedback"></div>
                                </form>
                            </li>
                            <li class="nav-item dropdown nav-hover">
                                <a class="nav-link dropdown-toggle active" role="button" id="navbarDropdownButton" data-toggle="dropdown">
                                    Categories
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownButton">
                                    <c:forEach items="${requestScope.categoryList}" var="o">
                                        <a class="dropdown-item"
                                           href="${path}/course?cID=${o.id}">
                                            ${o.categoryName}
                                        </a>
                                    </c:forEach>
                                </div>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page" href="${path}/blog">Blogs</a>
                            </li>
                            <c:if test="${sessionScope.isAdmin != true}">
                                <c:choose>
                                    <c:when test="${sessionScope.isTeacher == true}">
                                        <li class="nav-hover nav-item">
                                            <a href="${path}/auth/teacher/subject" class="nav-link" style="padding-top: 8px; padding-bottom: 8px">
                                                Management
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="nav-hover nav-item">
                                            <a href="${path}/auth/user/course" class="nav-link active" style="padding-top: 8px; padding-bottom: 8px">
                                                Courses
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <li class="gap-3">
                                <div style="margin-top: 4.5px; white-space: nowrap">
                                    <c:choose>
                                        <c:when test="${sessionScope.user == null}">
                                            <button type="button" class="btn btn-outline-primary py-2 px-3 mx-2" data-toggle="modal" data-target="#login-modal">
                                                Log in
                                            </button>
                                            <button type="button" class="btn btn-secondary py-2 px-3 mx-2" data-toggle="modal" data-target="#register-modal">
                                                Register
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="">
                                                <a href="#" id="shopping-cart" style="border-radius: 25px; padding: 12px 12px;;color: lightslategray">
                                                    <i class="fas fa-shopping-cart fa-lg"></i>
                                                </a>
                                                <ul id="setting-dropdown-ul" >
                                                    <li id="setting-dropdown-li">
                                                        <a href="#" style="border-radius: 25px; padding: 12px 12px; color: lightslategray" id="setting">
                                                            <i class="fas fa-cog fa-lg"></i>
                                                        </a>
                                                        <ul id="setting-dropdown-sub-ul">

                                                            <c:if test="${sessionScope.isAdmin != true }">
                                                                <li id="li-top">
                                                                    <a href="${path}/auth/user/UserCourse?operation=" style="padding-top: 5px; padding-bottom: 5px">
                                                                        My Registrations
                                                                    </a>
                                                                </li>
                                                                <li id="li-middle">
                                                                    <a href="${path}/auth/user">Account setting</a>
                                                                </li>
                                                                <c:if test="${sessionScope.isAdmin != true && sessionScope.isTeacher == true}">
                                                                    <li id="li-bottom">
                                                                        <a href="${path}/auth/admin" style="padding-bottom: 5px">Management</a>
                                                                    </li>
                                                                </c:if>
                                                            </c:if>
                                                            <li id="li-middle">
                                                                <a href="${path}/authenticate?operation=LOGOUT" style="padding-bottom: 5px; padding-top: 5px; border-bottom: 1px solid lightgray">Log out</a>
                                                            </li>
                                                            <c:if test="${sessionScope.isAdmin != true && sessionScope.isTeacher != true}">
                                                                <li id="li-bottom">
                                                                    <a href="${path}/auth/user/course?operation=VIEWMYCOURSE&userId=${user.getId()}" style="padding-bottom: 5px">My Course</a>
                                                                </li>
                                                            </c:if>
                                                            <c:if test="${sessionScope.isAdmin == true}">
                                                                <li id="li-bottom">
                                                                    <a href="${path}/auth/admin" style="padding-bottom: 5px">Management</a>
                                                                </li>
                                                            </c:if>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </aside>
            <div id="colorlib-main">
                <section class="ftco-section ftco-no-pt ftco-no-pb">
                    <div class="container">
                        <div class="row d-flex">
                            <div class="col-xl-8 py-5 px-md-5">
                                <div class="row pt-md-4">
                                    <c:forEach items="${hmPost}" var="p">
                                        <div class="col-md-12">
                                            <div class="blog-entry ftco-animate d-md-flex">
                                                <a href="${path}/blog?operation=VIEWBLOGDETAIL&id=${p.postId}" 
                                                   class="img img-2" style="background-image:url(${p.thumbnail})"></a>
                                                <div class="text text-2 pl-md-4">
                                                    <h3 class="mb-2"><a href="${path}/blog?operation=VIEWBLOGDETAIL&id=${p.postId}">${p.title}</a></h3>
                                                    <div class="meta-wrap">
                                                        <p class="meta">
                                                            <span><i class="far fa-calendar-alt mr-2"></i>${p.updatedDate}</span>
                                                            <span><a href="#"><i class="far fa-folder mr-2"></i>${p.categoryId}</a></span>
                                                            <span><i class="fas fa-user-edit mr-2"></i>${p.authorId}</span>
                                                        </p>
                                                    </div>
                                                    <p class="mb-4">${p.briefInfo}</p>
                                                    <p><a href="${path}/blog?operation=VIEWBLOGDETAIL&id=${p.postId}" class="btn-custom">Read More <i class="far fa-arrow-alt-circle-right ml-2"></i></a></p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="block-27">
                                            <ul>
                                                <c:if test="${requestScope.categoryId == null && title == null}">
                                                    <li class="page-item ${curPage == 1?"disabled":""}">
                                                        <a class="page-link" href="blog?curPage=${curPage - 1}" 
                                                           tabindex="-1">&lt;</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${requestScope.categoryId != null}">
                                                    <li class="page-item ${curPage == 1?"disabled":""}">
                                                        <a  class="page-link"
                                                            href="blog?curPage=${curPage - 1}&operation=postByCategory&cateId=${requestScope.categoryId}" 
                                                            tabindex="-1">&lt;</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${requestScope.title != null && categoryId == null}">
                                                    <li class="page-item ${curPage == 1?"disabled":""}">
                                                        <a  class="page-link"
                                                            href="blog?curPage=${curPage - 1}&operation=SearchByTitle&title=${title}" 
                                                            tabindex="-1">&lt;</a>
                                                    </li>
                                                </c:if>
                                                <c:forEach begin="1" end="${requestScope.nOfPage}" var="i">
                                                    <c:if test="${requestScope.categoryId == null && title == null}">
                                                        <li class="page-item ${curPage == i?"active":""}">
                                                            <a  href="blog?curPage=${i}">${i}</a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${requestScope.categoryId != null}">
                                                        <li class="page-item ${curPage == i?"active":""}">
                                                            <a 
                                                                href="blog?curPage=${i}&operation=postByCategory&cateId=${requestScope.categoryId}">${i}</a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${requestScope.categoryId == null && title != null}">
                                                        <li class="page-item ${curPage == i?"active":""}">
                                                            <a 
                                                                href="blog?curPage=${i}&operation=SearchByTitle&title=${title}">${i}</a>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${requestScope.categoryId == null && title == null}">  
                                                    <li class="page-item ${curPage == nOfPage?"disabled":""}">
                                                        <a class="page-link" href="blog?curPage=${curPage + 1}">&gt;</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${requestScope.categoryId != null}">        
                                                    <li class="page-item ${curPage == nOfPage?"disabled":""}">
                                                        <a class="page-link" href="blog?curPage=${curPage + 1}&operation=postByCategory&cateId=${requestScope.categoryId}">&gt;</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${requestScope.categoryId == null && title != null}">        
                                                    <li class="page-item ${curPage == nOfPage?"disabled":""}">
                                                        <a class="page-link" href="blog?curPage=${curPage + 1}&operation=SearchByTitle&title=${title}">&gt;</a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4 sidebar ftco-animate bg-light pt-5">
                                <div class="sidebar-box pt-md-4">
                                    <form class="search-form" method="POST" action="${path}/blog">
                                        <div class="request-info">
                                            <input name="previousPage" value="blogList.jsp" hidden="true" />
                                            <div class="invalid-feedback"></div>
                                            <input name="operation" value="SearchByTitle" hidden="true" />
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="form-group">
                                            <input class="form-control" type="search" name="title" 
                                                   value="${title}" placeholder="Search" aria-label="Search">
                                        </div>
                                        <button style="position: absolute; top: 90px; right:50px" class="btn btn-outline-success my-2 my-sm-0" type="submit">
                                            <span class="fas fa-search"></span>
                                        </button>
                                    </form>
                                </div>
                                <div class="sidebar-box ftco-animate">
                                    <h3 class="sidebar-heading">Categories</h3>
                                    <ul class="categories">
                                        <c:forEach items="${hmCategory}" var="c">
                                            <li><a href="${path}/blog?operation=VIEWBLOGCATEGORY&categoryId=${c.key}">${c.value} <span>(6)</span></a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                                <div class="sidebar-box ftco-animate">
                                    <h3 class="sidebar-heading">Latest Articles</h3>
                                    <c:forEach items="${latest}" var="p">
                                        <div class="block-21 mb-4 d-flex">
                                            <a class="blog-img mr-4" href="${path}/blog?operation=VIEWBLOGDETAIL&id=${p.key}" 
                                               style="background-image:url(${p.value.thumbnail}); border-radius: 20px"></a>
                                            <div class="text">
                                                <h3 class="heading"><a href="${path}/blog?operation=VIEWBLOGDETAIL&id=${p.key}">${p.value.title}</a></h3>
                                                <div class="meta">
                                                    <div><i class="far fa-calendar-alt mr-2"></i> ${p.value.updatedDate}</div>
                                                    <div><i class="far fa-folder mr-2"></i> ${p.value.categoryId}</div>
                                                    <div><i class="fas fa-user-edit mr-2"></i> ${p.value.authorId}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" /><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg></div>
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
        <!-- Import if have form input -->
        <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
        <script src="${path}/utilities/jquery.min.js"></script>
        <script src="${path}/utilities/jquery-migrate.js"></script><script>eval(mod_pagespeed_j9z0BOW3Xo);</script>
        <script>eval(mod_pagespeed_Sv59xs6XfY);</script>
        <script>eval(mod_pagespeed_6hivBMilT4);</script>
        <script src="${path}/utilities/jquery-easing.js"></script><script>eval(mod_pagespeed_a9in6ca9LP);</script>
        <script>eval(mod_pagespeed_E5Rl_eUdra);</script>
        <script>eval(mod_pagespeed_J7fR9gg3IV);</script>
        <script>eval(mod_pagespeed_JGC9XV$UWo);</script>
        <script src="${path}/utilities/jquery-magnific.js"></script><script>eval(mod_pagespeed_9FDixMCPbL);</script>
        <script>eval(mod_pagespeed_cWDp1F0K9O);</script>
        <script>eval(mod_pagespeed_tBed9FYmsP);</script>
        <script>eval(mod_pagespeed_xipmmYMCNr);</script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script>eval(mod_pagespeed_YuUFcjXVeT);</script>
        <script>eval(mod_pagespeed_6pY$MsZg3C);</script>

        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());

            gtag('config', 'UA-23581568-13');
        </script>
        <script defer src="https://static.cloudflareinsights.com/beacon.min.js" data-cf-beacon='{"rayId":"66cf512d9f1fd94e","token":"cd0b4b3a733644fc843ef0b185f98241","version":"2021.6.0","si":10}'></script>
    </body>
</html>

