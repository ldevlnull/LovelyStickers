<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product</title>
</head>
<body>
	<c:forEach var="product" items="${products}">
		${product.getName()} ${product.getPrice()}<form><button formaction="delete/${product.getID()}">Delete</button></form>
	</c:forEach>

	<sf:form modelAttribute="productMODEL" action="newProduct"
		method="post">
		<table>
		<tr>
		<td>
		<label for="name">Name:</label>
		</td><td>
		<sf:input id="name" path="name" />
		</td>
		</tr><tr>
		<td>
		<label for="price">Price:</label>
		</td><td>
		<sf:input id="price" path="price" />
		</td>
		</tr><tr>
		<td colspan="2">
		<button>Save product</button>
		</td>
		</tr>
		</table>
	</sf:form>
</body>
</html>