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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <jsp:useBean id="dal" scope="page" class="user.UserRepository" />
        <jsp:useBean id="dall" scope="page" class="auth.AuthRepository" />
        <style>
            .activepage{
                background-color: red;
            }
            .nutadd{
                float: right;
            }
            .nutadd a{
                text-decoration: none;
                background-color: green;
                padding: 15px 20px;
                color: white;
                border-radius: 10px;
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
                background-color: orange;
                color:white;
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
            <div id="sider" class="col-md-2  mx-4" style="background:#FFFFF0">
                <li style="color: black ; font-size: 20px; list-style: none ; padding-left: 0.5rem; padding-top: 8rem" > <strong> Data Administration </strong><br>
                    <ul >
                        <li>  Manage User ></li>
                        <li>
                            <a href="${path}/auth/admin/setting.jsp ">
                                Mangage Setting
                            </a>
                        </li>
                    </ul>
                </li>
            </div>
            <div id="content-p" class="col-md-9">
                <div style="background:#FFFFF0; align-item: center; display:flex; justify-content:center;  " class="py-2" id="screen-header">
                    <a class="navbar-brand" style="font-size: 2.5rem;" href="${path}/home">
                        <span style="color:blue">O</span>
                        <span style="color:orange">L</span>
                        <span style="color:green">S</span>
                    </a>
                </div>
                <h1 id="user-list-title">
                    <a href="home" style="padding-bottom: 15px;">
                        MANAGE USERS
                    </a>
                </h1>
                <form action="${path}/auth/admin/search" method="GET">
                    <div style="display: flex;padding-top: 20px;">
                        <div>
                            <input type="text" name="searchtxt" placeholder="By name, email, mobile number" style="width: 500px; height: 2.4rem; border-radius: 5px; margin-right: 0.5rem; border: 1px solid black">
                        </div>
                        <div>
                            <button class="btn btn-dark" type="submit">Search</button>
                        </div>
                    </div>
                </form>
                <form action="${path}/auth/admin/filter" method="GET">
                    <div style=" width: 100%;
                         height: auto;
                         float: left;
                         margin-bottom: 10px;">
                        <span style="float: left; margin-left: 10px;">Gender</span>
                        <span style="float: left; margin-left: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name="gender" value="1" ${a1} > Male <br>
                        </span>
                        <span style="float: left; margin-left: 10px; border-right: 2px solid black;
                              padding-right: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name = "gender" value="0" ${a0} > Female <br>
                        </span>
                        <span style="float: left; margin-left: 10px;">Role</span>
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
                        <span style="padding-left: 10px; float: left; padding-right: 10px;">Status</span>
                        <span style="float: left;padding-right: 10px;">
                            <input type="checkbox" onclick="this.form.submit()" name="status" value="1" ${c1} > Active <br>
                        </span>
                        <span>
                            <input type="checkbox" onclick="this.form.submit()" name = "status" value="0" ${c0}> Inactive<br>
                        </span>
                    </div>
                    <div class="container1">
                        <div class="bentrai col-md-10">
                            <table id="myTable" class="table table-light table-striped" style="border: 1px solid black; border-collapse: collapse">
                                <thead>
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
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${UserList}">
                                        <tr>
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
                                                <button> <a href="viewuser?uid=${o.id}">View</a> </button>
                                                <button>
                                                    <a href="edituser?uid=${o.id}">Edit</a>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="nutadd">
                                <a href="auth/admin/adduser.jsp">
                                    Add
                                </a>
                            </div>
                            <div style="display: flex">
                                <div class="pagination" style="display: inline-block;">
                                    <c:forEach var="i" begin="1" end="${endPage}">
                                        <button value="${i}" class="${page==i?"activepage":""}" name="page" onclick="this.form.submit()">
                                            ${i}
                                        </button>
                                    </c:forEach>
                                </div>
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
</html>
