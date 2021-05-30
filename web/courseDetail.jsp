<%-- 
    Document   : courseDetail
    Created on : May 29, 2021, 8:12:28 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Webpage Skeleton</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
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

        <section id="page-content-secondary">
            <div class="d-flex" style="background: black">
                <button style="position: absolute; margin-top: 6rem" type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal1">
                    Launch demo modal
                </button>
                <button style="position: absolute" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                    Launch demo modal1
                </button>
                <h1 style="color: white; flex: 40%; margin: 6rem 5rem">Title</h1>
                <img class="card-img-top" style="flex: 40%" src="courseImg/courseIT1.jpg" alt="Card image cap">
            </div>
        </section>


        <section id="page-content-primary" class="container my-5 d-flex flex-column justify-content-center align-items-center">

        </section>



        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Blue
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Red
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
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

</html>


