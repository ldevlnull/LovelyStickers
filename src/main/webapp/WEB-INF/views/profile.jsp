<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>

	${user.getName()}
	<hr>
	<c:forEach var="product" items="${user.products}">
			${product.name} ${product.price} <br>
	</c:forEach>

	<br><hr>

	<sf:form action="saveImage" method="post" enctype="multipart/form-data">
		<input type="file" name="image">
		<button>Upload</button>
	</sf:form>



</body>
</html>