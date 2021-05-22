<%--
    Document   : index
    Created on : May 22, 2021, 5:00:41 PM
    Author     : Hoang Tien Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${requestScope.user.id}</h1>
        <img src="${requestScope.user.avatarURL}" alt="avatar" />
        <h1>${requestScope.user.fullName}</h1>
        <h1>${requestScope.user.gender}</h1>
        <h1>${requestScope.user.email}</h1>
        <h1>${requestScope.user.role}</h1>
        <h1>${requestScope.user.address}</h1>
        <h1>${requestScope.user.status}</h1>
        <h1>${requestScope.user.phoneNumber}</h1>
    </body>
</html>
