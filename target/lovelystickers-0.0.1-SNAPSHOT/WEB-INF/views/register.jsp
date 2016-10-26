<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">
<link href="resources/css/styleRegister.css" rel="stylesheet" type="text/css">
<title>Register page</title>
</head>
<body>
	<div class="testbox">
		<h1>Registration</h1>
		<sf:form modelAttribute="user" action="register" method="post">
			<hr>
			<label id="icon" for="email"><i class="icon-envelope "></i></label> 
			<sf:input type="text" path="email" id="email" placeholder="Email" required="required" /> 
			<label id="icon" for="username"><i class="icon-user"></i></label> 
			<sf:input type="text" path="username" id="username" placeholder="Username" required="required" /> 
			<label id="icon" for="name"><i class="icon-shield"></i></label> 
			<sf:input type="password" path="password" id="password" placeholder="Password" required="required" />
			<button>Register</button>
		</sf:form>
	</div>
</body>
</html>