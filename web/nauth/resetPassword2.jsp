<%-- 
    Document   : resetPassword2
    Created on : Jun 6, 2021, 11:57:46 PM
    Author     : AS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <title>Reset Password</title>
    </head>
    <body>
        <jsp:include page="../components/global/navbar.jsp"/>
        <form action="authenticate"
              method="POST"
              class="needs-validation shadow-lg w-25 bg-light p-3 d-block"
              style="margin: 120px auto 158px auto;"
              novalidate>
            <div class="request-info">
                <input name="previousPage" value="nauth/resetPassword2.jsp" hidden="true" />
                <div class="invalid-feedback"></div>
                <input name="operation" value="RESETPW2" hidden="true" />
                <div class="invalid-feedback"></div>
            </div>
            <input type="text" hidden name="email" value="${email}">
            <div class="p-3">
                <label for="new-password">New Password</label>
                <input class="form-control" name="new-password" type="password" id="new-password"
                       placeholder="Enter New password" data-value-missing="Can't be empty" required />
                <div class="invalid-feedback"></div>
            </div>
            <div class="pb-3 px-3">
                <label for="confirm-password">Confirm New Password</label>
                <input class="form-control" name="confirm-password" type="password"
                       id="confirm-password" placeholder="Confirm New password"
                       data-value-missing="Can't be empty" required />
                <div class="invalid-feedback"></div>
            </div>
            <button type="submit" class="btn btn-primary mx-3 my-3">Change Password</button>
        </form>
        <jsp:include page="../components/global/footer.jsp"/>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous">
    </script>
</html>
