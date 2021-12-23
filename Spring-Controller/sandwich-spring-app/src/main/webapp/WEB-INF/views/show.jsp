<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 23/12/2021
  Time: 10:37 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Success</h3>

<ul>
    <c:forEach var="c" items="${condiment}">
        <li>
            <c:out value="${c.toString()}"/>
        </li>
    </c:forEach>
</ul>
</body>
</html>
