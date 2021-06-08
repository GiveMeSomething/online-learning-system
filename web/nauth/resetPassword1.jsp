<%-- 
    Document   : resetPassword1
    Created on : Jun 6, 2021, 11:54:33 PM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${path}/styles.css" />
    </head>
    <body>
        <jsp:include page="../components/global/navbar.jsp"/>
        <form action="${path}/authenticate" method="POST" 
              class="needs-validation w-25 bg-light d-block" 
              novalidate style="margin: auto;">
            <div class="request-info">
                <input name="previousPage" value="reset-password2.jsp" hidden="true" />
                <div class="invalid-feedback"></div>
                <input name="operation" value="RESETPW1" hidden="true" />
                <div class="invalid-feedback"></div>
            </div>
            <div class="my-2">
                <label for="reset-email">Email address</label>
                <input class="form-control" name="email" type="email" id="reset-email" placeholder="Enter email"
                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}" 
                       required data-value-missing="Can't be empty"
                       data-pattern-mismatch="Not a valid email" />
                <div class="invalid-feedback"></div>
            </div>
            <button type="submit" class="btn btn-primary my-2 align-mid">Send reset request</button>
        </form>
        <jsp:include page="../components/global/navbar.jsp"/>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous">
    </script>
    <!-- Import if have form input -->
    <script type="text/javascript" src="${path}/form-validate.js"></script>
</html>
