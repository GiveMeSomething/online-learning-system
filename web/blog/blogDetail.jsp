<%-- 
    Document   : blogDetail
    Created on : May 30, 2021, 1:57:05 AM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${requestScope.hmPost.value.title}</h1>
        <h5>Published on: ${requestScope.hmPost.value.updatedDate}</h5>
        <hr>
        <div class="container">
            <div class="row">
                <div class="col-4">
                    <form method="POST" action="../">
                        <input type="text" placeholder="Post searching">
                        <input type="submit">
                    </form>
                    <div class="category">
                        <h2>Category</h2>
                        <hr>
                        <c:forEach var="o" items="${hmCat}">
                            <h6>${o.value}</h6>
                        </c:forEach>
                    </div>
                    <div class="lastest-post">
                        <h2>Top 3 latest posts</h2>
                        <hr>
                        <c:forEach var="o" items="${latest}">
                            <h6>${o.value.title}</h6>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-8">
                    <article>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dictum nisi metus, ut aliquam dolor cursus in. Integer tincidunt nunc purus, at pellentesque diam malesuada eu. Mauris vehicula dignissim convallis. Quisque ut risus suscipit, posuere augue in, lacinia mi. In ac erat vitae felis tempor viverra sed fringilla dolor. Maecenas at posuere diam. Aenean tempus eget leo a tristique. Pellentesque est mauris, vulputate eu faucibus lobortis, condimentum eget libero. Phasellus at vulputate mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque fringilla et erat in porttitor. Integer mauris mauris, pulvinar blandit lacus varius, sagittis faucibus ligula. Morbi feugiat, lacus quis gravida egestas, mauris odio suscipit nisi, vel venenatis orci nibh sit amet mauris. Fusce pulvinar libero quis nibh elementum, quis eleifend tellus sodales.

                        Ut aliquam facilisis ante, nec pulvinar libero mattis et. Sed ac elit viverra, ultrices ligula vel, scelerisque metus. Donec a magna ac sapien mollis aliquet. Maecenas ullamcorper tempus fermentum. Donec vel luctus mauris. Proin imperdiet, nisl facilisis porta ultrices, eros nunc iaculis tellus, id fringilla tellus diam vel odio. Maecenas id rhoncus ex. Ut at porttitor lectus. Suspendisse potenti. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.

                        Quisque elementum neque odio, non dictum elit pharetra commodo. Sed elementum et nibh non suscipit. Aliquam eu metus a risus consequat pulvinar vitae sed orci. Nullam quis tristique risus, quis pretium eros. Quisque vel nisl at erat volutpat interdum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Suspendisse sed justo ut nibh fermentum congue. Donec at dolor nibh. Donec a mattis libero. Sed tortor magna, rutrum eu nunc id, rutrum euismod massa. Nulla in sapien in turpis rhoncus tincidunt tristique at leo. In hac habitasse platea dictumst. Phasellus non convallis purus, et bibendum augue. Proin efficitur leo ac est luctus tempor.

                        Praesent varius nunc vel semper imperdiet. Curabitur tempus dictum tincidunt. Sed sit amet magna sit amet elit aliquam tempor at non leo. Maecenas venenatis ac magna iaculis molestie. Donec ullamcorper lacus eget ipsum rhoncus, sit amet tristique lorem pellentesque. Maecenas fringilla quam metus, id interdum leo auctor eu. Maecenas ultricies quam ac blandit tempus. Praesent quam sem, lacinia a fringilla vehicula, gravida eget urna. Nullam finibus risus at nunc gravida tincidunt. Etiam vel dolor ut felis ornare facilisis. Etiam metus quam, sollicitudin et magna eget, sollicitudin placerat diam. Vestibulum at facilisis urna. Quisque at leo sed nisl feugiat varius non ut dui. Nunc placerat leo condimentum turpis scelerisque, eu auctor massa venenatis. Curabitur lobortis, nisi in posuere consequat, ligula ipsum mollis massa, vitae posuere ipsum urna vitae lacus. Vestibulum in porta metus.

                        Vivamus lectus tellus, rhoncus ut volutpat tempus, mollis quis ex. Nullam ultricies non erat vel scelerisque. Donec venenatis nisl sit amet tellus sodales pretium. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus faucibus purus lobortis elementum fringilla. Curabitur metus tortor, rutrum vitae orci sed, sodales aliquet elit. Nunc lacinia neque ante. Ut sed luctus sem, vel sollicitudin augue. Aliquam ex orci, lobortis in ultricies eu, hendrerit eu nibh. In blandit vestibulum leo, sed porta tortor ultricies at. Aliquam viverra, purus convallis faucibus mattis, ligula erat tempus arcu, sed finibus tellus lacus sit amet mi. Aliquam lobortis, erat at molestie feugiat, nisl odio consectetur ex, at molestie magna purus quis velit. Nulla vulputate nisl eget massa porttitor egestas.
                    </article>
                </div>
            </div> 
        </div>
        <h5>Written by: Author</h5>
        <hr>
    </body>
</html>
