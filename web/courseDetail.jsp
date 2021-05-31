<%-- 
    Document   : courseDetail
    Created on : May 29, 2021, 8:12:28 PM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <jsp:include page="header.jsp"></jsp:include>
            <div>
                <section id="brief-content">
                    <div id="brief-content-upper" class=" bg-dark d-flex" style="height: 18.5rem">
                        <div id="brief-info" style="flex: 50; margin-left: 12rem; margin-top: 3.2rem">
                            <h3 class="text-light" style="margin-bottom: .4rem; font-weight: bolder">${detail.courseName}</h3>
                        <p class="text-light" style="font-size: 1.2rem; margin-bottom: 0">${detail.tag}</p>
                        <div style="margin-bottom: .2rem">
                            <span style="color: #ffa805">4.0</span>
                            <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                            <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                            <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                            <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                            <i class="far fa-star fa-xs" style="color: #ffa805"></i>
                        </div>
                        <p style="font-size: 0.8rem; margin-bottom: .2rem" class="text-light">Created by #author</p>

                        <p style="font-size: 0.8rem" class="text-light"><span style="color:#2996A9 ">#</span><a id="course-detail-category" href="#" style="color: #2996A9; text-decoration: none">${detail.category}</a></p>
                    </div>
                    <div  id="brief-image"><img style="margin-right: 12rem; margin-top:3.5rem;width: 20rem; float: right; border-radius: 5px;box-shadow: 10px 10px 7px black;" src="${detail.imageLink}"></div>
                </div>
            </section>

            <section id="main-content" >
                <div id="main-content-lower" class="d-flex">
                    <div id="main-content-lower-left" style="flex: 30; margin-left: 12rem; margin-top: 2rem">
                        <h4 style="font-weight: bolder">Course content</h4>
                        <p>${detail.description}</p>
                        <h4 style="font-weight: bolder">Requirement</h4>
                        <p>${detail.description}</p>
                        <h4 style="font-weight: bolder">Description</h4>
                        <p>${detail.description}</p>
                        <p>${detail.description}</p>
                        <p>${detail.description}</p>
                        <p>${detail.description}</p>
                    </div>
                    <div id="main-content-lower-right"  style="flex: 30;margin-left: 3.5rem">
                        <div style=" border-radius: 5px;border: 1px solid whitesmoke; height: 10rem; width: 20rem; margin-top: 2rem; box-shadow: 2px 4px 15px black;">
                            <div class="d-flex" style="margin-bottom: -0.5rem; margin-left: 2.5rem; margin-top: 0.5rem">
                                <div style="margin-top: 0rem; margin-right: 5px"><i class="fas fa-tag fa-xs"></i></div>
                                <p class="font-weight-bold" style="font-size: larger; margin-right: 6px">
                                    $<fmt:formatNumber type="number" maxFractionDigits="2" value="${detail.price * 0.8}" />
                                </p> 
                                <p style="text-decoration: line-through; font-size: small" class="card-text mr-2">$${detail.price}</p>  

                            </div>

                            <div class="mb-2" style="margin-left: 2.5rem"><a href="#" class="btn btn-danger px-sm-5 py-2" style="padding: 0.5rem 5.6rem!important">Buy now</a></div>
                            <div style="margin-left: 2.5rem"><a href="#" class="btn btn-outline-primary px-4 py-2" style="padding: 0.5rem 5rem!important">Add to cart</a></div>

                        </div>

                    </div>
                </div>


            </section>  
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</html>


