<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product</title>
</head>
<body>
	<hr>
	<c:forEach var="product" items="${products}">
		${product.name} ${product.price}
		<sec:authorize access="isAuthenticated()">
			<form>
				<button formaction="buy/${product.ID}">Add product to cart</button>
				<button formaction="delete/${product.ID}">Delete</button>
			</form>
		</sec:authorize>
	</c:forEach>
	<hr>
		<sf:form modelAttribute="productMODEL" action="newProduct"
			method="post">
			<table>
				<tr>
					<td><label for="name">Name:</label></td>
					<td><sf:input id="name" path="name" /></td>
				</tr>
				<tr>
					<td><label for="price">Price:</label></td>
					<td><sf:input id="price" path="price" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<button>Save product</button>
					</td>
				</tr>
			</table>
		</sf:form>
		<hr>
	<form>
		<button formaction="back/">Back</button>
	</form>
</body>
</html>