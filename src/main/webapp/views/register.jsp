<%--
  Created by IntelliJ IDEA.
  User: devnull
  Date: 16.11.16
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" type="text/css" href="/css/styleRegister.css">
</head>
<body>
<form>
    <ul class="items"></ul>
    <fieldset class="username enable">
        <div class="icon left"><i class="user"></i></div>
        <input min="4" max="32" type="text" id="username" name="username" placeholder="Логін"/>
        <div class="icon right button"><i class="arrow"></i></div>
    </fieldset>
    <fieldset class="email">
        <div class="icon left"><i class="letter"></i></div>
        <input min="4" max="32" type="email" id="email"name="email" placeholder="Електронна адреса"/>
        <div class="icon right button"><i class="arrow"></i></div>
    </fieldset>
    <fieldset class="password">
        <div class="icon left"><i class="lock"></i></div>
        <input min="4" max="32" type="password" id="password" name="password" placeholder="Password"/>
        <div class="icon right button"><i class="arrow"></i></div>
    </fieldset>
    <fieldset class="thanks">
        <div class="icon left"><i class="heart"></i></div>
        <p>Лист про реєстрацію був надісланний</p>
        <div class="icon right"><i class="heart"></i></div>
    </fieldset>
</form>
<input readonly type="hidden" name="csrf_name" value="${_csrf.parameterName}"/>
<input readonly type="hidden" name="csrf_value" value="${_csrf.token}"/>
<script src="/js/register.js"></script>
<script src="/js/jquery-3.1.1.min.js"></script>
</body>
</html>
