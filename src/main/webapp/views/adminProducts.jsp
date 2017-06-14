<%--
  Created by IntelliJ IDEA.
  User: devnull
  Date: 16.11.16
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
</head>
<body>
<core:forEach var="product" items="${products}">
    <table>
        <tr>
            <td rowspan="2">
                <img src="${product.iconPath}"/>
            </td>
            <td>
                <p>${product.name}</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>${product.price}$</p>
                <br>
            </td>
            <td>
                <form>
                    <button formaction="delete/${product.ID}">Видалити</button>
                </form>
            </td>
        </tr>
        <tr>
            <p>${product.category.name}</p>
        </tr>
    </table>
</core:forEach>
<!-- //////////// ADD NEW PRODUCT IN DATABASE \\\\\\\\\ -->
<form:form acceptCharset="utf-8" modelAttribute="productMODEL"
           action="./newProduct?${_csrf.parameterName}=${_csrf.token}"
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
            <td><label for="category">Категорія:</label></td>
            <td><select name="categorys" id="category">
                <option disabled>Категорія:</option>
                <core:forEach var="category" items="${categoryies}">
                    <option value="${category.id}">${category.name}</option>
                </core:forEach>
            </select></td>
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
<!-- ///////////////// END ADD NEW PRODUCT \\\\\\\\\\\\\\ -->
<core:forEach var="category" items="${categories}">
    Категорії:
    <ul>
        <li>${category.name}</li>
    </ul>
</core:forEach>
</body>
</html>
