<%-- 
    Document   : userdetail
    Created on : May 23, 2021, 10:09:53 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/userdetail.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="dal" scope="page" class="database.DAO" />
    </head>
    <body>
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
                                <input type="radio" name="gender" value="1" ${user.gender?"checked":""} />Male
                                <input type="radio" name="gender" value="0" ${user.gender?"":"checked"} />Female
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
                                ${dal.getRoleById(user.roleId).getName()}
                            </td>
                            <td>
                                <button>
                                    Edit
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Status:
                            </td>
                            <td>
                               ${user.status?"Active":"Inactive"}
                            </td>
                            <td>
                                <button>
                                    Edit
                                </button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
