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
 <form action="/save" method="get">
   <input type="checkbox" value="Lettuce"> Lettuce
   <input type="checkbox" value="Tomato"> Tomato
   <input type="checkbox" value="Mustard"> Mustard
   <input type="checkbox" value="Sprouts"> Sprouts

   <hr>
   <input type="submit" value="save">
 </form>
  </body>
</html>
