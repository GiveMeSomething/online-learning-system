<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : adduser
    Created on : May 24, 2021, 2:31:00 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="dal" scope="page" class="user.UserRepository" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous"/>
        <link rel="stylesheet" type="text/css" href="${path}/style/styles.css">
        <style>
            /*            body {
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
                        .maincontent{
                            display: flex;
                        }
                        .left{
                            width: 50%;
                            height: auto;
                            padding-right: 5px;
                        }
                        .right{
                            width: 50%;
                            height: auto;
                        }*/
        </style>
    </head>
    <body>
        <div class="container">
            <!--   <form action="adduser" method="GET">
                   <div class="maincontent">
                       <div class="left">
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
                       </div>
                       <div class="right">
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
                                   <option value="0" >ADMIN</option>
                                   <option value="1" >TEACHER</option>
                                   <option value="2" >STUDENT</option>
                           </select>
                       </div>
                   </div>
                   <input type="submit" value="Add">
               </form>
            -->
           
            <form name="myForm" action="adduser"
                  method="GET"
                  class="needs-validation"
                  novalidate onsubmit="return validateForm()">
                <div class="request-info">
                    <input name="previousPage" value="/" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="REGISTER" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">ADD USER</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="my-2">
                                <label for="fname">Email address</label>
                                <input class="form-control"
                                       name="email"
                                       type="email"
                                       id="fname"
                                       placeholder="Enter email"
                                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}"
                                       required
                                       data-value-missing="Can't be empty"
                                       data-pattern-mismatch="Not a valid email"/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="my-2">
                                <label for="fname">Password</label>
                                <input class="form-control"
                                       name="password"
                                       type="password"
                                       id="fname"
                                       placeholder="Enter password"
                                       required
                                       data-value-missing="Can't be empty" />
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <!-- Use modal footer to seperate parts of form -->
                        <div class="modal-footer d-block">
                            <div class="my-2">
                                <label for="fname">Full name</label>
                                <input class="form-control"
                                       name="fullname"
                                       type="text"
                                       id="fname"
                                       placeholder="Enter full name"
                                       required
                                       data-value-missing="Can't be empty"/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="my-2">
                                <label for="fname">Avatar</label><br>
                                <input 
                                    name="image"
                                    type="text"
                                    id="fname"
                                    placeholder="Enter link url avatar"
                                    required
                                    data-value-missing="Can't be empty"/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="my-2">
                                <label for="fname">Contact</label>
                                <input
                                    class="form-control"
                                    name="mobile"
                                    id="fname"
                                    type="text"
                                    placeholder="Mobile number"
                                    required
                                    pattern="^\d{10}$"
                                    data-value-missing="Can't be empty"
                                    data-pattern-mismatch="Not a valid phonenumber"/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="my-2">
                                <label for="fname">Address</label>
                                <input
                                    class="form-control"
                                    name="address"
                                    id="fname"
                                    type="text"
                                    placeholder="Address"
                                    required                                      
                                    data-value-missing="Can't be empty"/>
                                <div class="invalid-feedback"></div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <div class="my-2">
                                        <label for="gender">Gender</label>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio" id="male" name="gender" class="custom-control-input" value="MALE" checked>
                                            <label class="custom-control-label" for="male">Male</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio" id="female" name="gender" class="custom-control-input" value="FEMALE">
                                            <label class="custom-control-label" for="female">Female</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="my-2">
                                        <label for="role">Register as</label>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio" id="student" name="role" class="custom-control-input" value="STUDENT" checked>
                                            <label class="custom-control-label" for="student">Student</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio" id="teacher" name="role" class="custom-control-input" value="TEACHER">
                                            <label class="custom-control-label" for="teacher">Teacher</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="my-2">
                                        <label for="status" >Status</label>
                                        <div class="custom-control custom-radio custom-control">                                       
                                            <input type="radio" id="active" name="status" class="custom-control-input" value="ACTIVE" checked>
                                            <label class="custom-control-label" for="active">Active</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio" id="inactive" name="status" class="custom-control-input"  value="INACTIVE">
                                            <label class="custom-control-label" for="inactive">Inactive</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Add User</button>
                        </div>
                    </div>  
                </div>
            </form>
        </div>
    </body>
   <!--  <script>
                function validateForm() {
                    var email = document.forms["myForm"]["email"].value;
                    var password = document.forms["myForm"]["password"].value;
                    var fullname = document.forms["myForm"]["fullname"].value;
                    var image = document.forms["myForm"]["image"].value;
                    var mobile = document.forms["myForm"]["mobile"].value;
                    var address = document.forms["myForm"]["address"].value;
                    if (email == "") {
                        alert("Email must be filled out");
                        return false;
                    }
                    if (password == "") {
                        alert("password must be filled out");
                        return false;
                    }
                    if (fullname == "") {
                        alert("fullname must be filled out");
                        return false;
                    }
                    if (image == "") {
                        alert("image must be filled out");
                        return false;
                    }
                    if (mobile == "") {
                        alert("mobile must be filled out");
                        return false;
                    }
                    if (address == "") {
                        alert("address must be filled out");
                        return false;
                    }
                }
            </script> -->
   <script>// JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict';
    // Form example - please follow the guide below when making form in this project
    /*
     <div>
     <label for="formName">Password</label>
     <input class="form-control"
     type="password"
     id="password"
     name="password"
     placeholder="Enter password"
     data-value-missing="" -> data value is required
     data-pattern-mismatch="" -> check pattern of input (pass in regex)
     data-too-long="" -> check length
     data-too-short="" -> check length
     pattern="" // If needed
     max=""     // If needed
     min=""     // If needed
     required
     />
     <div class="invalid-feedback"></div>
     </div>
     */
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation');
    var messageDisplayers = document.querySelectorAll('.invalid-feedback');
    // Loop over them and prevent submission
    forms.forEach(form => {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
                var ele = document.getElementsByTagName('input');
                for (var i = 0; i < ele.length; i++) {
                    var reason = ele[i].validity;
                    if (reason.valueMissing) {
                        messageDisplayers[i].innerHTML = ele[i].getAttribute("data-value-missing");
                    } else if (reason.patternMismatch) {
                        messageDisplayers[i].innerHTML = ele[i].getAttribute("data-pattern-mismatch");
                    } else if (reason.tooLong) {
                        messageDisplayers[i].innerHTML = ele[i].getAttribute("data-too-long");
                    } else if (reason.tooShort) {
                        messageDisplayers[i].innerHTML = ele[i].getAttribute("data-too-short");
                    }
                }
            }
            form.classList.add('was-validated');
        }, false);
    });
})();</script>
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
    <script type="text/javascript" src="${path}/utilities/form-validator.js"></script>
</html>
