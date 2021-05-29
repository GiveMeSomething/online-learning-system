<%-- 
    Document   : home
    Created on : May 22, 2021, 10:11:29 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Webpage Skeleton</title>
        <!--        Bootstrap_Carousel-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <!--        fontawesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <link rel="stylesheet" type="text/css" href="pages/skeleton/styles.css">

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
                                                    <li id="li-bottom"><a href="#" style="padding-bottom: 5px; padding-top: 5px">Log out</a></li>
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
                    <img class="d-block w-100" style="height: 388.72px" src="bannerImg/banner1.jpg" alt="First slide">
                </div>
                <div class="carousel-item banner-margin-top">
                    <div class="banner-information bg-dark mx-lg-5">
                        <div class="banner-sub-information p-3">
                            <b class="text-light banner-primary">Study anywhere with your laptop.</b><br>
                            <span class="text-light banner-secondary">Let’s create a knowledgeable world.</span><br>
                            <button class="btn btn-secondary">Learn more</button>
                        </div>
                    </div>
                    <img class="d-block w-100" src="bannerImg/banner2_1.jpg" alt="Second slide">
                </div>
                <div class="carousel-item banner-margin-top">
                    <div class="banner-information bg-dark mx-lg-5">
                        <div class="banner-sub-information p-3">
                            <b class="text-light banner-primary">Learn from the expert from all over the world.</b><br>
                            <span class="text-light banner-secondary">Widen your knowledge.</span><br>
                            <button class="btn btn-secondary">Learn more</button>
                        </div>
                    </div>
                    <img class="d-block w-100" src="bannerImg/banner3_1.jpg" alt="Third slide">
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
            <div id="subject-course-list-left" class="p-5 col-lg-4 bg-light mt-5" style="height: 15rem">
                <h5>The nation's largest selection of courses</h5>
                <p>Choose from over 100000 online video courses with new additions published every month</p>
            </div>
            <div id="subject-course-list-right" class="col-lg-7">
                <div id="myDIV">
                    <button class="btn1 active1" onclick="businessOn()"style="outline: none">Business</button>
                    <button class="btn1" onclick="designOn()"style="outline: none">Design</button>
                    <button class="btn1" onclick="marketingOn()"style="outline: none">Marketing</button>
                    <button class="btn1" onclick="itOn()" style="outline: none">IT&Software</button>
                </div>  
                <!--                busines-->
                <div id="business" style="display: block; margin-top: -2rem; margin-left: 5rem">
                    <div  class="row">
                        <!--                        1-->
                        <div class="col-sm-2 " >
                            <div class="card " style="width: 13.5rem;">
                                <img class="card-img-top" src="courseImg/courseBN1.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">Award Winning Business School Prof</h5>
                                    <span class="card-text">Chris Haroun</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="far fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>
                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        2-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem;margin-left: 8rem">
                                <img class="card-img-top" src="courseImg/courseBN2.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">Chief Financial Officer Leadership Program</h5>
                                    <span class="card-text">Blair Cook CPA</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        3-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem; margin-left: 16rem">
                                <img class="card-img-top" src="courseImg/courseBN3.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">Sales and Persuasion Skills for Startups</h5>
                                    <span class="card-text">Len Smith, Sean Kaye</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star-half fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>


                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!--                design-->
                <div id="design" style="display: none; margin-top: -2rem; margin-left: 5rem">
                    <div  class="row">
                        <!--                        1-->
                        <div class="col-sm-2 " >
                            <div class="card " style="width: 13.5rem;">
                                <img class="card-img-top" src="courseImg/courseDS1.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">The Ultimate Drawing Course</h5>
                                    <span class="card-text">Jaysen Batchelor, Quinton Batchelor</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="far fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>
                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        2-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem;margin-left: 8rem">
                                <img class="card-img-top" src="courseImg/courseDS2.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">Learn 3D Modelling for Beginners</h5>
                                    <span class="card-text">Rick Davidson</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        3-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem; margin-left: 16rem">
                                <img class="card-img-top" src="courseImg/courseDS3.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">Complete Character Drawing Course</h5>
                                    <span class="card-text">Scott Harris</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star-half fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>


                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!--                marketing-->
                <div id="marketing" style="display: none; margin-top: -2rem; margin-left: 5rem">
                    <div  class="row">
                        <!--                        1-->
                        <div class="col-sm-2 " >
                            <div class="card " style="width: 13.5rem;">
                                <img class="card-img-top" src="courseImg/courseMK1.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">The Complete Digital Marketing Course</h5>
                                    <span class="card-text">Rob Percival</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="far fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>
                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        2-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem;margin-left: 8rem">
                                <img class="card-img-top" src="courseImg/courseMK2.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">Social Media Marketing MASTERY</h5>
                                    <span class="card-text">COURSE ENVY</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        3-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem; margin-left: 16rem">
                                <img class="card-img-top" src="courseImg/courseMK3.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">Ultimate Google Ads Training 2020</h5>
                                    <span class="card-text">Isaac Rudansky</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star-half fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>


                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!--                it&software-->
                <div id="it" style="display: none; margin-top: -2rem; margin-left: 5rem">
                    <div  class="row">
                        <!--                        1-->
                        <div class="col-sm-2 " >
                            <div class="card " style="width: 13.5rem;">
                                <img class="card-img-top" src="courseImg/courseIT1.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">The Complete 2021 Web Development Bootcamp</h5>
                                    <span class="card-text">Dr. Angela Yu</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="far fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>
                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        2-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem;margin-left: 8rem">
                                <img class="card-img-top" src="courseImg/courseIT2.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">React - The Complete Guide (Hooks, Redux)</h5>
                                    <span class="card-text">Maximilian</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        3-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem; margin-left: 16rem">
                                <img class="card-img-top" src="courseImg/courseIT3.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">The Complete 2021 App Development Bootcamp</h5>
                                    <span class="card-text">Dr. Angela Yu</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star-half fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>


                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!--                second right screen-->

                <div id="myDIV">
                    <a class="btn2 " style="outline: none; font-weight: normal; font-size: 20px">Students are viewing</a>

                </div>  
                <!--                busines-->
                <div id="it" style="display: block; margin-top: -2rem; margin-left: 5rem">
                    <div  class="row">
                        <!--                        1-->
                        <div class="col-sm-2 " >
                            <div class="card " style="width: 13.5rem;">
                                <img class="card-img-top" src="courseImg/courseIT1.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">The Complete 2021 Web Development Bootcamp</h5>
                                    <span class="card-text">Dr. Angela Yu</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="far fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>
                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        2-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem;margin-left: 8rem">
                                <img class="card-img-top" src="courseImg/courseIT2.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">React - The Complete Guide (Hooks, Redux)</h5>
                                    <span class="card-text">Maximilian</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        3-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem; margin-left: 16rem">
                                <img class="card-img-top" src="courseImg/courseIT3.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">The Complete 2021 App Development Bootcamp</h5>
                                    <span class="card-text">Dr. Angela Yu</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star-half fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>


                                </div>
                            </div>
                        </div>

                    </div>
                </div>



                <div id="myDIV">
                    <a class="btn2 " style="outline: none; font-weight: normal; font-size: 20px">Some of the best</a>

                </div>  
                <!--                busines-->
                <div id="it" style="display: block; margin-top: -2rem; margin-left: 5rem">
                    <div  class="row">
                        <!--                        1-->
                        <div class="col-sm-2 " >
                            <div class="card " style="width: 13.5rem;">
                                <img class="card-img-top" src="courseImg/courseBN3.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">The Complete 2021 Web Development Bootcamp</h5>
                                    <span class="card-text">Dr. Angela Yu</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="far fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>
                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        2-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem;margin-left: 8rem">
                                <img class="card-img-top" src="courseImg/courseMK1.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">React - The Complete Guide (Hooks, Redux)</h5>
                                    <span class="card-text">Maximilian</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>
                                </div>
                            </div>
                        </div>
                        <!--                        3-->
                        <div class="col-sm-2">
                            <div class="card" style="width: 13.5rem; margin-left: 16rem">
                                <img class="card-img-top" src="courseImg/courseDS2.jpg" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title my-0">The Complete 2021 App Development Bootcamp</h5>
                                    <span class="card-text">Dr. Angela Yu</span>
                                    <div>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                                        <i class="fas fa-star-half fa-xs" style="color: #ffa805"></i>
                                    </div>
                                    <h5>$12.99</h5>

                                    <a href="#" class="btn btn-danger">Read more</a>


                                </div>
                            </div>
                        </div>

                    </div>
                </div>



            </div>


    </section>

    <div class="container">
        <div class="row">
            <c:forEach items="${course}" var="o">
                <div class="col-3">
                    <p>${o.courseName}</p>
                    <img src="${o.imageLink}"/>
                </div>
            </c:forEach>
        </div>
    </div>

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
