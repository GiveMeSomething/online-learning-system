<%--
    Document   : QuestionDetail
    Created on : Jun 16, 2021
    Author     : Nguyen Khanh Toan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
              integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
              crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${path}/style/subject.css">
    </head>
    <body>
        <div class="container-fluid" style="width:85%;margin:0 auto;font-size: 19px">
            <h3 class="text-center">Questions</h3>
            <form action="${path}/auth/teacher/question" method="post" style="width: 85%;margin:0 auto;font-size: 19px">
                <div class="request-info">
                    <input name="previousPage" value="home" hidden="true" />
                    <div class="invalid-feedback"></div>
                    <input name="operation" value="UPDATEQUESTION" hidden="true" />
                    <div class="invalid-feedback"></div>
                </div>
                <div class="form-group">
                    <label for="questionId">
                        Question id
                    </label>
                    <input name="questionId"
                           type="text"
                           class="form-control"
                           value="${questionDetail.id}"
                           disabled
                           id="questionId"
                           data-value-missing="Can't be empty"
                           required>
                </div>
                <div class="form-group">
                    <label for="subject">
                        Subject
                    </label>
                    <select name="subject" class="form-control">
                        <c:forEach items="${courseList}" var="o">
                            <option ${o.courseName == questionDetail.course ? "selected":""} 
                                value="${o.id}">
                                ${o.courseName}
                            </option>
                        </c:forEach>
                    </select>

                </div>
                <div class="form-group">
                    <label for="dimension">
                        Dimension
                    </label>
                    <select name="dimension" class="form-control">
                        <c:forEach items="${dimensionList}" var="o">
                            <option ${o.name == questionDetail.dimension_name ? "selected":""} 
                                value="${o.id}">
                                ${o.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="lesson">
                        Lesson
                    </label>
                    <select name="lesson" class="form-control">
                        <c:forEach items="${lessonList}" var="o">
                            <option ${o.name == questionDetail.lesson_name ? "selected":""} 
                                value="${o.id}">
                                ${o.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">
                        Status
                    </label>
                    <select name="status" class="form-control">
                        <option ${questionDetail.status == "ACTIVE" ? "selected":""} value="1">Active</option>
                        <option ${questionDetail.status == "INACTIVE" ? "selected":""} value="0">Inactive</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="content">
                        Content
                    </label>
                    <input name="content"
                           type="text"
                           class="form-control"
                           value="${questionDetail.content}"
                           id="content"
                           data-value-missing="Can't be empty"
                           required>
                </div>
                <div class="form-group">
                    Answer Options<br/>
                    <div class="text-right mb-2">
                        <button type="button" class="btn btn-success mb-2"
                                data-toggle="modal" data-target="#AddAnswer">
                            Add Answer
                        </button>

                    </div>
                    <table class="table table-bordered">
                        <thead>
                            <tr style='black'>
                                <th style='border-bottom: 0px;width:5%' scope="col">#</th>
                                <th style='border-bottom: 0px;width:70%' scope="col">Answer options</th>
                                <th style='border-bottom: 0px' scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach begin="1" end="${totalAnswerOptions}" var="i">
                                <c:set var="property" value="option${i}" />
                                <c:if test="${questionDetail[property] == null}"></c:if>
                                <c:if test="${questionDetail[property] != null && questionDetail[property] != ''}">
                                    <tr>
                                        <th scope="row">${i}</th>
                                        <td ${questionDetail[property].trim() == (answer.answer) ? "style='background-color:#00f77b'":""}>
                                            <textarea cols="97" 
                                                      rows="2" 
                                                      name="option${i}"
                                                      style="border:none;font-size: inherit;resize:none;overflow:hidden;background: transparent;outline:none"/>${questionDetail[property]}
                                            </textarea>
                                        </td>

                                        <td>
                                            <a href="${path}/auth/teacher/question?operation=EDITANSWER&&id=${i}&&column=option${i}">
                                                <button type="button" class="btn btn-warning mb-2">Edit Answer</button>
                                            </a>
                                            <a href="${path}/auth/teacher/question?operation=DELETEANSWER&&column=option${i}&&media=${image}">
                                                <button type="button" class="btn btn-danger">Delete Answer</button>  
                                            </a>

                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="form-group">
                    <label for="answer">
                        Answer
                    </label>
                    <select name="answer" class="form-control">
                        <c:forEach begin="1" end="${totalAnswerOptions}" var="i">
                            <c:set var="property" value="option${i}" />
                            <c:if test="${questionDetail[property] == null}"></c:if>
                            <c:if test="${questionDetail[property] != null && questionDetail[property] != ''}">
                                <option style="font-size: inherit" ${questionDetail[property].trim() == answer.answer ? "selected":""} value="${questionDetail[property].trim()}">
                                    ${questionDetail[property]}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>         

                <div class="form-group mt-2">
                    <label for="explaination">
                        Explaination
                    </label>
                    <textarea name="explaination"
                              rows="5"
                              type="text"
                              style="font-size: 19px"
                              placeholder="${questionDetail.explaination}"
                              class="form-control"
                              id="explaination"
                              data-value-missing="Can't be empty"
                              required>${questionDetail.explaination}</textarea>
                </div>

                <div class="form-group">
                    <label for="media">
                        Media
                    </label>
                    <input name="media"
                           type="text"
                           class="form-control mb-2"
                           value="${image}"
                           id="media"
                           data-value-missing="Can't be empty"
                           required>
                    <img src="${path}/assets/questionImg/${image}" style="width: 50%"/>
                </div>


                <button type="submit" class="btn btn-warning">Update Question</button> 



            </form>

            <!--UPLOAD MEDIA-->
            <div style="width: 85%;margin: 0 auto">
                <form class="mt-3" action="${path}/auth/teacher/question?operation=UPLOADMEDIA" method="POST" enctype="multipart/form-data">
                    <div class="d-flex align-items-center">
                        <label style="background: rgb(200,94,103);margin-bottom: 0;
                               text-align:center;padding:5px;width: 146px;height:34px;
                               border-radius:5px;color:white;font-size:17px" for="file-upload" class="custom-file-upload">
                            Upload Media
                            <input style="color:transparent;opacity: 0"
                                   type="file" name="photo" value="" id="file-upload" /></label>
                        <div style="margin-left: 10px">
                            <button style="width: 146px;height: 34px;padding: 5px" class="btn btn-success" type="submit" value="Save">
                                Update Media
                            </button>            
                        </div>
                    </div>
                </form>
<!--                <div><a href="${path}/auth/teacher/question" class="btn btn-primary" style="margin-top: 1rem">Back</a></div>-->
            </div>


            <!--ADD ANSWER-->
            <div class="modal fade" id="AddAnswer">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="font-weight-bold">Add answer</h5>
                            <button data-dismiss="modal" class="close">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form action="${path}/auth/teacher/question?operation=ADDANSWER"
                                  method="post">
                                <div class="form-group">
                                    <label class="d-block text-left" for="id">
                                        Id
                                    </label>
                                    <input name="id"
                                           type="text"
                                           class="form-control"
                                           id="id"
                                           data-value-missing="Can't be empty"
                                           required>
                                </div>
                                <div class="form-group">
                                    <label class="d-block text-left"
                                           for="answerContent">
                                        Answer content
                                    </label>
                                    <textarea rows="5"
                                              name="answerContent"
                                              type="text"
                                              class="form-control"
                                              id="answerContent"
                                              data-value-missing="Can't be empty"
                                              required></textarea>
                                </div>
                                <div class="modal-footer myModalFooter">
                                    <button class="btn btn-success" type="submit">
                                        Add
                                    </button> 
                                    <button data-dismiss="modal" class="btn btn-danger">
                                        Close
                                    </button>
                                </div>
                            </form>


                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@4.6.0/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
            crossorigin="anonymous">
    </script>
    <script src="${path}/utilities/form-validator.js"></script>
</html>