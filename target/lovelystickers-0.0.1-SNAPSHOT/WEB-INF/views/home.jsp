<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

	<sec:authentication property="name" />

	<sec:authorize access="!isAuthenticated()">
		<form>
			<table>
				<tr>
					<td>
						<button formaction="register">Sign up</button>
					</td>
					<td>
						<button formaction="loginpage">Log in</button>
					</td>
				</tr>
			</table>
		</form>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<form>
			<button formaction="product">Add product</button>
		</form>
		<sf:form action="logout" method="post">
			<button>Log out</button>
		</sf:form>
	</sec:authorize>
</body>
</html>