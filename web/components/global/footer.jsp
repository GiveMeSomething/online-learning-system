<%-- 
    Document   : footer
    Created on : May 31, 2021, 9:42:19 AM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section id="page-footer" class="container-fluid mt-5 d-flex justify-content-between align-items-center">
        <div class="page-dummy-footer px-5">
             <a class="navbar-brand" style="font-size: 2rem;" href="${path}/home">
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
</html>
