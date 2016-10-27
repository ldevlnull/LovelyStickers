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
		<form>
			<button formaction="product">Products</button>
		</form>
	<sec:authorize access="isAuthenticated()">
	<c:forEach var="product" items="${products}">
		${product.name} ${product.price}
	</c:forEach>
		<form>
			<button formaction="profile">Profile</button>
		</form>
		<sf:form action="logout" method="post">
			<button>Log out</button>
		</sf:form>
	</sec:authorize>
</body>
</html>