<%-- 
    Document   : ImportQuestion
    Created on : Jun 17, 2021, 3:47:00 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title>Import Question Trong Đúng Trang</title>
        <style>
            .city {display:none}
            .button {
                background-color: #008CBA;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
    <center></center>
    <div class="w3-container">

        <button onclick="document.getElementById('id01').style.display = 'block'" class="w3-button w3-green">Import Question</button>

        <div id="id01" class="w3-modal">
            <div class="w3-modal-content w3-card-4 w3-animate-zoom">



                <header class="w3-container w3-blue"> 
                    <span onclick="document.getElementById('id01').style.display = 'none'" 
                          class="w3-button w3-blue w3-xlarge w3-display-topright">&times;</span>
                    <h2>Bulk Upload Question</h2>
                </header>

                <div class="w3-bar w3-border-bottom">
                    <button class="tablink w3-bar-item w3-button" onclick="openCity(event, 'London')"></button>
                </div>

                <div id="London" class="w3-container city">
                    <h4> <strong>1. Import Question Of The Quiz </strong></h4>
                    <p>You will need a Microsoft Excel (.xlxs) with the quesiton and answers</p>
                    <p><a style="color: blue;" href="https://drive.google.com/u/0/uc?id=1J-qoXy1yGmn5kKjPSmXSCs2CXKOhNx1u&export=download">
                            Download Sample Upload File .xlxs
                        </a></p>
                    <h4><strong>2. Upload questions in spreadsheet format</strong></h4>
                    <form method="POST" action="/online-learning-system/auth/teacher/question?operation=Import" enctype="multipart/form-data">
                        <table border="0">
                            <tr>
                                <td>File: </td>
                                <td><input type="file" name="fileExcel"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td colspan="2">
                                    <input type="submit" class="button" value="Import Question">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="w3-container w3-light-grey w3-padding">
                    <button class="w3-button w3-right w3-white w3-border" 
                            onclick="document.getElementById('id01').style.display = 'none'">Close</button>
                </div>
            </div>
        </div>

    </div>

    <script>
        document.getElementsByClassName("tablink")[0].click();

        function openCity(evt, cityName) {
            var i, x, tablinks;
            x = document.getElementsByClassName("city");
            for (i = 0; i < x.length; i++) {
                x[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablink");
            for (i = 0; i < x.length; i++) {
                tablinks[i].classList.remove("w3-light-grey");
            }
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.classList.add("w3-light-grey");
        }
    </script>



</body>
</html>
