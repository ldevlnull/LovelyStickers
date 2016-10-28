<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">
<link href="resources/css/styleRegister.css" rel="stylesheet" type="text/css">
<title>Login page</title>
</head>
<body>
	<div class="testbox">
		<h1>Login</h1>
		<sf:form action="loginprocesing" method="post">
			<hr>
			<label id="icon" for="username"><i class="icon-user"></i></label>
			<input type="text" name="username" id="username"
				placeholder="Username" required />
			<label id="icon" for="name"><i class="icon-shield"></i></label>
			<input type="password" name="password" id="password"
				placeholder="Password" required />
			<table>
			<tr><td><button class="button">Log in</button></td></tr>
			<tr height="50px"><td><a href="register">Are you here for first time?</a></td></tr>
			</table>
		</sf:form>
	</div>
</body>
</html>