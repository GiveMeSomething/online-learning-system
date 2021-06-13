<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
    Document   : userdetail
    Created on : May 23, 2021, 10:09:53 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/userdetail.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="dal" scope="page" class="user.UserRepository" />
        <style>
            .container{
                width: 100%;
                height: auto;
                display: flex;
            }
            .left{
                margin-top: 50px;
                text-align: center;
                width: 50%;
                height: auto;
            }
            .right{
                margin-top: 50px;
                width: 50%;
                height: auto;
            }
            .image img{
                border-radius: 50%;
                width: 200px;;
                height: 200px;
            }
            .username{
                font-weight: bold;
                font-family: arial;
                font-size: 30px;
            }
            .title{
                text-align: left;
                font-weight: bold;
                font-family: arial;
                font-size: 30px;
            }
            .detailinfor{
                margin-top: 30px;
                font-size: 20px;
            }
            .detailinfor table,tr,td {
                font-weight: bold;
                height: 50px;
                width: auto;
                border-bottom: 1px solid black;
                border-collapse: collapse;
            }
            .detailinfor table tr td:first-child {
                width: 250px;
            }
            .button {
                background-color: #4CAF50;
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
            .lastbutton{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <form action="${path}/auth/admin/updaterole" method="GET">
            <div class="container">
                <div class="left">
                    <div class="image">
                        <img src="${user.image}"/>
                    </div>
                    <div class="username">
                        ${user.name}
                    </div>
                </div>
                <div class="right">
                    <div class="title">
                        Basic information
                    </div>
                    <div class="detailinfor">
                        <table>
                            <tr>
                                <td>
                                    Name:
                                </td>
                                <td>
                                    ${user.name}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Gender:
                                </td>
                                <td>
                                    ${user.gender}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Mobile:
                                </td>
                                <td>
                                    ${user.mobile}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Email:
                                </td>
                                <td>
                                    ${user.email}
                                    <input type="hidden" name="mailne" value="${user.email}" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Address:
                                </td>
                                <td>
                                    ${user.address}

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Role:
                                </td>
                                <td>
                                    <div class="custom-control custom-radio custom-control">
                                        <input type="radio"
                                               id="student"
                                               name="role"
                                               class="custom-control-input"
                                               value="STUDENT"
                                               <c:if test="${account.role.toString() eq 'STUDENT'}">checked</c:if>>
                                               <label class="custom-control-label" for="student">Student</label>
                                               <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio"
                                                   id="student" name="role"
                                                   class="custom-control-input"
                                                   value="TEACHER" <c:if test="${account.role.toString() eq 'TEACHER'}">checked</c:if>>
                                            <label class="custom-control-label" for="student">Teacher</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio"
                                                   id="student"
                                                   name="role" class="custom-control-input"
                                                   value="ADMIN" <c:if test="${account.role.toString() eq 'ADMIN'}">checked</c:if>>
                                            <label class="custom-control-label" for="student">Admin</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                    </td>
                                    <td>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Status:
                                    </td>

                                    <td>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio"
                                                   id="male"
                                                   name="status"
                                                   class="custom-control-input"
                                                   value="ACTIVE" <c:if test="${user.status.toString() eq  'ACTIVE'}">checked</c:if>>
                                            <label class="custom-control-label" for="male">ACTIVE</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                        <div class="custom-control custom-radio custom-control">
                                            <input type="radio"
                                                   id="female"
                                                   name="status"
                                                   class="custom-control-input"
                                                   value="INACTIVE" <c:if test="${user.status.toString() eq 'INACTIVE'}">checked</c:if>>
                                            <label class="custom-control-label" for="female">INACTIVE</label>
                                            <div class="invalid-feedback"></div>
                                        </div>
                                    </td>

                                    <td>

                                    </td>
                                </tr>
                            </table>
                        </div>
                        <input type="hidden" name="uid" value="${user.id}" />
                    <div class="lastbutton">
                        <button class="button" type="submit">Save</button>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
