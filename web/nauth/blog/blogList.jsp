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
        <link rel="stylesheet" href="${path}/style/blog.css" />
    </head>
    <body>
        <div id="colorlib-page">
            <a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
            <aside id="colorlib-aside" role="complementary" class="js-fullheight">
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li class="colorlib-active"><a href="index.html">Home</a></li>
                        <li><a href="fashion.html">Fashion</a></li>
                        <li><a href="travel.html">Travel</a></li>
                        <li><a href="about.html">About</a></li>
                        <li><a href="contact.html">Contact</a></li>
                    </ul>
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
                                                <a href="#" class="img img-2" style="background-image:url(${p.thumbnail})"></a>
                                                <div class="text text-2 pl-md-4">
                                                    <h3 class="mb-2"><a href="#">${p.title}</a></h3>
                                                    <div class="meta-wrap">
                                                        <p class="meta">
                                                            <span><i class="far fa-calendar-alt mr-2"></i>${p.updatedDate}</span>
                                                            <span><a href="#"><i class="far fa-folder mr-2"></i>${p.categoryId}</a></span>
                                                            <span><a href="#"><i class="fas fa-user-edit mr-2"></i>${p.authorId}</a></span>
                                                        </p>
                                                    </div>
                                                    <p class="mb-4">${p.briefInfo}</p>
                                                    <p><a href="#" class="btn-custom">Read More <i class="far fa-arrow-alt-circle-right ml-2"></i></a></p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="block-27">
                                            <ul>
                                                <li><a href="#">&lt;</a></li>
                                                <li class="active"><span>1</span></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">4</a></li>
                                                <li><a href="#">5</a></li>
                                                <li><a href="#">&gt;</a></li>
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
<!--<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <title>Blog</title>
    </head>
    <body>
<main>
    <div class="container">
         breadcrumb 
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="home">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Blog</li>
            </ol>
        </nav>
        <div class="row">
             left 
            <div class="col-md-4">
                <div class="row">
                    <div class="col-12">
                        <form class="form-inline" method="POST" action="blog">
                            <div class="request-info">
                                <input name="previousPage" value="blogList.jsp" hidden="true" />
                                <div class="invalid-feedback"></div>
                                <input name="operation" value="SearchByTitle" hidden="true" />
                                <div class="invalid-feedback"></div>
                            </div>
                            <input class="form-control mr-sm-2" type="search" name="title" 
                                   value="${title}" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <h3>Category</h3>
                        <hr>
                        <ul class="list-group list-group-flush shadow-sm">
<c:forEach var="i" items="${requestScope.hmCategory}">
    <a href="blog?operation=postByCategory&categoryId=${i.key}&curPage=1" 
       class="list-group-item list-group-item-action ${categoryId==i.key?"active":""}">${i.value}</a>
</c:forEach>
</ul>
</div>
</div>
<div class="row">
<h3 class="mt-4">Top 3 Latest Posts</h3>
<div class="col-12">
<ul class="list-group list-group-flush shadow-sm">
<c:forEach items="${latest}" var="o">
    <a href="blog?operation=blogDetail&id=${o.value.postId}" 
       class="list-group-item list-group-item-action">${o.value.title}</a>
</c:forEach>
</ul>
</div>
</div>
</div>
right 
<div class="col-md-8">
<div class="row">
<div class="col-12">
<c:forEach items="${hmPost}" var="o">
    <div class="d-flex position-relative my-3 py-3 bg-light shadow-lg">
        <img src="${o.thumbnail}" class="mx-5" style="width: 8rem; height: 8rem" alt="thumbnail">
        <div>
            <h5 class="mt-0">${o.title}</h5>
            <p>${o.briefInfo}</p>
            <a href="blog?operation=blogDetail&id=${o.postId}" class="stretched-link">Read more</a>
        </div>
    </div>
</c:forEach>
</div>
</div>
<div class="row">
<div class="col-12">
<nav aria-label="Blog-pagination">
    <ul class="pagination justify-content-center">
<c:if test="${requestScope.categoryId == null && title == null}">
    <li class="page-item ${curPage == 1?"disabled":""}">
        <a class="page-link" href="blog?curPage=${curPage - 1}" 
           tabindex="-1">Previous</a>
    </li>
</c:if>
<c:if test="${requestScope.categoryId != null}">
    <li class="page-item ${curPage == 1?"disabled":""}">
        <a class="page-link" 
           href="blog?curPage=${curPage - 1}&operation=postByCategory&cateId=${requestScope.categoryId}" 
           tabindex="-1">Previous</a>
    </li>
</c:if>
<c:if test="${requestScope.title != null && categoryId == null}">
    <li class="page-item ${curPage == 1?"disabled":""}">
        <a class="page-link" 
           href="blog?curPage=${curPage - 1}&operation=SearchByTitle&title=${title}" 
           tabindex="-1">Previous</a>
    </li>
</c:if>
<c:forEach begin="1" end="${requestScope.nOfPage}" var="i">
    <c:if test="${requestScope.categoryId == null && title == null}">
        <li class="page-item ${curPage == i?"active":""}">
            <a class="page-link" href="blog?curPage=${i}">${i}</a>
        </li>
    </c:if>
    <c:if test="${requestScope.categoryId != null}">
        <li class="page-item ${curPage == i?"active":""}">
            <a class="page-link" 
               href="blog?curPage=${i}&operation=postByCategory&cateId=${requestScope.categoryId}">${i}</a>
        </li>
    </c:if>
    <c:if test="${requestScope.categoryId == null && title != null}">
        <li class="page-item ${curPage == i?"active":""}">
            <a class="page-link" 
               href="blog?curPage=${i}&operation=SearchByTitle&title=${title}">${i}</a>
        </li>
    </c:if>
</c:forEach>
<c:if test="${requestScope.categoryId == null && title == null}">        
    <li class="page-item ${curPage == nOfPage?"disabled":""}">
        <a class="page-link" href="blog?curPage=${curPage + 1}">Next</a>
    </li>
</c:if>
<c:if test="${requestScope.categoryId != null}">        
    <li class="page-item ${curPage == nOfPage?"disabled":""}">
        <a class="page-link" 
           href="blog?curPage=${curPage + 1}&operation=postByCategory&cateId=${requestScope.categoryId}">Next</a>
    </li>
</c:if>
<c:if test="${requestScope.categoryId == null && title != null}">        
    <li class="page-item ${curPage == nOfPage?"disabled":""}">
        <a class="page-link" 
           href="blog?curPage=${curPage + 1}&operation=SearchByTitle&title=${title}">Next</a>
    </li>
</c:if>
</ul>
</nav>
</div>
</div>
</div>
</div>
</div>
</main>
footer
</body>
javascript
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
Import if have form input 
<script type="text/javascript" src="${path}/utilities/form-validator.js"></script>

</html>-->
