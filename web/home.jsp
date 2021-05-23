<%-- 
    Document   : home
    Created on : May 22, 2021, 10:11:29 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Webpage Skeleton</title>
        <!--        Bootstrap_Carousel-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <!--        Bootstrap_Carousel-->
        <!--        fontawesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <!--        fontawesome-->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../skeleton/styles.css">

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
                                    <a class="dropdown-item" href="#">Action</a>
                                    <a class="dropdown-item" href="#">Another action</a>
                                    <a class="dropdown-item" href="#">Something else here</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Separated link</a>
                                </div>
                            </li>
                            <li class="nav-item nav-hover">
                                <a class="nav-link active" aria-current="page" href="#">Blogs</a>
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
        <!--  Banner--> 
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active banner-margin-top" >
                    <div class="banner-information bg-dark mx-lg-5">
                        <div class="banner-sub-information p-3">
                            <b class="text-light banner-primary">This is your moment. Let’s study.</b><br>
                            <span class="text-light banner-secondary">Let’s build a more connected, inclusive and flexible future together.</span><br>
                            <button class="btn btn-secondary">Learn more</button>
                        </div>
                    </div>
                    <img class="d-block w-100" style="height: 388.72px" src="../../bannerImg/banner1.jpg" alt="First slide">
                </div>
                <div class="carousel-item banner-margin-top">
                    <div class="banner-information bg-dark mx-lg-5">
                        <div class="banner-sub-information p-3">
                            <b class="text-light banner-primary">Study anywhere with your laptop.</b><br>
                            <span class="text-light banner-secondary">Let’s create a knowledgeable world.</span><br>
                            <button class="btn btn-secondary">Learn more</button>
                        </div>
                    </div>
                    <img class="d-block w-100" src="../../bannerImg/banner2_1.jpg" alt="Second slide">
                </div>
                <div class="carousel-item banner-margin-top">
                    <div class="banner-information bg-dark mx-lg-5">
                        <div class="banner-sub-information p-3">
                            <b class="text-light banner-primary">Learn from the expert from all over the world.</b><br>
                            <span class="text-light banner-secondary">Widen your knowledge.</span><br>
                            <button class="btn btn-secondary">Learn more</button>
                        </div>
                    </div>
                    <img class="d-block w-100" src="../../bannerImg/banner3_1.jpg" alt="Third slide">
                </div>
            </div>
            <a class="carousel-control-prev banner-btn" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon " aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next banner-btn" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!--  Banner-->      
    </section>
    <section id="introduction" style="height: 3.5rem">
        <div class="row mx-3" style="height: 3.5rem; margin-left: 10.5rem!important">
            <div class="col-6 col-md-4 mt-2">
                <i class="far fa-check-circle banner-icon-sz fa-2x" style="height: 1.5rem"></i>
                <div class="d-flex flex-column introduction-detail-left">
                    <span class="introduction-primary">Plenty of online courses</span>
                    <span class="introduction-secondary">Explore our online course now</span>
                </div>
            </div>
            <div class="col-6 col-md-4 mt-2">
                <i class="far fa-comment banner-icon-sz fa-2x" ></i>
                <div class="d-flex flex-column introduction-detail">
                    <span class="introduction-primary">Expert instruction</span>
                    <span class="introduction-secondary">Find your instructor</span>
                </div>
            </div>
            <div class="col-6 col-md-4 mt-2">
                <i class="fas fa-history banner-icon-sz fa-2x"></i>
                <div class="d-flex flex-column introduction-detail">
                    <span class="introduction-primary">Life time access</span>
                    <span class="introduction-secondary">Learn on your schedule</span>
                </div>
            </div>
        </div>
    </section>
    <section id="subject-course-list">
        <div id="subject-course-list-main" class="d-flex mx-lg-5">
            <div id="subject-course-list-left" class="p-5 col-lg-4 bg-dark">
                <h5>What is Lorem Ipsum?
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</h5>
            </div>
            <div id="subject-course-list-right" class="col-lg-7">
                <div id="myDIV">
                    <button class="btn1 active1" onclick="businessOn()">Business</button>
                    <button class="btn1" onclick="designOn()">Design</button>
                    <button class="btn1" onclick="marketingOn()">Marketing</button>
                    <button class="btn1" onclick="itOn()">IT&Software</button>
                </div>  
                <!--                busines-->
                <div id="business" style="display: block">
                    <div  class="row">
                        <c:forEach items="${course}" var="i">
                            <div class="col-md-4 ">
                                <div class="card cardProduct" >

                                    <img style="width:80%; margin-left: 18px" src="perfume_product/${i.imageLink}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title" style="text-align: center">${i.courseName}</h5>
                                        <p style="text-align: center" class="card-text"><span>$</span>${i.price}</p>
                                        <div style="padding-left: 10px; display: flex">

                                            <button onclick="totalCart()" id="sumCart" class="btn btn-dark" style="padding: 0.25rem; margin-right: 5px"><a href="#" class="btn btn-dark btn-block">AddCart</a></button>

                                            <a href="detail?pid=${i.id}" title="View Product" class="btn btn-dark" style="padding: 0.25rem 1.25rem;padding-top: 10px">View</a> 
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </c:forEach>                                             
                    </div>
                </div>


                <!--                marketing-->
                <div id="marketing" style="display: none">
                    <div  class="row">
                        <c:forEach items="${listNameDESC}" var="i">
                            <div class="col-md-4 ">
                                <div class="card cardProduct" >

                                    <img style="width:80%; margin-left: 18px" src="perfume_product/${i.imageLink}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title" style="text-align: center">${i.productName}</h5>
                                        <p style="text-align: center" class="card-text"><span>$</span>${i.price}</p>
                                        <div style="padding-left: 10px; display: flex">

                                            <button onclick="totalCart()" id="sumCart" class="btn btn-dark" style="padding: 0.25rem; margin-right: 5px"><a href="#" class="btn btn-dark btn-block">AddCart</a></button>

                                            <a href="detail?pid=${i.id}" title="View Product" class="btn btn-dark" style="padding: 0.25rem 1.25rem;padding-top: 10px">View</a> 
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </c:forEach>                                             
                    </div>
                </div>

                <!--                design-->
                <div id="design" style="display: none">
                    <div  class="row">
                        <c:forEach items="${listNameDESC}" var="i">
                            <div class="col-md-4 ">
                                <div class="card cardProduct" >

                                    <img style="width:80%; margin-left: 18px" src="perfume_product/${i.imageLink}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title" style="text-align: center">${i.productName}</h5>
                                        <p style="text-align: center" class="card-text"><span>$</span>${i.price}</p>
                                        <div style="padding-left: 10px; display: flex">

                                            <button onclick="totalCart()" id="sumCart" class="btn btn-dark" style="padding: 0.25rem; margin-right: 5px"><a href="#" class="btn btn-dark btn-block">AddCart</a></button>

                                            <a href="detail?pid=${i.id}" title="View Product" class="btn btn-dark" style="padding: 0.25rem 1.25rem;padding-top: 10px">View</a> 
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </c:forEach>                                             
                    </div>
                </div>

                <!--                busines-->
                <div id="it" style="display: none">
                    <div  class="row">
                        <c:forEach items="${listNameDESC}" var="i">
                            <div class="col-md-4 ">
                                <div class="card cardProduct" >

                                    <img style="width:80%; margin-left: 18px" src="perfume_product/${i.imageLink}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title" style="text-align: center">${i.productName}</h5>
                                        <p style="text-align: center" class="card-text"><span>$</span>${i.price}</p>
                                        <div style="padding-left: 10px; display: flex">

                                            <button onclick="totalCart()" id="sumCart" class="btn btn-dark" style="padding: 0.25rem; margin-right: 5px"><a href="#" class="btn btn-dark btn-block">AddCart</a></button>

                                            <a href="detail?pid=${i.id}" title="View Product" class="btn btn-dark" style="padding: 0.25rem 1.25rem;padding-top: 10px">View</a> 
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </c:forEach>                                             
                    </div>
                </div>

            </div>


    </section>

    <section id="page-footer" class="container-fluid mt-5 d-flex justify-content-between align-items-center">
        <div class="page-dummy-footer px-5">
            <a class="navbar-brand" style="font-size: 2rem;" href="#">
                <span style="color:blue">O</span>
                <span style="color:orange">L</span>
                <span style="color:green">S</span>
            </a>
        </div>
        <div class="page-dummy-footer px-5">
            © 2021 OLS, Inc.
        </div>
    </section>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script>
// Add active class to the current button (highlight it)
                                                var header = document.getElementById("myDIV");
                                                var btns = header.getElementsByClassName("btn1");
                                                for (var i = 0; i < btns.length; i++) {
                                                    btns[i].addEventListener("click", function () {
                                                        var current = document.getElementsByClassName("active1");
                                                        current[0].className = current[0].className.replace(" active1", "");
                                                        this.className += " active1";
                                                    });
                                                }

//display and none
                                                function businessOn() {
                                                    var business = document.getElementById('business');
                                                    var design = document.getElementById('design');
                                                    var marketing = document.getElementById('marketing');
                                                    var it = document.getElementById('it');
                                                    business.style.display = 'block';
                                                    design.style.display = 'none';
                                                    marketing.style.display = 'none';
                                                    it.style.display = 'none';
                                                }
                                                function designOn() {
                                                    var business = document.getElementById('business');
                                                    var design = document.getElementById('design');
                                                    var marketing = document.getElementById('marketing');
                                                    var it = document.getElementById('it');
                                                    business.style.display = 'none';
                                                    design.style.display = 'block';
                                                    marketing.style.display = 'none';
                                                    it.style.display = 'none';
                                                }
                                                function marketingOn() {
                                                    var business = document.getElementById('business');
                                                    var design = document.getElementById('design');
                                                    var marketing = document.getElementById('marketing');
                                                    var it = document.getElementById('it');
                                                    business.style.display = 'none';
                                                    design.style.display = 'none';
                                                    marketing.style.display = 'block';
                                                    it.style.display = 'none';
                                                }
                                                function itOn() {
                                                    var business = document.getElementById('business');
                                                    var design = document.getElementById('design');
                                                    var marketing = document.getElementById('marketing');
                                                    var it = document.getElementById('it');
                                                    business.style.display = 'none';
                                                    design.style.display = 'none';
                                                    marketing.style.display = 'none';
                                                    it.style.display = 'block';
                                                }
</script>
</html>
