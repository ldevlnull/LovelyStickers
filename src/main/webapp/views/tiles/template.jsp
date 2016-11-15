<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: devnull
  Date: 12.11.16
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="/css/styleFooter_Header.css">
        <script src="/js/functionalFooter_Header.js"></script>
    </head>
<body>
    <div><tiles:insertAttribute name="header"/></div>
    <div><tiles:insertAttribute name="body"/></div>
    <div><tiles:insertAttribute name="footer"/></div>
</body>
</html>