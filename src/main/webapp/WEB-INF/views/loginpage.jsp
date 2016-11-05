<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">
<link href="css/styleRegister.css" rel="stylesheet" type="text/css">
<title>Сторінка входу</title>
</head>
<body>
	<div class="testbox">
		<h1>Вхід</h1>
		<form:form action="loginprocesing" method="post">
			<hr>
			<label id="icon" for="username"><i class="icon-user"></i></label>
			<input type="text" name="username" id="username"
				placeholder="Логін" required />
			<label id="icon" for="password"><i class="icon-shield"></i></label>
			<input type="password" name="password" id="password"
				placeholder="Пароль" required />
			<table>
			<tr><td><button class="button">Увійти</button></td></tr>
			<tr height="50px"><td><a href="register">Ви тут вперше?</a></td></tr>
			</table>
		</form:form>
	</div>
</body>
</html>