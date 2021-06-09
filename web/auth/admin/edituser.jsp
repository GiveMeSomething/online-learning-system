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
        <style>
            body {
                font-family: Arial;
            }
            input[type=text], select {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type=submit] {
                width: 100%;
                background-color: #04AA6D;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }

            .container {
                border-radius: 5px;
                background-color: #f2f2f2;
                padding: 20px;
            }
        </style>
    </head>
    <body>
    
        <h3>Edit Information User</h3>
        <div class="container">
            <form action="updateuser" method="GET">
                <input type="hidden" name="uid" value="${user.id}" />
                <label for="fname">Image</label>
                <input type="text" id="fname" value="${user.image}" name="image" placeholder="Your img..">
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
                    <option value="male" ${user.gender.toString() eq  'MALE'?"selected":""} >Male</option>
                    <option value="female" ${user.gender.toString() eq  'FEMALE'?"selected":""} >Female</option>
                </select>
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
