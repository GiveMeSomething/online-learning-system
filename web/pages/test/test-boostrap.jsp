<%--
    Document   : test-boostrap
    Created on : May 12, 2021, 10:13:39 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Testing</title>
        <link rel="stylesheet" type="text/css" href="${path}/bootstrap/css/bootstrap.min.css" />
    </head>it s
    <body>
        <div>
            <button type="button" class="btn btn-primary">
                Testing Testing
            </button>
        </div>
    </body>
</html>
