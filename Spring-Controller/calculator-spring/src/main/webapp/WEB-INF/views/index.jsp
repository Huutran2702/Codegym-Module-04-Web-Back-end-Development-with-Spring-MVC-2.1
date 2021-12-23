<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23/12/2021
  Time: 10:27 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sandwich</title>
</head>
<body>
<h2>Calculator</h2>
<form action="/save" method="post">
    <input type="text" name="operator1">
    <input type="text" name="operator2">
    <br>
    <br>
    <input type="submit" name="submit" value="Addition(+)">
    <input type="submit" name="submit" value="Subtraction(-)">
    <input type="submit" name="submit" value="Multiplication(x)">
    <input type="submit" name="submit" value="Division(/)">
</form>
<br>
<h3>Result ${operator} : ${result} </h3>
</body>
</html>
