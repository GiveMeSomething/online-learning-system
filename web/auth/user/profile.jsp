<%-- 
    Document   : userprofile
    Created on : May 30, 2021, 3:55:33 PM
    Author     : Nguyen Khanh Toan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
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

        <link rel="stylesheet" type="text/css" href="${path}/skeleton/styles.css">
    </head>
    <body>
        <jsp:include page="/components/global/navbar.jsp"></jsp:include>
            <div class="container">
                <h2 class="text-center">User Profile</h2> 
                <div class="row">
                    <div class="col-md-5">
                        <div class="w-75">
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


                <form action="${path}/user"
                      method="POST"
                      class="col-md-7 needs-validation"
                      novalidate>
                    <div class="request-info">
                        <input name="previousPage" value="home" hidden="true" />
                        <div class="invalid-feedback"></div>
                        <input name="operation" value="changeUserProfile" hidden="true" />
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="id">Id</label>
                        <input class="form-control"
                               name="id"
                               type="text"
                               id="id" disabled
                               placeholder="Id"
                               value="${user.id}"/>  
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="image">Image</label>
                        <input class="form-control"
                               name="image"
                               type="text"
                               id="image"
                               placeholder="Image url"
                               value="${user.image}"/> 
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group" style="margin-bottom: -11px">
                        <label for="fullName">Full Name</label> 
                        <input class="form-control"
                               name="fName"
                               type="text"
                               id="fullName"
                               placeholder="Full Name"
                               value="${user.name}"/><br>
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="form-group">
                        <div class="d-flex align-items-center">
                            <p class="mb-0 mr-3">Gender</p> 
                            <c:if test="${user.gender == 'MALE'}">
                                <input checked type="radio" id="male" name="gender" value="MALE"> 
                                <label style="margin-top: 5px" class="mr-3 ml-1" for="male">Male</label><br>
                                <div class="invalid-feedback"></div>
                                <input type="radio" id="female" name="gender" value="FEMALE">
                                <label style="margin-top: 5px" class="ml-1" for="female">Female</label><br>  
                                <div class="invalid-feedback"></div>
                            </c:if>
                            <c:if test="${user.gender == 'FEMALE'}">
                                <input type="radio" id="male" name="gender" value="MALE">  
                                <label style="margin-top: 5px" class="mr-3 ml-1" for="male">Male</label><br>
                                <div class="invalid-feedback"></div>
                                <input checked type="radio" id="female" name="gender" value="FEMALE">
                                <label style="margin-top: 5px" class="ml-1" for="female">Female</label><br>  
                                <div class="invalid-feedback"></div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input class="form-control"
                               name="email"
                               type="text"
                               id="email" disabled
                               placeholder="Email"
                               value="${user.email}"/> 
                        <div class="invalid-feedback"></div>
                    </div>


                    <div class="form-group">
                        <label for="address">Address</label>
                        <input class="form-control"
                               name="address"
                               type="text"
                               id="address"
                               placeholder="Address"
                               value="${user.address}"/>  
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <div class="d-flex align-items-center">
                            <p class="mb-0 mr-3">Status</p> 
                            <c:if test="${user.status == 'ACTIVE'}">
                                <input checked type="radio" id="active" name="status" value="ACTIVE">
                                <label style="margin-top: 7px" class="mr-3 ml-1" for="male">Active</label><br>
                                <div class="invalid-feedback"></div>
                                <input type="radio" id="inactive" name="status" value="INACTIVE">
                                <label style="margin-top: 7px" class="ml-1" for="female">Inactive</label><br>  
                                <div class="invalid-feedback"></div>
                            </c:if>

                            <c:if test="${user.status == 'INACTIVE'}">
                                <input type="radio" id="active" name="status" value="ACTIVE">
                                <label style="margin-top: 7px" class="mr-3 ml-1" for="male">Active</label><br>
                                <div class="invalid-feedback"></div>
                                <input checked type="radio" id="inactive" name="status" value="INACTIVE">
                                <label style="margin-top: 7px" class="ml-1" for="female">Inactive</label><br>  
                                <div class="invalid-feedback"></div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile">Mobile</label>
                        <input class="form-control"
                               name="mobile"
                               type="text"
                               id="mobile"
                               placeholder="Mobile"
                               value="${user.mobile}"/> 
                        <div class="invalid-feedback"></div>
                    </div>
                    <button type="submit" class="btn btn-success">Update Information</button><br>
                </form>
                <br>
            </div>
        </div>
        <jsp:include page="/components/global/footer.jsp"></jsp:include>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</html>


<!--https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZSt-J1nobJ_ElZN6XZdKh01z4u3zy7uUsIw&usqp=CAU-->