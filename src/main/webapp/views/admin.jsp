<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: devnull
  Date: 16.11.16
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <body>
    <div id="context">
        <div id="admin-products">
            <form action="/adminProducts" method="get">
                <button>Продукти</button>
            </form>
        </div>
        <div id="add-category">
            <form modelAttribute="categoryEmpty" action="./addCategory?${_csrf.parameterName}=${_csrf.token}"
                  method="post">
                <input type="text" required placeholder="категорія">
                <button>Додати</button>
            </form>
        </div>
        <div id="admin-users">
            <form action="/adminUsers" method="get">
                <button>Користувачі</button>
            </form>
        </div>
    </div>
    </body>
</security:authorize>
</html>
