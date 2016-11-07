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
    <script src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/styleProduct.css">

</head>
<body <sec:authorize access="!isAuthenticated() || hasRole('ROLE_UNACTIVATED_USER')"> onload="alert('Ви маєте зареєструватись, щоб замовити товар.')" </sec:authorize >>
<hr>
    <!-- /////////// SHOWING ALL PRODUCTS, WHICH ARE AVAIBLED IN STORE \\\\\\\\\\\\  -->
<%--<c:forEach var="product" items="${products}">
<table>
<tr>
            <td rowspan="2">
                <img src="${product.productIconPath}"/>
            </td>
                <form action="buy/${product.ID}?${_csrf.parameterName}=${_csrf.token}" method="POST">
            <td>

                <p>${product.name}</p>
                <sec:authorize access="isAuthenticated()">
            </td>
            <td>
                    <sec:authorize access="!hasRole('ROLE_ADMIN')">
                        <button>Додати товар до кошика</button>
                    </sec:authorize>
                </sec:authorize>
            </td>
                </form>
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
</table>--%>
    <!-- ////////// END SHOWING PRODUCTS \\\\\\\\\\\\\-->
<hr>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <hr>
    <!-- //////////// ADD NEW PRODUCT IN DATABASE \\\\\\\\\ -->
<%--    <form:form modelAttribute="productMODEL" action="./newProduct?${_csrf.parameterName}=${_csrf.token}"
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
    </form:form>--%>
    <!-- ///////////////// END ADD NEW PRODUCT \\\\\\\\\\\\\\ -->
    <div id="productJS">

    </div>
    <div id="formJS" >
        <input placeholder="Name" id="product_name" type="text" required />
        <input placeholder="Price" id="product_price" type="text" required />
        <button id="product_new" onclick='
            var product = {name: $("#product_name").val(), price: $("product_price").val()};
        $.ajax({
        url: "./newProduct?<%--${_csrf.parameterName}=${_csrf.token}",--%>
        type: "POST",
        data: JSON.stringify(product)
        })' > Add</button>
    </div>
    <%--<script>
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
    </script>--%>
</sec:authorize>
    <hr>
<form>
    <button formaction="back/">Назад</button>
</form>
</body>
</html>