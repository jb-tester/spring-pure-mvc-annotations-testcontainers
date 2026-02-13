<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h1>Users</h1>
<ul>
    <c:forEach var="u" items="${usersByAge}">
        <li>${u.first_name} ${u.last_name} (${u.age})</li>
    </c:forEach>
</ul>

<h2>Set the age range: users with age more than selected will be displayed</h2>
<form action="./byAge" method="post">
    <label for="age">Age range:</label>
    <input type="text" name="age" id="age">

    <button type="submit">add age range</button>
</form>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/updateUserAge">update users with invalid age</a>
</body>
</html>
