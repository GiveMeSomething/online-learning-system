<%-- 
    Document   : courselist
    Created on : May 27, 2021, 7:38:35 AM
    Author     : Nguyen Khanh Toan
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Course List</title>
        <!--        Bootstrap_Carousel-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">      
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <!--        fontawesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <link rel="stylesheet" type="text/css" href="${path}/skeleton/styles.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
            <div class="container-fluid" style='width:85%;margin:0 auto'>
                <h3 class="mb-4">${title}</h3>
            <!--FEATURED COURSE-->
            <h3 class="text-center">Featured Course</h3>
            <div id="carouselExampleControls" class="carousel slide mb-5" data-ride="carousel">
                <div class="carousel-inner">
                    <c:forEach items="${courseFeature}" var="o">
                        <div class="carousel-item ${o.id == id ? "active":""} banner-margin-top" >
                            <div class="banner-information bg-dark mx-lg-5">
                                <div class="banner-sub-information p-3">
                                    <b class="text-light banner-primary">
                                        ${o.courseName}
                                    </b><br>
                                    <span class="text-light banner-secondary">
                                        ${o.description}
                                        <div style="margin-top:10px;background-color: #fce59b;width:80px;height:35px;color:#2b1b13;border-radius: 10px;text-align: center;line-height:30px;align-items: center">Best seller</div>
                                    </span><br/>
                                    <div class="row">
                                        <a href="#" class="btn ml-3 mr-2"
                                           style="background-color: #f53f34;color:white;border-radius:0px">
                                            Check Out
                                        </a> 
                                        <a href="#" class="btn"
                                           style="background-color: #072b7d;color:white;border-radius:0px">
                                            Register
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <img class="d-block w-100"
                                 style="height: 500px"
                                 src="${o.imageLink}" alt="First slide">
                        </div>
                    </c:forEach>

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


            <div class="row" style="justify-content: space-around">
                <!--FILTER COURSE-->
                <div class="col-2">
                    <h3>Filter</h3>
                    <hr/>
                    <div>
                        <div class="accordion" id="accordionExample">
                            <div class="accordion-item">
                                <h4 class="accordion-button"
                                    type="button" data-toggle="collapse"
                                    data-target="#collapseOne">
                                    Price<i class="fa fa-angle-down" style="margin-left:50%"></i></h4>
                               
                                <div id="collapseOne" class="accordion-collapse collapse show"
                                     data-parent="#accordionExample">
                                    <input ${sessionScope.price == 1 ? "checked":""} name="price" type='radio' value="1"/>
                                    <div style='margin-top: -20px;margin-left:21px'>
                                        <span>Ascending Price</span>  
                                    </div>
                                    <input ${sessionScope.price == 0 ? "checked":""} name="price" type='radio' value="0">
                                    <div style='margin-top: -20px;margin-left:21px'>
                                        <span>Descending Price</span>  
                                    </div>
                                </div>
                            </div>
                            
                           
                        </div>
                        <hr/>
                        <div class="accordion" id="filterAlpha">
                            <div class="accordion-item">
                                <h4 class="accordion-button"
                                    type="button" data-toggle="collapse"
                                    data-target="#filterA">
                                    Alphabet<i class="fa fa-angle-down" style="margin-left:26%"></i></h4>
                               
                                <div id="filterA" class="accordion-collapse collapse show"
                                     data-parent="#filterAlpha">
                                    <input ${sessionScope.alpha == "ascAlpha" ? "checked":""} name="alpha" type='radio' value="ascAlpha"/>
                                    <div style='margin-top: -20px;margin-left:21px'>
                                        <span>A-Z</span>  
                                    </div>
                                    <input ${sessionScope.alpha == "descAlpha" ? "checked":""} name="alpha" type='radio' value="descAlpha">
                                    <div style='margin-top: -20px;margin-left:21px'>
                                        <span>Z-A</span>  
                                    </div>
                                </div>
                            </div>
                            
                           
                        </div>



                    </div>
                </div>
                <div class="col-10">
                    <h3>Other Courses</h3>
                    <div class="row">
                        <c:forEach items="${course}" var="o">
                            <div class="col-3">
                                <div class="card mb-3" style="width: 101%;height:360px">
                                    <img style="cursor: pointer" src="${o.imageLink}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <div style="height:145px">
                                            <h5 class="card-title">${o.courseName}</h5>
                                            <div class="d-flex">
                                                <p style="text-decoration: line-through;color:#dd012d" class="card-text mr-2">$${o.price}</p>  
                                                <p class="card-text font-weight-bold">
                                                    $<fmt:formatNumber type="number" maxFractionDigits="2" value="${o.price * 0.8}" />
                                                </p> 
                                            </div>
                                            <div style="font-family: cursive;margin-top: -10px">
                                                ${o.tag}
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-6">
                                                <a href="#" class="btn"
                                                   style="background-color: #f53f34;color:white;border-radius:0px">
                                                    Check Out
                                                </a> 
                                            </div>
                                            <div class="col-6">
                                                <a href="#" class="btn"
                                                   style="background-color: #072b7d;color:white;border-radius:0px">
                                                    Register
                                                </a>
                                            </div>
                                        </div>

                                    </div>
                                </div> 
                            </div>

                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="clearfix text-center">
                <ul class="pagination justify-content-center mb-5">
                    <li class="page-item">
                        <a style="color:#005b96" href="#" class="page-link">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${end}" var="i">
                        <li class="page-item page-item-paging ${tag == i?"active":""}">
                            <a style=""
                               href="paging?index=${i}&&cID=${cateID}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=${sessionScope.alpha}"
                               class="page-link course__list__paging">
                                ${i}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a style="color:#005b96" href="#" class="page-link">
                            Next
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
            <script>
                $(function () {
                    $("input[name$='price']").click(function () {
                        var value = $(this).val();
                        if (value == 1) {
                            window.location.assign("CourseListController?cID=${cateID}&&price="+value);
                        } else if (value == 0) {
                            window.location.assign("CourseListController?cID=${cateID}&&price="+value);
                        }
                    });
                });
                
                $(function () {
                    $("input[name$='alpha']").click(function () {
                        var value = $(this).val();
                        if (value === 'ascAlpha') {
                            window.location.assign("CourseListController?cID=${cateID}&&alpha="+value);
                        } else if (value === 'descAlpha') {
                            window.location.assign("CourseListController?cID=${cateID}&&alpha="+value);
                        }
                    });
                });
        </script>
    </body>
</html>
