<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <label for="name">Name:
            <input type="text" name="name" id="name">
        </label><br>
        <label for="birthday">Birthday:
            <input type="date" name="birthday" id="birthday">
        </label><br>
        <label for="emailId">Email:
            <input type="text" name="email" id="emailId">
        </label><br>
        <label for="passwordId">Password:
            <input type="password" name="password" id="passwordId">
        </label><br>
        <label for="role">Role:
            <select name="role" id="role">
                <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role}">${role}</option>
                </c:forEach>
            </select>
        </label><br>
        <c:forEach var="gender" items="${requestScope.genders}">
            <input type="radio" name="gender" value="${gender}"> ${gender}
        </c:forEach>
        <br>
        <button type="submit">Send</button>
    </form>
</body>
</html>
