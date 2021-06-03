<%-- 
    Document   : userprofile
    Created on : May 30, 2021, 3:55:33 PM
    Author     : Nguyen Khanh Toan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <!--        Bootstrap_Carousel-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <!--        fontawesome-->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <link rel="stylesheet" type="text/css" href="skeleton/styles.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <h2 class="text-center">User Profile</h2> 
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group w-75">
                            <img class="mb-3" style="width: 90%" 
                                 src="${user.image}" class="img-rounded img-responsive"><br>
                        <p class="text-center" style="color:#959fb2">
                            Email: kancute@gmail.com
                        </p>
                        <h4>Change your password</h3>
                            <label>Enter Old Password</label> 
                            <input type="password" class="form-control"> 
                            <label>Enter New Password</label>
                            <input type="password" class="form-control"> 
                            <label>Confirm New Password</label>
                            <input type="password" class="form-control"><br>
                            <a href="#" class="btn btn-warning">Change Password</a>
                    </div>
                </div>


                <form action="userProfile" method="post" class="col-md-7">
                    <div class="form-group">
                        <label>Id</label>
                        <input disabled value="${user.id}" name="id" type="text" class="form-control" placeholder="Id">  
                    </div>
                   
                    <div class="form-group">
                        <label>Image</label>
                        <input value="${user.image}" name="image" type="text" class="form-control" placeholder="Image url"> 
                    </div>

                    <div class="form-group" style="margin-bottom: -11px">
                        <label>Full Name</label> 
                        <input value="${user.name}" name="fName" type="text" class="form-control" placeholder="Full Name"><br>
                    </div>
                    <div class="form-group">
                        <div class="d-flex align-items-center">
                            <p class="mb-0 mr-3">Gender</p> 
                            <c:if test="${user.gender == 'MALE'}">
                                <input checked type="radio" id="male" name="gender" value="MALE"> 
                                <label style="margin-top: 5px" class="mr-3 ml-1" for="male">Male</label><br>
                                <input type="radio" id="female" name="gender" value="FEMALE">
                                <label style="margin-top: 5px" class="ml-1" for="female">Female</label><br>  
                            </c:if>
                            <c:if test="${user.gender == 'FEMALE'}">
                                <input type="radio" id="male" name="gender" value="MALE">  
                                <label style="margin-top: 5px" class="mr-3 ml-1" for="male">Male</label><br>
                                <input checked type="radio" id="female" name="gender" value="FEMALE">
                                <label style="margin-top: 5px" class="ml-1" for="female">Female</label><br>  
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input disabled value="${user.email}" name="email" type="text" class="form-control" placeholder="Email"> 
                    </div>


                    <div class="form-group">
                        <label>Address</label>
                        <input value="${user.address}" name="address" type="text" class="form-control" placeholder="Address">  
                    </div>

                    <div class="form-group">
                        <div class="d-flex align-items-center">
                            <p class="mb-0 mr-3">Status</p> 
                            <c:if test="${user.status == 'ACTIVE'}">
                                <input checked type="radio" id="active" name="status" value="ACTIVE">
                                <label style="margin-top: 7px" class="mr-3 ml-1" for="male">Active</label><br>
                                <input type="radio" id="inactive" name="status" value="INACTIVE">
                                <label style="margin-top: 7px" class="ml-1" for="female">Inactive</label><br>  
                            </c:if>

                            <c:if test="${user.status == 'INACTIVE'}">
                                <input type="radio" id="active" name="status" value="ACTIVE">
                                <label style="margin-top: 7px" class="mr-3 ml-1" for="male">Active</label><br>
                                <input checked type="radio" id="inactive" name="status" value="INACTIVE">
                                <label style="margin-top: 7px" class="ml-1" for="female">Inactive</label><br>  
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Mobile</label>
                        <input value="${user.mobile}" name="mobile" type="text" class="form-control" placeholder="Mobile"> 
                    </div>
                    <button type="submit" class="btn btn-success">Update Information</button><br>
                    <br>
                    </div>
                    </div>
                    </div>
                    <jsp:include page="footer.jsp"></jsp:include>


                    </body>
                    </html>

                    
                    <!--https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZSt-J1nobJ_ElZN6XZdKh01z4u3zy7uUsIw&usqp=CAU-->