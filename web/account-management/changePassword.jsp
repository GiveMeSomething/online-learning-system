<%-- 
    Document   : changePassword
    Created on : May 29, 2021, 9:49:41 PM
    Author     : AS
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Learning System</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../style/global.css" />
</head>

<body>
    <section class="container" id="main">
        <div class="d-flex flex-column align-items-center justify-content-center" style="height: 100vh">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#change-password-modal">
                Change Password
            </button>
            <div class="modal fade" id="change-password-modal" tabindex="-1" role="dialog">
                <form action="" method="POST" class="needs-validation" novalidate>
                    <div class="request-info">
                        <input name="previousPage" value="index.jsp" hidden="true" />
                        <div class="invalid-feedback"></div>
                    </div>
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Login</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div>
                                    <label for="current-password">Current Password</label>
                                    <input class="form-control" name="current-password" type="password"
                                        id="current-password" placeholder="Enter Current Password"
                                        data-value-missing="Can't be empty" required />
                                    <div class="invalid-feedback"></div>
                                </div>
                                <div>
                                    <label for="new-password">New Password</label>
                                    <input class="form-control" name="new-password" type="password" id="new-password"
                                        placeholder="Enter New password" data-value-missing="Can't be empty" required />
                                    <div class="invalid-feedback"></div>
                                </div>
                                <div>
                                    <label for="confirm-password">Confirm New Password</label>
                                    <input class="form-control" name="confirm-password" type="password"
                                        id="confirm-password" placeholder="Confirm New password"
                                        data-value-missing="Can't be empty" required />
                                    <div class="invalid-feedback"></div>
                                </div>
                                <div>
                                    <!-- Trigger when wrong current password (use Javascript) -->
                                    <p class="access-denied d-none">Wrong current password</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Change Password</button>
                                </div>
                            </div>
                        </div>
                </form>
            </div>
        </div>
    </section>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
    </script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous">
    </script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>
<!-- Import if have form input -->
<!--<script>
    // JavaScript for disabling form submissions if there are invalid fields
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
})();
</script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/utilities/form-validator.js"></script>

</html>
