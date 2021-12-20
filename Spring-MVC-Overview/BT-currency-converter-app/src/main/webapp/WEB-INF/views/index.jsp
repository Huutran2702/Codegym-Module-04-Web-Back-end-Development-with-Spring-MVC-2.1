<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20/12/2021
  Time: 1:34 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME</title>
    <style>
        form{
            line-height: 40px;
        }
        #submit {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<form action="/convert" method="post">
    <label> Rate </label> <br>
    <input type="text" name="rate" placeholder="Rate"/> <br>
    <label>USD</label> <br>
    <input type="text" name="usd" placeholder="USD"/> <br>
    <input type="submit" id="submit" value="Converter"/>
</form>
</body>
</html>
