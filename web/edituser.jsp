<%-- 
    Document   : edituser
    Created on : May 23, 2021, 10:37:29 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/edituser.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h3>Edit Information User</h3>
        <div class="container">
            <form action="EditUser" method="POST">
                <input type="hidden" name="uid" value="${user.id}" />
                <label for="fname">Full Name</label>
                <input type="text" id="fname" value="${user.name}" name="fullname" placeholder="Your name..">
                <label for="fname">Email</label>
                <input type="text" readonly style="background-color: silver;" id="fname" value="${user.email}" name="email" placeholder="Your email..">
                <label for="fname">Address</label>
                <input type="text" id="fname" value="${user.address}" name="address" placeholder="Your address..">
                <label for="fname">Mobile</label>
                <input type="text" id="fname" value="${user.mobile}" name="mobile" placeholder="Your mobilephone..">
                <label for="gender">Gender</label>
                <select id="country" name="gender">
                    <option value="male" ${user.gender?"selected":""} >Male</option>
                    <option value="female" ${user.gender?"":"selected"} >Female</option>
                </select>
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
