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
<title>Головна</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script>
	function resendLink(){
		alert("Лист з інформацією про активацію був надісланний.");
	}
</script>
</head>
<body>
	<div class="login-page">
		<div class="form">
			<sec:authorize access="hasRole('ROLE_UNACTIVATED_USER')">
				<p>Ви маєте активувати вашу сторінку за допомогою листа, що був надісланний на вашу електронну адресу</p>
				<p id="ResendLinkText">
					<a id="resendActivateLink" href="resendActivateLink/" onclick="resendLink()">Нема листа?</a>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<form>
					<button formaction="register">Зареєструватися
					</button>
					<br>
					<button formaction="loginpage">Увійти</button>
					<hr>
				</form>
			</sec:authorize>
				<sec:authorize access="!hasRole('ROLE_UNACTIVATED_USER')">
					<form>
						<button formaction="product">Магазин</button>
					</form>
				</sec:authorize>
			<sec:authorize access="hasRole('ROLE_USER')">
				<form>
					<button formaction="profile">Особистий кабінет</button>
				</form>
			</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<sf:form action="logout" method="post">
						<button>Вийти</button>
					</sf:form>
				</sec:authorize>
		</div>
	</div>
</body>
</html>