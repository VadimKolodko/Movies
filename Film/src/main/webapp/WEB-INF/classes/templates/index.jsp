<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>

<html>
<style>
    <%@include file='/resources/layout/styles/layout.css' %>
    <%@include file='/resources/layout/styles/framework.css' %>
    <%@include file='/resources/layout/styles/font-awesome.min.css' %>
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Movies</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body id="top">
<div class="bgded">
    <div class="wrapper row1">
        <header id="header" class="hoc clear">
            <div id="logo" class="fl_left">
                <h1><a href="/film/index">Best Movies</a></h1>
            </div>
            <nav id="mainav" class="fl_right">
                <ul class="clear">
                    <li><a href="/film">Back main</a></li>
                    <li class="active"><a href="">Welcome</a></li>
                </ul>
            </nav>
        </header>
    </div>
    <div class="wrapper">
        <article id="pageintro" class="hoc clear">
            <div class="transbox">
                <h3 class="heading">Welcome to the Best Movies website</h3>
                <p>Here you can enjoy new products, protect a girl from horror, shed a stream of peremomodrama, protect New York with super heroes or just drive on steep wheelbarrows.</p>
            </div>
            <footer><a class="btn" href="/film">Movies</a></footer>
        </article>
</body>
</html>