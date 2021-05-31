<%-- 
    Document   : changePassword
    Created on : May 29, 2021, 9:49:41 PM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="../changePass" method="POST">
            <label for="current-pass">Current Password</label><br/>
            <input type="password" id="current-pass" name="current"><br/>
            <label for="new-pass">New Password</label><br/>
            <input type="password" id="new-pass" name="new"><br/>
            <label for="re-enter">Re-enter New Password</label> <br/>
            <input type="password" id="re-enter" name="rePass"><br/>
            <input type="submit" value="Change Password"> 
        </form>
    </body>
</html>
