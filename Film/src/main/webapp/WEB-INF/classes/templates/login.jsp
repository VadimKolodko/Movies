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
                    <li class="active"><a href="">Login</a></li>
                </ul>
            </nav>
        </header>
    </div>
    <div class="wrapper">
        <article id="pageintro" class="hoc clear">
            <div class="transbox">
                <h3 class="heading">Login to the site</h3>
                <footer id="footer" class="hoc clear">
                    <div class="one_third first">
                        <c:out value="${accReg}" />
                        <c:if test="${accReg == 'accRegg'}">
                            <h1>Account registered, you can log in</h1>
                        </c:if>
                        <form method="POST" action="/login">
                            <input class="btmspace-15" type="text" value="" placeholder="Name" name="userName">
                            <button type="submit" value="submit">Login</button>
                        </form>
                    </div>
                </footer>
            </div>
        </article>
</body>
</html>
