<%--
    Document   : courseDetail
    Created on : May 29, 2021, 8:12:28 PM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Webpage style</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous"/>
        <link rel="stylesheet" type="text/css" href="${path}/style/styles.css">
    </head>

    <body>
        <jsp:include page="/components/global/navbar.jsp"/>
        <div>
            <section id="brief-content">
                <div id="brief-content-upper" class=" bg-dark d-flex" style="height: 19.5rem">
                    <div id="brief-info" style="flex: 50; margin-left: 12rem; margin-top: 1.5rem">
                        <h3 class="text-light" style="margin-bottom: .4rem; font-weight: bolder">${detail.courseName}</h3>
                        <p class="text-light" style="font-size: 1.2rem; margin-bottom: 0">${detail.tag}</p>
                        <div class="d-flex mt-1">
                            <p class="font-weight-bold" style="font-size: larger; margin-right: 6px; color:white">
                                $<fmt:formatNumber type="number" maxFractionDigits="2" value="${detail.price * 0.8}" />
                            </p>
                            <p style="text-decoration: line-through; font-size: small;color: white" class="card-text mr-2">$${detail.price}</p>
                        </div>
                        <div style="margin-bottom: .2rem; margin-top: -1rem">
                            <span style="color: #ffa805">4.0</span>
                            <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                            <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                            <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                            <i class="fas fa-star fa-xs" style="color: #ffa805"></i>
                            <i class="far fa-star fa-xs" style="color: #ffa805"></i>
                        </div>
                        <p style="font-size: 0.8rem; margin-bottom: .2rem" class="text-light">Created by #author</p>
                        <p style="font-size: 0.8rem" class="text-light"><span style="color:#2996A9 ">#</span>
                            <a id="course-detail-category" href="#" style="color: #2996A9; text-decoration: none">
                                ${detail.category}
                            </a>
                        </p>
                    </div>
                    <div id="brief-image">
                        <img
                            style="margin-right: 12rem; margin-top:1.5rem;width: 20rem; float: right; border-radius: 5px 5px 0px 0px;box-shadow: 1px 1px 7px black"
                            src="${detail.imageLink}"
                            />
                        <div style="width: 320px;height:217px; border-radius:0px 0px 5px 5px;background: black; margin-top: 50px;box-shadow: 1px 1px 7px black">
                            <div style="margin-top: 2rem; margin-left: 5rem">
                                <a href="#"
                                   class="btn px-sm-5 py-2"
                                   style="padding: 0.5rem 3rem!important; background: #007791; color: white; font-weight: bold; margin-top: 10px">
                                    Buy now
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section id="main-content">
                <div id="main-content-lower" class="d-flex">
                    <div id="main-content-lower-left" style="flex: 30; margin-left: 12rem; margin-top: 2rem; ">
                        <div id="summary-board" class="bg-light p-3" style="border: 1px solid lightgray; border-radius: 5px; margin-bottom: 14px;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                            <h4 style="font-weight: bolder">What you'll learn</h4>
                            <div class="d-flex ">
                                <div >
                                    <p>${detail.description}</p>
                                    <p>${detail.description}</p>
                                </div>
                                <div>
                                    <p>${detail.description}</p>
                                    <p>${detail.description}</p>
                                </div>
                            </div>
                        </div>
                        <h4 style="font-weight: bolder">Course content</h4>
                        <p>${detail.description}</p>
                        <h4 style="font-weight: bolder">Requirement</h4>
                        <ul>
                            <li><p>${detail.description}</p></li>
                            <li><p>${detail.description}</p></li>
                        </ul>
                        <h4 style="font-weight: bolder">Description</h4>
                        <p>${detail.description}</p>
                        <p>${detail.description}</p>
                        <p>${detail.description}</p>
                        <p>${detail.description}</p>
                        <p>${detail.description}</p>
                        <p>${detail.description}</p>
                    </div>
                    <div id="main-content-lower-right"  style="flex: 30;margin-left: 3.5rem">
                        <section id="flip-price-package">
                            <div class="flip-card">
                                <div class="flip-card-inner">
                                    <div class="flip-card-front">
                                        <div style="margin-top: 2.5rem">
                                            <span style="font-weight: bold; text-transform: uppercase">Curious about the price package?</span><br>
                                            <span >Hover over me to find out</span>
                                        </div>
                                    </div>
                                    <div class="flip-card-back d-flex">
                                        <div style="border: 1px solid lightgray; margin-right: .5rem; height: 130px; border-radius: 5px;width: 116px;height: 180px; text-align: center">
                                            <span style="font-weight: bold; background: #FFA76C;padding: 5px 19px; border-radius: 5px 5px 0px 0px">Package 1</span>
                                            <div style="color: #FFA76C;margin-top: .5rem">from <span style="font-weight: bold">$12.99</span></div>
                                            <div style="width: 62px;margin-left: 1.5rem">3-month access Package</div>
                                            <div style=" margin-top: 1.5rem"><span style="font-weight: bold;">#notthatcheap</span></div>
                                        </div>
                                        <div style="border: 1px solid lightgray; margin-right: .5rem; height: 130px; border-radius: 5px; width: 116px;height: 180px; text-align: center">
                                            <span style="font-weight: bold; background: #48B1FF; width: 50px; padding: 5px 19px; border-radius: 5px 5px 0px 0px">Package 2</span>
                                            <div style="color: #48B1FF; margin-top: .5rem">from <span style="font-weight: bold">$24.99</span></div>
                                            <div style="width: 62px;margin-left: 1.5rem;">6-month access  Package</div>
                                            <div style=" margin-top: 1.5rem"><span style="font-weight: bold;">#cool</span></div>
                                        </div>
                                        <div style="border: 1px solid lightgray; height: 130px; border-radius: 5px;width: 116px;height: 180px; text-align: center">
                                            <span style="font-weight: bold;background: #835DED; width: 50px; padding: 5px 19px; border-radius: 5px 5px 0px 0px" >Package 3</span>
                                            <div style="color: #835DED; margin-top: .5rem">from <span style="font-weight: bold">$29.99</span></div>
                                            <div style="width: 62px;margin-left: 1.5rem">Permanent Package</div>
                                            <div style=" margin-top: 3rem"><span style="font-weight: bold;">#nice</span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section id="recommendation-course">
                            <div>
                                <jsp:include page="/components/courseDetail/oneFeaturedCourse.jsp"></jsp:include>
                                </div>
                            </section>
                            <section id="contact-link">
                                <h4 style="font-weight: bolder; margin-bottom: 1rem">Contact us</h4>
                                <div class="d-flex">
                                    <i class="fab fa-facebook fa-lg"></i>
                                    <i class="fab fa-instagram fa-lg"></i>
                                    <i class="fab fa-twitter-square fa-lg"></i>
                                    <i class="fas fa-envelope fa-lg"></i>
                                    <i class="fas fa-phone-square-alt fa-lg"></i>
                                </div>
                            </section>
                        </div>
                    </div>
                </section>
            </div>
        <jsp:include page="/components/global/footer.jsp"/>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            ntegrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.6.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
</html>


