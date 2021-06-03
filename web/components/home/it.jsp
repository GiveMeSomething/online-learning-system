<%-- 
    Document   : it
    Created on : May 31, 2021, 11:54:44 AM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div  class="row" style="margin-left: -1.5rem">
            <!--                        1-->
            <c:forEach items="${itCourse}" var="o">
                <div class="col-4" style="height: max-content">
                    <div class="card" style="width: 13rem;">
                        <a href="course?cid=${o.id}"><img style="cursor: pointer" src="${o.imageLink}" class="card-img-top" alt="..."></a>
                        <div class="card-body">
                            <div style="height:145px">
                                <a href="course?cid=${o.id}" style="text-decoration: none; color: black"><h5 class="card-title" style="cursor: pointer">${o.courseName}</h5></a>
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

                            <div class="row mt-5"  style="margin-left: -25.7px">
                                <div class="col-6 mr-1">
                                    <a href="#" class="btn"
                                       style="background-color: #f53f34;color:white;border-radius:0px">
                                        Check Out
                                    </a> 
                                </div>
                                <div class="col-1">
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
    </body>
</html>
