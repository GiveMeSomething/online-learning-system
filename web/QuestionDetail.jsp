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
            <form action="question" method="post" style="width: 85%;margin:0 auto;font-size: 19px">
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
                           id="questionId">
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
                            <option ${o.lessonName == questionDetail.lesson_name ? "selected":""} 
                                value="${o.id}">
                                ${o.lessonName}
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
                           id="content">
                </div>
                <div class="form-group">
                    <label for="media">
                        Media
                    </label>
                    <input name="media"
                           type="text"
                           class="form-control"
                           value="${questionDetail.media}"
                           id="media">

                </div>

                <div class="form-group">
                    Answer Options<br/>
                    <div class="text-right mb-2">
                        <button class="btn btn-success">Add Answer</button>  
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
                            <c:if test="${questionDetail.option1 == null}"></c:if>
                            <c:if test="${questionDetail.option1 != null && questionDetail.option1 != ''}">
                                <tr>
                                    <th scope="row">1</th>
                                    <td style="background-color: #00f77b">
                                        <textarea cols="97" 
                                                  rows="2" 
                                                  name="option1"
                                                  style="border:none;resize:none;overflow:hidden;background: transparent;outline:none"/>${questionDetail.option1}
                                        </textarea>
                                    </td>

                                    <td>
                                        <a href="${path}/question?operation=EDITANSWER&&id=1&&column=option1">
                                            <button type="button" class="btn btn-warning mb-2">Edit Answer</button>
                                        </a>
                                        <a href="question?operation=DELETEANSWER&&column=option1">
                                            <button type="button" class="btn btn-danger">Delete Answer</button>  
                                        </a>

                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${questionDetail.option2 == null}"></c:if>
                            <c:if test="${questionDetail.option2 != null && questionDetail.option2 != ''}">
                                <tr>
                                    <th scope="row">2</th>
                                    <td>
                                        <textarea cols="97" 
                                                  rows="2" 
                                                  name="option2"
                                                  style="border:none;resize:none;overflow:hidden;background: transparent;outline:none"/>${questionDetail.option2}
                                        </textarea>

                                    </td>
                                    <td>
                                        <a href="${path}/question?operation=EDITANSWER&&id=2&&column=option2">
                                            <button type="button" class="btn btn-warning mb-2">Edit Answer</button>
                                        </a>
                                        <a href="question?operation=DELETEANSWER&&column=option2">
                                            <button type="button" class="btn btn-danger">Delete Answer</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${questionDetail.option3 == null}"></c:if>
                            <c:if test="${questionDetail.option3 != null && questionDetail.option3 != ''}">
                                <tr>
                                    <th scope="row">3</th>
                                    <td>
                                        <textarea cols="97" 
                                                  rows="2" 
                                                  name="option3"
                                                  style="border:none;resize:none;background: transparent;overflow:hidden;outline:none"/>${questionDetail.option3}
                                        </textarea>
                                    </td>
                                    <td>
                                        <a href="${path}/question?operation=EDITANSWER&&id=3&&column=option3">
                                            <button type="button" class="btn btn-warning mb-2">Edit Answer</button>
                                        </a>
                                        <a href="question?operation=DELETEANSWER&&column=option3">
                                            <button type="button" class="btn btn-danger">Delete Answer</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${questionDetail.option4 == null}"></c:if>
                            <c:if test="${questionDetail.option4 != null && questionDetail.option4 != ''}">
                                <tr>
                                    <th scope="row">4</th>
                                    <td>
                                        <textarea cols="97" 
                                                  rows="2" 
                                                  name="option4"
                                                  style="border:none;resize:none;overflow:hidden;background: transparent;outline:none"/>${questionDetail.option4}
                                        </textarea>
                                    </td>
                                    <td>
                                        <a href="${path}/question?operation=EDITANSWER&&id=4&&column=option4">
                                            <button 
                                                   type="button" class="btn btn-warning mb-2">Edit Answer</button>
                                        </a>
                                        <a href="${path}/question?operation=DELETEANSWER&&column=option4">
                                            <button type="button" class="btn btn-danger">Delete Answer</button>
                                        </a>
                                    </td>
                                </tr> 
                            </c:if>
                        </tbody>
                    </table>
                </div>

                <div class="form-group mt-2">
                    <label for="explaination">
                        Explaination
                    </label>
                    <textarea name="explaination"
                              rows="5"
                              type="text"
                              class="form-control"
                              id="explaination">${questionDetail.explaination}</textarea>
                </div>



                <button type="submit" class="btn btn-warning">Update</button> 
            


            </form>
            
        </div>



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
    </body>
</html>