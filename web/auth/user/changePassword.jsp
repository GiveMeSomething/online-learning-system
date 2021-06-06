<%--
    Document   : changePassword
    Created on : May 29, 2021, 9:49:41 PM
    Author     : AS
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Learning System</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${path}/style/global.css" />
    </head>
    <body>
        <section class="container" id="main">
            <div class="d-flex flex-column align-items-center justify-content-center" style="height: 100vh">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#change-password-modal">
                    Change Password
                </button>
                <div class="modal fade" id="change-password-modal" tabindex="-1" role="dialog">
                    <form action="${path}/authenticate"
                          method="POST"
                          class="needs-validation"
                          novalidate>
                        <div class="request-info">
                            <input name="previousPage" value="index.jsp" hidden="true" />
                            <div class="invalid-feedback"></div>
                            <input name="operation" value="CHANGEPW" hidden="true" />
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
