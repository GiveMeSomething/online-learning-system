<%--
    Document   : userprofile
    Created on : May 30, 2021, 3:55:33 PM
    Author     : Nguyen Khanh Toan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous"/>
        <link rel="stylesheet" type="text/css" href="${path}/style/styles.css">
    </head>
    <body>
        <jsp:include page="/components/global/navbar.jsp"/>
        <div class="container">
            <h2 class="text-center">User Profile</h2>
            <div class="row">
                <div class="col-md-5">
                    <div class="w-75">
                        <img class="mb-3" style="width: 90%"
                             src="${sessionScope.user.image}" class="img-rounded img-responsive"><br>
                        <p class="text-center" style="color:#959fb2">
                            Email: ${sessionScope.user.email}
                        </p>
                        <h4>Change your password</h3>
                            <form action="${path}/authenticate"
                                  method="POST"
                                  class="needs-validation"
                                  novalidate>
                                <div class="request-info">
                                    <input name="previousPage" value="home" hidden="true" />
                                    <div class="invalid-feedback"></div>
                                    <input name="operation" value="CHANGEPW" hidden="true" />
                                    <div class="invalid-feedback"></div>
                                </div>
                                <div class="form-group">
                                    <label for="current-password">Enter Old Password</label>
                                    <input class="form-control"
                                           name="current-password"
                                           type="password"
                                           id="current-password"
                                           data-value-missing="Can't be empty"
                                           required/>
                                    <div class="invalid-feedback"></div>
                                </div>
                                <div class="form-group">
                                    <label for="id">Enter New Password</label>
                                    <input class="form-control"
                                           name="new-password"
                                           type="password"
                                           id="new-password"
                                           data-value-missing="Can't be empty"
                                           required/>
                                    <div class="invalid-feedback"></div>
                                </div>
                                <div class="form-group">
                                    <label for="confirm-password">Confirm New Password</label>
                                    <input class="form-control"
                                           name="confirm-password"
                                           type="password"
                                           id="confirm-password"
                                           data-value-missing="Can't be empty"
                                           required/>
                                    <div class="invalid-feedback"></div>
                                </div>
                                <button class="btn btn-primary">
                                    Change Password
                                </button>
                            </form>
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
                               value="${sessionScope.user.id}"
                               data-value-missing="Can't be empty"
                               required/>
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="form-group">
                        <label for="image">Image</label>
                        <input class="form-control"
                               name="image"
                               type="text"
                               id="image"
                               placeholder="Image url"
                               value="${sessionScope.user.image}"
                               data-value-missing="Can't be empty"
                               required/>
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="form-group" style="margin-bottom: -11px">
                        <label for="fullName">Full Name</label>
                        <input class="form-control"
                               name="fName"
                               type="text"
                               id="fullName"
                               placeholder="Full Name"
                               value="${sessionScope.user.name}"
                               data-value-missing="Can't be empty"
                               required/><br>
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="form-group">
                        <div class="d-flex align-items-center">
                            <p class="mb-0 mr-3">Gender</p>
                            <c:if test="${sessionScope.user.gender == 'MALE'}">
                                <input name="gender"
                                       type="radio"
                                       id="male"
                                       value="MALE"
                                       checked
                                       data-value-missing="Can't be empty"
                                       required/>
                                <label style="margin-top: 5px" class="mr-3 ml-1" for="male">
                                    Male
                                </label><br>
                                <div class="invalid-feedback"></div>
                                <input name="gender"
                                       type="radio"
                                       id="female"
                                       value="FEMALE"
                                       data-value-missing="Can't be empty"
                                       required/>
                                <label style="margin-top: 5px" class="ml-1" for="female">
                                    Female
                                </label><br>
                                <div class="invalid-feedback"></div>
                            </c:if>
                            <c:if test="${sessionScope.user.gender == 'FEMALE'}">
                                <input name="gender"
                                       type="radio"
                                       id="male"
                                       value="MALE"
                                       data-value-missing="Can't be empty"
                                       required/>
                                <label style="margin-top: 5px" class="mr-3 ml-1" for="male">
                                    Male
                                </label><br>
                                <div class="invalid-feedback"></div>
                                <input name="gender"
                                       type="radio"
                                       id="female"
                                       value="FEMALE"
                                       checked
                                       data-value-missing="Can't be empty"
                                       required/>
                                <label style="margin-top: 5px" class="ml-1" for="female">
                                    Female
                                </label><br>
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
                               value="${sessionScope.user.email}"
                               />
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input class="form-control"
                               name="address"
                               type="text"
                               id="address"
                               placeholder="Address"
                               value="${sessionScope.user.address}"
                               data-value-missing="Can't be empty"
                               required/>
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="form-group">
                        <div class="d-flex align-items-center">
                            <p class="mb-0 mr-3">Status</p>
                            <c:if test="${sessionScope.user.status == 'ACTIVE'}">
                                <input name="status"
                                       type="radio"
                                       id="active"
                                       value="ACTIVE"
                                       checked
                                       data-value-missing="Can't be empty"
                                       required/>
                                <label style="margin-top: 7px" class="mr-3 ml-1" for="male">
                                    Active
                                </label><br>
                                <div class="invalid-feedback"></div>
                                <input name="status"
                                       type="radio"
                                       id="inactive"
                                       value="INACTIVE"
                                       data-value-missing="Can't be empty"
                                       required/>
                                <label style="margin-top: 7px" class="ml-1" for="female">
                                    Inactive
                                </label><br>
                                <div class="invalid-feedback"></div>
                            </c:if>

                            <c:if test="${sessionScope.user.status == 'INACTIVE'}">
                                <input name="status"
                                       type="radio"
                                       id="active"
                                       value="ACTIVE"
                                       data-value-missing="Can't be empty"
                                       required>
                                <label style="margin-top: 7px" class="mr-3 ml-1" for="male">
                                    Active
                                </label><br>
                                <div class="invalid-feedback"></div>
                                <input
                                    checked type="radio" id="inactive"
                                    name="status" value="INACTIVE">
                                <label style="margin-top: 7px" class="ml-1" for="female">
                                    Inactive
                                </label><br>
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
                               pattern="^\d{10}$"
                               value="${sessionScope.user.mobile}"
                               data-value-missing="Can't be empty"
                               data-pattern-mismatch="Not a valid phonenumber"
                               required/>
                        <div class="invalid-feedback"></div>
                    </div>
                    <button type="submit" class="btn btn-success">Update Information</button><br>
                </form>
                <br>
            </div>
        </div>
        <jsp:include page="/components/global/footer.jsp"/>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
            crossorigin="anonymous">
    </script>
    <script src="${path}/utilities/form-validator.js"></script>
</html>
