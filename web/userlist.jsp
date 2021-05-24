<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : userlist
    Created on : May 21, 2021, 1:51:02 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>User List</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/userlist.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1 id="user-list-title">
            <a href="UserController">
                MANAGE USERS
            </a>
        </h1>

        <form action="UserController" method="POST">
            <div style="display: flex">
                <div>
                    <input type="text" name="searchtxt" placeholder="By name, email, mobile number" style="width: 500px; height: 2.4rem; border-radius: 5px; margin-right: 0.5rem; border: 1px solid black">

                </div>
                <div>
                    <button class="btn btn-dark" type="submit">Search</button>

                </div>
            </div>

        </form>
        <br>
        <div class="container1">
            <div class="bentrai col-md-10">
                <table class="table table-light table-striped" style="border: 1px solid black; border-collapse: collapse">
                    <thead>
                        <tr >
                            <th scope="col">
                                
                                    <span style="margin-right: 10px;">
                                        id
                                    </span>
                                   
                                        <a href="SortUser">
                                            <i class="fas fa-sort-up"></i>
                                        </a>
                                        <a href="SortUserDesc">
                                            <i class="fas fa-sort-down"></i>
                                        </a>
                                     
                                
                                
                            </th>
                            <th scope="col"> 
                                
                                   <span style=" margin-right: 10px;">
                                    Full  name
                                </span>
                                                                  
                                    <a href="SortUser">
                                        <i class="fas fa-sort-up"></i>
                                    </a>
                                    <a href="SortUserDesc">
                                        <i class="fas fa-sort-down"></i>
                                    </a>
                                
                                
                            </th>
                            <th scope="col">Gender</th>
                            <th scope="col"> Email</th>
                            <th scope="col"> Mobile</th>
                            <th scope="col">Role</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="o" items="${UserList}">
                            <tr>
                                <th>${o.id}</th>
                                <td>${o.name}</td>
                                <td>${o.gender?"Male":"Female"}</td>
                                <td>${o.email}</td>
                                <td>${o.mobile}</td>
                                <td>
                                    <c:if test="${o.roleId==0}">
                                        Admin
                                    </c:if>
                                    <c:if test="${o.roleId==1}">
                                        Teacher
                                    </c:if>
                                    <c:if test="${o.roleId==2}">
                                        Student
                                    </c:if>

                                </td>
                                <td>${o.status?"Active":"Inactive"}</td>
                                <td>
                                    <button> <a href="ViewDetailUser?uid=${o.id}">View</a> </button>
                                    <button>
                                        <a href="EditUser?uid=${o.id}">Edit</a> 
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div style="display: flex">
                    <div style="justify-content: flex-start">
                        <a class="btn btn-success" href="adduser.jsp" class="btn">Add</a> <br>  
                </div>
                
                    <div class="pagination" style="margin-left: 58rem">
                    <c:forEach var="i" begin="1" end="${endPage}">
                        <a href="UserController?page=${i}" class="${page==i?"active":""}">${i}</a>
                    </c:forEach>
                </div> 
                </div>
                
            </div>
            <div class="benphai col-md-1 mx-2">
                <section class="right" style="background: lightgray; border: 1px solid grey; height:268px; width: 10rem">
                    <form action="searchby" method="POST">
                        <span style="text-decoration: underline; color: red">Gender</span> <br>
                        <input type="checkbox" onclick="this.form.submit()" name="gender" value="1" ${dcm1} > Male <br>
                        <input type="checkbox" onclick="this.form.submit()" name = "gender" value="0" ${dcm0} > Female <br>
                        <span style="text-decoration: underline; color: red">Role</span> <br>
                        <input type="checkbox" onclick="this.form.submit()" name="role" value="2" ${clm2}> Student <br>
                        <input type="checkbox" onclick="this.form.submit()" name = "role" value="1" ${clm1}> Teacher<br>
                        <input type="checkbox" onclick="this.form.submit()" name = "role" value="0" ${clm0}> Admin<br>
                        <span style="text-decoration: underline; color: red">Status</span><br>
                        <input type="checkbox" onclick="this.form.submit()" name="status" value="1" ${vkl1} > Active <br>
                        <input type="checkbox" onclick="this.form.submit()" name = "status" value="0" ${vkl0}> Inactive<br>
                    </form>
                </section>
            </div>
        </div>
    </body>
</html>

