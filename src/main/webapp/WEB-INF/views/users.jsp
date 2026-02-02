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
    <c:forEach var="user" items="${users}">
        <li>${user.first_name} ${user.last_name} (${user.age})</li>
    </c:forEach>
</ul>

<h2>Add New User</h2>
<form action="users" method="post">
    <label for="first_name">First Name:</label>
    <input type="text" name="first_name" id="first_name">
    <label for="last_name">Last Name:</label>
    <input type="text" name="last_name" id="last_name">
    <label for="age">Age:</label>
    <input type="text" name="age" id="age">
    <button type="submit">Add User</button>
</form>
</body>
</html>
