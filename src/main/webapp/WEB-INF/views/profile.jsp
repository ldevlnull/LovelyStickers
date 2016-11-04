<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Особистий кабінет</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<table>
    <tr>
        <td rowspan="2">
            <img width="128px" height="128px" src="${user.pathImage}" alt="Картинка не може бути відображенною :("/>
        </td>
        <td>
            <h3 id="username">${user.getName()}</h3>
        </td>
    </tr>
    <tr>
        <td>
            <form:form action="./saveImage?${_csrf.parameterName}=${_csrf.token}"
                       method="post" enctype="multipart/form-data">
                <input type="file" name="image">
                <button>Завантажити</button>
            </form:form>
        </td>
    </tr>
</table>
<hr>
<c:forEach var="product" items="${user.products}">
    <table>
        <tr>
            <td rowspan="2">
                <img height="64px" width="64px" src="${product.productIconPath}"/>
            </td>
            <td>
                <p>${product.name}</p>
            </td>
            <td rowspan="2">
                <form>
                    <button formaction="deleteFromCart/${product.ID}">Видалити з кошика</button>
                </form>
            </td>
        </tr>
        <tr>
            <td align="right">
                <p>${product.price}$</p>
                <br>
            </td>
        </tr>

    </table>
</c:forEach>
<form method="post">
    <button onclick="alert('Лист з інформацією про замовлення був відправлений на вашу електронну адресу.')"
            formaction="createPurchaseOrder?${_csrf.parameterName}=${_csrf.token}">Замовити
    </button>
</form>
<hr>
<form>
    <button formaction="back/">Назад</button>
</form>
</body>
</html>