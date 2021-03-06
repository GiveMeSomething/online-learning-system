<%--
    Document   : list
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Course List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous"/>
        <link rel="stylesheet" type="text/css" href="${path}/style/styles.css">
    </head>
    <body>
        <jsp:include page="/components/global/navbar.jsp" />
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
                                        ${o.tag}
                                        <div style="margin-top:10px;background-color: #fce59b;width:80px;height:35px;color:#2b1b13;border-radius: 10px;text-align: center;line-height:30px;align-items: center">Best seller</div>
                                    </span><br/>
                                    <div class="row">
                                        <a href="course?courseId=${o.id}" class="btn ml-3 mr-2"
                                           style="background-color: #f53f34;color:white;border-radius:0px">
                                            More...
                                        </a>

                                    </div>
                                </div>
                            </div>
                            <a href="course?courseId=${o.id}">
                                <img class="d-block w-100"
                                     style="height: 500px"
                                     src="${o.imageLink}" alt="First slide"> 
                            </a>

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
                            <div class="col-3 mb-3">
                                <div class="card h-100" style="font-size: 1rem; ">
                                    <a href="course?courseId=${o.id}">
                                        <img style="cursor: pointer" src="${o.imageLink}"
                                             class="card-img-top" alt="...">
                                    </a>
                                    <div class="card-body">
                                        <div style="min-height: 25vh">
                                            <a href="course?courseId=${o.id}" style="text-decoration: none; color: black">
                                                <h5 class="card-title" style="cursor: pointer">${o.courseName}</h5>
                                            </a>
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
                                        <div class="text-center">
                                            <a href="course?courseId=${o.id}" class="text-decoration-none">
                                                <button class="btn btn-success">More...</button>
                                            </a>
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
                        <c:choose>
                            <c:when test="${tag > 1}">
                                <a style="color:#005b96"
                                   href="course?index=${tag-1}&&cID=${sessionScope.categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=${sessionScope.alpha}"
                                   class="page-link">
                                    Previous
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a style="color:#005b96"
                                   href="course?index=${tag}&&cID=${sessionScope.categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=${sessionScope.alpha}"
                                   class="page-link">
                                    Previous
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <c:forEach begin="1" end="${end}" var="i">
                        <li class="page-item page-item-paging ${tag == i?"active":""}">
                            <a style=""
                               href="course?index=${i}&&cID=${sessionScope.categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=${sessionScope.alpha}"
                               class="page-link course__list__paging">
                                ${i}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <c:choose>
                            <c:when test="${end < (tag+1)}">
                                <a style="color:#005b96"
                                   href="course?index=${tag}&&cID=${sessionScope.categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=${sessionScope.alpha}"
                                   class="page-link">
                                    Next
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a style="color:#005b96"
                                   href="course?index=${tag+1}&&cID=${sessionScope.categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=${sessionScope.alpha}"
                                   class="page-link">
                                    Next
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/components/global/footer.jsp" />
    </body>
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
    <script>
        $(function () {
            $("input[name$='price']").click(function () {
                var value = $(this).val();
                if (value == 1) {
                    window.location.assign("course?index=${tag}&&cID=${categoryId}&&searchName=${sessionScope.searchName}&&price=" + value + "&&alpha=${sessionScope.alpha}");
                } else if (value == 0) {
                    window.location.assign("course?index=${tag}&&cID=${categoryId}&&searchName=${sessionScope.searchName}&&price=" + value + "&&alpha=${sessionScope.alpha}");
                }
            });
        });

        $(function () {
            $("input[name$='alpha']").click(function () {
                var value = $(this).val();
                if (value === 'ascAlpha') {
                    window.location.assign("course?index=${tag}&&cID=${categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=" + value);
                } else if (value === 'descAlpha') {
                    window.location.assign("course?index=${tag}&&cID=${categoryId}&&searchName=${sessionScope.searchName}&&price=${sessionScope.price}&&alpha=" + value);
                }
            });
        });
    </script>
</html>
