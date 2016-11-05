<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">
<link href="css/styleRegister.css" rel="stylesheet" type="text/css">
<title>Сторінка реєстрації</title>
</head>
<body>
	<div class="testbox">
		<h1>Реєстрація</h1>
		<form:form modelAttribute="user" action="register" method="post">
			<hr>
			<label id="icon" for="email"><i class="icon-envelope "></i></label> 
			<form:input type="text" path="email" id="email" placeholder="Електронна адреса" required="required" />
			<label id="icon" for="username"><i class="icon-user"></i></label>
			<input name="username" type="text" id="username" placeholder="Логін" required="required" />
			<label id="icon" for="password"><i class="icon-shield"></i></label>
			<form:input type="password" path="password" id="password" placeholder="Пароль" required="required" />
			<table >
			<tr><td ><button onclick="">Register</button></td></tr>
			<tr><td height="50px"><a id="regToLog" href="loginpage">Я вже зареєстрований!</a></td></tr>
			</table>
		</form:form>
	</div>
</body>
</html>