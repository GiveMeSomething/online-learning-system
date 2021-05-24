<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : adduser
    Created on : May 24, 2021, 2:31:00 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/edituser.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="dal" scope="page" class="database.DAO" />
    </head>
    <body>
        <div class="container">
            <form action="InsertUser" method="POST">
                <label for="fname">Image</label>
                <input type="text" id="fname" value="" name="image" placeholder="Input link image">
                <label for="fname">Full Name</label>
                <input type="text" id="fname" value="" name="fullname" placeholder="Your name..">
                <label for="fname">Email</label>
                <input type="text"  id="fname" value="" name="email" placeholder="Your email..">
                <label for="fname">Password</label>
                <input type="text"  id="fname" value="" name="password" placeholder="">
                <label for="fname">Address</label>
                <input type="text" id="fname" value="" name="address" placeholder="Your address..">
                <label for="fname">Mobile</label>
                <input type="text" id="fname" value="" name="mobile" placeholder="Your mobilephone..">
                <label for="gender">Gender</label>
                <select id="country" name="gender">
                    <option value="male" selected="" >Male</option>
                    <option value="female">Female</option>
                </select>
                <label for="gender">Status</label>
                <select id="country" name="status">
                    <option value="1" selected="" >Active</option>
                    <option value="0">Inactive</option>
                </select>
                <label for="gender">Role</label>
                <select id="country" name="role">
                    <c:forEach var="o" items="${dal.allRole}">
                        <option value="${o.id}" >${o.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Add">
            </form>
        </div>
    </body>
</html>
