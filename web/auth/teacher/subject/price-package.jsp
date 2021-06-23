<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : SubjectDetail
    Created on : Jun 16, 2021, 6:30:40 PM
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">-->
        <!--<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>-->
        <title>Subject Detail</title>
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
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                min-width: 1000px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {        
                padding-bottom: 15px;
                background: #435d7d;
                color: #fff;
                padding: 16px 30px;
                min-width: 100%;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn-group {
                float: right;
            }
            .table-title .btn {
                color: #fff;
                float: right;
                font-size: 13px;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
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
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
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
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
                outline: none !important;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
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
            .pagination li.active a, .pagination li.active a.page-link {
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
            /* Custom checkbox */
            .custom-checkbox {
                position: relative;
            }
            .custom-checkbox input[type="checkbox"] {    
                opacity: 0;
                position: absolute;
                margin: 5px 0 0 3px;
                z-index: 9;
            }
            .custom-checkbox label:before{
                width: 18px;
                height: 18px;
            }
            .custom-checkbox label:before {
                content: '';
                margin-right: 10px;
                display: inline-block;
                vertical-align: text-top;
                background: white;
                border: 1px solid #bbb;
                border-radius: 2px;
                box-sizing: border-box;
                z-index: 2;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                content: '';
                position: absolute;
                left: 6px;
                top: 3px;
                width: 6px;
                height: 11px;
                border: solid #000;
                border-width: 0 3px 3px 0;
                transform: inherit;
                z-index: 3;
                transform: rotateZ(45deg);
            }
            .custom-checkbox input[type="checkbox"]:checked + label:before {
                border-color: #03A9F4;
                background: #03A9F4;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                border-color: #fff;
            }
            .custom-checkbox input[type="checkbox"]:disabled + label:before {
                color: #b8b8b8;
                cursor: auto;
                box-shadow: none;
                background: #ddd;
            }
            /* Modal styles */
            .modal .modal-dialog {
                max-width: 400px;
            }
            .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                padding: 20px 30px;
            }
            .modal .modal-content {
                border-radius: 3px;
                font-size: 14px;
            }
            .modal .modal-footer {
                background: #ecf0f1;
                border-radius: 0 0 3px 3px;
            }
            .modal .modal-title {
                display: inline-block;
            }
            .modal .form-control {
                border-radius: 2px;
                box-shadow: none;
                border-color: #dddddd;
            }
            .modal textarea.form-control {
                resize: vertical;
            }
            .modal .btn {
                border-radius: 2px;
                min-width: 100px;
            }	
            .modal form label {
                font-weight: normal;
            }	
        </style>
    </head>
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Manage <b>Package Price</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Package Price</span></a>
                                <!--<a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>-->						
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Package</th>
                                <th>Descriptions</th>
                                <th>Duration</th>
                                <th>Price</th>
                                <th>Sale Price</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--int id, int duration, String name, double price, Status status, String descriptions, double discount-->
                            <c:forEach items="${listP}" var="o">
                                <tr>
                                    <td>
                                        ${o.id}
                                    </td>
                                    <td>${o.name}</td>
                                    <td>${o.descriptions}</td>
                                    <td>${o.duration}</td>
                                    <td>${o.price}</td>
                                    <td>${o.getSalePrice()}</td>
                                    <td>${o.getStatus()}</td>
                                    <td>
                                        <a href="#editPackageModal" onclick="loadData(${o.id}, '${o.name}', ${o.duration}, ${o.price}, ${o.discount},
                                           ${o.status eq 'ACTIVE'?1:0}, '${o.descriptions}')" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="#deletePackageModal" onclick="getId(${o.id})" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <script>
                        function loadData(id, name, duration, price, discount, status, descriptions) {
                            const xhttp = new XMLHttpRequest();
                            xhttp.onload = function () {
                                document.getElementById("editId").value = id;
                                document.getElementById("editName").value = name;
                                document.getElementById("duration").value = duration;
                                document.getElementById("price").value = price;
                                document.getElementById("discount").value = discount;
                                document.getElementById("status").value = status;
                                document.getElementById("descriptions").value = descriptions;
                            }
                            xhttp.open("GET", "asdasd");
                            xhttp.send();
                        }
                    </script>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${totalPackageOfPage}</b> out of <b>${totalPackage}</b> entries</div>
                        <ul class="pagination">
                            <c:if test="${index>1}">
                                <li class="page-item disabled">
                                    <a href="${path}/auth/teacher/subject?operation=LISTPACKAGE&page=${index-1}">
                                        Previous
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach var="i" begin="1" end="${endPage}">
                                <li class="page-item ${index==i?"active":""}">
                                    <a href="${path}/auth/teacher/subject?operation=LISTPACKAGE&page=${i}" class="page-link">
                                        ${i}
                                    </a>
                                </li>
                            </c:forEach>
                            <c:if test="${index<endPage}">
                                <li class="page-item">
                                    <a href="${path}/auth/teacher/subject?operation=LISTPACKAGE&page=${index+1}" class="page-link">
                                        Next
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>        
        </div>
        <!-- Add Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="${path}/auth/teacher/subject?operation=addPackage" method="POST">
                    <!--<form action="/auth/teacher/subject?operation=addPackage" method="GET">-->
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Package</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" name="namepackage" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Duration</label>
                                <input type="number" name="duration" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input type="number" name="price" step="0.01" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Discount (%)</label>
                                <input type="number" step="0.01" name="discount" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <select name="status" class="form-control">
                                    <option value="1">Active</option>
                                    <option value="0">Inactive</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea  name="descriptions" class="form-control" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="editPackageModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="${path}/auth/teacher/subject?operation=editPackage" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Package</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>#</label>
                                <input type="number" name="eId" id="editId" class="form-control" required readonly="">
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" name="editName" id="editName" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Duration</label>
                                <input type="number" name="duration" id="duration" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input type="number" name="price" id="price" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Discount (%)</label>
                                <input type="number" step="0.01" name="discount" id="discount" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <select name="status" id="status" class="form-control">
                                    <option value="1">Active</option>
                                    <option value="0">Inactive</option>
                                </select>
                            </div>	
                            <div class="form-group">
                                <label>Description</label>
                                <textarea class="form-control" name="descriptions" id="descriptions" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Save">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Delete Modal HTML -->
        <div id="deletePackageModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="${path}/auth/teacher/subject?operation=deletePackage" method="POST">
                        <input hidden="" type="text" id="txtId" name="id" value="" />
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete Package</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <p>Are you sure you want to delete these Records?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            function getId(str) {
                if (str == "") {
                    document.getElementById("txtId").value = "";
                    return;
                }
                const xhttp = new XMLHttpRequest();
                xhttp.onload = function () {
                    document.getElementById("txtId").value = str;
                }
                xhttp.open("GET", "");
                xhttp.send();
            }
        </script>
    </body>
</html>
