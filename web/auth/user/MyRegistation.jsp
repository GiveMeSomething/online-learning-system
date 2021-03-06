<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bootstrap Order Details Table with Search Filter</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-wrapper .btn {
                float: right;
                color: #333;
                background-color: #fff;
                border-radius: 3px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-wrapper .btn:hover {
                color: #333;
                background: #f2f2f2;
            }
            .table-wrapper .btn.btn-primary {
                color: #fff;
                background: #03A9F4;
            }
            .table-wrapper .btn.btn-primary:hover {
                background: #03a3e7;
            }
            .table-title .btn {		
                font-size: 13px;
                border: none;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            .table-title {
                color: #fff;
                background: #4b5366;		
                padding: 16px 25px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .show-entries select.form-control {        
                width: 60px;
                margin: 0 5px;
            }
            .table-filter .filter-group {
                float: right;
                margin-left: 15px;
            }
            .table-filter input, .table-filter select {
                height: 34px;
                border-radius: 3px;
                border-color: #ddd;
                box-shadow: none;
            }
            .table-filter {
                padding: 5px 0 15px;
                border-bottom: 1px solid #e9e9e9;
                margin-bottom: 5px;
            }
            .table-filter .btn {
                height: 34px;
            }
            .table-filter label {
                font-weight: normal;
                margin-left: 10px;
            }
            .table-filter select, .table-filter input {
                display: inline-block;
                margin-left: 5px;
            }
            .table-filter input {
                width: 200px;
                display: inline-block;
            }
            .filter-group select.form-control {
                width: auto;
            }
            .filter-icon {
                float: right;
                margin-top: 7px;
            }
            .filter-icon i {
                font-size: 18px;
                opacity: 0.7;
            }	
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 80px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }	
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.view {        
                width: 30px;
                height: 30px;
                color: #2196F3;
                border: 2px solid;
                border-radius: 30px;
                text-align: center;
            }
            table.table td a.view i {
                font-size: 22px;
                margin: 2px 0 0 1px;
            }   
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }	
            .pagination li.active a {
                background: #03A9F4;
            }
            .pagination li.active a:hover {        
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }    
            .img{
                width: 20px;
                height: 20px;
            }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script><jsp:useBean id="dao" scope="page" class="user_course.UserCourseService" />
    </head>
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-4">
                                <h2>Your <b>Registation</b></h2>
                            </div>
                            <!--                            <div class="col-sm-8">						
                                                            <a href="#" class="btn btn-primary"><i class="material-icons">&#xE863;</i> <span>Refresh List</span></a>
                                                            <a href="#" class="btn btn-secondary"><i class="material-icons">&#xE24D;</i> <span>Export to Excel</span></a>
                                                        </div>-->
                        </div>
                    </div>
                    <div class="table-filter">
                        <div class="row">
                            <div class="col-sm-3">
                                <!--                                <div class="show-entries">
                                                                    <span>Show</span>
                                                                    <select class="form-control">
                                                                        <option>5</option>
                                                                        <option>10</option>
                                                                        <option>15</option>
                                                                        <option>20</option>
                                                                    </select>
                                                                    <span>entries</span>
                                                                </div>-->
                            </div>
                            <div class="col-sm-9">
                                <button type="button" class="btn btn-primary"><i class="fa fa-search"></i></button>
                                <div class="filter-group">
                                    <label>Subject</label>
                                    <input type="text" class="form-control" name="title" oninput="searchCourseByTitle(this.value)">
                                </div>
                                <div class="filter-group">
                                    <label>Category</label>
                                    <select class="form-control" name="category" onchange="searchCourse(this.value)">
                                        <option value="0">All</option>
                                        <c:forEach var="o" items="${dao.allCategory}">
                                            <option value="${o.id}">${o.getCategoryName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <!--                                <div class="filter-group">
                                                                    <label>Status</label>
                                                                    <select class="form-control">
                                                                        <option>Any</option>
                                                                        <option>Delivered</option>
                                                                        <option>Shipped</option>
                                                                        <option>Pending</option>
                                                                        <option>Cancelled</option>
                                                                    </select>
                                                                </div>-->
                                <span class="filter-icon"><i class="fa fa-filter"></i></span>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Subject</th>
                                <th>Registation Time</th>
                                <th>Total Cost</th>						
                                <th>Valid From</th>
                                <th>Valid To</th>
                                <th>Status </th>   
                                <th>Package</th>
                            </tr>
                        </thead>
                        <tbody id="txtHint">
                            <!--int id, String title, Date registationTime, String packages, double totalCost, int status, Date validFrom, Date validTo-->
                            <c:forEach var="o" items="${listC}">
                                <tr>
                                    <td>${o.id}</td>
                                    <td>
                                        <a href="${path}/auth/user/UserCourse?operation=VIEWDETAIL&courseId=${o.id}&userId=${sessionScope.user.id}&type=1">
                                            ${o.title}
                                        </a>
                                    </td>
                                    <td>${o.registationTime}</td>  
                                    <td>${o.totalCost}</td>
                                    <td>${o.validFrom}</td>
                                    <td>${o.validTo}</td>
                                    <c:if test="${o.status == 2}">
                                        <td><span class="status text-success">&bull;</span> Completed</td>
                                    </c:if>
                                    <c:if test="${o.status == 0}">
                                        <td><span class="status text-danger">&bull;</span> Cancelled</td>
                                    </c:if>
                                    <c:if test="${o.status == 1}">
                                        <td><span class="status text-warning">&bull;</span> 
                                            <a href="${path}/auth/user/UserCourse?operation=UpdateStatus&courseId=${o.id}" class="confirmation">Submited</a>
                                        </td>
                                    </c:if>
                                    <td>${o.packages}</td>                        
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <ul class="pagination" id="paging">
                            <c:forEach var="i" begin="1" end="${endPage}">
                                <li class="page-item ${i==index?"active":""}"><a href="UserCourse?operation=&page=${i}" class="page-link">${i}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>        
        </div>     
        <script type="text/javascript">
            var elems = document.getElementsByClassName('confirmation');
            var confirmIt = function (e) {
                if (!confirm('Are you sure you want to cancel this Course?'))
                    e.preventDefault();
            };
            for (var i = 0, l = elems.length; i < l; i++) {
                elems[i].addEventListener('click', confirmIt, false);
            }

            function searchCourse(str) {
                if (str == "") {
                    document.getElementById("txtHint").innerHTML = "";
                    return;
                }
                const xhttp = new XMLHttpRequest();
                xhttp.onload = function () {
                    document.getElementById("txtHint").innerHTML = this.responseText;
                    document.getElementById("paging").innerHTML = "";
                }
                xhttp.open("GET", "UserCourse?operation=SearchByCategory&category=" + str);
                xhttp.send();
            }
            function searchCourseByTitle(str) {
                const xhttp = new XMLHttpRequest();
                xhttp.onload = function () {
                    document.getElementById("txtHint").innerHTML = this.responseText;
                    document.getElementById("paging").innerHTML = "";
                }
                xhttp.open("GET", "UserCourse?operation=SearchByTitle&title=" + str);
                xhttp.send();
            }
        </script>
    </body>
</html>