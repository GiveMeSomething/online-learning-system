<%-- 
    Document   : blogDetail
    Created on : May 30, 2021, 1:57:05 AM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

    <!DOCTYPE html>
    <html lang="en">
        <head>
            <title>${requestScope.post.title}</title>
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
                <nav id="colorlib-main-menu" role="navigation">
                    <ul>
                        <li><a href="index.html">Home</a></li>
                        <li><a href="fashion.html">Fashion</a></li>
                        <li class="colorlib-active"><a href="travel.html">Travel</a></li>
                        <li><a href="about.html">About</a></li>
                        <li><a href="contact.html">Contact</a></li>
                    </ul>
                </nav>
                <div class="colorlib-footer">
                    <h1 id="colorlib-logo" class="mb-4"><a href="index.html" style="background-image:url(images/xbg_1.jpg.pagespeed.ic.fkgeUtddhh.webp)">Andrea <span>Moore</span></a></h1>
                    <div class="mb-4">
                        <h3>Subscribe for newsletter</h3>
                        <form action="#" class="colorlib-subscribe-form">
                            <div class="form-group d-flex">
                                <div class="icon"><span class="icon-paper-plane"></span></div>
                                <input type="text" class="form-control" placeholder="Enter Email Address">
                            </div>
                        </form>
                    </div>
                    <p class="pfooter">
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                    </p>
                </div>
            </aside>
            <div id="colorlib-main">
                <section class="ftco-section ftco-no-pt ftco-no-pb">
                    <div class="container">
                        <div class="row d-flex">
                            <div class="col-lg-8 px-md-5 py-5">
                                <div class="row pt-md-4">
                                    <h1 class="mb-3">${post.title}</h1>
                                    
                                    <div>${post.description}</div>
                                </div>
                            </div>
                            <div class="col-lg-4 sidebar ftco-animate bg-light pt-5">
                                <div class="sidebar-box pt-md-4">
                                    <form action="#" class="search-form">
                                        <div class="form-group">
                                            <span class="icon icon-search"></span>
                                            <input type="text" class="form-control" placeholder="Type a keyword and hit enter">
                                        </div>
                                    </form>
                                </div>
                                <div class="sidebar-box ftco-animate">
                                    <h3 class="sidebar-heading">Categories</h3>
                                    <ul class="categories">
                                        <li><a href="#">Fashion <span>(6)</span></a></li>
                                        <li><a href="#">Technology <span>(8)</span></a></li>
                                        <li><a href="#">Travel <span>(2)</span></a></li>
                                        <li><a href="#">Food <span>(2)</span></a></li>
                                        <li><a href="#">Photography <span>(7)</span></a></li>
                                    </ul>
                                </div>
                                <div class="sidebar-box ftco-animate">
                                    <h3 class="sidebar-heading">Popular Articles</h3>
                                    <div class="block-21 mb-4 d-flex">
                                        <a class="blog-img mr-4" style="background-image:url(images/ximage_1.jpg.pagespeed.ic.bP9m1ezc08.webp)"></a>
                                        <div class="text">
                                            <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control</a></h3>
                                            <div class="meta">
                                                <div><a href="#"><span class="icon-calendar"></span> June 28, 2019</a></div>
                                                <div><a href="#"><span class="icon-person"></span> Dave Lewis</a></div>
                                                <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="block-21 mb-4 d-flex">
                                        <a class="blog-img mr-4" style="background-image:url(images/ximage_2.jpg.pagespeed.ic.Bj2SSShAni.webp)"></a>
                                        <div class="text">
                                            <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control</a></h3>
                                            <div class="meta">
                                                <div><a href="#"><span class="icon-calendar"></span> June 28, 2019</a></div>
                                                <div><a href="#"><span class="icon-person"></span> Dave Lewis</a></div>
                                                <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="block-21 mb-4 d-flex">
                                        <a class="blog-img mr-4" style="background-image:url(images/ximage_3.jpg.pagespeed.ic.GG-cYs-yp6.webp)"></a>
                                        <div class="text">
                                            <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control</a></h3>
                                            <div class="meta">
                                                <div><a href="#"><span class="icon-calendar"></span> June 28, 2019</a></div>
                                                <div><a href="#"><span class="icon-person"></span> Dave Lewis</a></div>
                                                <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                                            </div>
                                        </div>
                                    </div>
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
<!--    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
                  integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
            <link rel="stylesheet" type="text/css" href="${path}/style/styles.css">
        <title>${requestScope.post.title}</title>
    </head>
    <body>
        header
<main>
<div class="container">
     breadcrumb 
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home">Home</a></li>
            <li class="breadcrumb-item"><a href="blog">Blog</a></li>
            <li class="breadcrumb-item active" aria-current="page">${requestScope.post.title}</li>
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
                               placeholder="Search" value="${title}" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </div>
            categories
            <div class="row">
                <div class="col-12">
                    <h3>Category</h3>
                    <hr>
                    <ul class="list-group list-group-flush shadow-sm">
<c:forEach var="o" items="${hmCategory}">
    <a href="blog?operation=postByCategory&categoryId=${o.key}&curPage=1" 
       class="list-group-item list-group-item-action ${cateId==o.key?"active":""}">${o.value}</a>
</c:forEach>
</ul>
</div>
</div>
latest posts
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
<div class="col-8 bg-light">
<article class="p-3">
<h1 style="text-align: center;">${post.title}</h1>
<h3 class="mt-5" style="text-align: end;">Published date: ${post.updatedDate}</h3>
<hr>
<p style="white-space: pre-line;">
${post.description}
</p>
<h3  style="text-align: end;">Written by: ${post.authorId}</h3>
<hr>
</article>
</div>
</div>
</div>
</main>
</body>
footer
boostrap
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
