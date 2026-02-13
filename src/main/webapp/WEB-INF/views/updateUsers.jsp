<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Users Age</title>
</head>
<body>
    <h1>Update Incorrect User Ages</h1>



    <c:if test="${not empty invalidUsers}">
        <h2>Updated Users (Age set to ${age}):</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${invalidUsers}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.first_name} ${user.last_name}</td>
                        <td>${user.age}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
        <c:if test="${empty invalidUsers}">
            <h3>no users with invalid age are found</h3>
        </c:if>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/updateUsers">
        <label for="age">Enter new age value:</label>
        <input type="number" id="age" name="age" required />
        <input type="submit" value="Update Users" />
    </form>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/users">Back to Home</a>
</body>
</html>
