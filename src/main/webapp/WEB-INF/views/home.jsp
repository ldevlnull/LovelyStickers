<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script>
	function resendLink(){
		alert("Activation link was sended");
	}
</script>
</head>
<body>
	<div class="login-page">
		<div class="form">
			<sec:authorize access="hasRole('ROLE_UNACTIVATED_USER')">
				<p>You must activate your account by following link in your
					email</p>
				<p id="ResendLinkText">
					<a id="resendActivateLink" href="resendActivateLink/" onclick="resendLink()">Don't
						have a link?</a>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<form>
					<!-- 	<table>
						<tr>
							<td> -->
					<button formaction="register">Sign up</button>
					<br>
					<!-- 	</td>
							<td> -->
					<button formaction="loginpage">Log in</button>
					<hr>
					<!-- </td>
						</tr>
					</table> -->
				</form>
			</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')">
					<form>
						<button formaction="product">Products</button>
					</form>
				</sec:authorize>
			<sec:authorize access="hasRole('ROLE_USER')">
				<form>
					<button formaction="profile">Profile</button>
				</form>
			</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<sf:form action="logout" method="post">
						<button>Log out</button>
					</sf:form>
				</sec:authorize>
		</div>
	</div>
</body>
</html>