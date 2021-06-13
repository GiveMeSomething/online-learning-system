<%--
    Document   : oneFeaturedCourse
    Created on : Jun 1, 2021, 7:32:48 AM
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
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="row mt-4" style="margin-left: -1.5rem">
            <h4 style="font-weight: bolder; margin-left: .8rem; margin-bottom: 1rem">You might like</h4>
            <c:forEach items="${siderDetail}" var="o">
                <div style="width: 500px; display: flex; margin-left: .8rem">
                    <div style=" margin-right: 10px;height: 45px; margin-bottom: 5px">
                        <a href="course?courseId=${o.id}">
                            <img src="${o.imageLink}" style="width: 5rem; margin-right: 5px; margin-bottom: 10px;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
                        </a>
                    </div>
                    <div style="height: 45px; width: 250px">
                        <a class="sider-course-hover" href="course?courseId=${o.id}" style="margin-right: 5px">
                            ${o.courseName}
                        </a>
                    </div>
                    <div style="margin-top: 2px; margin-left: 5px">
                        <a class="sider-course-hover" href="course?courseId=${o.id}">
                            <i class="far fa-arrow-alt-circle-right"></i>
                        </a>
                    </div>
                </div>
                <div>
                    <hr style="margin-left: .8rem; width: 380px">
                </div>
            </c:forEach>
        </div>
    </body>
</html>
