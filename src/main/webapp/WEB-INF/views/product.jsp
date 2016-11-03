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
<script src="resources/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
	<%-- <hr>
	<c:forEach var="product" items="${products}">
		${product.name} ${product.price}
		<sec:authorize access="isAuthenticated()">
			<form>
				<sec:authorize access="!hasRole('ROLE_ADMIN')">
					<button formaction="buy/${product.ID}">Add product to cart</button>
				</sec:authorize>
				<button formaction="delete/${product.ID}">Delete</button>
			</form>
		</sec:authorize>
		<br>
	</c:forEach>
	<hr> --%>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<hr>
		<%-- <sf:form modelAttribute="productMODEL" action="newProduct"
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
		</sf:form> --%>
		<div id="products">
			
		</div>
		<table>
			<tr>
				<td><label for="name">Name:</label></td>
				<td><input id="name" name="name" /></td>
			</tr>
			<tr>
				<td><label for="price">Price:</label></td>
				<td><input id="price" name="price" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<button id="saveButton">Save product</button>
				</td>
			</tr>
		</table>
		<script>
			$("#saveButton").click(function(){
				var product = {
						name : $("#name").val,
						price : $("#price").val
				}
				$.ajax({
					url : "/newProduct?"+$("input[name=csrf_name]").val()+"="+$("input[name=csrf_value]").val,
					contentType : "application/json",
					type : "POST",
					data : JSON.stringify(product),
					success : function(result) {
						var html = "";
						$.each(result, function(i, country) {
							html+=product+"<hr>"
						})
					}
				})
				
				$("#products").append(html)
			});
		</script>
		<hr>
	</sec:authorize>
	<form>
		<button formaction="back/">Back</button>
	</form>
	<input type="hidden" name="csrf_name" value="${_csrf.parameterName}" />
	<input type="hidden" name="csrf_value" value="${_csrf.token}" />
</body>
</html>