<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<html>
<head>
</head>
<body>
<core:forEach var="user" items="${users}">
    <ul>
        <li>${user.ID}<img class="user_icons" src="${user.pathImage}" /> ${user.email}
            <form method="post" action="/deleteUser/{user.ID}?${_csrf.parameterName}=${_csrf.token}">
            <button>видалити</button>
        </form></li>
    </ul>
</core:forEach>
</body>
</html>
