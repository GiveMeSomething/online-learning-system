<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
    Document   : userlist
    Created on : May 21, 2021, 1:51:02 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>User List</title>
        <meta charset="UTF-8">
        <link href="css/newcss.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="${path}/style/setting.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <jsp:useBean id="dal" scope="page" class="user.UserRepository" />
        <jsp:useBean id="dall" scope="page" class="auth.AuthRepository" />
        <style>
            .btn-outline-success:hover{
                background-color: #5cb85c !important;
                color: white !important;
            }
            .activepage{
                background-color: #5cb85c;
                color: white !important;
            }
            #myTable tr th{
                cursor: pointer;
            }
            .pagination button{
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                font-size: 22px;
            }
            .d-flex ul {
                list-style: none;
                text-align: center;
                padding-right: 2rem;

            }
            .d-flex ul li {
                padding: 15px;
                color:black;
                font-size: 18px;

                border-bottom: orange 2px solid;
            }


            .d-flex ul li:hover {
                background-color: #5cb85c;
                color: white !important;
            }
            .py-2 a{
                align-items: center;
                width: 2rem;
                height: 3rem;
            }
        </style>
    </head>
    <body>

        <div id="container" class="d-flex">
            <div id="sider" class="col-md-2  mx-4" style="background:none">
                <div id="mySidebar" class="sidebar">
                    <button id="closeNav" class="openbtn" onclick="closeNav()" style="display: none; margin-top: -4rem; margin-bottom: 2rem; margin-left: 13rem"><span style="text-transform: uppercase">X</span></button>
                    <!--<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>-->
                    <a href="${path}/auth/admin" style="background: white; color: black">User List</a>
                    <hr>
                    <a href="${path}/auth/admin/dashboard">Dashboard</a>
                    <hr>
                    <a href="${path}/auth/admin/admin_blog?operation=VIEWALLPOST">Post List</a>
                    <hr>
                    <a href="${path}/auth/teacher/subject">Subject List</a>
                    <hr>
                    <a href="${path}/auth/admin/slider">Slider List</a>
                    <hr>
                    <a href="${path}/auth/teacher/registration?operation=VIEWALL">Registration List</a>
                </div>
            </div>

            <div id="content-p" class="col-md-9">
                <div style="background:#FFFFF0; align-item: center; display:flex;  " class="py-2" id="screen-header">
                    <div>
                        <button id="openNav" class="openbtn" onclick="openNav()" style="background: #FFFFF0; color: black">&#9776;</button>
                    </div>
                    <div style="justify-content: center; margin-left: 26.5rem">
                        <a class="navbar-brand" style="font-size: 2.5rem;" href="${path}/home">
                            <span style="color:blue">O</span>
                            <span style="color:orange">L</span>
                            <span style="color:green">S</span>
                        </a>
                    </div>

                    <div style="margin-left: 19.5rem; margin-top: .5rem">
                        <a href="${path}/authenticate?operation=LOGOUT" style="padding-bottom: 5px; padding-top: 5px; border-bottom: 1px solid lightgray">
                            <button class="btn btn-secondary">Log out</button>
                        </a>
                    </div>
                </div>
                <div class="d-flex">
                    <h1 id="user-list-title">
                        <a href="${path}/home" style="padding-bottom: 15px;text-decoration: none; color: black">
                            MANAGE USERS
                        </a>
                    </h1>
                </div>
                <form action="${path}/auth/admin/search" method="GET">
                    <div class="d-flex align-items-center w-100">
                        <div class="row">
                            <input type="text"
                                   name="searchtxt"
                                   placeholder="By name, email, mobile number"
                                   class="form-control mx-2" style="width: 70vh">
                        </div>
                        <div>
                            <button class="btn btn-primary mx-2" type="submit">Search</button>
                        </div>
                    </div>
                </form>
                <form action="${path}/auth/admin/filter" method="GET">
                    <div class="my-3">
                        <span style="float: left; margin-left: 10px;"><b>Gender:</b></span>
                        <span style="float: left; margin-left: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name="gender" value="1" ${a1} > Male <br>
                        </span>
                        <span style="float: left; margin-left: 10px; border-right: 2px solid black;
                              padding-right: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name = "gender" value="0" ${a0} > Female <br>
                        </span>
                        <span style="float: left; margin-left: 10px;"><b>Role:</b></span>
                        <span style="float: left; margin-left: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name="role" value="2" ${b2}> Student <br>
                        </span>
                        <span style="float: left; margin-left: 10px;
                              ">
                            <input type="checkbox" onclick="this.form.submit()" name="role" value="1" ${b1}> Teacher <br>
                        </span>
                        <span style="float: left; margin-left: 10px; border-right: 2px solid black;
                              padding-right: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name="role" value="0" ${b0}> Admin <br>
                        </span>
                        <span style="padding-left: 10px; float: left; padding-right: 10px;"><b>Status:</b></span>
                        <span style="float: left;padding-right: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name="status" value="1" ${c1} > Active <br>
                        </span>
                        <span>
                            <input type="checkbox" onclick="this.form.submit()" name = "status" value="0" ${c0}> Inactive<br>
                        </span>
                    </div>
                    <div class="container1">
                        <div class="bentrai col-md-10">
                            <table id="myTable" class="table my-5">
                                <thead class="text-center">
                                    <tr>
                                        <th onclick="sortTable(0)">
                                            ID
                                        </th>
                                        <th onclick="sortTable(1)" scope="col" style="  cursor: pointer;">
                                            FullName
                                        </th>
                                        <th onclick="sortTable(2)" scope="col">Gender</th>
                                        <th onclick="sortTable(3)" scope="col"> Email</th>
                                        <th onclick="sortTable(4)" scope="col"> Mobile</th>
                                        <th onclick="sortTable(5)" scope="col">Role</th>
                                        <th onclick="sortTable(6)" scope="col">Status</th>
                                        <th scope="col" colspan="2">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${UserList}">
                                        <tr class="align-middle text-center">
                                            <td id="getit">${o.id}</td>
                                            <td>${o.name}</td>
                                            <td>${o.gender}</td>
                                            <td>${o.email}</td>
                                            <td>${o.mobile}</td>
                                            <td>
                                                <c:if test="${dall.getRoleIdOK(o.email)==0}">
                                                    Admin
                                                </c:if>
                                                <c:if test="${dall.getRoleIdOK(o.email)==1}">
                                                    Teacher
                                                </c:if>
                                                <c:if test="${dall.getRoleIdOK(o.email)==2}">
                                                    Student
                                                </c:if>
                                            </td>
                                            <td>${o.status}</td>
                                            <td>
                                                <a class="btn btn-primary" href="${path}/auth/admin/viewuser?uid=${o.id}">
                                                    View
                                                </a>
                                            </td>
                                            <td>
                                                <a class="btn btn-success" href="${path}/auth/admin/edituser?uid=${o.id}">
                                                    Edit
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="pagination" style="display: inline-block;">
                                    <div>
                                        <c:forEach var="i" begin="1" end="${endPage}">
                                            <button
                                                value="${i}"
                                                class="${page==i?"activepage":""} btn btn-outline-success"
                                                name="page"
                                                onclick="this.form.submit()">
                                                ${i}
                                            </button>
                                        </c:forEach>
                                    </div>
                                </div>
                                <a href="${path}/auth/admin/adduser.jsp" class="btn btn-success px-3 py-2">
                                    Add new user
                                </a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
    </body>
    <script>
        function sortTable(n) {
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
            table = document.getElementById("myTable");
            switching = true;
            //Set the sorting direction to ascending:
            dir = "asc";
            /*Make a loop that will continue until
             no switching has been done:*/
            while (switching) {
                //start by saying: no switching is done:
                switching = false;
                rows = table.rows;
                /*Loop through all table rows (except the
                 first, which contains table headers):*/
                for (i = 1; i < (rows.length - 1); i++) {
                    //start by saying there should be no switching:
                    shouldSwitch = false;
                    /*Get the two elements you want to compare,
                     one from current row and one from the next:*/
                    x = rows[i].getElementsByTagName("TD")[n];
                    y = rows[i + 1].getElementsByTagName("TD")[n];
                    /*check if the two rows should switch place,
                     based on the direction, asc or desc:*/
                    if (dir == "asc") {
                        if (n == 0) {

                            if (Number(x.innerHTML) > Number(y.innerHTML)) {
                                shouldSwitch = true;
                                break;
                            }
                        } else {
                            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                //if so, mark as a switch and break the loop:
                                shouldSwitch = true;
                                break;
                            }
                        }
                    } else if (dir == "desc") {
                        if (n == 0) {
                            if (Number(x.innerHTML) < Number(y.innerHTML)) {
                                shouldSwitch = true;
                                break;
                            }
                        } else {
                            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                //if so, mark as a switch and break the loop:
                                shouldSwitch = true;
                                break;
                            }
                        }
                    }
                }
                if (shouldSwitch) {
                    /*If a switch has been marked, make the switch
                     and mark that a switch has been done:*/
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                    //Each time a switch is done, increase this count by 1:
                    switchcount++;
                } else {
                    /*If no switching has been done AND the direction is "asc",
                     set the direction to "desc" and run the while loop again.*/
                    if (switchcount == 0 && dir == "asc") {
                        dir = "desc";
                        switching = true;
                    }
                }
            }
        }
    </script>
    <script>
        function openNav() {
            document.getElementById("openNav").style.color = "#FFFFF0";
            document.getElementById("closeNav").style.display = "block";
            document.getElementById("mySidebar").style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
        }

        function closeNav() {
            document.getElementById("openNav").style.color = "black";
            document.getElementById("closeNav").style.display = "none";
            document.getElementById("mySidebar").style.width = "0";
            document.getElementById("main").style.marginLeft = "0";
        }
        function myAccFunc() {
            var x = document.getElementById("demoAcc");
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
                x.previousElementSibling.className += " w3-black";
            } else {
                x.className = x.className.replace(" w3-show", "");
                x.previousElementSibling.className =
                        x.previousElementSibling.className.replace(" w3-black", "");
            }
        }

        function myDropFunc() {
            var x = document.getElementById("demoDrop");
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
                x.previousElementSibling.className += " w3-black";
            } else {
                x.className = x.className.replace(" w3-show", "");
                x.previousElementSibling.className =
                        x.previousElementSibling.className.replace(" w3-black", "");
            }
        }
    </script>
</html>

