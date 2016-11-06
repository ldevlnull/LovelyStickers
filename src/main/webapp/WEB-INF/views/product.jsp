<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Магазин</title>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/styleProduct.css">

</head>
<body <sec:authorize access="!isAuthenticated() || hasRole('ROLE_UNACTIVATED_USER')"> onload="alert('Ви маєте зареєструватись, щоб замовити товар.')" </sec:authorize >>
<hr>
<table>
<c:forEach var="product" items="${products}">
        <tr>
            <td rowspan="2">
                <img src="${product.productIconPath}"/>
            </td>
            <td>
                <p>${product.name}</p>
            </td>
            <td>
                <sec:authorize access="isAuthenticated()">
                <form>
                    <sec:authorize access="!hasRole('ROLE_ADMIN')">
                        <button formaction="buy/${product.ID}">Додати товар до кошика</button>
                    </sec:authorize>
                </form>
                </sec:authorize>
            </td>
        </tr>
        <tr>
            <td>
                <p>${product.price}$</p>
                <br>
            </td>
            <td>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <form>
                        <button formaction="delete/${product.ID}">Видалити</button>
                    </form>
                </sec:authorize>
            </td>
        </tr>
</c:forEach>
</table>
<hr>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <hr>
    <form:form modelAttribute="productMODEL" action="./newProduct?${_csrf.parameterName}=${_csrf.token}"
               method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td><label for="name">Назва:</label></td>
                <td><form:input id="name" path="name"/></td>
            </tr>
            <tr>
                <td><label for="price">Вартість:</label></td>
                <td><form:input id="price" path="price"/></td>
            </tr>
            <tr>
                <td><input type="file" name="image"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button>Зберегти</button>
                </td>
            </tr>
        </table>
    </form:form>
    <%--<div id="products">--%>
    <%----%>
    <%--</div>--%>
    <%--<table>--%>
    <%--<tr>--%>
    <%--<td><label for="name">Name:</label></td>--%>
    <%--<td><input id="name" name="name" /></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td><label for="price">Price:</label></td>--%>
    <%--<td><input id="price" name="price" /></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td colspan="2">--%>
    <%--<button id="saveButton">Save product</button>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--<script>--%>
    <%--$("#saveButton").click(function(){--%>
    <%--var product = {--%>
    <%--name : $("#name").val,--%>
    <%--price : $("#price").val--%>
    <%--}--%>
    <%--$.ajax({--%>
    <%--url : "/newProduct?"+$("input[name=csrf_name]").val()+"="+$("input[name=csrf_value]").val,--%>
    <%--contentType : "application/json",--%>
    <%--type : "POST",--%>
    <%--data : JSON.stringify(product),--%>
    <%--success : function(result) {--%>
    <%--var html = "";--%>
    <%--$.each(result, function(i, country) {--%>
    <%--html+=product+"<hr>"--%>
    <%--})--%>
    <%--}--%>
    <%--})--%>
    <%----%>
    <%--$("#products").append(html)--%>
    <%--});--%>
    <%--</script>--%>
</sec:authorize>
    <hr>
<form>
    <button formaction="back/">Назад</button>
</form>
<input type="hidden" name="csrf_name" value="${_csrf.parameterName}"/>
<input type="hidden" name="csrf_value" value="${_csrf.token}"/>
</body>
</html>