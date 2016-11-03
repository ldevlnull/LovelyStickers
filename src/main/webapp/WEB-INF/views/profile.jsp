<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
	<img width="128px" height="128px" src="${user.pathImage}" alt="The picture can not be showed :("/>
	<br>
	<h3 id="username">${user.getName()}</h3>
	<hr>
	<c:forEach var="product" items="${user.products}">
			${product.name} ${product.price} <form><button formaction="deleteFromCart/${product.ID}">Delete product from cart</button></form> <br>
	</c:forEach>
	<br>
	<form method="post"><button formaction="/createPurchaseOrder">Make order</button></form>
	<br>
	<hr>
	<form:form action="./saveImage?${_csrf.parameterName}=${_csrf.token}"
		method="post" enctype="multipart/form-data">
		<input type="file" name="image">
		<button>Upload</button>
	</form:form>
	<hr>
	<form>
		<button formaction="back/">Back</button>
	</form>
</body>
</html>